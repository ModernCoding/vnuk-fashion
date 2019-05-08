package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5210InsertIntoOrders {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5210InsertIntoOrders(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO orders (customer_id, price_id , qty) "
				+ 	"values "
				
				+ 	"(1 , 1 , 1), "
				+ 	"(2 , 3 , 2), "
				+ 	"(3 , 3 , 3), "
				+ 	"(4 , 2 , 2), "
				+ 	"(5 , 1 , 3) "
		
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5210InsertIntoOrders started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'orders\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5210InsertIntoOrders ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
