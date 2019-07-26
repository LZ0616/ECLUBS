package Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import club.ActListBean;
/**
 * Servlet implementation class JoinActServlet
 */
@WebServlet("/JoinActServlet")
public class JoinActServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     String account;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinActServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sql;
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		HttpSession session = request.getSession(true);
		account=(String)session.getAttribute("account");
		String sname = null,atitle = null,aclub=null,atime=null;;
		int sno = 0,aid = 0,cid=0;
		System.out.println("hello");
		try{
			Connection myconn=new DBHelper().getConn();
			ResultSet rs=null;
			sql="select count(*) from my_activity";
			int sum=0;
			PreparedStatement stmt=myconn.prepareStatement(sql);
			try{
				rs=stmt.executeQuery(sql);
				while(rs.next()){
					sum+=rs.getInt(1);
				}
			}catch(Exception e){
					e.printStackTrace();
				}
			
			sql="select sno,sname from student where account='"+account+"'";
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				sno=rs.getInt("sno");
				sname=rs.getString("sname");
				System.out.println(sno+sname);
			}
			sql="select id,cname,title,time from club_activity where id='"+id+"'";
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				aid=rs.getInt("id");
				aclub=rs.getString("cname");
				atitle=rs.getString("title");
				atime=rs.getString("time");
				System.out.println(aid+atitle+aclub);
			}
			sql="select id from club_info where cname='"+aclub+"'";
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				cid=rs.getInt("id");
				System.out.println(cid);
			}
			sql="insert into my_activity(id,sno,sname,aid,atitle,aclub,atime,cid) values(?,?,?,?,?,?,?,?)";
			stmt=myconn.prepareStatement(sql);
			stmt.setInt(1,sum+1);
			stmt.setInt(2, sno);
			stmt.setString(3, sname);
			stmt.setInt(4, aid);
			stmt.setString(5, atitle);
			stmt.setString(6, aclub);
			stmt.setString(7, atime);
			stmt.setInt(8, cid);
			stmt.execute();
			rs.close();
			stmt.cancel();
			myconn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		request.getRequestDispatcher("").forward(request, response);
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
