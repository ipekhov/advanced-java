package my.learning.mysql;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;

public class UserDaoImplTest {
	
	private static final int NUM_TEST_USERS_LIMIT = 10000;
	
	private Connection conn;
	private List<User> users;
	
	private List<User> loadUsers() throws IOException {
		return Files
			.lines(Paths.get("names.txt"))
			.map(line -> line.split("[^A-Za-z]"))
			.map(Arrays::asList)
			.flatMap(list -> list.stream())
			.filter(word -> word.length() > 3 && word.length() < 20)
			.distinct()
			.sorted()
			.map(word -> new User(word))
			.limit(NUM_TEST_USERS_LIMIT)
			.collect(Collectors.toList());
	}
	
	@Before
	public void setUp() throws SQLException, IOException {
		this.users = loadUsers();
		this.users.forEach(System.out::println);
		System.out.println(this.users.size());
		
		var db = Database.instance();
		db.connect();
		this.conn = db.getConnection();
		this.conn.setAutoCommit(false);
	}
	
	@After
	public void tearDown() throws SQLException {
		Database.instance().close();
	}
	
	public void testSaveMultiple() {
		
	}

	@Test
	public void testSave() throws SQLException {
		User user = new User("Jupiter");
		
		UserDao userDao = new UserDaoImpl();
		userDao.save(user);
		
		var stmt = this.conn.createStatement();
		var rs = stmt.executeQuery("select id, name from user order by id desc");
		
		var result = rs.next();
		assertTrue("Cannot retrieve inserted user", result);
		
		String name = rs.getString("name");
		assertEquals("User retirved doesn't match the one inserted", user.getName(), name);
		
		stmt.close();
	}
}
