package club;

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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import exper.DBHelper;


/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/AddNewsServlet")
public class AddNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("GBK");
		InputStream stream=null;
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart==false)
			{
				response.sendRedirect("AddNews.jsp");
				return;
			}
			String cname=null;
			String master = null ;
			String title = null;
			String content = null;
			String time = null;
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
					if("cname".equalsIgnoreCase(name)) {
						cname=new String(item.getString().getBytes("iso-8859-1"),"utf-8");
					}
					if("master".equalsIgnoreCase(name)) {
						master=new String(item.getString().getBytes("iso-8859-1"),"utf-8");
					}
					if("title".equalsIgnoreCase(name)) {
						title=new String(item.getString().getBytes("iso-8859-1"),"utf-8");;
					}
					if("content".equalsIgnoreCase(name)) {
						content =new String(item.getString().getBytes("iso-8859-1"),"utf-8");
					}
					if("time".equalsIgnoreCase(name)) {
						time=new String(item.getString().getBytes("iso-8859-1"),"utf-8");
					}
	
				}else {
					String filename=item.getName();
					savePicture(cname,master,title,content,time,filename,stream,(int)size);
				}
			}
			response.sendRedirect("NewsListServlet");
		}catch(Exception e)
    	{
    		e.printStackTrace();
    	}finally{
    		try {if (stream!=null) stream.close();}catch(Exception e) {}
    	}
		//doGet(request, response);
	}
	private boolean savePicture(String cname,String master,String title,String content,String time ,String filename , InputStream stream,int size) {
		Connection conn=null;
		PreparedStatement stmt=null;
		FileInputStream ins=null;
		try {
			if(cname==null)
				cname=filename;
			conn=new DBHelper().getConn();
			stmt=conn.prepareStatement("INSERT INTO club_news VALUES(0,?,?,?,?,?,?,?)");
			stmt.setString(1, cname);
			stmt.setString(2, master);
			stmt.setString(3, title);
			stmt.setString(4, content);
			stmt.setString(5, time);
			stmt.setString(6, filename);
			stmt.setBinaryStream(7, stream, stream.available());
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
