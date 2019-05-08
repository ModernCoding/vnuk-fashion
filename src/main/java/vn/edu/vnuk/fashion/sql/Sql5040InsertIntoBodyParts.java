package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5040InsertIntoBodyParts {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5040InsertIntoBodyParts(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO body_parts (label) "
				+ 	"values "
				+ 	"('Top'), "
				+ 	"('Bottom'), "
				+ 	"('Whole-length')"
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5040InsertIntoBodyParts started");
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'body_parts\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5040InsertIntoBodyParts ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
