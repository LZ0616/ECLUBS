package exper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetActPicture
 */
@WebServlet("/GetActPicture")
public class GetActPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetActPictureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection myconn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		InputStream in=null;
		OutputStream out=null;
		try {
			String id=request.getParameter("id");
			myconn = new DBHelper().getConn();
			stmt=myconn.prepareStatement("SELECT filename,img FROM club_activity WHERE id=?");
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
    		try {if (myconn!=null) myconn.close();}catch(Exception e1) {}
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
