package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5172InsertIntoProductsColors {
	private final Connection connection;
	private final String sqlQuery;
	
	public  Sql5172InsertIntoProductsColors(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO products_colors (product_id, color_id) "
				+ 	" values "
				
				+ 	"( 1 , 3 ), "
				+ 	"( 2 , 4 ), "
				+   "( 3 , 5 )"
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("> Sql5172InsertIntoProductsColors started");
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'products_colors\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<   Sql5172InsertIntoProductsColors ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
