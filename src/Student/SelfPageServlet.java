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

import club.ActListBean;
import exper.DBHelper;

/**
 * Servlet implementation class SelfPageServlet
 */
@WebServlet("/SelfPageServlet")
public class SelfPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String identity;
	String account;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelfPageServlet() {
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
		String sname=null;
		try{
			Connection myconn = new DBHelper().getConn();
			Statement stmt = myconn.createStatement();
			String sql;
			if(identity=="学生"){
				sql="select * from student where account='"+account+"'";
				ResultSet rs=stmt.executeQuery(sql);
				List<SelfStuBean> list = new ArrayList<>();		
				while(rs.next()){								
					SelfStuBean stu = new SelfStuBean();					
					stu.setId(rs.getInt("id"));
					stu.setAccount(rs.getString("account"));
					stu.setPassword(rs.getString("password"));
					stu.setSno(rs.getInt("sno"));
					stu.setSname(rs.getString("sname"));
					stu.setSex(rs.getString("sex"));
					stu.setAge(rs.getInt("age"));
					stu.setDep(rs.getString("dep"));
					stu.setPhone(rs.getString("phone"));
					list.add(stu); 							
				}
				request.setAttribute("list", list);
				
				sql="select account,sname,aid,atitle,aclub,atime,cid from my_activity where account='"+account+"'";
				stmt=myconn.createStatement();
				rs=stmt.executeQuery(sql);
				List<SelfActBean> stuact=new ArrayList<>();
				while(rs.next()){
					SelfActBean  act=new SelfActBean();
					act.setSname(rs.getString("sname"));
					act.setAid(rs.getInt("aid"));
					act.setAtitle(rs.getString("atitle"));
					act.setAclub(rs.getString("aclub"));
					act.setAtime(rs.getString("atime"));
					act.setCid(rs.getInt("cid"));
					stuact.add(act);
				}
				request.setAttribute("stuact", stuact);
				
				sql="select sname from student where account='"+account+"'";
				stmt=myconn.createStatement();
				rs=stmt.executeQuery(sql);
				if(rs.next()){
					sname=rs.getString("sname");
				}
				sql="select id,cid,cname,master,sname from club_member where sname='"+sname+"'";
				rs=stmt.executeQuery(sql);
				List<ClubMemberBean> c = new ArrayList<>();
				while(rs.next()){
					ClubMemberBean cl=new ClubMemberBean();
					cl.setId(rs.getInt("id"));
					cl.setCid(rs.getInt("cid"));
					cl.setCname(rs.getString("cname"));
					cl.setMaster(rs.getString("master"));
					cl.setSname(rs.getString("sname"));
					c.add(cl);
				}
				request.setAttribute("sclub", c);
				rs.close();									
				stmt.close();									
				myconn.close();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		if(identity.equals("学生")){
			System.out.println(identity);
			request.getRequestDispatcher("self_stu.jsp").forward(request, response);
		}
		else if(identity.equals("管理")){
			System.out.println(identity);
			request.getRequestDispatcher("self_admin.jsp").forward(request, response);
		}
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
