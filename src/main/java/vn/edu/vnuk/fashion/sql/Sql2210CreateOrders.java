package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2210CreateOrders {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2210CreateOrders (Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE IF NOT EXISTS orders ("
				+ "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," 
				+ "customer_id INT NOT NULL," 
				+ "price_id INT NOT NULL," 
				+ "qty INT NOT NULL,"
				+ "CONSTRAINT fk_orders_customer_id FOREIGN KEY (customer_id) REFERENCES customers(id),"
				+ "CONSTRAINT fk_orders_price_id FOREIGN KEY (price_id) REFERENCES prices(id)" 
				+ 	") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2210CreateOrders started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'orders \' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2210CreateOrders ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
