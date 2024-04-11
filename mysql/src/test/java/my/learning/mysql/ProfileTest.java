package my.learning.mysql;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProfileTest {

	@Test
	public void testLoadDbConfig() {
		var props = Profile.getProperties("database");
		assertNotNull("Cannot load database properties", getClass());

		var dbName = props.getProperty("database");
		assertEquals("dbName incorrect", "peopletest", dbName);
	}
}
