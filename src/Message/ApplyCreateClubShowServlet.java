package Message;

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
 * Servlet implementation class ApplyCreateClubShowServlet
 */
@WebServlet("/ApplyCreateClubShowServlet")
public class ApplyCreateClubShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyCreateClubShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		String account=(String)session.getAttribute("account");
		Connection conn=null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql=null;
		ApplyCreateClubBean ccb = new ApplyCreateClubBean();
		try{
			conn=new DBHelper().getConn();
			stmt=conn.createStatement();
			sql="select sname from student where account = '"+account+"'";
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				ccb.setMaster(rs.getString("sname"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("ccb", ccb);
		request.getRequestDispatcher("AddClub.jsp").forward(request, response);
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
