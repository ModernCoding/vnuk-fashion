package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2170CreateProducts {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2170CreateProducts (Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE IF NOT EXISTS products ("
				+ "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
				+ "name VARCHAR(255) NOT NULL,"
				+ "subcategory_id INT NOT NULL, "	
				+ "sleeve_id INT NULL, "
				+ "shape_id INT NULL, "
				+ "collar_id  INT NULL, "
				+ "height_id INT NULL,"
				+ "material_id INT NULL,"
				+ "maker_id INT NOT NULL,"
				+ "CONSTRAINT fk_products_subcategory_id FOREIGN KEY (subcategory_id) REFERENCES subcategories(id),"
				+ "CONSTRAINT fk_products_sleeve_id FOREIGN KEY (sleeve_id) REFERENCES sleeves(id),"
				+ "CONSTRAINT fk_products_shape_id FOREIGN KEY (shape_id) REFERENCES shapes(id),"
				+ "CONSTRAINT fk_products_collar_id FOREIGN KEY (collar_id) REFERENCES collars(id),"
				+ "CONSTRAINT fk_products_height_id FOREIGN KEY (height_id) REFERENCES heights(id),"
				+ "CONSTRAINT fk_products_material_id FOREIGN KEY (material_id) REFERENCES materials(id),"
				+ "CONSTRAINT fk_products_maker_id FOREIGN KEY (maker_id) REFERENCES makers(id)"				
				+ ");"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2170CreateProducts started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'productsproducts \' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2170CreateProducts ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
