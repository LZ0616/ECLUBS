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
 * Servlet implementation class ShowApplyNewsServlet
 */
@WebServlet("/ShowApplyNewsServlet")
public class ShowApplyNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowApplyNewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Connection myconn = new DBHelper().getConn();
			Statement stmt = myconn.createStatement();	
			String sql = "select * from check_news";
			ResultSet rs = stmt.executeQuery(sql);
			List<ApplyActNewsBean> list = new ArrayList<>();		
			while(rs.next()){								
				ApplyActNewsBean news = new ApplyActNewsBean();					
				news.setId(rs.getInt("id"));
				news.setCid(rs.getInt("cid"));
				news.setCname(rs.getString("cname"));
				news.setMaster(rs.getString("master"));
				news.setTitle(rs.getString("title"));
				news.setContent(rs.getString("content"));
				news.setTime(rs.getString("time"));
				news.setFilename(rs.getString("filename"));
				list.add(news); 							
			}
			request.setAttribute("list", list); 
			
			rs.close();									
			stmt.close();									
			myconn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
			request.getRequestDispatcher("ShowApplyNews.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
