package club;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exper.DBHelper;

@WebServlet("/ActivityDetailServlet")
public class ActivityDetailServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public ActivityDetailServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		System.out.println("1");
		String id = String.valueOf(request.getParameter("id"));
		System.out.println(id);
		HttpSession session = request.getSession(true);
		String account = (String)session.getAttribute("account");
		String identity = (String)session.getAttribute("identity");
		String master=null;
		String checkAccount=null;
		int flagjoinact=0;
		int flagApplyact=0;	
		try
		{
			Connection conn = new DBHelper().getConn();	 
			Statement stmt = conn.createStatement();	
			String sql = "select * from club_activity where id ='"+id+"'";			
			ResultSet rs = stmt.executeQuery(sql);		
			List<ActListBean> list = new ArrayList<>();		
			while(rs.next())
			{
				ActListBean act = new ActListBean();					
				act.setId(rs.getInt("id"));				
				act.setCname(rs.getString("cname"));		
				act.setMaster(rs.getString("master"));
				act.setTitle(rs.getString("title"));		
				act.setContent(rs.getString("content"));	
				act.setTime(rs.getString("time"));
				act.setFilename(rs.getString("filename"));
				list.add(act);
			}
			request.setAttribute("list", list); 	
			sql="select account from my_activity where aid='"+id+"'";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				String account1=null;
				account1=rs.getString("account");
				if(account1.equals(account))
					flagjoinact=1;
			}
			request.setAttribute("flagjoinact", flagjoinact);
			System.out.print("flagjoinact="+flagjoinact);
			sql="select account from check_joinact where aid='"+id+"'";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				String account1=null;
				account1=rs.getString("account");
				System.out.println("account="+account);
				System.out.println("account1="+account1);
				if(account1.equals(account))
					flagApplyact=1;
			}
			request.setAttribute("flagApplyact", flagApplyact);
			System.out.print("flagApplyact="+flagApplyact);
			rs.close();
			stmt.close();									
			conn.close();									
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		if(identity.equals("管理"))
			request.getRequestDispatcher("ClubActivityDetails_admin.jsp").forward(request, response);
		else if(identity.equals("学生"))
			request.getRequestDispatcher("ClubActivityDetails_stu.jsp").forward(request, response);
		//doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//doGet(request, response);
	}

}
