package Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exper.DBHelper;

/**
 * Servlet implementation class SelfActServlet
 */
@WebServlet("/SelfActServlet")
public class SelfActServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
	Connection myconn=null;
	Statement stmt=null;
	ResultSet rs=null;
    public SelfActServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id=request.getParameter("id");
		HttpSession session=request.getSession(true);
		String account=(String)session.getAttribute("account");
		System.out.println(account);
		String sql;
		String sno=null;
		int t=0;
		try{
			myconn=new DBHelper().getConn();
			sql="select sno from student where account='"+account+"'";
			stmt=myconn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				sno=String.valueOf(rs.getInt("sno"));
				t=1;
			}
			sql="select account,sname,aid,atitle,aclub,atime,cid from my_activity where account='"+account+"'";
			stmt=myconn.createStatement();
			rs=stmt.executeQuery(sql);
			List<SelfActBean> stuact=new ArrayList<>();
			while(rs.next()){
				SelfActBean  act=new SelfActBean();
				act.setSname(rs.getString("sname"));
				act.setAid(rs.getInt("aid"));
				act.setAtitle(rs.getString("atitle"));
				act.setAclub(rs.getString("aclub"));
				act.setAtime(rs.getString("atime"));
				act.setCid(rs.getInt("cid"));
				stuact.add(act);
			}
			request.setAttribute("stuact", stuact);
			rs.close();
			stmt.close();
			myconn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		request.getRequestDispatcher("SelfActPage.jsp").forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
