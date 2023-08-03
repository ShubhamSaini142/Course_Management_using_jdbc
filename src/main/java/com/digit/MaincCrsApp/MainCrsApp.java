package com.digit.MaincCrsApp;
import com.digit.beans.Users;
import com.digit.peopleServices.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MainCrsApp {
	public static Connection connection ;

	public static void main(String[] args) throws Exception {
		//Loading JDBC
			Class.forName("com.mysql.jdbc.Driver");
//			System.out.println("Driver Loaded");
		//Establishing Connection
			String url = "jdbc:mysql://localhost:3306/csr";
			String user = "root";
			String pwd  = "root";
		 connection = DriverManager.getConnection(url, user, pwd);
			if(connection!=null) {
				System.out.println("Connection Established to Main");
			}
		Scanner scanner = new Scanner(System.in);
        System.out.println("---Welcome to Project---");
        System.out.println("---Enter your Choice---");
        System.out.println("1. Admim Login");
        System.out.println("2. Student Login");
        System.out.println("3. Professor Login");
        System.out.println("4. Exit");
        while(true) {
        int choice = scanner.nextInt();
        switch (choice) {
		case 1: {
			boolean res = Users.AdminLogin();
			if(res == true) {
				System.out.println("----Admin Login SucessFull----");
				adminServices.menu();
			}
			else {
				System.out.println("Invalid Credential");
				MainCrsApp.main(null);
			}
			break;
			
		}
		case 2:{
			boolean res = Users.StudentLogin();
			if(res == true) {
				System.out.println("---Student Login Sucessfull---");
				studentServices.menu();
			}
			else {
				System.out.println("Invalid Credential");
				MainCrsApp.main(null);
				
			}
			break;
		}
		case 3:{
			boolean res = Users.ProfessorLogin();
			if(res == true) {
				System.out.println("---Professor Login Sucessfull---");
				professorServices.menu();
			}
			else {
				System.out.println("Invalid Credential");
				MainCrsApp.main(null);
				
			}
			break;
			
		}
		case 4:{
			System.out.println("----Exiting System , Goodbye!!----");
		        System.exit(0);
			
		}
		default:
			System.out.println("----Invalid Choice!!----");
			System.out.println("----Enter Your Choice Again----");
			MainCrsApp.main(null);
	
		}
        }
        
	}

}
