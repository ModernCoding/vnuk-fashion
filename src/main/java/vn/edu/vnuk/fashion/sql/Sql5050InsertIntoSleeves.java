package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5050InsertIntoSleeves {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5050InsertIntoSleeves(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO sleeves (label) "
				+ 	"values "
				
				+ 	"('Raglan'), "
				+ 	"('Kimono'), "
				+ 	"('Bishop'),"
				+ 	"('Off Shoulder'),"
				+ 	"('Butterfly'),"
				+ 	"('Puffed'),"
				+ 	"('Cap'),"
				+ 	"('Angle'),"
				+ 	"('Bracelet'),"
				+ 	"('Cuff'),"
				+ 	"('Half'),"
				+ 	"('Bell'),"
				+ 	"('Long'),"
				+ 	"('Cape'),"
				+ 	"('Petal'),"
				+ 	"('Open'),"
				+ 	"('Slit'),"
				+ 	"('Batwing')"
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5050InsertIntoSleeves started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'sleeves\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5050InsertIntoSleeves ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
