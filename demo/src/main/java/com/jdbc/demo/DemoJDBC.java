package com.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DemoJDBC {

	private static final Log LOGGER = LogFactory.getLog(DemoJDBC.class);
	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String DB_URL = "jdbc:sqlserver://localhost;trustServerCertificate=true;databaseName=Vicky;integratedSecurity=true";

	static final String USER = "ASUS\\ASUS";
	static final String PASS = " ";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL,USER,PASS);
			statement = connection.createStatement();
			String sql;
			sql = "select * from VICKYDATA";
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				String name = resultset.getString(1);
				int age = resultset.getInt(2);
				System.out.println(" Name: " + name + ", Age: " + age);
			}
			resultset.close();
			statement.close();
			connection.close();
		} catch (SQLException se) {
			// This handles errors for JDBC
			System.out.println(se);
		} catch (Exception e) {
			// This handles errors for Class.forName
			LOGGER.info(e);
		} finally {
			// finally block closes all the resources
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException se2) {
				LOGGER.info(se2);
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				LOGGER.info(se);
			}

		}
	}
}
