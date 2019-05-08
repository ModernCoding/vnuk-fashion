package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5140InsertIntoMakers {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5140InsertIntoMakers(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO makers (label, address, phone, email) "
				+  "values "
				
				+  "('Prada', 'Milano, Italia', '559910099', 'prada@gmail.com'), "
				+  "('Nike', 'Oregon, USA', '145043869', 'nike@gmail.com'), "
				+  "('Fendi', 'Roma, Italia', '664029374', 'fendi@gmail.com'), "
				+  "('Versace', 'Milano, Italia', '559910099', 'versace@gmail.com'), "
				+  "('Vetements', 'Z�rich, Switzerland', '337839352', 'vetements@gmail.com'), "
				+  "('Dolce & Gabbana', 'Milanio, Italia', '999722541', 'dolceandgabbana@gmail.com'), "
				+  "('Givenchy', 'Paris, France', '977232326', 'givenchy@gmail.com'), "
				+  "('Balenciaga', 'Paris, France', '205136651', 'balenciaga@gmail.com'), "
				+  "('Off-White', 'Milano, Italia', '209613231', 'offwhite@gmail.com'), "
				+  "('Gucci', 'Firenze, Italia', '664550158', 'gucci@gmail.com'), "
				+  "('Kate Spade', 'New York, USA', '698955550', 'katespade@gmail.com'), "
				+  "('Michael Kors', 'New York, USA', '376117628', 'michaelkors@gmail.com'), "
				+  "('Alexander McQueen', 'London, United Kingdom', '246317001', 'alexandermcqueen@gmail.com'), "
				+  "('Yves Saint Laurent', 'Paris, France', '648949735', 'saintlaurent@gmail.com'), "
				+  "('Celine', 'Paris, France', '122369478', 'celine@gmail.com'), "
				+  "('Lana Marks', 'San Francisco, USA', '865449126', 'lanamarks@gmail.com'), "
				+  "('Hilde Palladino', 'Milanio, Italia', '914835826', 'hildepalladino@gmail.com'), "
				+  "('Judith Lieber', 'Paris, France', '953317427', 'judithlieber@gmail.com'), "
				+  "('Marc Jacobs', 'California, USA', '690110211', 'marcjacobs@gmail.com'), "
				+  "('Mouawad', 'Gen�ve, Sweden', '394808345', 'mouawad@gmail.com')"

				+  ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5140InsertIntoMakers started");
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'makers\'");
		
		}
		
		catch (Exception e) {
			e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5140InsertIntoMakers ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}


}
