import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*; 
class SendMail extends Thread{
	String link,fname,email;
	public SendMail(String fname, String link, String email){
		this.link=link;
		this.fname=fname;
		this.email=email;
	}
	public void run(){
		
	  try{
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
			message.setSubject("Welcome to Child~Vaccination Care");
			
			
			
			message.setContent("Dear "+fname+",<br/><br/>Thank you registering for Child Vaccination.<br/><br/>Please click below to confirm your email addresss.<br/><br/><a href='"+link+"'>"+link+"</a><br/><br/>Your Login detail are as follow<br/><br/>User id : "+email+"<br/><br/>Please keep this information safe for future reference.<br/><br/>Thank You<br/><br/>Child Vaccination Care","text/html");
			
			Transport.send(message);
			System.out.println("Mail has been sent");
	  }
	  catch(Exception e){
		  System.out.println("In Exception "+e);
	  }
	}
}	