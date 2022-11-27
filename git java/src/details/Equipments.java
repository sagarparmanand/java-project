package details;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.*;

 class equip{
	private int EqId;
	private String Name;
	private String ContactNo;
	private double Amount;
	private String Description;
	private String Date;
	private String Vendor;
	public equip(int EqId, String Name, String ContactNo ,double Amount, String Description, String Date,String Vendor){
		
		this.EqId=EqId;
		this.Name=Name;
		this.ContactNo=ContactNo;
		this.Amount=Amount;
		this.Description=Description;
		this.Date=Date;
		this.Vendor=Vendor;
	}
public equip(String Name, String ContactNo ,double Amount, String Description, String Date,String Vendor){
		
		this.Name=Name;
		this.ContactNo=ContactNo;
		this.Amount=Amount;
		this.Description=Description;
		this.Date=Date;
		this.Vendor=Vendor;
	}
	
	public int getEqId(){
		return EqId;
	}
	public String getName(){
		return Name;
	}
	public String getContactNo(){
		return ContactNo;
	}
	public double getAmount(){
		return Amount;
	}
	public String getDescription(){
		return Description;
	}
	public String getDate(){
		return Date;
	}
	public String getVendor() {
		return Vendor;
	}
	public String toString() {
		return EqId+"\t"+Name+"\t"+ContactNo+"\t"+"\tRs."+Amount+"\t"+Description+"\t"+Date+"\t"+Vendor;
	}
	public void Equ() {
		System.out.println(Name+"\t"+ContactNo+"\t"+"\tRs."+Amount+"\t"+Description+"\t"+Date+"\t"+Vendor);
	}
}


public class Equipments {

	public static void main(String[] args)throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		Connection con = DBConnection.getConnection();
		Statement stm = con.createStatement();
		try {
		int EqId;
		String Name;
		String ContactNo;
		double Amount;
		String Description;
		String Date;
		String Vendor;
		int choice=0;
		do {
			System.out.println("\n\t\t\t\t\t   EQUIPMENT DETAILS\n");
			System.out.println(" Enter    1 For Insert \t       2 For Update");
			System.out.println("   \t  3 For Delete \t       4 For Single Record");
			System.out.println("       \t  5 For All Record     0 For Exit\n");
			
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Enter  EquipmentId");
				EqId = sc.nextInt();
				if(EqId>0) {
				System.out.println("Enter Equipment Name ");
				Name = sc.next();
				char c=Name.charAt(0);
				if(c>='a' && c<='z' || c>='A' && c<='Z') {
				System.out.println("Enter ContactNo");
				ContactNo = sc.next();
				 int len =ContactNo.length();
					
					if(len==10) {
				System.out.println("Enter Amount");
				Amount = sc.nextDouble();
				System.out.println("Enter Description");
				Description = sc.next();
				System.out.println("Enter Purchase Date (YYYY-MM-DD)");
				Date = sc.next();
				System.out.println("Enter Vendor Name");
				Vendor = sc.next();
				stm.executeUpdate("insert into Equipment values('"+EqId+"','"+Name+"','"+ContactNo+"','"+Amount+"','"+Description+"','"+Date+"','"+Vendor+"')");
				equip e = new equip(EqId, Name, ContactNo, Amount, Description, Date, Vendor);
				System.out.println("\n"+e+"\n");
				System.out.println("Inserted Successefully...");
				
					}else {
						System.out.println("Enter valid Phone No...");
					}
				}else {System.out.println("Enter valid Equipment Name...");}
					}else {
					System.out.print("Please enter positive numbers....");
				}
				break;
			case 2:
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Enter EquipmentId To Update Equipments Details");
				EqId = sc.nextInt();
				
						System.out.println("Enter Equipment Name");
						Name = sc.next();
						System.out.println("Enter ContactNo");
						ContactNo = sc.next();
						 int len =ContactNo.length();
							
							if(len==10) {
					System.out.println("Enter Amount");
					Amount = sc.nextDouble();
					System.out.println("Enter Description");
					Description = sc.next();
					System.out.println("Enter Date (YYYY-MM-DD)");
					Date = sc.next();
					System.out.println("Enter Vendor Name");
					Vendor = sc.next();
					int E=stm.executeUpdate("update Equipment set Name='"+Name+"' ,ContactNo='"+ContactNo+"', Amount='"+Amount+"', Description='"+Description+"', Date='"+Date+"',Vendor='"+Vendor+"' where Eid='"+EqId+"' ");
					equip q = new equip(Name, ContactNo, Amount, Description, Date, Vendor);
					if(E==0) {
						System.out.println("\nEquipmentId does not exist");
					}
					else {
						
						q.Equ();
						System.out.println("\nUpdated");
					}
							}else {
								System.out.println("Enter valid Phone No.");
							}
					
					
			
						System.out.println("--------------------------------------------------------------------------------");
				break;
			case 3:
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Enter EquipmentId To Delete Equipment Details");
				EqId = sc.nextInt();
				int c = stm.executeUpdate("delete from Equipment where Eid='"+EqId+"'");
				if(c==0) {
					System.out.println("\nEquipmentId does not exist");
				}
				else {
					System.out.println("\nDeleted");
				}
				System.out.println("--------------------------------------------------------------------------------");
				break;
				
			case 4:
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Enter EquipmentId To Select Equipment Details");
				EqId = sc.nextInt();
				
				ResultSet rs = stm.executeQuery("select * from Equipment where Eid='"+EqId+"'");
				if(rs.next()) {
					System.out.println("EquipmentId: "+rs.getInt(1));
					System.out.println("Equipment Name: "+rs.getString(2));
					System.out.println("Contact No.: "+rs.getLong(3));
					System.out.println("Amount: "+rs.getDouble(4));
					System.out.println("Description: "+rs.getString(5));
					System.out.println("Joining Date: "+rs.getDate(6));
					System.out.println("Vendor Name; "+rs.getString(7));
				}
				else {
					System.out.println("\nEquipmentId does not exist");
					
				}
				
				System.out.println("--------------------------------------------------------------------------------");
				break;
				
			case 5:
				System.out.println("--------------------------------------------------------------------------------");
				 rs = stm.executeQuery("select * from Equipment");
					
					while(rs.next()) {
						
						System.out.println("EquipmentId: "+rs.getInt(1));
						System.out.println("Equipment Name: "+rs.getString(2));
						System.out.println("Contact No.: "+rs.getLong(3));
						System.out.println("Amount: "+rs.getDouble(4));
						System.out.println("Description: "+rs.getString(5));
						System.out.println("Joining Date: "+rs.getDate(6));
						System.out.println("Vendor Name; "+rs.getString(7));
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
		 System.out.println("This EquipmentId is already taken...");
	 }
	catch(Exception e) {
		 System.out.println("Something went wrong.. Enter your values in valid form...like (YYYY/MM/DD)");
	 }

	}

}
