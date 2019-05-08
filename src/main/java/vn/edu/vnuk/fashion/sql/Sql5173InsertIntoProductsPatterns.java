package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5173InsertIntoProductsPatterns {
	private final Connection connection;
	private final String sqlQuery;
	
	public  Sql5173InsertIntoProductsPatterns(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO products_patterns (product_id, pattern_id) "
				+ 	" values "
				
				+ 	"( 1 , 4 ), "
				+ 	"( 2 , 6 ), "
				+   "( 3 , 2 )"
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("> Sql5173InsertIntoProductsPatterns started");
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'products_patterns\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<   Sql5173InsertIntoProductsPatterns ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
