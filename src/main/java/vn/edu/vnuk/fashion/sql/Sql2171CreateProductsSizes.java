package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2171CreateProductsSizes {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2171CreateProductsSizes (Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE IF NOT EXISTS products_sizes ("
				+ "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
				+ "product_id BIGINT NOT NULL, "
				+ "size_id BIGINT NULL, "
				+ "CONSTRAINT fk_products_sizes_product_id FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,"
				+ "CONSTRAINT fk_products_sizes_size_id FOREIGN KEY (size_id) REFERENCES sizes(id) ON DELETE CASCADE"				
				+ 	") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2171CreateProductsSizes started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'products_sizes \' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2171CreateProductsSizes ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
