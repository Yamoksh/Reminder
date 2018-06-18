import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.Timer;
import java.util.TimerTask;
import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
class Task extends TimerTask{
	Connection c ;
	java.util.Date dt;
	SimpleDateFormat sdf;
	String today,date;
	static String[] v_code={"BIRTH","WEEK_6","WEEK_10","WEEK_14","MONTH_9","MONTH_12", "MONTH_15","MONTH_16TO18","MONTH_18","YEAR_2"};
	int cid;
	public Task(){
	 try{
		System.out.println("I am in Task cons");
		InitialContext ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("tindi");
		 c = ds.getConnection();	
		sdf  = new SimpleDateFormat("yyyy-MM-dd");
		
	 }
	 catch(Exception e){
		 e.printStackTrace();
	 }
	}
	//run is a abstract method that defines task performed at scheduling time
	public void run(){
	  try{
		java.util.Date tDate= new java.util.Date();
		today =sdf.format(tDate);
		Statement s = c.createStatement();
		ResultSet result = s.executeQuery("select * from child_vaccine");
		while(result.next()){
			 cid=result.getInt("c_id");
			 for(int i=0;i<v_code.length;i++){
				date = result.getString(v_code[i]);
				dt=sdf.parse(date);
				if(today.equals(sdf.format(dt))){
					MailNotify mt = new MailNotify(c,cid,v_code[i]);
					mt.start();
					break;
				}
			 }
		}	
	  }
	  catch(Exception e){
		System.out.println(""+e);  
	  }
	}
}
public class CheckMyDate extends Thread{
	public void run(){
		Timer timer =  new Timer();
		int delay = 1000; //delay for 1 min in millisec
		int period = 1000*60*1; //repeat every  1 min.
		timer.scheduleAtFixedRate(new Task(),delay,period);	
		
	}
}
class MailNotify extends Thread{
	private Connection c;
	int cid;
	String date,v_code;
	String v_name="";
	String v_age;
	public MailNotify(Connection c,int cid,String v_code){
		this.c =c;
		this.cid=cid;
		this.v_code=v_code;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
			java.util.Date dt = new java.util.Date();
			date=sdf.format(dt);
		}
		catch(Exception e){
			System.out.println("Caught exception in MailNotify Construtor "+e);
		}
	}
	public void run(){
		try{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from child_info where c_id="+cid);
			rs.next();
			String cname=rs.getString("c_name");
			String fname=rs.getString("f_name");
			String email=rs.getString("email");
			String gender=rs.getString("gender");
			System.out.println(cid+" "+fname+" "+date+" "+v_code);
			rs=s.executeQuery("select * from vaccine_info where v_code ='"+v_code+"'");
			while(rs.next()){
				v_age=rs.getString("age");
				v_name=v_name+" "+rs.getString("v_name")+" ,";
			}
			//send Mail Notification mail
			
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("vaccinationcare@gmail.com","8890841463");
				}
			});
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("vaccinationcare@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject("Child~Vaccination Care Reminder");
			
			
			
			message.setContent("Dear "+fname+",<br/><br/>This is Child~vaccination Care reminder. Today ( "+date+" ) is vaccination day for your "+gender+" "+cname+".<br/><br/><h2>Your Vaccination details are as follow</h2><br/><br/><h3>Vaccine Name  :  "+v_name+"<br/>Child age at :  "+v_age+"</h3><br/><br/><hr>Please keep this information safe for future reference.</hr><br/><br/>Thank You<br/><br/>Child Vaccination Care","text/html");
			
			Transport.send(message);
			System.out.println("Mail has been sent of checkMyDate");
		}
		catch(Exception e){
			System.out.println("run mehtod of MailNotify "+e);
		}
	}
	
}