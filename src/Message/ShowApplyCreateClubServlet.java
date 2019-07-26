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
 * Servlet implementation class ShowApplyCreateClubServlet
 */
@WebServlet("/ShowApplyCreateClubServlet")
public class ShowApplyCreateClubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowApplyCreateClubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		try {
			Connection myconn = new DBHelper().getConn();
			Statement stmt = myconn.createStatement();	
			String sql = "select * from check_creclub";
			ResultSet rs = stmt.executeQuery(sql);
			List<ApplyCreateClubBean> list = new ArrayList<>();		
			while(rs.next()){								
				ApplyCreateClubBean ccb = new ApplyCreateClubBean();					
				ccb.setId(rs.getInt("id"));
				ccb.setAccount(rs.getString("account"));
				ccb.setCname(rs.getString("cname"));
				ccb.setMaster(rs.getString("master"));
				ccb.setIntro(rs.getString("intro"));
				ccb.setRules(rs.getString("rules"));
				ccb.setTime(rs.getString("time"));
				ccb.setFilename(rs.getString("filename"));
				list.add(ccb); 							
			}
			request.setAttribute("list", list); 
			
			rs.close();									
			stmt.close();									
			myconn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
			request.getRequestDispatcher("ShowApplyCreateClub.jsp").forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
