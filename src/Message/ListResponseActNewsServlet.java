package Message;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
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
 * Servlet implementation class ListResponseCreateClubServlet
 */
@WebServlet("/ListResponseActNewsServlet")
public class ListResponseActNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListResponseActNewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cid=request.getParameter("cid");
		System.out.println("cid="+cid);
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql=null;
		try{
			conn=new DBHelper().getConn();
			stmt=conn.createStatement();
			sql="select * from response_act where cid ='"+cid+"'";
			rs=stmt.executeQuery(sql);
			List<ResponseActNewsBean> list = new ArrayList<>();		
			while(rs.next()){								
				ResponseActNewsBean anb = new ResponseActNewsBean();
				anb.setId(rs.getInt("id"));
				anb.setCid(rs.getInt("cid"));
				anb.setContent(rs.getString("content"));
				anb.setFlag(1);
				list.add(anb);
			}
			stmt=conn.createStatement();
			sql="select * from response_news where cid ='"+cid+"'";
			rs=stmt.executeQuery(sql);
			while(rs.next()){								
				ResponseActNewsBean anb = new ResponseActNewsBean();
				anb.setId(rs.getInt("id"));
				anb.setCid(rs.getInt("cid"));
				anb.setContent(rs.getString("content"));
				anb.setFlag(2);
				list.add(anb);
			}
			request.setAttribute("list", list);
			
			rs.close();									
			stmt.close();									
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		request.getRequestDispatcher("ShowClubMess.jsp").forward(request, response);
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
