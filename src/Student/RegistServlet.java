package Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exper.DBHelper;


/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
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
		String sno =  String.valueOf(request.getParameter("sno"));
		String sname =  String.valueOf(request.getParameter("sname"));
		String sex =  String.valueOf(request.getParameter("sex"));
		String age =  String.valueOf(request.getParameter("age"));
		String dep =  String.valueOf(request.getParameter("dep"));
		String club1 = "null";
		String club2 = "null";
		String club3 = "null";
		String phone =  String.valueOf(request.getParameter("phone"));
	
		try {
			
			Connection conn = new DBHelper().getConn();	
			ResultSet rs = null, rs1=null, rs2=null;
			
			String sql1 = "select * from student where account=?";
			PreparedStatement pstmt=conn.prepareStatement(sql1);
			pstmt.setString(1, account); 
			rs1 = pstmt.executeQuery();
			
			String sql2 = "select * from student where sno=?";
			pstmt=conn.prepareStatement(sql2);
			pstmt.setString(1, sno); 
			rs2 = pstmt.executeQuery();
			
			if(rs1.next()){
				request.setAttribute("error","注册失败！帐号已被占用，请重新输入账号！");
				request.getRequestDispatcher("login.jsp").forward(request,response);
			}
			else if(rs2.next()){
				request.setAttribute("error","注册失败！学号已注册，请重新输入学号！");
				request.getRequestDispatcher("login.jsp").forward(request,response);
			}
			else{
				int sum = 0;
				String sql = "select count(*) from student";
				try { 
					pstmt = conn.prepareStatement(sql); 
					rs = pstmt.executeQuery();
					while (rs.next()) { 
						sum += rs.getInt(1); 
					} 
				} catch (Exception e) { 
					e.printStackTrace(); 
				} 
				
				sql = "insert into student(id,account,password,sno,sname,sex,age,dep,club1,club2,club3,phone) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			
				pstmt = conn.prepareStatement(sql);
			
				pstmt.setInt(1, sum+1); 
				pstmt.setString(2, account); 	
				pstmt.setString(3, pwd); 	
				pstmt.setString(4, sno); 
				pstmt.setString(5, sname); 	
				pstmt.setString(6, sex); 	
				pstmt.setString(7, age); 	
				pstmt.setString(8, dep); 
				pstmt.setString(9, club1); 
				pstmt.setString(10, club2); 
				pstmt.setString(11, club3); 
				pstmt.setString(12, phone); 	
				pstmt.execute();
			}
			pstmt.close();
			conn.close(); 
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 request.getRequestDispatcher("login.jsp").forward(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
