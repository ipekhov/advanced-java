package my.learning.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

	private static Database INSTANCE;

	private final String url;
	private final String user;
	private final String password;

	private Connection conn;
	private Properties props;

	public static synchronized Database instance() {
		if (INSTANCE == null) {
			INSTANCE = new Database();
		}
		return INSTANCE;
	}

	private Database() {
		props = Profile.getProperties("database");

		this.url = String.format("jdbc:mysql://%s:%s/%s",
				props.getProperty("host"),
				props.getProperty("port"),
				props.getProperty("database")
				);
		this.user = props.getProperty("user");
		this.password = props.getProperty("password");
	}

	public Connection getConnection() {
		return this.conn;
	}

	public void connect() throws SQLException {
		this.conn = DriverManager.getConnection(this.url, this.user, this.password);
	}

	public void close() throws SQLException {
		this.conn.close();
	}
}
