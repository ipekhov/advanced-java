package my.learning.mysql;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		var db = Database.instance();
		
		try {
			db.connect();
			System.out.println("Connected to the database");
			
		} catch (SQLException e) {
			System.out.println("Cannot connect to the database");
		}
			
		
		try {
			db.close();
			System.out.println("Database connection is closed");
		} catch (SQLException e) {
			System.out.println("Cannot close the database connection");
		}
	}
}
