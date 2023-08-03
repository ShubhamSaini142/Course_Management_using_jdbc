package com.digit.peopleServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.digit.MaincCrsApp.MainCrsApp;

public class studentServices {
	private static PreparedStatement psmt;
	private static ResultSet res;
	private static Statement st;

	public static void menu() throws Exception {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("---Enter Your Choice---");
			System.out.println();
			System.out.println("1. Enroll in Course");
			System.out.println("2. View Marks");
			System.out.println("3. Exit");
			int choice = sc.nextInt();
			switch (choice) {

			case 1: {
				enrollInCourse();
				break;
			}
			case 2: {
				viewMarks();
				break;
			}
			case 3: {
				System.out.println("---Exiting to Main Panel---");
				MainCrsApp.main(null);
				break;
			}
			}
		}

	}

	private static void viewMarks() throws SQLException {
	Scanner sc= new Scanner(System.in);
	System.out.println("----Enter the StudentId to Select----");
	int stId =sc.nextInt();
	String sql3 = "select * from student where sid = ?";
	PreparedStatement ps = MainCrsApp.connection.prepareStatement(sql3);
	ps.setInt(1, stId);
    ResultSet res = ps.executeQuery();
  if(res.next() == true) {
	System.out.println("Your Marks Out of 10 is " + res.getInt(6)); 
	System.out.println();
}
	
  
	}

	private static void enrollInCourse() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("---Available Courses---");
		adminServices.viewAllCourses();
		System.out.println("----Enter the CourseID to Enroll----");
		int choice = sc.nextInt();
		System.out.println("----Enter your ID to Enroll----");
		int Id = sc.nextInt();
		String sql = "update student set cid = ? where sid = ?";
		psmt = MainCrsApp.connection.prepareStatement(sql);
		psmt.setInt(1, choice);
		psmt.setInt(2, Id);
		int x = psmt.executeUpdate();
		if(x>0) {
			System.out.println("You are Enrolled in course");
			System.out.println();
		}
		else {
			System.out.println("---Invalid Input---");
			
		}

	}

}
