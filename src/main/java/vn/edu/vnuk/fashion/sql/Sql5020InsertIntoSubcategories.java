package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5020InsertIntoSubcategories {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5020InsertIntoSubcategories(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO subcategories (category_id, label) "
				+  "values "
				
				+  "(1, 'Shirts'), "
				+  "(1, 'T-shirts'), "
				+  "(1, 'Jeans'), "
				+  "(1, 'Trousers'), "
				+  "(1, 'Sweater'), "
				+  "(1, 'Jackets'), "
				+  "(1, 'Shorts'), "
				+  "(1, 'Coats'), "
				+  "(1, 'Pullover'), "
				+  "(1, 'Harem pants'), "
				+  "(1, 'Tights'), "
				+  "(1, 'Rain suits'), "
				+  "(1, 'Waist coats'), "
				+  "(1, 'Suits'), "
				+  "(1, 'Swimsuits'), "
				+  "(1, 'Dresses'), "
				+  "(1, 'Skirts'), "
				+  "(1, 'Hoodies'), "
				+  "(1, 'Parkas'), "
				+  "(1, 'Pyjamas'), "
				+  "(1, 'Kilts'), "
				+  "(1, 'Aodai'), "
				+  "(1, 'Overalls'), "
				
				+  "(2, 'Sneakers'), "
				+  "(2, 'Trainers'), "
				+  "(2, 'Flip flops'), "
				+  "(2, 'Boots'), "
				+  "(2, 'Slippers'), "
				+  "(2, 'Slides'), "
				+  "(2, 'Slip-on'), "
				+  "(2, 'Wellies'), "
				+  "(2, 'Derby shoes'), "
				+  "(2, 'Oxford shoes'), "
				+  "(2, 'Moccasin'), "
				+  "(2, 'Venetian'), "
				+  "(2, 'Ballet shoes'), "
				+  "(2, 'High heels'), "
				
				+  "(3, 'Baseball cap'), "
				+  "(3, 'Beanie'), "
				+  "(3, 'Beret'), "
				+  "(3, 'Cloche'), "
				+  "(3, 'Cowboy'), "
				+  "(3, 'Fedora'), "
				+  "(3, 'Panama'), "
				+  "(3, 'Homburg'), "
				+  "(3, 'Bucket hat'), "
				+  "(3, 'Bowler'), "
				+  "(3, 'Visor'), "
				+  "(3, 'Boater'), "
				+  "(3, 'Pillbox'), "
				+  "(3, 'Deerstalker'), "
				
				+  "(4, 'Ties'), "
				+  "(4, 'Belts'), "
				+  "(4, 'Handbags'), "
				+  "(4, 'Backpacks'), "
				+  "(4, 'Glasses'), "
				+  "(4, 'Wallets'), "
				+  "(4, 'Purses'), "
				+  "(4, 'Gloves'), "
				+  "(4, 'Scarves'), "
				+  "(4, 'Socks'), "
				+  "(4, 'Landyards'), "
				+  "(4, 'Canes'), "

				+  "(5, 'Rings'), "
				+  "(5, 'Necklaces'), "
				+  "(5, 'Bracelets'), "
				+  "(5, 'Ear-rings'), "
				+  "(5, 'Anklets'), "
				+  "(5, 'Watches'), "
				
				+  "(6, 'Boxer briefs'), "
				+  "(6, 'Trunks'), "
				+  "(6, 'Thongs'), "
				+  "(6, 'Bikinis'), "
				+  "(6, 'Classics'), "
				+  "(6, 'Strings'), "
				+  "(6, 'Cheeky'), "
				+  "(6, 'Tanga'), "
				+  "(6, 'Pantaloons'), "
				+  "(6, 'Slip'), "
				+  "(6, 'Bra')"

				+  ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5020InsertIntoSubcategories started");
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'subcategories\'");
		
		}
		
		catch (Exception e) {
			e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5020InsertIntoSubcategories ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}


}
