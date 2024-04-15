package my.learning.mysql;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * MySQL App
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
			
			// var users = userDao.findAll();
			// users.forEach(System.out::println);

			/* var userOpt = userDao.findById(4);
			if(userOpt.isPresent()) {
				User u = userOpt.get();
				System.out.println("Retrieved: " + u);
				u.setName("Harry");
				userDao.update(u);
			} else {
				System.out.println("No user retrieved");
			} */
			
			// userDao.delete(new User(11, null));
			// userDao.delete(new User(13, null));
			
			userDao.findAll()
				   .forEach(System.out::println);
			
		} catch (SQLException e) {
			System.out.println("Cannot connect to the database");
			e.printStackTrace();
			return;
		}
			
		
		try {
			db.close();
			System.out.println("Database connection is closed");
		} catch (SQLException e) {
			System.out.println("Cannot close the database connection");
		}
	}
}
