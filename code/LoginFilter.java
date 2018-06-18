import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;
import java.text.SimpleDateFormat;

		
public class LoginFilter extends HttpServlet{
	ServletOutputStream out;
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		ServletOutputStream out = res.getOutputStream();
		this.out=out;
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String checkbox = req.getParameter("checkbox");
		try{
				res.setContentType("text/html");
				InitialContext ctx = new InitialContext();
				DataSource ds = (DataSource)ctx.lookup("tindi");
				Connection c = ds.getConnection();
				Statement s = c.createStatement();
				ResultSet result = s.executeQuery("select * from login_info where email='"+email+"' and password='"+password+"'");
				
				if(result.next()){
					if(checkbox!=null){
						Cookie email_cookie = new Cookie("email_cookie",email);
						Cookie pass_cookie = new Cookie("password_cookie",password);
						email_cookie.setMaxAge(60*60*24);
						pass_cookie.setMaxAge(60*60*24);
						res.addCookie(email_cookie);
						res.addCookie(pass_cookie);
					}
					HttpSession session = req.getSession();
					int cid=result.getInt("cid");
					session.setAttribute("cid",cid);
					session.setAttribute("password",password);
					
					ResultSet rs1 = s.executeQuery("select * from child_info where c_id = "+cid);
					rs1.next();
					
					String fname=rs1.getString("f_name");
					String cname=rs1.getString("c_name");
					String dob=rs1.getString("dob");
					String gender=rs1.getString("gender");
					String email1=rs1.getString("email");
					String mobile=rs1.getString("mob");
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date dt = sdf.parse(dob);
					long ldt = dt.getTime();
					dt = new java.util.Date(ldt);
					sdf = new SimpleDateFormat("dd-MM-yyyy");
					dob=sdf.format(dt);
					session.setAttribute("cname",cname);
					session.setAttribute("fname",fname);
					session.setAttribute("dob",dob);
					session.setAttribute("gender",gender);
					session.setAttribute("email",email1);
					session.setAttribute("mobile",mobile);
					
					ResultSet rs3 = s.executeQuery("select * from child_vaccine where c_id = "+cid);
					rs3.next();
					String v1 = rs3.getString("birth");
					String v2 = rs3.getString("week_6");
					String v3 = rs3.getString("week_10");
					String v4 = rs3.getString("week_14");
					String v5 = rs3.getString("month_9");
					String v6 = rs3.getString("month_12");
					String v7 = rs3.getString("month_15");
					String v8 = rs3.getString("MONTH_16TO18");
					String v9 = rs3.getString("MONTH_18");
					String v10 = rs3.getString("YEAR_2");
					session.setAttribute("v1",v1.substring(0,11));
					session.setAttribute("v2",v2.substring(0,11));
					session.setAttribute("v3",v3.substring(0,11));
					session.setAttribute("v4",v4.substring(0,11));
					session.setAttribute("v5",v5.substring(0,11));
					session.setAttribute("v6",v6.substring(0,11));
					session.setAttribute("v7",v7.substring(0,11));
					session.setAttribute("v8",v8.substring(0,11));
					session.setAttribute("v9",v9.substring(0,11));
					session.setAttribute("v10",v10.substring(0,11));
					rs1.close();
					
					rs3.close();
					result.close();
					RequestDispatcher rd = req.getRequestDispatcher("SkyTabs.jsp");
					rd.include(req,res);
				}
				else{
					HttpSession session = req.getSession();
					session.setAttribute("wrong_uname_pass","1");
					res.sendRedirect("login.jsp");
				}
			
		}
		catch(Exception e){
			out.println("<br/>In exception "+e);
			e.printStackTrace();
			//res.sendRedirect("login.jsp");
		}
	}
}