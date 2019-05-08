package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2172CreateProductsColors {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2172CreateProductsColors (Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE IF NOT EXISTS products_colors ("
				+ "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
				+ "product_id INT NOT NULL, "
				+ "color_id INT NULL, "
				+ "CONSTRAINT fk_products_colors_product_id FOREIGN KEY (product_id) REFERENCES products(id),"
				+ "CONSTRAINT fk_products_colors_color_id FOREIGN KEY (color_id) REFERENCES colors(id)"				
				+ ");"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2172CreateProductsColors started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'products_colors \' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2172CreateProductsColors ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
