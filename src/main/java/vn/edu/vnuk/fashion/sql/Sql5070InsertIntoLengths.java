package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5070InsertIntoLengths {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5070InsertIntoLengths(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO lengths (label) "
				+ 	"values "
				+ 	"('Above the knee'), "
				+ 	"('Knee'), "
				+ 	"('Calf'), "
				+ 	"('Ankle'), "
				+ 	"('Foot')"
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5070InsertIntoLengths started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'lengths\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5070InsertIntoLengths ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
