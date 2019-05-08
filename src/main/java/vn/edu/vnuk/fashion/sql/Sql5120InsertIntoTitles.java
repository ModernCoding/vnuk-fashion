package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5120InsertIntoTitles {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5120InsertIntoTitles(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO titles (label) "
				+ 	"values "
				+ 	"('Mr'), "
				+ 	"('Miss'), "
				+ 	"('Mrs')"
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5120InsertIntoTitles started");
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'titles\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5120InsertIntoTitles ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
