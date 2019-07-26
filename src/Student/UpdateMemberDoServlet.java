package Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import exper.DBHelper;
/**
 * Servlet implementation class UpdateMemberServlet
 */
@WebServlet("/UpdateMemberDoServlet")
public class UpdateMemberDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberDoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn=new DBHelper().getConn();
		PreparedStatement stmt= null;
		ResultSet rs=null;
		request.setCharacterEncoding("UTF-8");
		String account=request.getParameter("account");
		String id = request.getParameter("id");
		System.out.println("account="+account);
		String pos=request.getParameter("pos");
		System.out.println("pos="+pos);
		String sql="update club_member set pos = '"+pos+"' where account='"+account+"' and id='"+id+"'";
		try{
			stmt=conn.prepareStatement(sql);
			stmt.execute();
			stmt.close();
			conn.close();	
		}catch(SQLException e){
			e.printStackTrace();
		}
		request.getRequestDispatcher("ListClubMemberServlet?id="+id).forward(request, response); 
		//doPost(request, response);
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
