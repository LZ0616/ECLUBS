package club;

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
import javax.servlet.http.HttpSession;

import exper.DBHelper;

/**
 * Servlet implementation class NewsListServlet
 */
@WebServlet("/NewsListServlet")
public class NewsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String identity;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsListServlet() {
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
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		identity=(String)session.getAttribute("identity");
		try {	
			Connection myconn = new DBHelper().getConn();					
			if(myconn!=null){
				System.out.println("数据库连接成功！");
			}else{
				System.out.println("数据库连接失败！");
			}
			Statement stmt = myconn.createStatement();	
			String sql = "select * from club_news";			
			ResultSet rs = stmt.executeQuery(sql);
			List<NewsListBean> list = new ArrayList<>();		
			while(rs.next()){								
				NewsListBean actl = new NewsListBean();					
				actl.setId(rs.getInt("id"));
				actl.setTitle(rs.getString("title"));
				actl.setCname(rs.getString("cname"));
				actl.setTime(rs.getString("time"));
				actl.setContent(rs.getString("content"));
				actl.setMaster(rs.getString("master"));
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
		if(identity=="学生")
			request.getRequestDispatcher("ListNews_stu.jsp").forward(request, response);
		else
			request.getRequestDispatcher("ListNews_admin.jsp").forward(request, response);
	}

}
