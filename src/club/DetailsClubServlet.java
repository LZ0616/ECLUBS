package club;

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
 * Servlet implementation class DetailsClubServlet
 */
@WebServlet("/DetailsClubServlet")
public class DetailsClubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    int temp = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailsClubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		String account=(String)session.getAttribute("account");
		String identity = (String)session.getAttribute("identity");
		int cid = Integer.parseInt(request.getParameter("id"));
		
		int flag_join = 0;
		int flag_applyjoin = 0;
		int flag_applyout = 0;
		String master=null;
		Connection conn = new DBHelper().getConn();	 
		
		try {
			Statement stmt = conn.createStatement();
			String sql = "select id,cname,master,time,intro,rules from club_info where id='"+cid+"'";
			ResultSet rs = stmt.executeQuery(sql);	
			
			List<ListClubBean> list = new ArrayList<>();
			while (rs.next()) {
			ListClubBean note = new ListClubBean();
			note.setId(rs.getInt("id"));
			note.setCname(rs.getString("cname"));
			note.setMaster(rs.getString("master"));
			master=rs.getString("master");
			note.setTime(rs.getString("time"));
			note.setIntro(rs.getString("intro"));
			note.setRules(rs.getString("rules"));
			list.add(note); 
			}
			request.setAttribute("list", list);
			
			sql="select sname from student where account = '"+account+"'";
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				if(master.equals(rs.getString("sname"))){
					temp=1;
				}
				else{
					temp=3;
				}
				System.out.println(master+"   "+rs.getString("sname"));
			}
			if(identity.equals("管理")){
				temp=2;
			}
			request.setAttribute("temp", temp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try { 
			Statement stmt = conn.createStatement();

			String sql = "select * from club_member where cid="+cid+" and account ='"+account+"'";
			ResultSet rs = stmt.executeQuery(sql);	
			
			if (rs.next()) {
				flag_join = 1;
			}
			request.setAttribute("flag_join", flag_join);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try { 
			Statement stmt = conn.createStatement();

			String sql = "select * from check_join where cid="+cid+" and account ='"+account+"'";
			ResultSet rs = stmt.executeQuery(sql);	
			
			if (rs.next()) {
				flag_applyjoin = 1;
			}
			request.setAttribute("flag_applyjoin", flag_applyjoin);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try { 
			Statement stmt = conn.createStatement();

			String sql = "select * from check_out where cid="+cid+" and account ='"+account+"'";
			ResultSet rs = stmt.executeQuery(sql);	
			
			if (rs.next()) {
				flag_applyout = 1;
			}
			request.setAttribute("flag_applyout", flag_applyout);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(identity.equals("管理"))
			request.getRequestDispatcher("DetailsClub_admin.jsp").forward(request, response);
		else if(identity.equals("学生"))
			request.getRequestDispatcher("DetailsClub_stu.jsp").forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
