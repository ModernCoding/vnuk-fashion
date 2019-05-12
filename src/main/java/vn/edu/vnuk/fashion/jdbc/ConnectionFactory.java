package vn.edu.vnuk.fashion.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection() {
		return returnConnection("jdbc:mysql://localhost/vnuk_e_commerce_fashion?useUnicode=true&characterEncoding=UTF-8");
	}
	
	
	public Connection getConnection(String url) {
		return returnConnection(url);
	}
	
	
	private Connection returnConnection(String url) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(
					url, 
					System.getenv("dbUser"), 
					System.getenv("dbPassword")
				);
		}
		
		catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
