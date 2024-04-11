package my.learning.mysql;

import java.sql.SQLException;
import java.util.List;

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
			UserDao userDao = new UserDaoImpl();
			// userDao.save(new User("Harrison"));
			// userDao.save(new User("Annabeth"));
			List<User> users = userDao.findAll();
			for(User u : users) {
				System.out.println(u);
			}
			
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
