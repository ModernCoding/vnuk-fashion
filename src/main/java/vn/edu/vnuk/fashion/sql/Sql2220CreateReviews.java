package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2220CreateReviews {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2220CreateReviews (Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE IF NOT EXISTS reviews ("
				+ "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," 
				+ "order_id INT NOT NULL," 
				+ "rating INT NOT NULL,"
				+ "description VARCHAR(255) NULL,"
				+ "CONSTRAINT fk_reviews_order_id FOREIGN KEY (order_id) REFERENCES orders(id)"
				+ ");"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2220CreateReviews started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'reviews \' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2220CreateReviews ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
