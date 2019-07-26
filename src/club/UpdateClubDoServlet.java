package club;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exper.DBHelper;

@WebServlet("/UpdateClubDoServlet")
public class UpdateClubDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateClubDoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id= Integer.parseInt(request.getParameter("id"));
		String cname = String.valueOf(request.getParameter("cname"));
		String intro = String.valueOf(request.getParameter("intro"));
		String rules = String.valueOf(request.getParameter("rules"));
		
		Connection conn = new DBHelper().getConn();
		PreparedStatement stat = null;
		
		try {
			try{
				String sql1 = "select * from club_info where cname=?";
				PreparedStatement pstmt=conn.prepareStatement(sql1);
				pstmt.setString(1, cname); 
				ResultSet rs = pstmt.executeQuery();
			
				if(rs.next()){
					int id1 = rs.getInt("id");
					System.out.println(id);
					System.out.println(id1);
					
					if(id!=id1){
						request.setAttribute("error","修改失败！社团名已被占用，请重新输入社团名！");
						request.getRequestDispatcher("UpdateClubShowServlet?id="+id).forward(request,response);
					}
				}
				
				rs.close();
				pstmt.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			String sql ="update club_info set cname=?,intro=?,rules=? where id=?";
			stat=conn.prepareStatement(sql);
			stat.setString(1, cname);
			stat.setString(2, intro);
			stat.setString(3, rules);
			stat.setInt(4, id);
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try{
			String sql ="update club_activity set cname=? where cid=?";
			stat=conn.prepareStatement(sql);
			stat.setString(1, cname);
			stat.setInt(2, id);
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try{
			String sql ="update club_news set cname=? where cid=?";
			stat=conn.prepareStatement(sql);
			stat.setString(1, cname);
			stat.setInt(2, id);
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("DetailsClubServlet?id="+id).forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
