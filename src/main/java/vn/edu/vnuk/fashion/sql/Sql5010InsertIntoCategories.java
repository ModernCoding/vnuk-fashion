package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5010InsertIntoCategories {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5010InsertIntoCategories(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO categories (label) "
				+ 	"values "
				+ 	"('Clothes'), "
				+ 	"('Footwear'), "
				+ 	"('Hats'), "
				+ 	"('Accessories'), "
				+ 	"('Jewels'), "
				+ 	"('Underwear')"
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5010InsertIntoCategories started");
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'categories\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5010InsertIntoCategories ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
