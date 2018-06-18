import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.sql.*;
import javax.sql.*;
import java.text.SimpleDateFormat;    
import java.util.*; 
import javax.naming.*;

public class RidActivator extends HttpServlet{
	int days[]={0,42,70,98,274,365,457,578,608,730};
	String dateString[] = new String[10];
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		ServletOutputStream out = res.getOutputStream();
		res.setContentType("text/html");
		String rid = req.getParameter("rid");
		try{
			out.println("In ridActivator<br/><br/>");
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("tindi");
			Connection con = ds.getConnection();
			Statement s = con.createStatement();
			ResultSet result = s.executeQuery("select * from login_info where rid= '"+rid+"'");
		if(result.next()){
			int cid = result.getInt("cid");
			int isActivate=result.getInt("isActivate");
			

			s.executeQuery("UPDATE login_info SET ISACTIVATE = 1 WHERE rid= '"+rid+"'");
			
			out.println("cid : "+cid+"<br/><br/>");
			ResultSet result1 = s.executeQuery("select dob from child_info where c_id= '"+cid+"'");
			result1.next();
			
			String dob = result1.getString("dob");
			
			SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dt=sdf.parse(dob);
			
			Calendar c = Calendar.getInstance();
			c.setTime(dt);
			out.println(dt+"");
			java.util.Date dt1;
			for(int i=0;i<dateString.length;i++){
				c.add(Calendar.DATE, days[i]);
				dt1=c.getTime();
				dateString[i]=sdf.format(dt1).toString();
				out.println("<br/><br/> "+dateString[i]);
				c.setTime(dt);
			}
		s.executeQuery("insert into child_vaccine values("+cid+",to_date('"+dateString[0]+"','yyyy-MM-dd'),to_date('"+dateString[1]+"','yyyy-MM-dd'),to_date('"+dateString[2]+"','yyyy-MM-dd'),to_date('"+dateString[3]+"','yyyy-MM-dd'),to_date('"+dateString[4]+"','yyyy-MM-dd'),to_date('"+dateString[5]+"','yyyy-MM-dd'),to_date('"+dateString[6]+"','yyyy-MM-dd'),to_date('"+dateString[7]+"','yyyy-MM-dd'),to_date('"+dateString[8]+"','yyyy-MM-dd'),to_date('"+dateString[9]+"','yyyy-MM-dd'))");	
			res.sendRedirect("activated.html");
		}
		else{
			res.sendRedirect("againRegister.html");
		}
		}
		catch(Exception e){
			res.sendRedirect("activated.html");
		}
	}
}