package Message;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import exper.DBHelper;

/**
 * Servlet implementation class ApplyCreateClubDoServlet
 */
@WebServlet("/ApplyCreateClubDoServlet")
public class ApplyCreateClubDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyCreateClubDoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		InputStream stream = null;
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		String account=(String)session.getAttribute("account");
		
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart==false)
			{
				System.out.println("false");
				return;
			}
			String cname=null;
			String master=null;
			String intro = null;
			String rules = null;
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(new File(getServletContext().getRealPath("/WEB-INF/lib/")));
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> iter=(List<FileItem>)(upload.parseRequest(request));
			for(FileItem item:iter)
			{
				String name=item.getFieldName();
				stream=item.getInputStream();
				long size =item.getSize();
				if(item.isFormField()) {
					if("cname".equals(item.getFieldName())){
						cname =new String(item.getString().getBytes("iso-8859-1"),"utf-8");
						System.out.println("cname="+cname);
					}
					if("intro".equals(item.getFieldName())){
						intro =new String(item.getString().getBytes("iso-8859-1"),"utf-8");
						System.out.println("intro="+intro);
					}
					if("rules".equals(item.getFieldName())) {
						rules =new String(item.getString().getBytes("iso-8859-1"),"utf-8");
					}
				}else {
					String filename=item.getName();
					savePicture(account,cname,master,intro,rules,filename,stream,(int)size);
				}
			}
			response.sendRedirect("ListClubServlet");
		}catch(Exception e)
    	{
    		e.printStackTrace();
    	}finally{
    		try {if (stream!=null) stream.close();}catch(Exception e) {}
    	}
		//doGet(request, response);
	}
	private boolean savePicture(String account,String cname,String master,String intro,String rules,String filename , InputStream stream,int size) {
		Connection conn=null;
		PreparedStatement stmt=null;
		FileInputStream ins=null;
		ResultSet rs = null;
		
		try {
			conn=new DBHelper().getConn();
			
			int sum=0;
			String sql="select count(*) from check_creclub";
			try{
				stmt=conn.prepareStatement(sql);
				rs=stmt.executeQuery();
				while(rs.next()){
					sum+=rs.getInt(1);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			sql="select sname from student where account='"+account+"'";
			System.out.println("account="+account);
			try{
				stmt=conn.prepareStatement(sql);
				rs=stmt.executeQuery();
				if(rs.next()){
					master = rs.getString("sname");
					System.out.println("master="+master);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			stmt=conn.prepareStatement("INSERT INTO check_creclub VALUES(?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1, sum+1);
			stmt.setString(2, account);
			stmt.setString(3, cname);
			stmt.setString(4, master);
			stmt.setString(5, intro);
			stmt.setString(6, rules);
			stmt.setString(7, "1998-8-8");
			stmt.setString(8, filename);
			stmt.setBinaryStream(9, stream, stream.available());
			int n=stmt.executeUpdate();
			return(n==1);
		}catch(Exception e)
    	{
    		return false;
    	}finally{
    		try {if (ins!=null) ins.close();}catch(Exception e1) {}
    		try {if (stmt!=null) stmt.close();}catch(Exception e1) {}
    		try {if (conn!=null) conn.close();}catch(Exception e1) {}
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
