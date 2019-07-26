package club;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;

import exper.DBHelper;


/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/GetClubByCnameServlet")
public class GetClubByCnameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetClubByCnameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String keyword =  String.valueOf(request.getParameter("keyword"));

		try {
			Connection conn = new DBHelper().getConn();	
			String sql = "select * from club_info where cname like ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,"%"+keyword+"%") ;
			
			ResultSet rs = pstmt.executeQuery();
			
			List<ListClubBean> list = new ArrayList<>();
			while (rs.next()) {
				
				ListClubBean club = new ListClubBean();
				
				club.setId(rs.getInt("id"));				
				club.setCname(rs.getString("cname"));		
				club.setMaster(rs.getString("master"));		
				club.setTime(rs.getString("time"));
			
				list.add(club);
			}
			request.setAttribute("list", list);
			rs.close();
			pstmt.close();
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}

		//if(status.equals("admin")){
			//request.setAttribute("status",status);
			request.getRequestDispatcher("ListClub_stu.jsp").forward(request, response);
		/*}
		else if(status.equals("student")){
			request.setAttribute("status",status);
			request.setAttribute("account",account);
			request.getRequestDispatcher("note_search_stu.jsp").forward(request, response);
		}*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
