package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5100InsertIntoColors {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5100InsertIntoColors(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO colors (label) "
				+ 	"values "
				+ 	"('Pink Blossom'), "
				+ 	"('Red Cardinal'), "
				+ 	"('Red Brick'), "
				+ 	"('Red Tomato'), "
				+ 	"('Orange Juice'), "
				+ 	"('Orange Autumn'), "
				+ 	"('yellow Golden'),"
				+ 	"('Yellow Sun'),"
				+ 	"('Green Prarie'),"
				+ 	"('Green Key Lime'),"
				+ 	"('Green Sage'),"
				+ 	"('Green Olive'),"
				+ 	"('Green Forest'),"
				+ 	"('Green Grass'),"
				+ 	"('Blue Seafoam'),"
				+ 	"('Blue Peacock'),"
				+ 	"('Blue Olympic'),"
				+ 	"('Blue Sports'),"
				+ 	"('Blue Saphire'),"
				+ 	"('Blue Navy'),"
				+ 	"('Blue Evening'),"
				+ 	"('Blue Powder'),"
				+ 	"('Blue WedgeWood'),"
				+ 	"('Purple Plum'),"
				+ 	"('Purple Lavendar'),"
				+ 	"('Pink Light Orchid'),"
				+ 	"('Brown Dark'),"
				+ 	"('Brown Cocoa'),"
				+ 	"('Beige Buckskin'),"
				+ 	"('Yellow Harvest Maize'),"
				+ 	"('Beige Tusk'),"
				+ 	"('Pink Carnation'),"
				+ 	"('Pink Cotton Candy'),"
				+ 	"('Black Shadow'),"
				+ 	"('Gray Dark'),"
				+ 	"('Gray Slate'),"
				+ 	"('White'),"
				+ 	"('Metallic Silver'),"
				+ 	"('Metallic Gold'),"
				+ 	"('Metallic Copper'),"
				+ 	"('Etched Glass')"
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5100InsertIntoColors started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'colors\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5100InsertIntoColors ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
	
}
