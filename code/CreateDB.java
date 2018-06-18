import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
// insert into my values(Date '2012-11-13');
class CreateDB{
	static String[][] V_Name ={{"BCG","OPVO","HepB1"},{"DTwP1/DTaP1","OPV1*/OPV1+IPV1","Hib1","HepB2","Rotavirus 1 *#","PCV1"},{"DTwP2/DTaP2","OPV2*/OPV2+IPV2","Hib 2","Rotavirus 2","PCV 2"},{"DTwP3/DTaP3","OPV3*/OPV3+IPV3","Hib3","Rotavirus 3","HepB3 **","PCV3"},{"Measles"},{"Hepatitis A1"},{"MMR1","Varivella","PCV Booster"},{"DTwP B1/DTaP B1","OPV4*/OPV4 + IPVB1","Hib booster"},{"Hepatitis A 2"},{"Typhoid 1#"}};
	static String[] age={"Birth","6 Weeks","10 Weeks","14 Weeks","9 Month","12 Month","15 Month","16 to 18 months","18 Months","2 years"};
	static String[] v_code={"BIRTH","WEEK_6","WEEK_10","WEEK_14","MONTH_9","MONTH_12", "MONTH_15","MONTH_16TO18","MONTH_18","YEAR_2"};
	public static void createTables(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","yam123");
			Statement s = con.createStatement();
			
			s.executeUpdate("create table login_Info(cid int NOT NULL  PRIMARY KEY, email varchar2(40) NOT NULL, password varchar2(30) NOT NULL, rid varchar2(50), isActivate number(1), timeStamp number(30))");
			//Bit 1 --> activate, 0 ---> deactiavate
			
			s.executeUpdate("create table Child_Info(C_Id int NOT NULL PRIMARY KEY,C_Name varchar2(50) NOT NULL,DOB DATE,Gender varchar2(10),F_Name varchar2(30),Mob varchar2(20), Email varchar2(30))");
			
			s.executeUpdate("create table Child_Vaccine(C_Id int NOT NULL PRIMARY KEY,Birth Date,Week_6 Date,Week_10 Date,Week_14 Date,Month_9 Date, Month_12 Date,Month_15 Date,Month_16to18 Date, Month_18 Date,Year_2 Date)");
			
			s.executeUpdate("create table Vaccine_Info(Age Varchar2(30), V_Name varchar2(30),v_code varchar2(30))");
		
			PreparedStatement ps = con.prepareStatement("insert into Vaccine_Info(age,v_name,v_code) values(?,?,?)");
			for(int i=0;i<age.length;i++){
				for(int j=0;j<V_Name[i].length;j++){
					ps.setString(1,age[i]);
					ps.setString(3,v_code[i]);
					ps.setString(2,V_Name[i][j]);
					ps.executeUpdate();
				}
			}
			ps.close();
				
		}
		catch(SQLException sqle){
			System.out.println("It is sql Exception "+sqle);
		}
		catch(ClassNotFoundException ce){
			System.out.println("Class Not found exception is occuring  "+ce);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public static void autoUpdate(int cid,String date) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","yam123");
		PreparedStatement ps = con.prepareStatement("insert into  Child_Vaccine(C_Id,Birth,Week_6,Week_10,Week_14,Month_9,Month_12,Month_15,Month_16to18,Month_18,Year_2) values(?,?,?,?,?,?,?,?,?,?)");
		
	}
	public static void main(String[] arg){
		createTables();
	}
}