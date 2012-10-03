package com.itsz.util.database;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
public class TomcatConnection {
    private static DataSource ds;
  private TomcatConnection() {
  }
  static{
      try {
          Context initCtx = new InitialContext();
          ds = (DataSource) initCtx.lookup("java:comp/env/tdxdb");
      }catch (NamingException ex) {
       ex.printStackTrace();
       //System.out.println("find datasource exception");
    }
  }
  public static Connection getConnection() {

    Connection conn = null;
    try {
      conn = ds.getConnection();
    }catch(SQLException ex){
       ex.printStackTrace();
       //System.out.println("create connection exception");
    }
    return conn;

  }
  /**
     * Use this method to return the connection to the connection pool
     * Do not use this method to close connection that is not from
     * the connection pool
     * @param connection : the connection that needs to be returned to the pool
     */
    public static void closeConnection(Connection connection) {
        if (connection == null) return;
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

  /**
     * Use this method to close the Statement
     * @param statement : the statement that needs to be closed
     */
    public static void closeStatement(Statement statement) {
        try {
            if (statement != null) statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Use this method to close the ResultSet
     * @param rs : the resultset that needs to be closed
     */
    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }

}