package cn.itsz.newsim.common;
import java.sql.*;
import java.util.*;
import java.io.*;

public class DBconncet {
	private static String username = "";
	private static String password = "";
	private static String url = "";
	private static String driver = "";

	static {
		getProperties();
		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	private static void getProperties() {
		
		InputStream input = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("newsim.properties");
		
		Properties config = new Properties();
		try {
		
			config.load(input);
			driver = config.getProperty("driver");
			username = config.getProperty("username");
			password = config.getProperty("password");
			url = config.getProperty("url");
			
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static Connection getConnection() {
		Connection conn = null;
		try {

			conn = DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}


	public static void close(ResultSet rs, Statement state, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (state != null)
				state.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		;
		try {
			Statement st = DBconncet.getConnection().createStatement();
		/*	st.execute("insert into userprofile(loginId,passWord,email,addNo,telephone,username,client)"+
"values('3692581','369258','dsf@q.com','dsf','sdf','sdf','sdf')");*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("连接成功");
	}
}

