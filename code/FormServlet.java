import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;


		
public class FormServlet extends HttpServlet{
	ServletOutputStream out;
	public void init(ServletConfig con){
		System.out.println("check Update has been started");
		CheckMyDate c =new CheckMyDate();
		CheckMyTimer ct =new CheckMyTimer();
		c.start();
		ct.start();
	}
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		ServletOutputStream out = res.getOutputStream();
		this.out=out;
		res.setContentType("text/html");
		out.println("<html><head><title>Basic Form Process Output</title></head>");
		out.println("<body>");
		out.println("<h1>Here is your form Details</h1>");
		//extract the form data here
		String cname = req.getParameter("cname");
		String gender = req.getParameter("gender");
		String  dob= req.getParameter("dob");
		String  fname= req.getParameter("fname");
		String  phone= req.getParameter("phone");
		String  email= req.getParameter("email");
		String  password= req.getParameter("password");
		out.println("<br>updated");
		out.println("cname "+cname);
		out.println("gender "+gender);
		out.println("dob "+dob);
		out.println("fname "+fname);
		out.println("phone "+phone);
		out.println("email "+email);
		out.println("password "+password);
		out.println("</body></html>");
		
	try{
		
		InitialContext ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("tindi");
		Connection con = ds.getConnection();
		Statement s = con.createStatement();
		ResultSet result = s.executeQuery("select * from child_info");
		int c_id=10;
			while(result.next()){
				c_id=result.getInt(1);
				out.println("<br/>Value of cid "+c_id);
			}	
			c_id++;
			out.println("<br/>Value of cid "+c_id);
			
		s.executeQuery("insert into child_info(c_id,c_name,dob,gender,f_name,MOB,email) values("+c_id+",'"+cname+"',to_date('"+dob+"','MM/DD/YYYY')"+",'"+gender+"', '"+fname+"','"+phone+"','"+email+"')");
		
		PreparedStatement ps1 = con.prepareStatement("insert into login_info(CID,email,password,rid,isActivate,timeStamp) values(?,?,?,?,?,?)");
		ps1.setInt(1,c_id);
		ps1.setString(2,email);
		ps1.setString(3,password);
		
		UUID rid = UUID.randomUUID();
		ps1.setString(4,rid.toString());
		ps1.setInt(5,0);
		long timeStamp = System.currentTimeMillis();
		ps1.setLong(6,timeStamp);
		ps1.executeUpdate();
		ps1.close();
		
		//link creation
		String link="http://localhost:7002/wp1/activate?rid="+rid;
		
		SendMail sm = new SendMail(fname,link,email);
		sm.start();
		out.println("<a href='"+link+"'>"+link+"</a>");
		res.sendRedirect("rsuccess.html");
	}
	catch(Exception e){
		res.sendRedirect("register.html");
		out.println("<br/>In exception "+e);
	}
	}
}