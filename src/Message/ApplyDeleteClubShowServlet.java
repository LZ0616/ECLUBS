package Message;

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

@WebServlet("/ApplyDeleteClubShowServlet")
public class ApplyDeleteClubShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ApplyDeleteClubShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int cid = Integer.parseInt(request.getParameter("cid"));
		
		Connection connection = new DBHelper().getConn();
		PreparedStatement statement = null;
		ResultSet set = null;
		ApplyDeleteClubBeen del = new ApplyDeleteClubBeen();
		
		String sql = "select * from club_info where id=?";
		try {
			statement=connection.prepareStatement(sql);
			statement.setInt(1, cid);
			set =statement.executeQuery();
			while (set.next()) {
				del.setCid(cid);
				del.setCname(set.getString("cname"));
				del.setMaster(set.getString("master"));
			}
			set.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				set.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		request.setAttribute("list", del);
		request.getRequestDispatcher("ApplyDeleteClub.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
