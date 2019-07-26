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
import javax.servlet.http.HttpSession;

import exper.DBHelper;

@WebServlet("/ApplyOutClubDoServlet")
public class ApplyOutClubDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ApplyOutClubDoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		String account=(String)session.getAttribute("account");
		int cid= Integer.parseInt(request.getParameter("cid"));
		String cname = String.valueOf(request.getParameter("cname"));
		String sname = null;
		String reason = String.valueOf(request.getParameter("reason"));
		
		Connection conn = new DBHelper().getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select sname from student where account=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, account);
			rs =pstmt.executeQuery();
			while (rs.next()) {
				sname = rs.getString("sname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			

			int sum = 0; 
			sql = "select count(*) from check_out"; 
			try { 
				pstmt = conn.prepareStatement(sql); 
				rs = pstmt.executeQuery();
				while (rs.next()) { 
					sum += rs.getInt(1); 
				} 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
			
			sql = "insert into check_out(id,cid,cname,account,sname,reason) values(?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, sum+1);
			pstmt.setInt(2, cid);
			pstmt.setString(3, cname);
			pstmt.setString(4, account);
			pstmt.setString(5, sname); 	
			pstmt.setString(6, reason); 
			pstmt.execute();
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("ListClubServlet").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
