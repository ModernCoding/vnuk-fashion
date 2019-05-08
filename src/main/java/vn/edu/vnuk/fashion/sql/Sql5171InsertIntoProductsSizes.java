package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5171InsertIntoProductsSizes {
	private final Connection connection;
	private final String sqlQuery;
	
	public  Sql5171InsertIntoProductsSizes(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO products_sizes (product_id, size_id) "
				+ 	" values "
				
				+ 	"( 1 , 1 ), "
				+ 	"( 2 , 2 ), "
				+   "( 3 , 3 )"
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("> Sql5171InsertIntoProductsSizes started");
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'products_sizes\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<   Sql5171InsertIntoProductsSizes ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
