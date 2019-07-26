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
import javax.servlet.http.HttpSession;

import exper.DBHelper;
/**
 * Servlet implementation class ApplyJoinActServlet
 */
@WebServlet("/ApplyJoinActServlet")
public class ApplyJoinActServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyJoinActServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String aid = request.getParameter("aid");
		System.out.println("aid="+aid);
		HttpSession session = request.getSession(true);
		String account=(String)session.getAttribute("account");
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=null;
		String aclub=null,master=null,atitle=null,atime=null,sname=null;
		int cid = 0;
		try{
			conn=new DBHelper().getConn();
			sql="select * from club_activity where id ='"+aid+"'";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				cid=rs.getInt("cid");
				aclub = rs.getString("cname");
				master=rs.getString("master");
				atitle=rs.getString("title");
				atime=rs.getString("time");
			}
			System.out.println("title="+atitle);
			sql="select sname from student where account='"+account+"'";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				sname=rs.getString("sname");
			}
			System.out.println("sname="+sname);
			sql="select count(*) from check_joinact";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			int  sum=0;
			while(rs.next()){
				sum+=rs.getInt(1);
			}
			System.out.println("sum="+sum);
			sql="insert into check_joinact values(?,?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, sum+1);
			pstmt.setString(2, account);
			pstmt.setInt(3, cid);
			pstmt.setInt(4, Integer.valueOf(aid));
			pstmt.setString(5,sname);
			pstmt.setString(6, atitle);
			pstmt.setString(7, atime);
			pstmt.setString(8, aclub);
			pstmt.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
		request.getRequestDispatcher("ActivityDetailServlet?id="+aid).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
