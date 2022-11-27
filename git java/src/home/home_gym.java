package home;

import java.util.*;
import details.*;
import java.util.InputMismatchException;
import java.sql.*;





public class home_gym {

	public static void main(String[] args)throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		try {
		int choice=0;
		do {
			
			System.out.println("\n\t\t\t\t\t \uD83D\uDE00 WELCOME MY FITNESS GYM \uD83D\uDE00\n");
	
			
			System.out.println("  Enter \t 1 For Member Detalis  \t 2 For Staff Details  \t 3 For Equipments Details\t4 For Transactions Details ");
			System.out.println("                 \t\t\t\t   0 For Exit\n");
			
			System.out.print("\tEnter your choice: ");
		 choice = sc.nextInt();
		 switch(choice) {
		
		 case 1: 			 
			 members.main(args);
			 break;
		 
		 case 2:
			 staff.main(args);
			 break;
		 case 3:
			 Equipments.main(args);
			 break;
		 case 4:
			 transactions.main(args);
			 break;
			 default:
				 break;
		 }
		
			
		
		}while(choice != 0);
		}
		catch(InputMismatchException e) {
			System.out.println("Please enter shown option numbers Only...");
		}
		catch(Exception e) {
			System.out.println("Something went wrong");
		}
		sc.close();
	}

}
