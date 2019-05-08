package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5160InsertIntoSubcategoriesBodyParts {
	private final Connection connection;
	private final String sqlQuery;


	public Sql5160InsertIntoSubcategoriesBodyParts(Connection connection) {
		this.connection = connection;
			
		this.sqlQuery = "INSERT INTO subcategories_body_parts (subcategory_id, body_part_id) "
				+ 	"values "
				+ 	"(1, 1), "
				+ 	"(2, 1), "
				+ 	"(3, 2), "
				+ 	"(4, 2), "
				+ 	"(5, 1), "
				+ 	"(6, 1), "
				+ 	"(7, 2), "
				+ 	"(8, 1), "
				+ 	"(8, 3), "
				+ 	"(9, 1), "
				+ 	"(10, 1), "
				+ 	"(11, 3), "
				+ 	"(12, 3), "
				+ 	"(14, 3), "
				+ 	"(15, 1), "
				+ 	"(15, 2), "
				+ 	"(15, 3), "
				+ 	"(16, 3), "
				+ 	"(17, 2), "
				+ 	"(18, 1), "
				+ 	"(19, 1), "
				+ 	"(20, 3), "
				+ 	"(21, 2), "
				+ 	"(22, 3), "
				+ 	"(23, 3), "
				
				+ 	"(24, 2), "
				+ 	"(25, 2), "
				+ 	"(26, 2), "
				+ 	"(27, 2), "
				+ 	"(28, 2), "
				+ 	"(29, 2), "
				+ 	"(30, 2), "
				+ 	"(31, 2), "
				+ 	"(32, 2), "
				+ 	"(33, 2), "
				+ 	"(34, 2), "
				+ 	"(35, 2), "
				+ 	"(36, 2), "
				+ 	"(37, 2), "
				
				+ 	"(38, 1), "
				+ 	"(39, 1), "
				+ 	"(40, 1), "
				+ 	"(41, 1), "
				+ 	"(42, 1), "
				+ 	"(43, 1), "
				+ 	"(44, 1), "
				+ 	"(45, 1), "
				+ 	"(46, 1), "
				+ 	"(47, 1), "
				+ 	"(48, 1), "
				+ 	"(49, 1), "
				+ 	"(50, 1), "
				+ 	"(51, 1), "
				
				+ 	"(52, 1), "
				+ 	"(53, 1), "
				+ 	"(53, 2), "
				+ 	"(54, 1), "
				+ 	"(55, 1), "
				+ 	"(56, 1), "
				+ 	"(57, 1), "
				+ 	"(57, 2),"
				+ 	"(58, 1), "
				+ 	"(58, 2), "
				+ 	"(59, 1), "
				+ 	"(60, 1), "
				+ 	"(61, 2), "
				+ 	"(62, 1), "
				+ 	"(63, 2), "
				
				+ 	"(64, 1), "
				+ 	"(65, 1), "
				+ 	"(66, 1), "
				+ 	"(67, 1), "
				+ 	"(68, 2), "
				+ 	"(69, 1), "
						
				+ 	"(71, 2), "
				+ 	"(72, 2), "
				+ 	"(73, 3), "
				+ 	"(74, 2), "
				+ 	"(75, 2), "
				+ 	"(76, 2), "
				+ 	"(77, 2), "
				+ 	"(78, 2), "
				+ 	"(79, 2), "
				+ 	"(80, 1)"
				
				+ ";"
			;
		}
		
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5160InsertIntoSubcategoriesBodyParts started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'subcategories_body_parts\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5160InsertIntoSubcategoriesBodyParts ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
	
}
