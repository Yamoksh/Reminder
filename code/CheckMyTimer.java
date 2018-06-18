import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.Timer;
import java.util.TimerTask;
class RemoveEntry extends TimerTask{
	static Statement s,s2;
	static Connection con;
	public RemoveEntry(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","yam123");
			s = con.createStatement();
			s2 = con.createStatement();
		}
		catch(Exception e){
			System.out.println("Exception "+e);
		}
	}
	public void run(){
		System.out.println("Remove Entry has been started");
		int cid;
		try{
			ResultSet result = s.executeQuery("select * from login_info where isActivate= 0");
			while(result.next()){
				System.out.println("email : "+result.getString("email"));
				System.out.println("timeStamp : "+result.getLong("timeStamp"));
				Long time=result.getLong("timeStamp")+1000*60*60*24; // after 24 hoursss
				Long  time1=System.currentTimeMillis();
				if(time<time1){
					cid=result.getInt("cid");
					s2.executeQuery("delete from login_info where cid="+cid);
					s2.executeQuery("delete from child_info where c_id="+cid);
					System.out.println("Entry has deleted");
				}
				else{
					System.out.println("No entry for deletion");
				}
			}
		}
		catch(Exception e){
			System.out.println("Exception "+e);
		}
		System.out.println("out run method");
	}
}
public class CheckMyTimer extends Thread{
	public void run(){
		Timer timer =  new Timer();
		int delay = 1000; //delay for 5 sec. in millisec
		int period = 1000*60*5; //repeat every  5 min.
		timer.scheduleAtFixedRate(new RemoveEntry(),delay,period);	
	}
	
	public static void main(String... s){
		CheckMyTimer ct = new CheckMyTimer();
		ct.start();
	}
}