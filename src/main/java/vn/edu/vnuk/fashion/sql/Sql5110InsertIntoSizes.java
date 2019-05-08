package vn.edu.vnuk.fashion.sql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5110InsertIntoSizes {
	private final Connection connection;
	private final String sqlQuery;
	
	public  Sql5110InsertIntoSizes(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO sizes (universal,us,uk,france,italy,germany,australia,japan) "
				+ 	" values "
				+ 	"('X-Small','2','4','32','36','30','6','5'), "
				+ 	"('Small','4','6','34','38','32','8','7'), "
				+ 	"('Small','6','8','36','40','34','10','9'), "
				+ 	"('Medium','8','10','38','42','36','12','11'), "
				+ 	"('Medium','10','12','40','44','38','14','13'), "
				+ 	"('Large','12','14','42','46','40','16','15'), "
				+ 	"('Large','14','16','44','48','42','18','17'), "
				+ 	"('X-Large','16','18','46','50','44','20','19'), "
				+ 	"('1X','18','20','48','52','46','22','21'), "
				+ 	"('2X','20','22','50','54','48','24','23'), "
				+ 	"('3X','22','24','52','56','50','26','25'), "
				+ 	"('3X','24','26','54','58','52','28','27'), "
				
				+ 	"('Small','32','32','42','42','42','32','42'), "
				+ 	"('Small','34','34','44','44','44','34','44'), "
				+ 	"('Small','36','36','46','46','46','36','46'), "
				+ 	"('Medium','38','38','48','48','48','38','48'), "
				+ 	"('Large','40','40','50','50','50','40','50'), "
				+ 	"('Large','42','42','52','52','52','42','52'), "
				+ 	"('Extra Large','44','44','54','54','54','44','54'), "
				+ 	"('Extra Large','46','46','56','56','56','46','56'), "
				
				+ 	"(' ','5','2.5','35','35','35','2.5','21'), "
				+ 	"(' ','5.5','3','35.5','35.5','35.5','3','21.5'), "
				+ 	"(' ','6','3.5','36','36','36','3.5','22.5'), "
				+ 	"(' ','6.5','4','37','37','37','4','23'), "
				+ 	"(' ','7','4.5','37.5','37.5','37.5','4.5','23.5'), "
				+ 	"(' ','7.5','5','38','38','38','5','24'), "
				+ 	"(' ','8','5.5','38.5','38.5','38.5','5.5','24.5'), "
				+ 	"(' ','8.5','6','39','39','39','6','25'), "
				+ 	"(' ','9','6.5','40','40','40','6.5','25.5'), "
				+ 	"(' ','9.5','7','40.5','40.5','40.5','7','26'), "
				+ 	"(' ','10','7.5','41','41','41','7.5','26.5'), "
				+ 	"(' ','10.5','8','42','42','42','8','27'), "
				+ 	"(' ','11','8.5','42.5','42.5','42.5','8.5','27.5') "
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("> Sql5150InsertIntoSizes started");
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'sizes\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<   Sql5150InsertIntoSizes ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
