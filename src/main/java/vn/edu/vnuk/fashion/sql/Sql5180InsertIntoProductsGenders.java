package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5180InsertIntoProductsGenders {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5180InsertIntoProductsGenders(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO products_genders (product_id, gender_id) "
				+ 	"values "
				
				+ 	"(1, 1), "
				+ 	"(1, 2), "
				+ 	"(2, 1), "
				+ 	"(2, 2), "
				+ 	"(3, 1), "
				+ 	"(3, 2), "
				+ 	"(4, 1), "
				+ 	"(4, 2), "
				+ 	"(5, 1), "
				+ 	"(5, 2), "
				+ 	"(6, 1), "
				+ 	"(6, 2), "
				+ 	"(7, 1), "
				+ 	"(7, 2), "
				+ 	"(8, 1), "
				+ 	"(8, 2), "
				+ 	"(9, 1), "
				+ 	"(9, 2), "
				+ 	"(10, 1), "
				+ 	"(10, 2) "
				
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5180InsertIntoProductsGenders started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'products_genders\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5180InsertIntoProductsGenders ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}

}