package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2020CreateSubcategories {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2020CreateSubcategories(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE IF NOT EXISTS subcategories ("
				+ 	"id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
				+   "category_id INT NOT NULL,"
				+ 	"label VARCHAR(255) NOT NULL,"
				+ 	"CONSTRAINT fk_subcategories_category_id FOREIGN KEY (category_id) REFERENCES categories(id)"
				+ 	");"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2020CreateSubcategories started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'subcategories\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2020CreateSubcategories ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}