package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2173CreateProductsPatterns {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2173CreateProductsPatterns (Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE IF NOT EXISTS products_patterns ("
				+ "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
				+ "product_id INT NOT NULL, "
				+ "pattern_id INT NULL, "
				+ "CONSTRAINT fk_products_patterns_product_id FOREIGN KEY (product_id) REFERENCES products(id),"
				+ "CONSTRAINT fk_products_patterns_pattern_id FOREIGN KEY (pattern_id) REFERENCES patterns(id)"				
				+ ");"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2173CreateProductsPatterns started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'products_patterns \' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2173CreateProductsPatterns ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}