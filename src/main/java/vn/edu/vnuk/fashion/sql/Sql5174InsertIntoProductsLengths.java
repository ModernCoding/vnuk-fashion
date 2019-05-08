package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5174InsertIntoProductsLengths {
	private final Connection connection;
	private final String sqlQuery;
	
	public  Sql5174InsertIntoProductsLengths(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO products_lengths (product_id, length_id) "
				+ 	" values "
				
				+ 	"( 1 , 1 ), "
				+ 	"( 2 , 2 ), "
				+   "( 3 , 3 )"
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("> Sql5174InsertIntoProductsLengths started");
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'products_lengths\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<   Sql5174InsertIntoProductsLengths ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
