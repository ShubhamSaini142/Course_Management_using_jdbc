package com.digit.peopleServices;

import com.digit.MaincCrsApp.MainCrsApp;
import com.digit.beans.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class adminServices {
	private static PreparedStatement psmt;

	private static Statement st;

	public static void menu() throws Exception {
		while(true) {
		System.out.println("---Select Option:---");
		System.out.println("1. Add course\n" + "2. Add Student\n" + "3. Add Professor\n" + "4. Remove Course\n"
				+ "5. Remove Professor\n" + "6. Remove Student\n" + "7. View All Students\n" + "8. View All Courses\n"
				+ "9. View All Professors\n" +"10. Assign Course to Professor\n"+ "0. Exit\n");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		switch (n) {
		case 1: {
			addCourse();
			break;
		}
		case 2: {
			addStudent();
			break;
		}
		case 3: {
			addProfessor();
			break;
		}
		case 4: {
			removeCourse();
			break;
		}
		case 5: {
			removeProfessor();
			break;
		}
		case 6: {
			removeStudent();
			break;
		}
		case 7: {
			viewAllStudent();
			break;
		}
		case 8: {
			viewAllCourses();
			break;
		}
		case 9: {
			viewAllProfessors();
			break;
		}
		case 10: {
			assignCourseToProfessor();
			break;
		}
		case 0:{
			System.out.println("---Exiting to Main Panel---");
			MainCrsApp.main(null);
		}

		default:
		}
		}
	}

	private static void assignCourseToProfessor() throws Exception {
	ResultSet res;
	Scanner sc = new Scanner(System.in);
	
	System.out.println("----Available Professors----");
	viewAllProfessors();
	System.out.println("---Enter the ProfessorID---");
	int choice = sc.nextInt();
	System.out.println("----Available Courses----");
	viewAllCourses();
	System.out.println("---Enter the CourseID---");
	int choice1 = sc.nextInt();
	String sql = "update course set pid = ? where cid = ?";
	psmt = MainCrsApp.connection.prepareStatement(sql);
	psmt.setInt(1, choice);
	psmt.setInt(2, choice1);
	int x = psmt.executeUpdate();
	if(x>0) {
		System.out.println("---Course Assigned to Professor---");
	}	
	}

	private static void removeCourse() throws Exception {
		viewAllCourses();
		Scanner sc = new Scanner(System.in);
		System.out.println("You have to entered the course Name and course Id");
		System.out.println("Enter the Id of the Course");
		int courseId = sc.nextInt();
		System.out.println("---CONFIRMATION---");
		System.out.println("Enter the Name of the Course");
		String courseName = sc.next();
		ForeignKeyCheckOff();
		String sql = "delete from course where cid = ? and cname = ? ";
		psmt = MainCrsApp.connection.prepareStatement(sql);
		psmt.setInt(1, courseId);
		psmt.setString(2, courseName);
		int x = psmt.executeUpdate();
		if (x > 0) {
			System.out.println("---Course Deleted Sucessfully---");
			System.out.println();
		}
		ForeignKeyCheckOn();

	}

	private static void removeStudent() throws Exception {
		viewAllStudent();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Id of the Student");
		int StudentId = sc.nextInt();
		System.out.println("---CONFIRMATION---");
		System.out.println("Enter the Name of the Student");
		String StudentName = sc.next();
		ForeignKeyCheckOff();
		String sql = "delete from student where sid = ? and sname = ? ";
		psmt = MainCrsApp.connection.prepareStatement(sql);
		psmt.setInt(1, StudentId);
		psmt.setString(2, StudentName);
		int x = psmt.executeUpdate();
		if (x > 0) {
			System.out.println("---Student Details Deleted Sucessfully---");
		}
		ForeignKeyCheckOn();
	}
	public static void ForeignKeyCheckOff() throws Exception {
		String sql = "SET FOREIGN_KEY_CHECKS=0";
		st = MainCrsApp.connection.createStatement();
	       st.executeUpdate(sql);	
	}
	public static void ForeignKeyCheckOn() throws Exception {
		String sql = "SET FOREIGN_KEY_CHECKS=1";
		st = MainCrsApp.connection.createStatement();
	       st.executeUpdate(sql);	
	}

	private static void removeProfessor() throws Exception {
		viewAllProfessors();
		Scanner sc = new Scanner(System.in);
		System.out.println("You have to entered the Professor Name and Professor Id");
		System.out.println("Enter the Id of the Professor");
		int ProfessorId = sc.nextInt();
		System.out.println("---CONFIRMATION---");
		System.out.println("Enter the Name of the Professor");
		String ProfessorName = sc.next();
		ForeignKeyCheckOff();
		String sql = "delete from professor where pid = ? and pname = ? ";
		psmt = MainCrsApp.connection.prepareStatement(sql);
		psmt.setInt(1, ProfessorId);
		psmt.setString(2, ProfessorName);
		int x = psmt.executeUpdate();
		if (x > 0) {
			System.out.println("---Professor Details Deleted Sucessfully---");
		}
		ForeignKeyCheckOn();

	}

	private static void addStudent() throws Exception {
		Students student = new Students();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the ID of the Student");
		student.setSid(sc.nextInt());
		System.out.println("Enter the Name of the Student");
		student.setSname(sc.next());
		System.out.println("Enter the Email of the Student");
		student.setEmail(sc.next());
		System.out.println("Enter the password of the Student");
		student.setPassword(sc.next());
		ForeignKeyCheckOff();
		String sql = "insert into student values(?,?,?,?,?,?)";
		psmt = MainCrsApp.connection.prepareStatement(sql);
		psmt.setInt(1, student.getSid());
		psmt.setString(2, student.getSname());
		psmt.setString(3, student.getEmail());
		psmt.setString(4, student.getPassword());
		psmt.setInt(5, 0);
		psmt.setInt(6,-1);
		int x = psmt.executeUpdate();
		if (x > 0) {
			System.out.println("---Student Registered Sucessfully---> " + student.getSname());
		}
		ForeignKeyCheckOn();
	}

	public static void addCourse() throws Exception {
		Courses course = new Courses();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Id of the Course");
		course.setCid(sc.nextInt());
		System.out.println("Enter the Name of the Course");
		course.setCname(sc.next());
		System.out.println("Enter the fees of the Course");
		course.setFees(sc.nextInt());
		System.out.println("Enter the Duration(In Months) of the Course");
		course.setDur_months(sc.nextInt());
		ForeignKeyCheckOff();
		String sql = "insert into course values(?,?,?,?,?)";
		psmt = MainCrsApp.connection.prepareStatement(sql);
		psmt.setInt(1, course.getCid());
		psmt.setString(2, course.getCname());
		psmt.setInt(3, course.getFees());
		psmt.setInt(4, course.getDur_months());
		psmt.setInt(5, 0);
//		psmt.setInt(6, 0);
		int x = psmt.executeUpdate();
		if (x > 0) {
			System.out.println("---Course Registered Sucessfully---> " + course.getCname());
			System.out.println();
		}
		ForeignKeyCheckOn();

	}

	public static void addProfessor() throws Exception {
		Professors professor = new Professors();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the ID of Professor ");
		professor.setPid(sc.nextInt());
		System.out.println("Enter the Name of Professor ");
		professor.setPname(sc.next());
		System.out.println("Enter the exp of Professor ");
		professor.setExp(sc.nextInt());
		System.out.println("Enter the Password of Professor ");
		professor.setPassword(sc.next());
		ForeignKeyCheckOff();
		String sql = "insert into professor values(?,?,?,?) ";
		psmt = MainCrsApp.connection.prepareStatement(sql);
		psmt.setInt(1, professor.getPid());
		psmt.setString(2, professor.getPname());
		psmt.setInt(3, professor.getExp());
		psmt.setString(4, professor.getPassword());
		int x = psmt.executeUpdate();
		if (x > 0) {
			System.out.println("---Professor Registered Sucessfully---> " + professor.getPname());
		}
		ForeignKeyCheckOn();

	}

	public static void viewAllStudent() throws SQLException {
		ResultSet res;
		String sql = "select * from student";
		st = MainCrsApp.connection.createStatement();

		res = st.executeQuery(sql);
		System.out.println("---All Students---");
		while (res.next() == true) {
			System.out.println();
			System.out.print("Student_Id ");
			System.out.print("->");
			System.out.println(res.getInt(1));
			System.out.print("Student_Name ");
			System.out.print("->");
			System.out.println(res.getString(2));
			System.out.print("Student_Email ");
			System.out.print("->");
			System.out.println(res.getString(3));
			System.out.println("--------------------------");
		}

	}

	public static void viewAllCourses() throws SQLException {
		ResultSet res;
		String sql = "select * from course";
		st = MainCrsApp.connection.createStatement();
		res = st.executeQuery(sql);
		System.out.println("---All Courses---");
		while (res.next() == true) {
			System.out.print("Course_Id ");
			System.out.print("->");
			System.out.println(res.getInt(1));
			System.out.print("Course_Name ");
			System.out.print("->");
			System.out.println(res.getString(2));
			System.out.print("Course_Fees ");
			System.out.print("->");
			System.out.println(res.getInt(3) + "rs");
			System.out.print("Course_Duration_In_Month");
			System.out.print("->");
			System.out.println(res.getInt(4) + " month(s)");
			System.out.println();
			System.out.println("--------------------------");
			System.out.println();

		}

	}
	
	public static void viewAllProfessors() throws SQLException {
		ResultSet res;
		String sql = "select * from professor";
		st = MainCrsApp.connection.createStatement();
		res = st.executeQuery(sql);
		System.out.println("----All Professor----");
		while (res.next() == true) {
			System.out.print("Professor_Id ");
			System.out.print("->");
			System.out.println(res.getInt(1));
			System.out.print("Professor_Name ");
			System.out.print("->");
			System.out.println(res.getString(2));
			System.out.print("Professor_Experience ");
			System.out.print("->");
			System.out.println(res.getInt(3));
			System.out.println();
			System.out.println("--------------------------");
			System.out.println();
		}

	}

}
