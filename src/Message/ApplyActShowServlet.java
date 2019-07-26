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
import exper.DBHelper;
/**
 * Servlet implementation class ApplyActServlet
 */
@WebServlet("/ApplyActShowServlet")
public class ApplyActShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyActShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cid=request.getParameter("id");
		Connection conn=null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql=null;
		ApplyActNewsBean act = new ApplyActNewsBean();
		try{
			conn=new DBHelper().getConn();
			stmt=conn.createStatement();
			sql="select id,cname,master from club_info where id = '"+cid+"'";
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				act.setCid(rs.getInt("id"));
				act.setCname(rs.getString("cname"));
				act.setMaster(rs.getString("master"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("act", act);
		request.getRequestDispatcher("AddActivity.jsp").forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
