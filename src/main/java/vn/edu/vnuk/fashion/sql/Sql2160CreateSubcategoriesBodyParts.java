package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2160CreateSubcategoriesBodyParts {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2160CreateSubcategoriesBodyParts (Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE IF NOT EXISTS subcategories_body_parts ("
				+ 	"id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ 	"subcategory_id INT NOT NULL,"  
				+	"body_part_id INT NOT NULL,"
				+ 	"CONSTRAINT fk_subcategories_body_parts_subcategory_id FOREIGN KEY (subcategory_id) REFERENCES subcategories(id),"
				+ 	"CONSTRAINT fk_subcategories_body_parts_body_part_id FOREIGN KEY (body_part_id) REFERENCES body_parts(id)"
				+ ");"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2160CreateSubcategoriesBodyParts started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'subcategories_body_parts \' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2160CreateSubcategoriesBodyParts ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
