package Student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UpdateStuDoServlet")
public class UpdateStuDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateStuDoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		String identity=(String)session.getAttribute("identity");
		String account=(String)session.getAttribute("account");
		
		SelfStuBean stu = new SelfStuBean();
		stu.setSname(request.getParameter("sname"));
		stu.setPassword(request.getParameter("password"));
		stu.setAge(Integer.valueOf(request.getParameter("age")));
		stu.setDep(request.getParameter("dep"));
		stu.setPhone(request.getParameter("phone"));
		stu.setAccount(account);
		StudentSer stuSer = new StudentSer();
		stuSer.updateStu(stu);
		if(identity=="Ñ§Éú")
		{
			request.getRequestDispatcher("SelfPageServlet").forward(request, response);
			
		}
		else
		{	
			request.getRequestDispatcher("ListStuServlet").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
