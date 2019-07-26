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


/**
 * Servlet implementation class JoinClubServlet
 */
@WebServlet("/JoinClubServlet")
public class JoinClubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinClubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(request.getParameter("id"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		String account = String.valueOf(request.getParameter("account"));
		String con = String.valueOf(request.getParameter("con"));
		String cname = null;
		String master = null;
		String sname=null;
		String pos="成员";
		String date="1998-8-8";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection conn = new DBHelper().getConn();	
		
		try {
			String sql = "select cname,master from club_info where id='"+cid+"'";
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				cname=rs.getString("cname");
				master=rs.getString("master");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			String sql = "select sname from student where account = '"+account+"'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				sname = rs.getString("sname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int sum = 0; 
		String sql = "select count(*) from response_join"; 
		try { 
			pstmt = conn.prepareStatement(sql); 
			rs = pstmt.executeQuery();
			while (rs.next()) { 
				sum += rs.getInt(1); 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		
		String content = sname+"同学，恭喜你，成功加入"+cname+"社团，今后一起努力哦！";
		
		sql = "insert into response_join(id,account,content) values(?,?,?)";
		
		try{
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, sum+1);
			pstmt.setString(2, account);
			pstmt.setString(3, content);
			pstmt.execute();
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		
		try{
			sql = "update club_info set sum_member=sum_member+1 where id ="+cid;
			pstmt = conn.prepareStatement(sql); 
			pstmt.execute();
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		
		try {
			sum=0;
			sql="select count(*) from club_member";
			try{
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()){
					sum+=rs.getInt(1);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			sql="insert into club_member(id,cid,cname,master,account,sname,pos,date) values(?,?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, sum+1);
			pstmt.setInt(2, cid);
			pstmt.setString(3, cname);
			pstmt.setString(4, master);
			pstmt.setString(5, account);
			pstmt.setString(6, sname);
			pstmt.setString(7, pos);
			pstmt.setString(8, date);
			pstmt.execute();
			
			pstmt.close();
			conn.close(); 
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("CheckApplyJoinClubServlet?id="+id+"&con="+con).forward(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
