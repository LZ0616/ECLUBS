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
 * Servlet implementation class ApplyNewsDetailServlet
 */
@WebServlet("/ApplyNewsDetailServlet")
public class ApplyNewsDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyNewsDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		System.out.println("id="+id);
		try {
			Connection myconn = new DBHelper().getConn();
			Statement stmt = myconn.createStatement();	
			String sql = "select * from check_news where id = '"+id+"'";
			ResultSet rs = stmt.executeQuery(sql);
			List<ApplyActNewsBean> list = new ArrayList<>();		
			while(rs.next()){								
				ApplyActNewsBean actl = new ApplyActNewsBean();					
				actl.setId(rs.getInt("id"));
				actl.setCid(rs.getInt("cid"));
				actl.setCname(rs.getString("cname"));
				actl.setMaster(rs.getString("master"));
				actl.setTitle(rs.getString("title"));
				actl.setContent(rs.getString("content"));
				actl.setTime(rs.getString("time"));
				actl.setFilename(rs.getString("filename"));
				list.add(actl);
			}
			request.setAttribute("list", list); 
			
			rs.close();									
			stmt.close();									
			myconn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
			request.getRequestDispatcher("ShowApplyNewsDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
