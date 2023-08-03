package com.digit.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.digit.MaincCrsApp.MainCrsApp;

public class Users {
	
	static PreparedStatement psmt;
	static ResultSet res;
	
	static String username;
	static String password;
	
	
	public static String getUser_name() {
		return username;
	}
	public static void setUser_name(String user_name) {
		Users.username = user_name;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		Users.password = password;
	}
	
	public static boolean AdminLogin() throws Exception {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("---Enter the UserName of Admin---");
		username = sc.next();
		System.out.println("---Enter the Password of Admin---");
		password = sc.next();
		String sql = "select * from admin where username = ? and password = ?";
		psmt = MainCrsApp.connection.prepareStatement(sql);
		psmt.setString(1, username);
		psmt.setString(2, password);
		res = psmt.executeQuery();
		int flag = 0;
		while(res.next()==true) {
			if(res.getString(1).equals(username) && res.getString(2).equals(password)) {
				flag = 1;
			}
			else {
				flag = 0;
			}
	}
		if(flag==1) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
public static boolean StudentLogin() throws Exception {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("---Enter the UserName of Student---");
		 username = sc.next();
		System.out.println("---Enter the Password of Student---");
	     password = sc.next();
		String sql = "select * from student where sname = ? and password = ?";
		psmt = MainCrsApp.connection.prepareStatement(sql);
		psmt.setString(1, username);
		psmt.setString(2, password);
		res = psmt.executeQuery();
		int flag =0;
		while(res.next()==true) {
			if(res.getString(2).equals(username) && res.getString(4).equals(password)) {
				flag = 1;
			}
			else {
				flag = 0;
			}
	}
		if(flag==1) {
			return true;
		}
		else {
			return false;
		}
	}
public static boolean ProfessorLogin() throws Exception {
	
	Scanner sc = new Scanner(System.in);
	System.out.println("---Enter the UserName of Professor---");
	 username = sc.next();
	System.out.println("---Enter the Password of Professor---");
     password = sc.next();
	String sql = "select * from professor where pname = ? and password = ?";
	psmt = MainCrsApp.connection.prepareStatement(sql);
	psmt.setString(1, username);
	psmt.setString(2, password);
	res = psmt.executeQuery();
	int flag =0;
	while(res.next()==true) {
		if(res.getString(2).equals(username) && res.getString(4).equals(password)) {
			flag = 1;
		}
		else {
			flag = 0;
		}
}
	if(flag==1) {
		return true;
	}
	else {
		return false;
	}
}
	

}
