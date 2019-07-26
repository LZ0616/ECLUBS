package Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exper.DBHelper;

@WebServlet("/ListClubMemberServlet")
public class ListClubMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListClubMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int id= Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		String cname = null;
		Connection conn = new DBHelper().getConn();	
		ResultSet rs = null;
		
		try{
			String sql = "select * from club_info where id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id); 
			rs = pstmt.executeQuery();
			if(rs.next()){
				cname = rs.getString("cname");
			}
			System.out.println("123456"+cname);
			pstmt.close();
			conn.close(); 
			rs.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		StudentSer stuSer = new StudentSer();
		//String status =  String.valueOf(request.getParameter("status"));
		request.setAttribute("stuList", stuSer.getClubMember(cname));	
		request.getRequestDispatcher("ClubMemberList2.jsp").forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
