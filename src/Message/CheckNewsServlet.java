package Message;

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

import exper.DBHelper;

/**
 * Servlet implementation class CheckNewsServlet
 */
@WebServlet("/CheckNewsServlet")
public class CheckNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckNewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("GBK");
		String id = request.getParameter("id");
		System.out.println("id="+id);
		String con = request.getParameter("con");
		Connection conn = new DBHelper().getConn();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String control=null;
		int cid=0;
		String cname = null,master = null,title=null,content=null,time=null,filename=null,img=null;
		String sql="select * from check_news where id='"+id+"'";
		try{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				cid=rs.getInt("cid");
				cname=rs.getString("cname");
				master=rs.getString("master");
				title=rs.getString("title");
				content=rs.getString("content");
				time=rs.getString("time");
				filename=rs.getString("filename");
				img=rs.getString("img");
			}
			System.out.println("cid="+cid);
			System.out.println("title="+title);
			if(con.equals("1")){
				control="您申请发布的社团新闻"+title+"已被管理员同意发布。";
				sql="select count(*) from club_news";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				int sum=0;
				while(rs.next()){
					sum+=rs.getInt(1);
				}
				sql="insert into club_news (id,cname,master,title,content,cid,time,filename,img) values(?,?,?,?,?,?,?,?,?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, sum+1);
				pstmt.setString(2, cname);
				pstmt.setString(3, master);
				pstmt.setString(4, title);
				pstmt.setString(5, content);
				pstmt.setInt(6, cid);
				pstmt.setString(7, time);
				pstmt.setString(8, filename);
				pstmt.setString(9, img);
				pstmt.execute();
			}
			else if(con.equals("2")){
				control="您申请发布的社团新闻"+title+"已被管理员拒绝发布！";
			}
			sql="delete from check_news where id='"+id+"'";
			pstmt=conn.prepareStatement(sql);
			pstmt.execute();
			sql="update check_news set id=id-1 where id>'"+id+"'";
			pstmt=conn.prepareStatement(sql);
			pstmt.execute();
			
			try{
				int sum1=0;
				sql="select count(*) from response_news";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()){
					sum1+=rs.getInt(1);
				}
				System.out.println("control="+control);
				
				sql="insert into response_news (id,cid,content) values(?,?,?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, sum1+1);
				pstmt.setInt(2, cid);
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
		request.getRequestDispatcher("ShowApplyNewsServlet?con=1").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
