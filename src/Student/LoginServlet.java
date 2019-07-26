package Student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exper.DBHelper;

import java.sql.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	PreparedStatement statement = null;	
	ResultSet set = null;	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String account =  String.valueOf(request.getParameter("account"));
		String pwd =  String.valueOf(request.getParameter("password"));
		String status =  String.valueOf(request.getParameter("status"));
		
		HttpSession session = request.getSession(true);
		session.setAttribute("account", account);
		String identity;
		try{	
			Connection myconn = new DBHelper().getConn();	
			
			if(myconn!=null){
				System.out.println("���ݿ����ӳɹ���");
			}else{
				System.out.println("���ݿ�����ʧ�ܣ�");
			}
			String sql="select * from student where account='"+account+"'and password='"+pwd+"'";
			Statement stmt=myconn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			if(status.equals("admin")&&account.equals("admin")&&pwd.equals("admin")){
				identity="����";
				session.setAttribute("identity", identity);
				request.setAttribute("status",status);
				request.getRequestDispatcher("IndexServlet").forward(request,response);
				System.out.println("��¼�ɹ�");
			}
			else if(rs.next()&&status.equals("student")){	
				identity="ѧ��";
				System.out.println(identity);
				session.setAttribute("identity", identity);
				request.getRequestDispatcher("IndexServlet").forward(request,response);
				System.out.println("��¼�ɹ�");
				System.out.println(account);
			}
			else{
				request.setAttribute("error1","�û��������ڻ��������");
				request.getRequestDispatcher("login.jsp").forward(request,response);
			}
			myconn.close();
		}catch(Exception e){
			e.printStackTrace();
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
