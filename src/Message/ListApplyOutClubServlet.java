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
 * Servlet implementation class ListApplyOutClubServlet
 */
@WebServlet("/ListApplyOutClubServlet")
public class ListApplyOutClubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListApplyOutClubServlet() {
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
		
		int cid = Integer.parseInt(request.getParameter("cid"));
		
		try {	
			Connection conn = new DBHelper().getConn();	 
			Statement stmt = conn.createStatement();	
			String sql = "select * from check_out where cid="+cid;			
			ResultSet rs = stmt.executeQuery(sql);		
			List<ApplyOutClubBeen> list = new ArrayList<>();		
			while(rs.next()){								
				ApplyOutClubBeen out = new ApplyOutClubBeen();					
				out.setId(rs.getInt("id"));	
				out.setCid(rs.getInt("cid"));
				out.setCname(rs.getString("cname"));		
				out.setAccount(rs.getString("account"));		
				out.setSname(rs.getString("sname"));	
				out.setReason(rs.getString("reason"));
					
				list.add(out);	
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
			request.getRequestDispatcher("ListApplyOutClub.jsp").forward(request, response);
		//}
		/*else if(status.equals("student")){
			request.setAttribute("status",status);
			request.setAttribute("account",account);
			request.getRequestDispatcher("note_list_stu.jsp").forward(request, response);
		}*/
	}

}
