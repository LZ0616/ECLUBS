package Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import club.ListClubBean;
import exper.DBHelper;

/**
 * Servlet implementation class UpdateMemberShowServlet
 */
@WebServlet("/UpdateMemberShowServlet")
public class UpdateMemberShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String sid= request.getParameter("sid");
		String account=null;
		System.out.println(sid);
		ClubMemberBean mem = new ClubMemberBean();
		Connection conn = new DBHelper().getConn();
		Statement stat = null;
		ResultSet rs = null;
		String sql="select account from student where id='"+sid+"'";
		try{
			stat=conn.createStatement();
			rs=stat.executeQuery(sql);
			if(rs.next()){
				account=rs.getString("account");
			}
			sql = "select * from club_member where account='"+account+"'";
			rs=stat.executeQuery(sql);
			while(rs.next()){
				mem.setId(rs.getInt("id"));
				mem.setCid(rs.getInt("cid"));
				mem.setCname(rs.getString("cname"));
				mem.setAccount(rs.getString("account"));
				System.out.println(rs.getString("account"));
				mem.setSname(rs.getString("sname"));
				mem.setPos(rs.getString("pos"));
				mem.setDate(rs.getString("date"));
				
			}
			rs.close();
			stat.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		request.setAttribute("mem", mem);
		request.getRequestDispatcher("UpdateMember.jsp").forward(request, response);
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
