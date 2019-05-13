package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2100CreateColors {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2100CreateColors (Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE IF NOT EXISTS colors ("
				+ 	"id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
				+ 	"label VARCHAR(255) NOT NULL"
				+ 	") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2100CreateColors started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'colors \' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2100CreateColors ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
