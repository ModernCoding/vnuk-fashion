package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5220InsertIntoReviews {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public  Sql5220InsertIntoReviews(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO reviews (order_id, rating, description) "
				+ 	" values "
				
				+ 	"(1, 4, 'Good. I like it!'), "
				+ 	"(2, 4, 'Good. Fast delivery!'), "
				+ 	"(3, 5, 'Love it!'), "
				+ 	"(4, 3, '3 stars is enough.'), "
				+ 	"(5, 2, 'Bad shipping') "
				
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("> Sql5220InsertIntoReviews started");
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'reviews\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<   Sql5220InsertIntoReviews ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
