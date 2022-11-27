package details;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;


class tran{
	private int transactId;
	private String transactType;
	private String transactBank;
	private int transactMID;
	
	public tran(int transactId, String transactType, String transactBank, int transactMID ){
		
		this.transactMID=transactMID;
		this.transactType=transactType;
		this.transactBank=transactBank;
		this.transactMID=transactMID;
		
	}
	
	
	public int gettransactId(){
		return transactId;
	}
	public String gettransactType(){
		return transactType;
	}
	
	public String gettransactBannk(){
		return transactBank;
	}
	public int gettransactMID(){
		return transactMID;
	}
	
	public String toString() {
		return transactId+"\t"+transactType+"\t"+transactBank+"\t"+transactMID;
	}
}
public class transactions {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		Connection con = DBConnection.getConnection();
		Statement stm = con.createStatement();
		 try {
		int transactId = 0;
		String transactType = null;
		String transactBank = null;
		int transactMID = 0;
		int choice=0;
do {
			
			System.out.println("\n\t\t\t\t\t    TRANSACTIONS DETAILS\n");
			System.out.println(" Enter    1 For Insert \t       2 For Update");
			System.out.println("   \t  3 For Delete \t       4 For Single Record");
			System.out.println("       \t  5 For All Record     0 For Exit\n");
			
			System.out.print("Enter your choice: ");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				
				System.out.println("Enter  TransactionId");
				transactId = sc.nextInt();
				if(transactId > 0) {
					System.out.println("Enter transactionType ");
					transactType = sc.next();
					char c=transactType.charAt(0);
					if(c>='a' && c<='z' || c>='A' && c<='Z') {
						System.out.println("Enter transactionBank");
						transactBank = sc.next();
					char cc=transactBank.charAt(0);
					if(cc>='a' && cc<='z' || cc>='A' && cc<='Z') {
						System.out.println("Enter transactionMID");
						transactMID= sc.nextInt();
						stm.executeUpdate("insert into transaction values('"+transactId+"','"+transactType+"','"+transactBank+"','"+transactMID+"')");
						System.out.println("Inserted Successefully...");
					}else {
						System.out.println("Enter only alphabets");
					}
						
						
					}
				
					else {
						System.out.println("Enter only alphabets");
					}
				}
				else {
					System.out.print("Please enter valid transaction Id...");
				}
				
				
				break;
			case 2:
				
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Enter transactionMID To Update transaction Details");
				transactMID = sc.nextInt();
					System.out.println("Enter transactionType");
					transactType = sc.next();
					System.out.println("Enter transactionBank");
					transactBank = sc.next();
					
					int U=stm.executeUpdate("update transaction set Ttype='"+transactType+"' ,TBank='"+transactBank+"' where MID='"+transactMID+"'");
					
					if(U==0) {
						System.out.println("\ntransactionId does not exist");
					}
					else {
						
						System.out.println("\nUpdated");
					}
					
				System.out.println("--------------------------------------------------------------------------------");
				break;
			case 3:
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Enter transactionMID To Delete Member Details");
				transactMID = sc.nextInt();
				int c = stm.executeUpdate("delete from transaction where MID='"+transactMID+"'");
				if(c==0) {
					System.out.println("\ntransactionId does not exist");
				}
				else {
					System.out.println("\nDeleted");
				}
				System.out.println("--------------------------------------------------------------------------------");
				break;
				
			case 4:
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Enter transactionMID To Select transaction Details");
				transactMID  = sc.nextInt();
				ResultSet rs = stm.executeQuery("select Name,Amount,Date,Tid,Ttype,TBank,MID from member left join transaction on member.Id=transaction.MID where MID='"+transactMID+"'");
				if(rs.next()) {
					
					System.out.println("Member Name: "+rs.getString(1));
					System.out.println("Amount: "+rs.getDouble(2));
					System.out.println("Joining Date: "+rs.getDate(3));
					System.out.println("Transaction ID: "+rs.getInt(4));
					System.out.println("Transaction Type: "+rs.getString(5));
					System.out.println("Transaction Bank: "+rs.getString(6));
					System.out.println("TMember Id: "+rs.getInt(7));
				}
				else {
					System.out.println("\ntransactionId does not exist");
					
				}
				
				System.out.println("--------------------------------------------------------------------------------");
				break;
				
			case 5:
				System.out.println("--------------------------------------------------------------------------------");
				
				 rs = stm.executeQuery("select * from member right join transaction on member.Id=transaction.MID");
				
				while(rs.next()) {
					
					
					System.out.println("Member Name: "+rs.getString(2));
					System.out.println("Amount: "+rs.getDouble(4));
					System.out.println("Joining Date: "+rs.getDate(6));
					System.out.println("Transaction ID: "+rs.getInt(7));
					System.out.println("Transaction Type: "+rs.getString(8));
					System.out.println("Transaction Bank: "+rs.getString(9));
					System.out.println("TMember Id: "+rs.getInt(10));
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
			 System.out.println("Transaction Id already Exist Or invalid Id...");
		 }
		catch(Exception e) {
			 System.out.println("Something went wrong.. ");
		 }

	}

}
