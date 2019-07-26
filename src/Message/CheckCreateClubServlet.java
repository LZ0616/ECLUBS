package Message;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exper.DBHelper;

/**
 * Servlet implementation class CheckCreateClubServlet
 */
@WebServlet("/CheckCreateClubServlet")
public class CheckCreateClubServlet extends HttpServlet {
   
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCreateClubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		    System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		request.setCharacterEncoding("GBK");
		String id = request.getParameter("id");
		String con = request.getParameter("con");
		Connection conn = new DBHelper().getConn();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String account=null;
		String control=null;
		String cname = null,master = null,intro=null,rules=null,time=df.format(new Date()),filename=null,img=null;
		int sum_member = 1;
		int sum_act = 0;
		String sql="select * from check_creclub where id='"+id+"'";
		Blob blob=null;
		InputStream stream=null;
		try{
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				account=rs.getString("account");
				cname=rs.getString("cname");
				master=rs.getString("master");
				intro=rs.getString("intro");
				rules=rs.getString("rules");
				//time=rs.getString("time");
				filename=rs.getString("filename");
				blob=rs.getBlob("img");
			}
			stream=blob.getBinaryStream();
			if(con.equals("1")){
				control="您申请创建的社团 "+cname+" 已被管理员审核同意，快去发展您的社团哦！";
				sql="select count(*) from club_info";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				int sum=0;
				while(rs.next()){
					sum+=rs.getInt(1);
				}
				sql="insert into club_info (id,cname,account,master,time,intro,rules,sum_member,sum_act,filename,img) values(?,?,?,?,?,?,?,?,?,?,?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, sum+1);
				pstmt.setString(2, cname);
				pstmt.setString(3, account);
				pstmt.setString(4, master);
				pstmt.setString(5, time);
				pstmt.setString(6, intro);
				pstmt.setString(7, rules);
				pstmt.setInt(8, sum_member);
				pstmt.setInt(9, sum_act);
				pstmt.setString(10, filename);
				pstmt.setBinaryStream(11, stream,stream.available());
				pstmt.execute();
				
				sql="select count(*) from club_member";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				int sum1=0;
				while(rs.next()){
					sum1+=rs.getInt(1);
				}
				sql="insert into club_member(id,cid,cname,master,account,sname,pos,date) values(?,?,?,?,?,?,?,?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, sum1+1);
				pstmt.setInt(2, sum+1);
				pstmt.setString(3, cname);
				pstmt.setString(4, master);
				pstmt.setString(5, account);
				pstmt.setString(6, master);
				pstmt.setString(7, "社长");
				pstmt.setString(8, df.format(new Date()));
				pstmt.execute();
			}
			else if(con.equals("2")){
				control="您申请创建的社团 "+cname+" 已被管理员审核失败，不要灰心呢！";
			}
			sql="delete from check_creclub where id='"+id+"'";
			pstmt=conn.prepareStatement(sql);
			pstmt.execute();
			sql="update check_creclub set id=id-1 where id>'"+id+"'";
			pstmt=conn.prepareStatement(sql);
			pstmt.execute();
			
			try{
				int sum1=0;
				sql="select count(*) from response_creclub";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()){
					sum1+=rs.getInt(1);
				}
				System.out.println("control="+control);
				
				sql="insert into response_creclub (id,account,content) values(?,?,?)";
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
		request.getRequestDispatcher("ShowApplyCreateClubServlet?con=1").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
