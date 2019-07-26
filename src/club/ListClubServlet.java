package club;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exper.DBHelper;

/**
 * Servlet implementation class cpc_listnote
 */
@WebServlet("/ListClubServlet")
public class ListClubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListClubServlet() {
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
		String identity;
		identity=(String)session.getAttribute("identity");
		try {	
			Connection conn = new DBHelper().getConn();	 
			Statement stmt = conn.createStatement();	
			String sql = "select * from club_info";			
			ResultSet rs = stmt.executeQuery(sql);		
			List<ListClubBean> list = new ArrayList<>();		
			while(rs.next()){								
				ListClubBean club = new ListClubBean();					
				club.setId(rs.getInt("id"));				
				club.setCname(rs.getString("cname"));		
				club.setMaster(rs.getString("master"));		
				club.setTime(rs.getString("time"));
				club.setSum_member(rs.getInt("sum_member"));
				club.setSum_act(rs.getInt("sum_act"));
				list.add(club); 							
			}
			request.setAttribute("list", list); 		
			rs.close();									
			stmt.close();									
			conn.close();									
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(identity=="№ЬАн")
			request.getRequestDispatcher("ListClub_admin.jsp").forward(request, response);
		else
			request.getRequestDispatcher("ListClub_stu.jsp").forward(request, response);
	}

}
