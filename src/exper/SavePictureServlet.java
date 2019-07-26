package exper;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.util.*;
public class SavePictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SavePictureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("GBK");
		InputStream stream=null;
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart==false)
			{
				response.sendRedirect("ListAct_stu.jsp");
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
						cname=Streams.asString(stream);
					}
					if("master".equalsIgnoreCase(name)) {
						master=Streams.asString(stream);
					}
					if("title".equalsIgnoreCase(name)) {
						title=Streams.asString(stream);
					}
					if("content".equalsIgnoreCase(name)) {
						content=Streams.asString(stream);
					}
					if("time".equalsIgnoreCase(name)) {
						time=Streams.asString(stream);
					}
	
				}else {
					String filename=item.getName();
					savePicture(cname,master,title,content,time,filename,stream,(int)size);
				}
			}
			response.sendRedirect("ListAct_stu.jsp");
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
			stmt=conn.prepareStatement("INSERT INTO club_activity VALUES(0,?,?,?,?,?,?,?)");
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

}
