package details;
import java.util.*;
import java.sql.*;
 class member{
	private int memberId;
	private String Name;
	private String MiddleName;
	private String  LastName;
	private String ContactNo;
	private double Amount;
	private String Service;
	private String Date;
	public member(int memberId, String Name,String MiddleName,String  LastName, String ContactNo ,double Amount, String Service, String Date){
		
		this.memberId=memberId;
		this.Name=Name;
		this.MiddleName=MiddleName;
		this.LastName=LastName;
		this.ContactNo=ContactNo;
		this.Amount=Amount;
		this.Service=Service;
		this.Date=Date;
	}
	
public member(double Amount, String Service, String Date){
		
		this.Amount=Amount;
		this.Service=Service;
		this.Date=Date;
		
	}
	
	public int getmemberId(){
		return memberId;
	}
	public String getName(){
		return Name;
	}
	public String getMiddleName() {
		return MiddleName; 
	}
	public String getLastName() {
		return LastName; 
	}
	public String getContactNo(){
		return ContactNo;
	}
	public double getAmount(){
		return Amount;
	}
	public String getService(){
		return Service;
	}
	public String getDate(){
		return Date;
	}
	public String toString() {
		return memberId+"\t"+Name+"\t"+MiddleName+"\t"+LastName+"\t"+ContactNo+"\t"+"\tRs."+Amount+"\t"+Service+"\t"+Date;
	}
	public void Up() {
		System.out.println("\tRs."+Amount+"\t"+Service+"\t"+Date);
	}
	
}
 class DBConnection{
	 public static Connection getConnection() {
		 Connection con= null;
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			  con = DriverManager.getConnection("jdbc:mysql://localhost:3308/gym1","root","");
		 }catch(ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		 }
		 return con;
	 }
 }
 

public class members {

	public static void main(String[] args)throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		Connection con=DBConnection.getConnection();
		Statement stm = con.createStatement();
		 try {
		int memberId = 0;
		String Name = null;
		String MiddleName=null;
		String LastName=null;
	String ContactNo = null;
		double Amount = 0;
		String Service = null;
		String Date = null;
		
		int choice=0;
		
		do {
			
			System.out.println("\n\t\t\t\t\t    MEMBERS DETAILS\n");
			System.out.println(" Enter    1 For Insert \t       2 For Update");
			System.out.println("   \t  3 For Delete \t       4 For Single Record");
			System.out.println("       \t  5 For All Record     0 For Exit\n");
			
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				
				System.out.println("Enter  MemberId");
				memberId = sc.nextInt();
				if(memberId > 0) {
					System.out.println("Enter Name ");
					Name = sc.next();
					char c=Name.charAt(0);
					if(c>='a' && c<='z' || c>='A' && c<='Z') {
						System.out.println("Enter Middle Name");
						MiddleName = sc.next();
						System.out.println("Enter Last Name");
						LastName = sc.next();
					System.out.println("Enter ContactNo");
					 ContactNo = sc.next();
					int len =ContactNo.length();
				
				if(len==10) {
					System.out.println("Enter Amount");
					Amount = sc.nextDouble();
					System.out.println("Enter Service ( eg. cardio / crossfit / fitness) ");
					Service = sc.next();
					char ss=Service.charAt(0);
					if(ss>='a' && ss<='z' || ss>='A' && ss<='Z') {
					System.out.println("Enter Joinig Date (YYYY-MM-DD)");
					Date = sc.next();
					stm.executeUpdate("insert into member values('"+memberId+"','"+Name+"','"+MiddleName+"','"+LastName+"','"+ContactNo+"','"+Amount+"','"+Service+"','"+Date+"')");
					member m =new member(memberId, Name,MiddleName,LastName, ContactNo, Amount, Service, Date);
					System.out.println("\n"+m+"\n");
					System.out.println("Inserted Successefully...");
					}else {System.out.println("Enter valid Service..");}
					
				}else {
					System.out.println("Enter valid Phone No.");
				}
					}else {System.out.println("Enter valid name...");}
				}
				else {
					System.out.print("Please enter positive numbers...");
				}
				
				
				break;
			case 2:
				
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Enter memberId To Update Member Details");
				memberId = sc.nextInt();
					System.out.println("Enter Amount");
					Amount = sc.nextDouble();
					System.out.println("Enter Service ( eg. cardio / crossfit / fitness)");
					Service = sc.next();
					System.out.println("Enter new Date (YYYY-MM-DD)");
					Date = sc.next();
					int U=stm.executeUpdate("update member set Amount='"+Amount+"' ,Service='"+Service+"',Date='"+Date+"' where Id='"+memberId+"'");
					member u = new member(Amount, Service, Date);
					if(U==0) {
						System.out.println("\nMemberId does not exist");
					}
					else {
						
						u.Up();
						System.out.println("\nUpdated");
					}
					
				System.out.println("--------------------------------------------------------------------------------");
				break;
			case 3:
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Enter memberId To Delete Member Details");
				memberId = sc.nextInt();
				int c = stm.executeUpdate("delete from member where Id='"+memberId+"'");
				if(c==0) {
					System.out.println("\nMemberId does not exist");
				}
				else {
					System.out.println("\nDeleted");
				}
				System.out.println("--------------------------------------------------------------------------------");
				break;
				
			case 4:
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Enter memberId To Select Member Details");
				memberId = sc.nextInt();
				ResultSet rs = stm.executeQuery("select * from member where Id='"+memberId+"'");
				if(rs.next()) {
					System.out.println("MemberId: "+rs.getInt(1));
					System.out.println("Member Name: "+rs.getString(2));
					System.out.println("Contact No.: "+rs.getLong(3));
					System.out.println("Amount: "+rs.getDouble(4));
					System.out.println("Service: "+rs.getString(5));
					System.out.println("Joining Date: "+rs.getDate(6));
				}
				else {
					System.out.println("\nMemberId does not exist");
					
				}
				
				System.out.println("--------------------------------------------------------------------------------");
				break;
				
			case 5:
				System.out.println("--------------------------------------------------------------------------------");
				
				 rs = stm.executeQuery("select * from member");
				
				while(rs.next()) {
					
				
					System.out.println("MemberId: "+rs.getInt(1));
					System.out.println("Member Name: "+rs.getString(2));
					System.out.println("Member Middle: "+rs.getString(3));
					System.out.println("Member Last: "+rs.getString(4));
					System.out.println("Contact No.: "+rs.getLong(5));
					System.out.println("Amount: "+rs.getDouble(6));
					System.out.println("Service: "+rs.getString(7));
					System.out.println("Joining Date: "+rs.getDate(8));
					System.out.println();
				}
				 
				System.out.println("--------------------------------------------------------------------------------");
				break;
			default:
				 break;
			}
			 
			
		}while(choice!=0);
		
		}
		 catch(InputMismatchException e) {
			 System.out.println("Please Enter numbers only And valid form.... ");
		 }
		 catch(SQLIntegrityConstraintViolationException e) {
			 System.out.println("This MemberId Or MiddleName is already taken...");
		 }
		catch(Exception e) {
			 System.out.println("Something went wrong.. Enter your values in valid form...like (YYYY/MM/DD)");
		 }

	}

}
