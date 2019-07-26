package club;

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


/**
 * Servlet implementation class AddClubIntroduction
 */
@WebServlet("/AddClubIntroduction")
public class AddClubIntroduction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClubIntroduction() {
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
		HttpSession session = request.getSession(true);
		String account;
		account=(String)session.getAttribute("account");
		String sname =null;
		String cname =  String.valueOf(request.getParameter("cname"));
		String master = String.valueOf(request.getParameter("master"));
		String time = String.valueOf(request.getParameter("time"));
		String intro =  String.valueOf(request.getParameter("intro"));
		String rules =  String.valueOf(request.getParameter("rules"));

		try {
			Connection conn = new DBHelper().getConn();	 	
			ResultSet rs = null;
			
			String sql1 = "select * from club_info where cname=?";
			PreparedStatement pstmt=conn.prepareStatement(sql1);
			pstmt.setString(1, cname); 
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				request.setAttribute("error","注册失败！社团名已被占用，请重新输入社团名！");
				request.getRequestDispatcher("AddClub.jsp").forward(request,response);
			}
			int sum = 0; 
			String sql = "select count(*) from club_info"; 
			try { 
				pstmt = conn.prepareStatement(sql); 
				rs = pstmt.executeQuery();
				while (rs.next()) { 
					sum += rs.getInt(1); 
				} 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
			
			sql="select sname from student where account='"+account+"'";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				sname=rs.getString("sname");
			}
			
			
			sql = "insert into club_info(id,cname,master,time,intro,rules) values(?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, sum+1);
			pstmt.setString(2, cname);
			pstmt.setString(3, master);
			pstmt.setString(4, time); 	
			pstmt.setString(5, intro); 
			pstmt.setString(6, rules); 
			pstmt.execute();
			
			int sum1=0;
			sql="select count(*) from club_member";
			try{
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()){
					sum1+=rs.getInt(1);
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			
			sql="insert into club_member(id,cid,cname,master,account,pos,date) values(?,?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, sum1+1);
			pstmt.setInt(2, sum+1);
			pstmt.setString(3, cname);
			pstmt.setString(4, master);
			pstmt.setString(5, account);
			pstmt.setString(6, sname); 	
			pstmt.setString(7, "社长"); 
			pstmt.setString(8, time); 
			pstmt.execute();
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("ListClubServlet").forward(request, response);

	}

}


