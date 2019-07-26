package Message;

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

import exper.DBHelper;

/**
 * Servlet implementation class ListApplyDeleteClubServlet
 */
@WebServlet("/ListApplyDeleteClubServlet")
public class ListApplyDeleteClubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListApplyDeleteClubServlet() {
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
		
		try {	
			Connection conn = new DBHelper().getConn();	 
			Statement stmt = conn.createStatement();	
			String sql = "select * from check_delclub";			
			ResultSet rs = stmt.executeQuery(sql);		
			List<ApplyDeleteClubBeen> list = new ArrayList<>();		
			while(rs.next()){								
				ApplyDeleteClubBeen del = new ApplyDeleteClubBeen();					
				del.setId(rs.getInt("id"));	
				del.setCid(rs.getInt("cid"));
				del.setAccount(rs.getString("account"));
				del.setCname(rs.getString("cname"));		
				del.setMaster(rs.getString("master"));			
				del.setReason(rs.getString("reason"));
					
				list.add(del);	
			}
			request.setAttribute("list", list); 		
			rs.close();									
			stmt.close();									
			conn.close();									
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//if(status.equals("admin")){
			//request.setAttribute("status",status);
			request.getRequestDispatcher("ListApplyDeleteClub.jsp").forward(request, response);
		//}
		/*else if(status.equals("student")){
			request.setAttribute("status",status);
			request.setAttribute("account",account);
			request.getRequestDispatcher("note_list_stu.jsp").forward(request, response);
		}*/
	}

}
