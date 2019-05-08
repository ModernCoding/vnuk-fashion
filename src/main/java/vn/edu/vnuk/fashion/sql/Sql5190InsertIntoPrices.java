package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5190InsertIntoPrices {
	private final Connection connection;
	private final String sqlQuery;
	
	public  Sql5190InsertIntoPrices(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO prices (products_size_id, products_color_id, products_pattern_id, products_length_id, seller_id, price_type_id, value) "
				+ 	" values "
				
				+ 	"( 1 , 1 , 3 , 1 , 6 , 1 , 345.5), "
				+ 	"( 3 , 2 , 1 , 2 , 6 , 2 , 456.2), "
				+ 	"( 2 , 3 , 2, 3 , 2, 1 , 123.4) "
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("> Sql5190InsertIntoPrices started");
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'prices\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<   Sql5190InsertIntoPrices ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
