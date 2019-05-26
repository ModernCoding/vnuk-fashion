package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2180CreateProductsGenders {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2180CreateProductsGenders (Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE IF NOT EXISTS products_genders ("
				+ 	"id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
				+ 	"product_id BIGINT NOT NULL, "  
				+	"gender_id BIGINT NOT NULL, "
				+ 	"CONSTRAINT fk_products_genders_product_id FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,"
				+ 	"CONSTRAINT fk_products_genders_gender_id FOREIGN KEY (gender_id) REFERENCES genders(id) ON DELETE CASCADE"
				+ 	") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2180CreateProductsGenders started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'products_genders \' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2180CreateProductsGenders ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
