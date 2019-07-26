package Message;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exper.DBHelper;


/**
 * Servlet implementation class CheckResponseServlet
 */
@WebServlet("/CheckResponseServlet")
public class CheckResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckResponseServlet() {
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
		int flag = Integer.parseInt(request.getParameter("flag"));
		System.out.println("id="+id);
		
		if(flag==1){
			try {
				String sql = "delete from response_join where id = "+id;
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();

				sql = "update response_join set id=id-1 where id > "+id;
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(flag==2){
			try {
				String sql = "delete from response_out where id = "+id;
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();

				sql = "update response_out set id=id-1 where id > "+id;
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(flag==3){
			try {
				String sql = "delete from response_creclub where id = "+id;
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();

				sql = "update response_creclub set id=id-1 where id > "+id;
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(flag==4){
			try {
				String sql = "delete from response_delclub where id = "+id;
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();

				sql = "update response_delclub set id=id-1 where id > "+id;
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(flag==5){
			try {
				String sql = "delete from response_joinact where id = "+id;
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();

				sql = "update response_joinact set id=id-1 where id > "+id;
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		request.getRequestDispatcher("IndexServlet").forward(request, response);

	}

}
