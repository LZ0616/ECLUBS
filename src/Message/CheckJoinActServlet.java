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
 * Servlet implementation class CheckJoinActServlet
 */
@WebServlet("/CheckJoinActServlet")
public class CheckJoinActServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckJoinActServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("GBK");
		String aid = request.getParameter("id");
		System.out.println("aid="+aid);
		String con = request.getParameter("con");
		System.out.println("con="+con);
		Connection conn = new DBHelper().getConn();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String control=null;
		int id=0,cid=0;
		String cname = null,title=null,content=null,time=null,account=null,sname=null;
		String sql="select * from check_joinact where aid='"+aid+"'";
		try{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				id = rs.getInt("id");
				account=rs.getString("account");
				cid=rs.getInt("cid");
				sname=rs.getString("sname");
				title=rs.getString("atitle");
				time=rs.getString("atime");
				cname=rs.getString("aclub");
			}
			System.out.println("account="+account);
			if(con.equals("1")){
				control="您申请参加的 "+cname+" 的社团活动 "+title+" 已被该社社长同意参加。";
				sql="select count(*) from my_activity";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				int sum=0;
				while(rs.next()){
					sum+=rs.getInt(1);
				}
				System.out.println("sum="+sum);
				sql="insert into my_activity (id,account,sname,aid,atitle,aclub,atime,cid) values(?,?,?,?,?,?,?,?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, sum+1);
				pstmt.setString(2, account);
				pstmt.setString(3, sname);
				pstmt.setInt(4, Integer.valueOf(aid));
				pstmt.setString(5, title);
				pstmt.setString(6,cname);
				pstmt.setString(7, time);
				pstmt.setInt(8, cid);
				pstmt.execute();
			}
			else if(con.equals("2")){
				control="您申请参加的 "+cname+" 的社团活动 "+title+" 已被该社社长拒绝了。";
			}
			
			sql="delete from check_joinact where id='"+id+"'";
			pstmt=conn.prepareStatement(sql);
			pstmt.execute();
			sql="update check_joinact set id=id-1 where id>'"+id+"'";
			pstmt=conn.prepareStatement(sql);
			pstmt.execute();
			
			try{
				int sum1=0;
				sql="select count(*) from response_joinact";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()){
					sum1+=rs.getInt(1);
				}
				System.out.println("control="+control);
				
				sql="insert into response_joinact (id,account,content) values(?,?,?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, sum1+1);
				pstmt.setString(2, account);
				pstmt.setString(3, control);
				pstmt.execute();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		//doGet(request, response);
		request.getRequestDispatcher("ShowApplyJoinActServlet?cid="+cid).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
