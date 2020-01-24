package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static LogUtil log = new LogUtil();

	private static String url;
	
	private static String user;
	
	private static String password;
	
	private static ConnectionFactory cf;
	
	private ConnectionFactory() {
		url = "jdbc:postgresql://" + System.getenv("POSTGRES_URL") + ":5432/postgres?currentSchema=superhuman_registry";
		user = System.getenv("POSTGRES_USERNAME");
		password = System.getenv("POSTGRES_PASSWORD");
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection createConnection() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			log.debug(e.getMessage());
		}
		
		return conn;
	}

	public static Connection getConnection() {
		if (cf == null) {
			cf = new ConnectionFactory();
		}
		
		return cf.createConnection();
	}
}
