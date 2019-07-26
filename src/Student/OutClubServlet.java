package Student;

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
import Student.StudentSer;

@WebServlet("/OutClubServlet")
public class OutClubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OutClubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		String account = String.valueOf(request.getParameter("account"));
		String con = String.valueOf(request.getParameter("con"));
		
		String cname = null;
		String sname = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		
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
		String sql = "select count(*) from response_out"; 
		try { 
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			rs = pstmt.executeQuery();
			while (rs.next()) { 
				sum += rs.getInt(1); 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		
		String content = sname+"同学，退出"+cname+"社团成功，下次努力吧！";
		
		sql = "insert into response_out(id,account,content) values(?,?,?)";
		
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, sum+1);
			pstmt.setString(2, account);
			pstmt.setString(3, content);
			pstmt.execute();
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		
		StudentSer stuSer = new StudentSer();
		stuSer.outStu(cid,account);
		request.getRequestDispatcher("CheckApplyOutClubServlet?id="+id+"&con="+con).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
