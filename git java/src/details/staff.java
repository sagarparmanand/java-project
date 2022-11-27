package details;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.*;


 class staf{
	private int staffId;
	private String Name;
	private String ContactNo;
	private String Designation;
	private double Salary;
	private String Date;
	public staf(int staffId, String Name, String ContactNo ,String Designation, double Salary, String Date){
		
		this.staffId=staffId;
		this.Name=Name;
		this.ContactNo=ContactNo;
		this.Designation=Designation;
		this.Salary=Salary;
		this.Date=Date;
	}
	
public staf( String Name, String ContactNo ,String Designation, double Salary, String Date){
		
		this.Name=Name;
		this.ContactNo=ContactNo;
		this.Designation=Designation;
		this.Salary=Salary;
		this.Date=Date;
	}
	
	public int getstaffId(){
		return staffId;
	}
	public String getName(){
		return Name;
	}
	public String getContactNo(){
		return ContactNo;
	}
	public String getDesignation(){
		return Designation;
	}
	public double getSalary(){
		return Salary;
	}
	public String getDate(){
		return Date;
	}
	public String toString() {
		return staffId+"\t"+Name+"\t"+ContactNo+"\t"+Designation+"\t"+"\tRs"+Salary+"\t"+Date;
	}
	public void Sta() {
		System.out.println(Name+"\t"+ContactNo+"\t"+Designation+"\t"+"\tRs"+Salary+"\t"+Date);
	}
}

public class staff {

	public static void main(String[] args)throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		Connection con = DBConnection.getConnection();
		Statement stm = con.createStatement();
		 try {
		int staffId;
		String Name;
		String ContactNo;
		String Designation;
		double Salary;
		String Date;
		int choice=0;
		do {
			System.out.println("\n\t\t\t\t\t    STAFF DETAILS\n");
			System.out.println(" Enter    1 For Insert \t       2 For Update");
			System.out.println("   \t  3 For Delete \t       4 For Single Record");
			System.out.println("       \t  5 For All Record     0 For Exit\n");
			
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Enter  StaffId");
				staffId = sc.nextInt();
				if(staffId >0) {
				System.out.println("Enter Name ");
				Name = sc.next();
				char c=Name.charAt(0);
				if(c>='a' && c<='z' || c>='A' && c<='Z') {
				System.out.println("Enter ContactNo");
				ContactNo = sc.next();
                 int len =ContactNo.length();
				
				if(len==10) {
				System.out.println("Enter Designation");
				Designation = sc.next();
				System.out.println("Enter Salary ");
				Salary = sc.nextDouble();
				System.out.println("Enter Joining Date (YYYY-MM-DD)");
				Date = sc.next();
				stm.executeUpdate("insert into staff values('"+staffId+"','"+Name+"','"+ContactNo+"','"+Designation+"','"+Salary+"','"+Date+"')");
				staf s = new staf(staffId, Name, ContactNo, Designation, Salary, Date);
				System.out.println("\n"+s+"\n");
				System.out.println("Inserted Successefully...");
				
				}else {
					System.out.println("Enter valid Phone No.");
				}
				}else {System.out.println("Enter valid Name...");}
				}else {
					System.out.print("Please enter positive numbers...");
				}
				break;
			case 2:
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Enter StaffId To Update Staff Details");
				staffId = sc.nextInt();
				
						System.out.println("Enter Name");
						Name = sc.next();
						System.out.println("Enter ContactNo");
						ContactNo = sc.next();
						 int len =ContactNo.length();
							
							if(len==10) {
					System.out.println("Enter Designation");
					Designation = sc.next();
					System.out.println("Enter Salary");
					Salary = sc.nextDouble();
					System.out.println("Enter new Date (YYYY-MM-DD)");
					Date = sc.next();
					int M=stm.executeUpdate("update staff set Sname='"+Name+"' ,ContactNo='"+ContactNo+"', Desig='"+Designation+"', Salary='"+Salary+"', Date='"+Date+"' where Sid='"+staffId+"'");
					staf f = new staf(Name, ContactNo, Designation, Salary, Date);
					if(M==0) {
						System.out.println("\nStaffId does not exist");
					}
					else {
						
						f.Sta();
						System.out.println("\nUpdated");
					}
							}else {
								System.out.println("Enter valid Phone No.");
							}
					
					System.out.println("--------------------------------------------------------------------------------");
				break;
			case 3:
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Enter StaffId To Delete Staff Details");
				staffId = sc.nextInt();
				int c = stm.executeUpdate("delete from staff where Sid='"+staffId+"'");
				if(c==0) {
					System.out.println("\nStaffId does not exist");
				}
				else {
					System.out.println("\nDeleted");
				}
				System.out.println("--------------------------------------------------------------------------------");
				break;
				
			case 4:
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Enter StaffId To Select Staff Details");
				staffId = sc.nextInt();
				ResultSet rs = stm.executeQuery("select * from staff where Sid='"+staffId+"'");
				if(rs.next()) {
					System.out.println("StaffId: "+rs.getInt(1));
					System.out.println("Staff Name: "+rs.getString(2));
					System.out.println("Contact No.: "+rs.getLong(3));
					System.out.println("Designation: "+rs.getString(4));
					System.out.println("Salary: "+rs.getDouble(5));
					System.out.println("Joining Date: "+rs.getDate(6));
				}
				else {
					System.out.println("\nStaffId does not exist");
					
				}
				System.out.println("--------------------------------------------------------------------------------");
				break;
				
			case 5:
				System.out.println("--------------------------------------------------------------------------------");
				 rs = stm.executeQuery("select * from staff");
					
					while(rs.next()) {
						
					
						System.out.println("StaffId: "+rs.getInt(1));
						System.out.println("Staff Name: "+rs.getString(2));
						System.out.println("Contact No.: "+rs.getLong(3));
						System.out.println("Designation: "+rs.getString(4));
						System.out.println("Salary: "+rs.getDouble(5));
						System.out.println("Joining Date: "+rs.getDate(6));
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
			 System.out.println("Please Enter numbers only And valid foarm.... ");
		 }
		 catch(SQLIntegrityConstraintViolationException e) {
			 System.out.println("This StaffId is already taken...");
		 }
		catch(Exception e) {
			 System.out.println("Something went wrong.. Enter your values in valid foarm...like (YYYY/MM/DD)");
		 }

	}

}
