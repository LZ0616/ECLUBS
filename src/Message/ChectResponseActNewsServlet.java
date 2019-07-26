package Message;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import exper.DBHelper;
/**
 * Servlet implementation class ResponseCreateClubServlet
 */
@WebServlet("/CheckResponseActNewsServlet")
public class ChectResponseActNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChectResponseActNewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cid=request.getParameter("cid");
		String flag=request.getParameter("flag");
		System.out.println("flag="+flag);
		Connection conn;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int id=0;
		try{
			conn=new DBHelper().getConn();
			String sql=null;
			if(flag.equals("1")){
				sql="select id from response_act where cid = '"+cid+"'";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()){
					id=rs.getInt("id");
				}
				sql="delete from response_act where id='"+id+"'";
				pstmt=conn.prepareStatement(sql);
				pstmt.execute();
				sql="update response_act set id=id-1 where id>'"+id+"'";
				pstmt=conn.prepareStatement(sql);
				pstmt.execute();
			}
			else if(flag.equals("2")){
				sql="select id from response_news where cid = '"+cid+"'";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()){
					id=rs.getInt("id");
				}
				sql="delete from response_news where id='"+id+"'";
				pstmt=conn.prepareStatement(sql);
				pstmt.execute();
				sql="update response_news set id=id-1 where id>'"+id+"'";
				pstmt=conn.prepareStatement(sql);
				pstmt.execute();
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("ListResponseActNewsServlet").forward(request, response);
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
