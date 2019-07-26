package club;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exper.DBHelper;

@WebServlet("/DetailsNewsServlet")
public class DetailsNewsServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public DetailsNewsServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession(true);
		String account = (String)session.getAttribute("account");
		String identity = (String)session.getAttribute("identity");
		String master=null;
		try 
		{
			Connection conn = new DBHelper().getConn();
			Statement stmt = conn.createStatement();
			String sql = "select id,cname,master,title,content,time from club_news where id='"+id+"' ";
			ResultSet rs = stmt.executeQuery(sql);
			List<NewsListBean> list = new ArrayList<>();
			while(rs.next())
			{
				NewsListBean act = new NewsListBean();
				act.setId(rs.getInt("id"));
				act.setCname(rs.getString("cname"));
				act.setMaster(rs.getString("master"));
				act.setTitle(rs.getString("title"));
				act.setContent(rs.getString("content"));
				act.setTime(rs.getString("time"));
				list.add(act);
			}
			request.setAttribute("list", list);						
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		if(identity=="№ЬАн")
			request.getRequestDispatcher("DetailsNews_admin.jsp").forward(request, response);
		else
			request.getRequestDispatcher("DetailsNews_stu.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
