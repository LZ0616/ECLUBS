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

import exper.DBHelper;

/**
 * Servlet implementation class ShowApplyJoinActServlet
 */
@WebServlet("/ShowApplyJoinActServlet")
public class ShowApplyJoinActServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowApplyJoinActServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cid = request.getParameter("id");
		try {
			Connection myconn = new DBHelper().getConn();
			Statement stmt = myconn.createStatement();	
			String sql = "select * from check_joinact where cid='"+cid+"'";
			ResultSet rs = stmt.executeQuery(sql);
			List<ApplyJoinActBean> list = new ArrayList<>();		
			while(rs.next()){								
				ApplyJoinActBean actl = new ApplyJoinActBean();					
				actl.setId(rs.getInt("id"));
				actl.setAccount(rs.getString("account"));
				actl.setCid(rs.getInt("cid"));
				actl.setAid(rs.getInt("aid"));
				actl.setSname(rs.getString("sname"));
				actl.setAtitle(rs.getString("atitle"));
				actl.setTime(rs.getString("atime"));
				actl.setAclub(rs.getString("aclub"));
				list.add(actl);
			}
			request.setAttribute("list", list); 
			
			rs.close();									
			stmt.close();									
			myconn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
			request.getRequestDispatcher("ShowApplyJoinAct.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
