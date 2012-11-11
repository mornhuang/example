/*
Copyright (c) 2006 Beyhan Meyrali [C*]
All rights reserved.
/**
Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:
1. Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright
   notice, this list of conditions and the following disclaimer in the
   documentation and/or other materials provided with the distribution.
3. The name of the author may not be used to endorse or promote products
   derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package net.sourceforge.smsengine;

import org.apache.log4j.Logger;

public class DBConnectionToMySQL {
	//Set logger
    private static final Logger log = Logger.getLogger(StartUp.class.getName());
	
	//The DBUserName String
	private String dbUserName="";
	
	//The DBPassword String
	private String dbPassword="";

	//The DBServerName String
	private String dbServerName="localhost";

	//The DBServerPort String
	private String dbServerPort="3306";	
	
	//The DB Name String
	private String dbName="";	
	
	//The Table Name String
	private String tableName="";	

	//The Column Name String
	private String columnName="";		

	//The External SQL Statement
	private String externalSQLStatement="";		
	
	// The connect string 
	private String connect_string ="jdbc:mysql://localhost:3306/test?user=monty&password=greatsqldb";
  
	// The query we will execute
	private String query = "select 'Hello JDBC: ' || sysdate from dual";

	// The connection to the database
	java.sql.Connection conn = null;
	
	
	/*
	 * Set Database User Name
	 * @param String
	 */
	public void setDBUserName(String dbUserName){
		this.dbUserName = dbUserName;
	}
	
	/*
	 * Get Database User Name
	 * @return String
	 */
	public String getDBUserName(){
		return this.dbUserName;
	}

	/*
	 * Set Database Password
	 * @param String
	 */
	public void setDBPassword(String dbPassword){
		this.dbPassword = dbPassword;
	}
	
	/*
	 * Get Database Password
	 * @return String
	 */
	public String getDBPassword(){
		return this.dbPassword;
	}

	/*
	 * Set Database Name / IP
	 * @param String
	 */
	public void setDBNameOrIP(String dbServerName){
		this.dbServerName = dbServerName;
	}
	
	/*
	 * Get Database Name / IP
	 * @return String
	 */
	public String getDBNameOrIP(){
		return this.dbServerName;
	}
	
	/*
	 * Set Database Server Connection Port
	 * @param String
	 */
	public void setDBPort(String dbServerPort){
		if((dbServerPort != null)&&(dbServerPort.trim().length()>0))
		   this.dbServerPort = dbServerPort;
	}	
	
	/*
	 * Get Database Server Connection Port
	 * @return String
	 */
	public String getDBPort(){
		return this.dbServerPort;
	}
	/*
	 * Set Database User Name
	 * @param String
	 */
	public void setDBName(String dbName){
		this.dbName = dbName;
	}
	
	/*
	 * Get Database Name
	 * @return String
	 */
	public String getDBName(){
		return this.dbName;
	}
	
	/*
	 * Set Table Name
	 * @param String
	 */
	public void setTableName(String tableName){
		this.tableName = tableName;
	}
	
	/*
	 * Get Table Name
	 * @return String
	 */
	public String getTableName(){
		return this.tableName;
	}
	
	/*
	 * Set Column Name
	 * @param String
	 */
	public void setColumnName(String columnName){
		this.columnName = columnName;
	}
	
	/*
	 * Get Column Name
	 * @return String
	 */
	public String getColumnName(){
		return this.columnName;
	}
	
	/*
	 * Set External SQL Statement
	 * @param String
	 */
	public void setExternalSQLStatement(String externalSQLStatement){
		this.externalSQLStatement = externalSQLStatement;
		if (externalSQLStatement.length()>10){
			query = externalSQLStatement;
		}
	}
	
	/*
	 * Get External SQL Statement
	 * @return String
	 */
	public String getExternalSQLStatement(){
		return this.externalSQLStatement;
	}

	private boolean buildConnectionString(){
		
		if(dbUserName.length()<1){
			log.debug("Check the User Name " + dbUserName + "\n");
			System.out.println ("Check the User Name " + dbUserName + "\n");
			return false;
		}

		if(dbPassword.length()<1){
			log.debug("Check the Password " + dbPassword + "\n");
			System.out.println ("Check the Password " + dbPassword + "\n");
			return false;
		}

		if(dbServerName.length()<1){
			log.debug("Check the Server Name " + dbServerName + "\n");
			System.out.println ("Check the Server Name " + dbServerName + "\n");
			return false;
		}

		if(dbServerPort.length()<1){
			log.debug("Check the Server Port " + dbServerPort + "\n");
			System.out.println ("Check the Server Port " + dbServerPort + "\n");
			return false;
		}

		if(dbName.length()<1){
			log.debug("Check the Database Name " + dbName + "\n");
			System.out.println ("Check the Database Name " + dbName + "\n");
			return false;
		}

		connect_string ="jdbc:mysql://" + dbServerName +":"+ dbServerPort+"/"+ dbName + "?user=" + dbUserName + "&password=" + dbPassword;
		return true;
	}
	
	/*
	 * Build Query
	 * That bits checks the table name and column name and sql statement parameters
	 * if SQL statement is null then builds another sql statement with column name and table name parameters
	 */
	private boolean buildQuery(){
		if(externalSQLStatement.length()<11){
			if(columnName.length()<1){
				return false;
			}
			if(tableName.length()<1){
				return false;
			}			
			query = "select " + columnName + " from " + tableName;			
		}
		log.debug ("SQL Statement to run " + query + "\n");
		System.out.println ("SQL Statement to run " + query + "\n");
		return true;
	}
	
	/*
	 * Connect to server
	 * Before you call this method you need to set the parameters
	 * @returns boolean
	 */  
	private boolean connectToDB(){
	      try{
	    	//Check table name/Column and SQL String
	    	if(!buildQuery()){
	    		return false;
	    	}
	    	  
	    	//Build the connection String
	    	if(!buildConnectionString()){	    		
	    		return false;
	    	}
	    	  
	  		// See if we need to open the connection to the database
	  		if (conn == null){
	  			  // Load the JDBC driver
                                  java.sql.DriverManager.registerDriver (new com.mysql.jdbc.Driver());

	  			  // Connect to the databse
	  			  log.debug("Connecting to " + connect_string + "\n");
	  			  System.out.println ("Connecting to " + connect_string + "\n");
	  			  conn = java.sql.DriverManager.getConnection (connect_string);
	  			  log.debug("Connected\n");
	  			  System.out.println ("Connected\n");	  			  
	  		}

	  		return true;
	      }catch (Exception e){
	  		// Oops
	    	  log.debug(e.getMessage () + "\n");
	    	  System.out.println(e.getMessage () + "\n");
	    	  return false;
	      }		
	}
	
	/*
	 * This method reads the data from server
	 * After setting the connection parameters you can call that method without calling connectToDB()
	 * @returns ArrayList
	 */
	public java.util.ArrayList getData(){
		try{
			if (connectToDB()){
				// Create a statement
				java.sql.Statement stmt = conn.createStatement ();

				// Execute the query
				log.debug("Executing query " + query + "\n");
				System.out.println ("Executing query " + query + "\n");
				java.sql.ResultSet rset = stmt.executeQuery (query);

				// Dump the result
				
				java.util.ArrayList data =  new java.util.ArrayList();
				
				while (rset.next ()){
					log.debug(rset.getString (1) + "\n");
					System.out.println (rset.getString (1) + "\n");
					data.add(rset.getString (1));
				}
				// We're done
				log.debug("done.\n");
				System.out.println("done.\n");
				
				//Disconnect from server
				disconnectFromDB();
				
				return data;				
			}else{
				log.debug("Could not connected to server ! \n");
				System.out.println("Could not connected to server ! \n");
				return null;
			}
		}catch(Exception e){
			log.debug(e.getMessage () + "\n");
	    	System.out.println(e.getMessage () + "\n");
			return null;
		}
	}
	
	/*
	 * Disconnect from server
	 * @returns boolean
	 */  
	private boolean disconnectFromDB(){
	      try{
	  		// See if we need to close the connection to the database
	  		if (conn != null){
	  			  conn.close();
	  			  log.debug("disonnected\n");
	  			  System.out.println ("disconnected\n");
	  		}

	  		return true;
	      }catch (Exception e){
	  		// Oops
	    	  log.debug(e.getMessage () + "\n");
	    	  System.out.println(e.getMessage () + "\n");
	    	  return false;
	      }		
	}
	
	public DBConnectionToMySQL() {
		// TODO Auto-generated constructor stub		
		log.debug("Load data from Oracle database");
		System.out.println("Load data from Oracle database");
	}
	
	public static void main(String[] args){
	//
		DBConnectionToMySQL db = new DBConnectionToMySQL();
		db.setDBUserName("UserName");
		db.setDBPassword("Password");
		db.setDBNameOrIP("localhost");
        db.setDBName("sakila");
		db.setColumnName("*");
		db.setTableName("position");
		db.setExternalSQLStatement("select * from sakila.actor");
		db.getData();
	}
}
