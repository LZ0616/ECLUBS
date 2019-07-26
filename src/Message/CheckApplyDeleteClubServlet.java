package Message;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exper.DBHelper;


/**
 * Servlet implementation class CheckApplyDeleteClubServlet
 */
@WebServlet("/CheckApplyDeleteClubServlet")
public class CheckApplyDeleteClubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckApplyDeleteClubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		Connection conn = new DBHelper().getConn();
		int id = Integer.parseInt(request.getParameter("id"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		String account = String.valueOf(request.getParameter("account"));
		String con = String.valueOf(request.getParameter("con"));
		
		if(con.equals("2")){
			String cname = null;
			String sname = null;
			ResultSet rs = null;
			
			try {
				String sql = "select cname from club_info where id = "+cid;
				PreparedStatement pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					cname = rs.getString("cname");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				String sql = "select sname from student where account = '"+account+"'";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					sname = rs.getString("sname");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			int sum = 0; 
			String sql = "select count(*) from response_delclub"; 
			try { 
				PreparedStatement pstmt = conn.prepareStatement(sql); 
				rs = pstmt.executeQuery();
				while (rs.next()) { 
					sum += rs.getInt(1); 
				} 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
			
			String content = sname+"同学，"+cname+"社团解散失败，请继续为了社团努力吧！";
			
			sql = "insert into response_delclub(id,account,content) values(?,?,?)";
			
			try{
				PreparedStatement pstmt = conn.prepareStatement(sql); 
				pstmt.setInt(1, sum+1);
				pstmt.setString(2, account);
				pstmt.setString(3, content);
				pstmt.execute();
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
			
		}
		
		try {
			String sql = "delete from check_delclub where id = "+id;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();

			sql = "update check_delclub set id=id-1 where id > "+id;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("ListApplyDeleteClubServlet").forward(request, response);

	}

}
