package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2190CreatePrices {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2190CreatePrices (Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE IF NOT EXISTS prices ("
				+ 	"id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
				+ 	"products_size_id BIGINT NOT NULL, "  
				+ 	"products_color_id BIGINT NOT NULL, "  
				+ 	"products_pattern_id BIGINT NULL, "  
				+ 	"products_length_id BIGINT NULL, "  
				+	"seller_id BIGINT NOT NULL,"
				+ 	"price_type_id BIGINT NOT NULL, "
				+ 	"value FLOAT NOT NULL,"
				+ 	"CONSTRAINT fk_prices_products_size_id FOREIGN KEY (products_size_id) REFERENCES products_sizes(id),"
				+ 	"CONSTRAINT fk_prices_products_color_id FOREIGN KEY (products_color_id) REFERENCES products_colors(id),"
				+ 	"CONSTRAINT fk_prices_products_pattern_id FOREIGN KEY (products_pattern_id) REFERENCES products_patterns(id),"
				+ 	"CONSTRAINT fk_prices_products_length_id FOREIGN KEY (products_length_id) REFERENCES products_lengths(id),"
				+ 	"CONSTRAINT fk_prices_seller_id FOREIGN KEY (seller_id) REFERENCES sellers(id), "
				+ 	"CONSTRAINT fk_prices_price_type_id FOREIGN KEY (price_type_id) REFERENCES price_types(id)"
				+ 	") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2190CreatePrices started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'prices \' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2190CreatePrices ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
