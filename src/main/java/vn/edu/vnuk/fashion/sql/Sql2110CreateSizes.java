package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2110CreateSizes {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2110CreateSizes (Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE IF NOT EXISTS sizes ("
				+ 	"id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
				+ 	"universal VARCHAR(255), "
				+   "us VARCHAR(255) NULL, " 
				+	"uk VARCHAR(255) NULL," 
				+	"france VARCHAR(255) NULL, " 
				+	"italy VARCHAR(255) NULL, " 
				+	"germany VARCHAR(255) NULL, " 
				+	"australia VARCHAR(255) NULL, " 
				+  	"japan VARCHAR(255) NULL " 
				+ 	") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2110CreateSizes started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'sizes \' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2110CreateSizes ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
