package com.digit.peopleServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.digit.MaincCrsApp.MainCrsApp;

public class professorServices {
	public static void menu() throws Exception {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("---Enter Your Choice---");
			System.out.println();
			System.out.println("1. Give Marks to Student");
			System.out.println("2. Exit");
			int choice = sc.nextInt();
			switch (choice) {

			case 1: {
			  giveMarksToStudent();
			  break;
			}
			case 2: {
				System.out.println("---Exiting to Main Panel---");
				MainCrsApp.main(null);
				break;
			}
		}
	}

}

	public static void giveMarksToStudent() throws Exception {
		Scanner sc = new Scanner(System.in);
//		System.out.println("This Is giveMarks");
		System.out.println("----Available Courses----");
		//need to chnage the logic
		adminServices.viewAllCourses();
		System.out.println("----Enter the CourseId----");
		int choice = sc.nextInt();
		System.out.println("----Enrolled Students----");
		String sql = "select * from student where cid = ?";
		PreparedStatement statement = MainCrsApp.connection.prepareStatement(sql);
		statement.setInt(1, choice);
		ResultSet rs = statement.executeQuery();
//		select student.*,course.* from student inner join course on student.cid = course.cid;
		while(rs.next()==true) {
			System.out.print("Student_Id ");
			System.out.print("->");
			System.out.println(rs.getInt(1)); 
			System.out.print("Student_Name ");
			System.out.print("->");
			System.out.println(rs.getString(2));
			System.out.print("Student_Email ");
			System.out.print("->");
		    System.out.println(rs.getString(3));
		    System.out.println();
		    System.out.println();
		}
		System.out.println("----Enter the StudentId to Select----");
		int stId =sc.nextInt();
		String sql3 = "select * from student where sid = ?";
		PreparedStatement ps = MainCrsApp.connection.prepareStatement(sql3);
		ps.setInt(1, stId);
	ResultSet res = ps.executeQuery();
	if(res.next() == true) {
		System.out.println("----Student Loaded----" + res.getString(2) + "----");
		System.out.println();
	}
		//need to write code for selecting student
		System.out.println("----Enter the Marks of the Student----");
		System.out.println();
//		System.out.println("You need to give MarksId to Store");
//		int mid = sc.nextInt();
//		System.out.println("Assignment-1 ");
//		int ass1 = sc.nextInt();
		System.out.println("Give Marks On a Scale Of 10 ");
		int ass1 = sc.nextInt();
		String sql1 = "update student set grades = ? where sid =?";
		PreparedStatement st = MainCrsApp.connection.prepareStatement(sql1);
		st.setInt(1,ass1 );
		st.setInt(2,stId);
		
		int x = st.executeUpdate();
		if(x>0) {
			System.out.println("----Marks has been sent to Student----");
		}
		
		

		
		
	}

}
