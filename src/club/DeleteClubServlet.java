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

import exper.DBHelper;


/**
 * Servlet implementation class DeleteClubServlet
 */
@WebServlet("/DeleteClubServlet")
public class DeleteClubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteClubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		Connection conn = new DBHelper().getConn();
		int id = Integer.parseInt(request.getParameter("id"));
		int cid = Integer.parseInt(request.getParameter("cid"));
		String account = String.valueOf(request.getParameter("account"));
		String con = String.valueOf(request.getParameter("con"));
		
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
		
		String content = sname+"同学，"+cname+"社团解散成功，下次努力吧！";
		
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
		
		try {
			sql = "delete from club_info where id="+cid;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();

			sql = "update club_info set id=id-1 where id>"+cid;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			sql = "update club_member set cid=cid-1 where cid>"+cid;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			sql = "update check_act set cid=cid-1 where cid>"+cid;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			sql = "update check_news set cid=cid-1 where cid>"+cid;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			sql = "update check_join set cid=cid-1 where cid>"+cid;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			sql = "update check_out set cid=cid-1 where cid>"+cid;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			sql = "select id from club_member where cid="+cid;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int cmid = 0;
			if(rs.next()){
				cmid = rs.getInt("id");
			}
			
			sum = 0; 
			sql = "select count(*) from club_member where cid="+cid; 
			try { 
				pstmt = conn.prepareStatement(sql); 
				rs = pstmt.executeQuery();
				while (rs.next()) { 
					sum += rs.getInt(1); 
				} 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
			
			sql = "delete from club_member where cid="+cid;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			sql = "update club_member set id=id-"+sum+" where id>"+cmid;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			sql = "select id from check_act where cid="+cid;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int caid = 0;
			if(rs.next()){
				caid = rs.getInt("id");
			}
			
			sum = 0; 
			sql = "select count(*) from check_act where cid="+cid; 
			try { 
				pstmt = conn.prepareStatement(sql); 
				rs = pstmt.executeQuery();
				while (rs.next()) { 
					sum += rs.getInt(1); 
				} 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
			
			sql = "delete from check_act where cid="+cid;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			sql = "update check_act set id=id-"+sum+" where id>"+caid;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			sql = "select id from check_news where cid="+cid;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int cnid = 0;
			if(rs.next()){
				cnid = rs.getInt("id");
			}
			
			sum = 0; 
			sql = "select count(*) from check_news where cid="+cid; 
			try { 
				pstmt = conn.prepareStatement(sql); 
				rs = pstmt.executeQuery();
				while (rs.next()) { 
					sum += rs.getInt(1); 
				} 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
			
			sql = "delete from check_news where cid="+cid;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			sql = "update check_news set id=id-"+sum+" where id>"+cnid;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			sql = "select id from check_join where cid="+cid;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int cjid = 0;
			if(rs.next()){
				cjid = rs.getInt("id");
			}
			
			sum = 0; 
			sql = "select count(*) from check_join where cid="+cid; 
			try { 
				pstmt = conn.prepareStatement(sql); 
				rs = pstmt.executeQuery();
				while (rs.next()) { 
					sum += rs.getInt(1); 
				} 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
			
			sql = "delete from check_join where cid="+cid;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			sql = "update check_join set id=id-"+sum+" where id>"+cjid;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			sql = "select id from check_out where cid="+cid;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int coid = 0;
			if(rs.next()){
				coid = rs.getInt("id");
			}
			
			sum = 0; 
			sql = "select count(*) from check_out where cid="+cid; 
			try { 
				pstmt = conn.prepareStatement(sql); 
				rs = pstmt.executeQuery();
				while (rs.next()) { 
					sum += rs.getInt(1); 
				} 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
			
			sql = "delete from check_out where cid="+cid;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			sql = "update check_out set id=id-"+sum+" where id>"+coid;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("CheckApplyDeleteClubServlet?id="+id+"&con="+con).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
