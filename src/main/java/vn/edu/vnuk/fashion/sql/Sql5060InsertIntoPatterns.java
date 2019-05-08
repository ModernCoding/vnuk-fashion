package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5060InsertIntoPatterns {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5060InsertIntoPatterns(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO patterns (label) "
				+ 	"values "
				
				+ 	"('Abstract'), "
				+ 	"('African'), "
				+ 	"('Animal'), "
				+ 	"('Airbrush'), "
				+ 	"('Basket weave'), "
				+ 	"('Batik'), "
				+ 	"('Birds Eye'), "
				+ 	"('Bulls Eye'), "
				+ 	"('Bohemian fabric'), "
				+ 	"('Brick network'), "
				+ 	"('Camouflage'), "
				+ 	"('Coin Dot'), "
				+ 	"('Chinoserie'), "
				+ 	"('Ditsy'), "
				+ 	"('Dotted swiss'), "
				+ 	"('Damask'), "
				+ 	"('Diagonal'), "
				+ 	"('Floral'), "
				+ 	"('Stripes'), "
				+ 	"('Gingham'), "
				+ 	"('Plaid'), "
				+ 	"('Herringbone'), "
				+ 	"('Paisley'), "
				+ 	"('Houndstooth'), "
				+ 	"('Hawaaian'), "
				+ 	"('Hexagonal'), "
				+ 	"('Ikat'), "
				+ 	"('Metallese'), "
				+ 	"('Natural'), "
				+ 	"('Nursery'), "
				+ 	"('Ogee'), "
				+ 	"('Oriental'), "
				+ 	"('Polka Dot'), "
				+ 	"('Provencal')"				
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5060InsertIntoPatterns started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'patterns\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5060InsertIntoPatterns ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
