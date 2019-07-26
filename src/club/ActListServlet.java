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
 * Servlet implementation class ActListServlet
 */
@WebServlet("/ActListServlet")
public class ActListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String identity;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActListServlet() {
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
		identity=(String)session.getAttribute("identity");
		try {	
			
			Connection myconn = new DBHelper().getConn();					
			if(myconn!=null){
				System.out.println("数据库连接成功！");
			}else{
				System.out.println("数据库连接失败！");
			}
			Statement stmt = myconn.createStatement();	
			String sql = "select * from club_activity";			
			ResultSet rs = stmt.executeQuery(sql);		
			List<ActListBean> list = new ArrayList<>();		
			while(rs.next()){								
				ActListBean actl = new ActListBean();					
				actl.setId(rs.getInt("id"));
				actl.setTitle(rs.getString("title"));
				actl.setCname(rs.getString("cname"));
				actl.setTime(rs.getString("time"));
				actl.setMaster(rs.getString("master"));
				actl.setContent(rs.getString("content"));
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
		
		if(identity=="学生"){
			System.out.println(identity);
			request.getRequestDispatcher("ListAct_stu.jsp").forward(request, response);
		}
		else if(identity=="管理"){
			System.out.println(identity);
			request.getRequestDispatcher("ListAct_admin.jsp").forward(request, response);
		}
		
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
