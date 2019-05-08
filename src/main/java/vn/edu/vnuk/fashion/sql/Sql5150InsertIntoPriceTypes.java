package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5150InsertIntoPriceTypes {
	private final Connection connection;
	private final String sqlQuery;
	
	public  Sql5150InsertIntoPriceTypes(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO price_types (label) "
				+ 	" values "
				+ 	"('Normal'), "
				+ 	"('Special') "
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("> Sql5150InsertIntoPriceTypes started");
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'price_types\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<   Sql5150InsertIntoPriceTypes ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
