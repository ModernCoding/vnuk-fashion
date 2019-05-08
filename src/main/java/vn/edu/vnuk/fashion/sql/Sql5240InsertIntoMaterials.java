package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5240InsertIntoMaterials {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5240InsertIntoMaterials(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO materials (label) "
				+ 	"values "
				+ 	"('Diamond'), "
				+ 	"('Gold'), "
				+ 	"('Silver'), "
				+ 	"('Titanium'), "
				+ 	"('Fabric'), "
				+ 	"('Denim'), "
				+ 	"('Leather'), "
				+ 	"('Down'), "
				+ 	"('Fur'), "
				+ 	"('Nylon'), "
				+ 	"('Polyesters'), "
				+ 	"('Spandex'), "
				+ 	"('Flannel'), "
				+ 	"('Lyocell'), "
				+ 	"('Natural fibers'), "
				+ 	"('Felt'), "
				+ 	"('Wool'), "
				+ 	"('Silk'), "
				+ 	"('Straw'), "
				+ 	"('Linen'), "
				+ 	"('Chiffon fabric'), "
				+ 	"('Khaki'), "
				+ 	"('Mohair'), "
				+ 	"('Buckram'), "
				+ 	"('Lace'), "
				+ 	"('Tussore')"
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5240InsertIntoMaterials started");
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'materials\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5240InsertIntoMaterials ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
