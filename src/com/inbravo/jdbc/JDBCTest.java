package com.inbravo.jdbc;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Create ODBC DSN in windows for text file using
 * (http://www.herongyang.com/JDBC/JDBC-ODBC-DSN-for-Flat-File.html)
 * 
 * @author amit.dixit
 *
 */
public final class JDBCTest {

  /**
   * 
   * @param args
   * @throws Exception
   */
  public static final void main(final String... args) throws Exception {

    /* Create new connection */
    final Connection conn = getConnection();

    /* Create new SQL statement */
    final Statement insertStatement = conn.createStatement();

    /* Insert a new record */
    insertStatement.executeUpdate("INSERT INTO INBRAVO.TXT(NAME, ADDRESS) VALUES('ALI','NOIDA')");

    /* Create new SQL statement */
    final Statement selectStatement = conn.createStatement();

    /* Execute the SQL */
    final ResultSet resultSet = selectStatement.executeQuery("SELECT * FROM INBRAVO.TXT");

    while (resultSet.next()) {

      /* Print all values */
      System.out.println("Name =" + resultSet.getString("NAME") + ", Address =" + resultSet.getString("ADDRESS"));
    }

    /* Get table meta data information */
    System.out.println("Column Count =" + resultSet.getMetaData().getColumnCount());

    /* Close all statements/resultsets/connections */
    resultSet.close();
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
