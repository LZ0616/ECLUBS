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

@WebServlet("/ApplyDeleteClubDoServlet")
public class ApplyDeleteClubDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ApplyDeleteClubDoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int cid= Integer.parseInt(request.getParameter("cid"));
		String reason = String.valueOf(request.getParameter("reason"));
		String cname = null;
		String account = null;
		String master = null;
		
		Connection conn = new DBHelper().getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select cname,account,master from club_info where id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			rs =pstmt.executeQuery();
			while (rs.next()) {
				cname = rs.getString("cname");
				account = rs.getString("account");
				master = rs.getString("master");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			

			int sum = 0; 
			sql = "select count(*) from check_delclub"; 
			try { 
				pstmt = conn.prepareStatement(sql); 
				rs = pstmt.executeQuery();	
				while (rs.next()) { 
					sum += rs.getInt(1); 
				} 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
			
			sql = "insert into check_delclub(id,cid,cname,account,master,reason) values(?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, sum+1);
			pstmt.setInt(2, cid);
			pstmt.setString(3, cname);
			pstmt.setString(4, account);
			pstmt.setString(5, master);
			pstmt.setString(6, reason); 
			pstmt.execute();
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("DetailsClubServlet?id="+cid).forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
