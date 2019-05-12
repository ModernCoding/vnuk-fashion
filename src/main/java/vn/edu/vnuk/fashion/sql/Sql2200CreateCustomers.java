package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2200CreateCustomers {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2200CreateCustomers (Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE IF NOT EXISTS customers ("
				+ "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," 
				+ "title_id INT NOT NULL," 
				+ "label VARCHAR(255) NOT NULL," 
				+ "address VARCHAR(255) NOT NULL," 
				+ "phone VARCHAR(255) NOT NULL," 
				+ "email VARCHAR(255) NULL," 				
				+ "CONSTRAINT fk_customers_title_id FOREIGN KEY (title_id) REFERENCES titles(id)"
				+ 	") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2200CreateCustomers started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'customers \' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2200CreateCustomers ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
