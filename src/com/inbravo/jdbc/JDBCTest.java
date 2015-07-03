package com.inbravo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * 
 * @author amit.dixit
 *
 */
public final class JDBCTest {

	public static void main(String[] args) throws Exception {

		/* Create new connection */
		final Connection conn = getConnection();

		/* Create new SQL statement */
		final Statement insertStatement = conn.createStatement();

		/* Insert a new record */
		insertStatement.executeUpdate("INSERT INTO INBRAVO.TXT(NAME, ADDRESS) VALUES('AMIT','GHAZIABAD')");

		/* Create new SQL statement */
		final Statement selectStatement = conn.createStatement();
		ResultSet rs = selectStatement.executeQuery("SELECT * FROM INBRAVO.TXT ");

		while (rs.next()) {

			/* Print all values */
			System.out.println("Name =" + rs.getString("NAME") + ", Address =" + rs.getString("ADDRESS"));
		}

		/* Get table meta data information */
		System.out.println("Column Count =" + rs.getMetaData().getColumnCount());

		selectStatement.close();
		insertStatement.close();
		conn.close();
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private static final Connection getConnection() throws Exception {

		final String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
		final String url = "jdbc:odbc:InBravo";
		final String username = "";
		final String password = "";

		/* Load Driver class */
		Class.forName(driver);

		/* Get connection from Driver Manager */
		return DriverManager.getConnection(url, username, password);
	}
}
