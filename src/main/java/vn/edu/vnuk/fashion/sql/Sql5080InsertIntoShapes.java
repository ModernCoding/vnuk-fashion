package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5080InsertIntoShapes {

	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5080InsertIntoShapes(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO shapes (label) "
				+ 	"values "
				+ 	"('Aviator'), "
				+ 	"('Cat Eye'), "
				+ 	"('Butterfly'), "
				+ 	"('Clubmaster Style'), "
				+ 	"('Wayfarer Style'), "
				+ 	"('Oversized'), "
				+ 	"('Round'),"
				+ 	"('Oval'),"
				+ 	"('Rectangular'),"
				+ 	"('Square'),"
				+ 	"('Sport'),"
				+ 	"('Shield'),"
				+ 	"('Accordion'),"
				+ 	"('Bubble'),"
				+ 	"('Circle'),"
				+ 	"('Cowl'),"
				+ 	"('Draped'),"
				+ 	"('Godet'),"
				+ 	"('Gypsy'),"
				+ 	"('Knife-Pleated'),"
				+ 	"('Layered'),"
				+ 	"('Mermaid'),"
				+ 	"('Mini'),"
				+ 	"('Paneled'),"
				+ 	"('Pegged'),"
				+ 	"('Pencil'),"
				+ 	"('Peplum'),"
				+ 	"('Ruffled'),"
				+ 	"('Sarong'),"
				+ 	"('Slit'),"
				+ 	"('Straight'),"
				+ 	"('Trumpet'),"
				+ 	"('Tube'),"
				+ 	"('Tulip'),"
				+ 	"('Tulle'),"
				+ 	"('Basic Normal Fitted'),"
				+ 	"('Shift'),"
				+ 	"('Sheat'),"
				+ 	"('Princess'),"
				+ 	"('A-line'),"
				+ 	"('Tunic'),"
				+ 	"('Bouffan'),"
				+ 	"('Tent'),"
				+ 	"('Jumper'),"
				+ 	"('Pinafone'),"
				+ 	"('Jacket Dress'),"
				+ 	"('Shirtwaist'),"
				+ 	"('Peasant'),"
				+ 	"('Flapper'),"
				+ 	"('Maternity'),"
				+ 	"('Caftan'),"
				+ 	"('MuuMuu'),"
				+ 	"('Dashiki'),"
				+ 	"('Granny'),"
				+ 	"('Long Dinner')"
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5080InsertIntoShapes started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'shapes\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5080InsertIntoShapes ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
	
}
