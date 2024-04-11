package my.learning.mysql;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
		
		var db = Database.instance();
		db.connect();
		this.conn = db.getConnection();
		this.conn.setAutoCommit(false);
	}
	
	@After
	public void tearDown() throws SQLException {
		Database.instance().close();
	}
	
	private int getMaxId() throws SQLException {
		var stmt = this.conn.createStatement();
		var rs = stmt.executeQuery("select max(id) as id from user");
		rs.next();
		var id = rs.getInt("id");
		stmt.close();
		return id;
	}
	
	private List<User> findUsersInRange(int minId, int maxId) throws SQLException {
		var stmt = this.conn.prepareStatement("select id, name from user where id >= ? and id <= ?");
		stmt.setInt(1, minId);
		stmt.setInt(2, maxId);
		var rs = stmt.executeQuery();
		
		List<User> res = new ArrayList<>();
		while(rs.next()) {
			res.add(new User(rs.getInt("id"), rs.getString("name")));
		}
		stmt.close();
		return res;
	}
	
	@Test
	public void testSaveMultiple() throws SQLException {
		UserDao userDao = new UserDaoImpl();
		for(var user : this.users) {
			userDao.save(user);
		}
		
		var maxId = getMaxId();
		for(int i = 0; i < users.size(); i++) {
			int id = (maxId - users.size()) + i + 1;
			users.get(i).setId(id);
		}
		
		var retrievedUsers = findUsersInRange((maxId - users.size()) + 1, maxId);
		assertEquals("Number of retrieved users not equal to the number of inserted ones", retrievedUsers.size(), this.users.size());
		assertEquals("Retrieved users don't match saved users", users, retrievedUsers);
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
