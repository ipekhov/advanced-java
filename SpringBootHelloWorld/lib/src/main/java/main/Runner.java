package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import entities.User;
import repositories.UserDao;

@Component
public class Runner implements CommandLineRunner {
	
	@Autowired
	private UserDao userDao;

	@Override
	public void run(String... args) throws Exception {
		/* var user = new User("Thomas", "thomas@awesome.com");
		var user1 = new User("Helena", "helena@wonderful.com");
		var user2 = new User("Jacob", "jacob@perfect.com");
		userDao.save(user);
		userDao.save(user1);
		userDao.save(user2); */
		// userDao.findAll().forEach(u -> System.out.println(u));
		// userDao.findById(3L).ifPresent(u -> System.out.println(u));
		
		userDao.findByName("Thomas").forEach(System.out::println);
	}

}
