package exper;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class GetPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPictureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		InputStream in=null;
		OutputStream out=null;
		try {
			String id=request.getParameter("id");
			conn=new DBHelper().getConn();
			stmt=conn.prepareStatement("SELECT filename,img FROM club_activity WHERE id=?");
			stmt.setString(1, id);
			rs=stmt.executeQuery();
			if(!rs.next()) return;
			String fname=rs.getString("filename");
			String contentType="image/jpeg";
			if(fname.endsWith(".gif")) contentType="image/gif";
			if(fname.endsWith(".png")) contentType="image/png";
			if(fname.endsWith(".bmp")) contentType="image/bmp";
			response.setContentType(contentType);
			out=response.getOutputStream();
			Blob blob=rs.getBlob("img");
			in=blob.getBinaryStream();
			byte[] buffer=new byte[8192];
			int length=-1;
			while((length=in.read(buffer))>0) {
				out.write(buffer,0,length);
			}
		}catch(Exception e)
    	{
    		return ;
    	}finally{
    		try {if (in!=null) in.close();}catch(Exception e1) {}
    		try {if (out!=null) out.close();}catch(Exception e1) {}
    		try {if (rs!=null) rs.close();}catch(Exception e1) {}
    		try {if (stmt!=null) stmt.close();}catch(Exception e1) {}
    		try {if (conn!=null) conn.close();}catch(Exception e1) {}
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
