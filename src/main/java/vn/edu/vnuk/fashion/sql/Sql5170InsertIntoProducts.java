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
				
				+ 	"('dragonballz shirt', 1 , 1 , NULL , 16 , NULL , 5 , 1), "
				+ 	"('anime shirt', 1 , 2 , NULL , 8 , NULL , 6 , 2), "
				+ 	"('vintage t-shirt', 2 , 3 , NULL , 8 , NULL , 6 , 3), "
				+ 	"('minion t-shirt', 2 , 4 , NULL , 9 , NULL , 9 , 4), "
				+ 	"('washed jeans', 3 , NULL , NULL , NULL , NULL , 22 , 5), "
				+ 	"('raw jeans', 3 ,  NULL , NULL , NULL , NULL , 23 , 6), "
				+ 	"('vintage trousers', 4 , NULL , NULL , NULL , NULL , 26 , 7), "
				+ 	"('school uniform trousers', 4 , NULL , NULL , NULL , NULL , 7 , 8), "
				+ 	"('crew-neck sweater', 5 , 13 , NULL , 16 , NULL , 8 , 9), "
				+ 	"('earth sweater', 5 , 13 , NULL , 16 , NULL , 8 , 10), "
				+ 	"('bomber jacket', 6 , 13 , NULL , 16 , NULL , 10 , 11), "
				+ 	"('denim jacket', 6 , 13 , NULL , 16 , NULL , 11 , 12), "
				+ 	"('destroyed shorts', 7 , NULL , NULL , NULL , NULL , 12 , 13), "
				+ 	"('washed shorts', 7 , NULL , NULL , NULL , NULL , 13 , 14), "
				+ 	"('trench coat', 8 , 13 , NULL , 16 , NULL , 18 , 15), "
				+ 	"('windbreaker' ,8 , 13 , NULL , 16 , NULL , 19 , 16), "
				+ 	"('dog lover pullover', 9 , 13 , NULL , 16 , NULL , 17 , 17), "
				+ 	"('skull pullover', 9 , 13 , NULL , 16 , NULL , 14 , 18), "
				+ 	"('basic harem pants', 10 , NULL , NULL , NULL , NULL , 11 , 19), "
				+ 	"('classic harem pants', 10 , NULL , NULL , NULL , NULL , 20 , 20) "
				
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
