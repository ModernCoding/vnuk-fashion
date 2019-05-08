package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5230InsertIntoHeights {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5230InsertIntoHeights(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO heights (label) "
				+ 	"values "
				+ 	"('Low'), "
				+ 	"('Mid'), "
				+ 	"('High') "
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5230InsertIntoHeights started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'heights\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5230InsertIntoHeights ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
	
}
