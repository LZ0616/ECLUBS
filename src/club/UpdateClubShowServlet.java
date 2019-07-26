package club;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import exper.DBHelper;

@WebServlet("/UpdateClubShowServlet")
public class UpdateClubShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateClubShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id= Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		ListClubBean st = new ListClubBean();
		Connection conn = new DBHelper().getConn();
		PreparedStatement stat = null;
		ResultSet set = null;
		
		String sql ="select * from club_info where id=?";
		try {
			stat=conn.prepareStatement(sql);
			stat.setInt(1, id);
			set =stat.executeQuery();
			while (set.next()) {
				st.setId(set.getInt("id"));
				st.setCname(set.getString("cname"));
				st.setMaster(set.getString("master"));
				st.setTime(set.getString("time"));
				st.setIntro(set.getString("intro"));
				st.setRules(set.getString("rules"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				set.close();
				stat.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("club", st);
		request.getRequestDispatcher("club_update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
