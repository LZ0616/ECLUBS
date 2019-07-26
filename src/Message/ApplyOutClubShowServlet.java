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
import javax.servlet.http.HttpSession;

import exper.DBHelper;

@WebServlet("/ApplyOutClubShowServlet")
public class ApplyOutClubShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ApplyOutClubShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		int cid = Integer.parseInt(request.getParameter("cid"));
		String account=(String)session.getAttribute("account");
		
		Connection connection = new DBHelper().getConn();
		PreparedStatement statement = null;
		ResultSet set = null;
		ApplyOutClubBeen out = new ApplyOutClubBeen();
		
		String sql = "select * from student where account=?";
		try {
			statement=connection.prepareStatement(sql);
			statement.setString(1, account);
			set = statement.executeQuery();
			while (set.next()) {
				out.setAccount(set.getString("account"));
				out.setSname(set.getString("sname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql = "select cname from club_info where id=?";
		try {
			statement=connection.prepareStatement(sql);
			statement.setInt(1, cid);
			set =statement.executeQuery();
			while (set.next()) {
				out.setCid(cid);
				out.setCname(set.getString("cname"));
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
		
		
		request.setAttribute("list", out);
		request.getRequestDispatcher("ApplyOutClub.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
