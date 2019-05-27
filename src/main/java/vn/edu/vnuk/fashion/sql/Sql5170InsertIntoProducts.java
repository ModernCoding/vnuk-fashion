package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5170InsertIntoProducts {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5170InsertIntoProducts(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO products (name, subcategory_id , sleeve_id , shape_id , collar_id , height_id , material_id , maker_id) "
				+ 	"values "
				
				+ 	"('dragonballz shirt', 1 , 1 , 2 , 16 , 2 , 5 , 1), "
				+ 	"('anime shirt', 1 , 2 , 3 , 8 , 1 , 6 , 2), "
				+ 	"('vintage t-shirt', 2 , 3 , 4 , 8 , 1 , 6 , 3), "
				+ 	"('minion t-shirt', 2 , 4 , 5 , 9 , 1 , 9 , 4), "
				+ 	"('washed jeans', 3 , 1 , 6 , 1 , 1 , 22 , 5), "
				+ 	"('raw jeans', 3 ,  1 , 1 , 1 , 1 , 23 , 6), "
				+ 	"('vintage trousers', 4 , 1 , 2 , 1 , 1 , 26 , 7), "
				+ 	"('school uniform trousers', 4 , 3 , 1 , 1 , 1 , 7 , 8), "
				+ 	"('crew-neck sweater', 5 , 13 , 1 , 16 , 3 , 8 , 9), "
				+ 	"('earth sweater', 5 , 13 , 2 , 16 , 2 , 8 , 10), "
				+ 	"('bomber jacket', 6 , 13 , 2 , 16 , 2 , 10 , 11), "
				+ 	"('denim jacket', 6 , 13 , 2 , 16 , 2 , 11 , 12), "
				+ 	"('destroyed shorts', 7 , 1 , 3 , 2 , 2 , 12 , 13), "
				+ 	"('washed shorts', 7 , 1 , 1 , 1 , 3 , 13 , 14), "
				+ 	"('trench coat', 8 , 13 , 1 , 16 , 1 , 18 , 15), "
				+ 	"('windbreaker' ,8 , 13 , 1 , 16 , 1 , 19 , 16), "
				+ 	"('dog lover pullover', 9 , 13 , 1 , 16 , 1 , 17 , 17), "
				+ 	"('skull pullover', 9 , 13 , 1 , 16 , 1 , 14 , 18), "
				+ 	"('basic harem pants', 10 , 1 , 1 , 1 , 1 , 11 , 19), "
				+ 	"('classic harem pants', 10 , 1 , 1 , 1 , 2 , 20 , 20) "
				
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5170InsertIntoProducts started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'products\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5170InsertIntoProducts ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}

}
