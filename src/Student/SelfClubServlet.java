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
import club.ListClubBean;

/**
 * Servlet implementation class SelfClubServlet
 */
@WebServlet("/SelfClubServlet")
public class SelfClubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelfClubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String account=(String)session.getAttribute("account");
		String sql;
		String sname = null;
		int t=0;
		Connection myconn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try{
			myconn=new DBHelper().getConn();
			sql="select sname from student where account='"+account+"'";
			stmt=myconn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				sname=rs.getString("sname");
			}
			sql="select id,cid,cname,master,sname from club_member where sname='"+sname+"'";
			rs=stmt.executeQuery(sql);
			List<ClubMemberBean> c = new ArrayList<>();
			while(rs.next()){
				ClubMemberBean cl=new ClubMemberBean();
				cl.setId(rs.getInt("id"));
				cl.setCid(rs.getInt("cid"));
				cl.setCname(rs.getString("cname"));
				cl.setMaster(rs.getString("master"));
				cl.setSname(rs.getString("sname"));
				c.add(cl);
			}
			request.setAttribute("sclub", c);
			rs.close();
			stmt.close();
			myconn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		request.getRequestDispatcher("self_stu.jsp").forward(request, response);
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
