package my.learning.mysql;

import java.io.IOException;
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
	private Properties props = new Properties();
	
	public static synchronized Database instance() {
		if(INSTANCE == null) {
			try {
				INSTANCE = new Database();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return INSTANCE;
	}
	
	private Database() throws IOException {
		
		props.load(App.class.getResourceAsStream("/config/database.dev.properties"));
		
		String host = props.getProperty("host");
		String port = props.getProperty("port");
		String database = props.getProperty("database");
		
		this.url = String.format("jdbc:mysql://%s:%s/%s", host, port, database);
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
