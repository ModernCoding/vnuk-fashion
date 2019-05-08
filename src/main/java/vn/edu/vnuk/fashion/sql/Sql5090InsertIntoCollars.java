package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5090InsertIntoCollars {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5090InsertIntoCollars(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO collars (label) "
				+ 	"values "
				
				+ 	"('Flat'), "
				+ 	"('Full roll'), "
				+ 	"('Partial roll'),"
				+ 	"('Convertible'),"
				+ 	"('Shawl type'),"
				+ 	"('Mandarin'),"
				+ 	"('Puritan'),"
				+ 	"('Sailor'),"
				+ 	"('Peter Pan'),"
				+ 	"('Tie with collar'),"
				+ 	"('Straight'),"
				+ 	"('Rolled'),"
				+ 	"('Bertha'),"
				+ 	"('Jabot'),"
				+ 	"('Turtleneck'), "
				
				+	"('Classic'),"
				+ 	"('Forward point'),"
				+ 	"('Wingtip'),"
				+ 	"('Mandarin'),"
				+ 	"('Constrast'),"
				+ 	"('Tab'),"
				+ 	"('Cutaway'),"
				+ 	"('Italian'),"
				+ 	"('Wide spead'),"
				+ 	"('Curved spread'),"
				+ 	"('Abbreviated spread'),"
				+ 	"('Band'),"
				+ 	"('Pin')"
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5090InsertIntoCollars started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'collars\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5090InsertIntoCollars ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
