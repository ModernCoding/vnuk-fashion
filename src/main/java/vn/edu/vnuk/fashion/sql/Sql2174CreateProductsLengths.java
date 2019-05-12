package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2174CreateProductsLengths {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2174CreateProductsLengths (Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE IF NOT EXISTS products_lengths ("
				+ "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
				+ "product_id INT NOT NULL, "
				+ "length_id INT NULL, "
				+ "CONSTRAINT fk_products_lengths_product_id FOREIGN KEY (product_id) REFERENCES products(id),"
				+ "CONSTRAINT fk_products_lengths_length_id FOREIGN KEY (length_id) REFERENCES lengths(id)"				
				+ 	") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2174CreateProductsLengths started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'products_lengths \' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2174CreateProductsLengths ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
