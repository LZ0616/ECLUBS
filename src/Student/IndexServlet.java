package Student;

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

import Message.ResponseBeen;
import exper.DBHelper;
import club.NewsListBean;
import club.ActListBean;
/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String identity;
    String account;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
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
		account=(String)session.getAttribute("account");
		try{
			Connection myconn = new DBHelper().getConn();
			Statement stmt = myconn.createStatement();
			String sql;
			sql="select * from club_news ";
			ResultSet rs = stmt.executeQuery(sql);
			List<NewsListBean> list2 = new ArrayList<>();
			while(rs.next()){
				NewsListBean news = new NewsListBean();
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setCname(rs.getString("cname"));
				news.setTime(rs.getString("time"));
				news.setContent(rs.getString("content"));
				news.setMaster(rs.getString("master"));
				list2.add(news);
			}
			request.setAttribute("news", list2);
			sql="select * from club_activity";
			rs=stmt.executeQuery(sql);
			List<ActListBean> list1 = new ArrayList<>();
			while(rs.next()){
				ActListBean actl = new ActListBean();					
				actl.setId(rs.getInt("id"));
				actl.setTitle(rs.getString("title"));
				actl.setCname(rs.getString("cname"));
				actl.setTime(rs.getString("time"));
				actl.setMaster(rs.getString("master"));
				list1.add(actl); 
			}
			request.setAttribute("act", list1);
			rs.close();									
			stmt.close();									
			myconn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<ResponseBeen> list = new ArrayList<>();
		Connection conn = new DBHelper().getConn();	
		String sql = null;
		ResultSet rs = null;
		
		try {	
			sql = "select * from response_join where account = '"+account+"'"; 
			Statement stmt = conn.createStatement();			
			rs = stmt.executeQuery(sql);	
			while(rs.next()){									
				ResponseBeen res = new ResponseBeen();					
				res.setId(rs.getInt("id"));		
				res.setAccount(rs.getString("account"));	
				res.setContent(rs.getString("content"));
				res.setFlag(1);
					
				list.add(res);	
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		try{
			sql = "select * from response_out where account = '"+account+"'"; 
			Statement stmt = conn.createStatement();			
			rs = stmt.executeQuery(sql);			
			while(rs.next()){										
				ResponseBeen res = new ResponseBeen();											
				res.setId(rs.getInt("id"));		
				res.setAccount(rs.getString("account"));	
				res.setContent(rs.getString("content"));
				res.setFlag(2);
					
				list.add(res);	
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		try{	
			sql = "select * from response_creclub where account = '"+account+"'"; 
			Statement stmt = conn.createStatement();		
			rs = stmt.executeQuery(sql);			
			while(rs.next()){										
				ResponseBeen res = new ResponseBeen();											
				res.setId(rs.getInt("id"));		
				res.setAccount(rs.getString("account"));	
				res.setContent(rs.getString("content"));
				res.setFlag(3);
					
				list.add(res);	
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		try{
			sql = "select * from response_delclub where account = '"+account+"'"; 
			Statement stmt = conn.createStatement();			
			rs = stmt.executeQuery(sql);			
			while(rs.next()){										
				ResponseBeen res = new ResponseBeen();											
				res.setId(rs.getInt("id"));		
				res.setAccount(rs.getString("account"));	
				res.setContent(rs.getString("content"));
				res.setFlag(4);
					
				list.add(res);	
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		try{
			sql = "select * from response_joinact where account = '"+account+"'"; 
			Statement stmt = conn.createStatement();			
			rs = stmt.executeQuery(sql);			
			while(rs.next()){										
				ResponseBeen res = new ResponseBeen();											
				res.setId(rs.getInt("id"));		
				res.setAccount(rs.getString("account"));	
				res.setContent(rs.getString("content"));
				res.setFlag(5);
					
				list.add(res);	
					
			}
			rs.close();									
			stmt.close();									
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
			
		request.setAttribute("list", list); 
		
		if(identity=="Ñ§Éú"){
			System.out.println(identity);
			request.getRequestDispatcher("student.jsp").forward(request, response);
		}
		else{
			System.out.println(identity);
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
