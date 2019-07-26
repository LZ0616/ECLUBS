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
import javax.servlet.http.HttpSession;

import exper.DBHelper;

/**
 * Servlet implementation class ListResponseServlet
 */
@WebServlet("/ListResponseServlet")
public class ListResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListResponseServlet() {
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
			
		Connection conn = new DBHelper().getConn();	
		String sql = null;
		ResultSet rs = null;
		
		List<ResponseBeen> list = new ArrayList<>();
		
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
											
		
		
		//if(status.equals("admin")){
			//request.setAttribute("status",status);
			request.getRequestDispatcher("student.jsp").forward(request, response);
		//}
		/*else if(status.equals("student")){
			request.setAttribute("status",status);
			request.setAttribute("account",account);
			request.getRequestDispatcher("note_list_stu.jsp").forward(request, response);
		}*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
