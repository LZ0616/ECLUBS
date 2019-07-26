package club;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

import exper.DBHelper;
/**
 * Servlet implementation class ActDetailServlet
 */
@WebServlet("/ActDetailServlet")
public class ActDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection myconn = null;	
	Statement stmt = null;	
	ResultSet set = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		myconn=new DBHelper().getConn();
		String sql="select * from club_activity where cname=? ";

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
