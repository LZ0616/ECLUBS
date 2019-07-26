package Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import exper.DBHelper;
/**
 * Servlet implementation class CheckJoinClubServlet
 */
@WebServlet("/CheckJoinClubServlet")
public class CheckJoinClubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String temp = "0";
    /**
     * 
     * @see HttpServlet#HttpServlet()
     */
    public CheckJoinClubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = new DBHelper().getConn();
		Statement stmt = null;
		ResultSet rs=null;
		String sql=null;
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		String stuname;
		stuname=(String)session.getAttribute("stuname");
		String account = (String) session.getAttribute("account");
		String cname = (String) request.getAttribute("cname");
		try{
			sql="select account from club_member where cname='"+cname+"'";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				if(rs.getString("account").equals(account)){
					temp="1";
					break;
				}
			}
			request.setAttribute("temp", temp);
		}catch(Exception e){
			return;
		}
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
