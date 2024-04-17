package com.prm.jdbc;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.CallableStatement;

public class TestJDBC {



	    // JDBC URL, username, and password of MySQL server
	    private static final String JDBC_URL = "jdbc:mysql://sql6.freesqldatabase.com:3306/sql6699156";
	    private static final String USERNAME = "sql6699156";
	    private static final String PASSWORD = "Xk7xKz6pZq";

	    public static void main(String[] args) {
	        // Load the MySQL JDBC driver
	        try {
	        	//1. load the driver
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            System.err.println("Failed to load MySQL JDBC driver.");
	            e.printStackTrace();
	            return;
	        }

	        // 2. Attempt to establish a connection to the database
	        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
	            if (connection != null) {
	                System.out.println("Connected to the MySQL database!");
	            } else {
	                System.out.println("Failed to establish connection.");
	            }
	            
	            
	            //3. query the table and display result
	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT * FROM EMP WHERE ID > 12");
	            
	            //STATEMENT
	            //SELECT * FROM EMP WHERE ID > 12;
	            //SELECT * FROM EMP WHERE ID > 25;
	            
	            
	            //PreparedStatement
	            //SELECT * FROM EMP WHERE ID > ?;
	            
	            PreparedStatement ps = connection.prepareStatement("SELECT * FROM EMP WHERE ID > ?");
	            ps.setInt(1, 100);
	            ResultSet rs1 = ps.executeQuery();
	            	         
	            
	            
	            try {
	            //1. start the transaction
	            connection.setAutoCommit(false);
	            
	            //2. fetch the person record.
	            //PreparedStatement ps = connection.prepareStatement("SELECT * FROM EMP WHERE ID = ?");
	            //store the record to a java object
		          // if there is a error
		           //connection.rollback();
	            //3. add the person's address1
	            //PreparedStatement ps = connection.prepareStatement("INSERT INTO EMP(ADDRESS) VALUES (?)");
	            
	            //4. add the person's address2
	            //PreparedStatement ps = connection.prepareStatement("INSERT INTO EMP(ADDRESS) VALUES (?)");
	          // if there is a error
	           //connection.rollback();
	           
	            //5. finally commit the transaction
	            connection.commit();
	            
	            } catch(Exception ex) {
	            	connection.rollback();
	            }
	            
	            
	            
	            //CallableStatement
	            //SELECT ID, NAME FROM EMP WHERE ID ? ;
	            //EXECUTE STORED PROCEDURES AND FUNCTIONS
	            
	            CallableStatement cs = connection.prepareCall("EXEC UPDATE_PRICE(?)");
	            cs.setInt(1, 100);
	            ResultSet rs2 = ps.executeQuery();

	            ps.close();
	            cs.close();
	            connection.close();
	            
	            
	            //4. get the result 
	            while(rs.next()) {
	            	System.out.println(rs.getInt(1));
	            	System.out.println(rs.getString(2));
	            	System.out.println(rs.getString(3));
	            }
	            
	            //3. insert operation
	            insertRecord(connection, "Anil", "Tvm");
	            //3. delete operation
	            deleteRecord(connection, 1);
	            
	            
	            
	        } catch (SQLException e) {
	            System.err.println("Failed to connect to MySQL database.");
	            e.printStackTrace();
	        }
	    }

		private static void insertRecord(Connection connection, String ename, String address) throws SQLException {
			//query the table and display result
			PreparedStatement stmt = connection.prepareStatement("INSERT INTO EMP(ENAME, ADDRESS) VALUES (?,?)");
			stmt.setString(1, ename);
			stmt.setString(2, address);

			Boolean status = stmt.execute();
			
			System.out.println(status);
			if (!status) {
				System.out.println("insert successful");
			}
		}
		
		private static void deleteRecord(Connection connection, Integer id) throws SQLException {
			//query the table and display result
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM EMP WHERE ID = ?");
			stmt.setInt(1, id);

			Boolean status = stmt.execute();

			if (!status) {
				System.out.println("delete successful");
			}
		}
}
