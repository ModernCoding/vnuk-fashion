package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5030InsertIntoGenders {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5030InsertIntoGenders(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO genders (label) "
				+ 	"values "
				
				+ 	"('Male'), "
				+ 	"('Female') "
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5030InsertIntoGenders started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'genders\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5030InsertIntoGenders ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
