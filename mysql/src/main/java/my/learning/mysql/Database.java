package my.learning.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private static final Database DB = new Database();
	private static final String URL = "jdbc:mysql://localhost:3306/people";
	private Connection conn;
	
	public static Database instance() {
		return DB;
	}
	
	private Database() {
	}
	
	public Connection getConnection() {
		return this.conn;
	}
	
	public void connect() throws SQLException {
		this.conn = DriverManager.getConnection(URL, "root", "1234");
	}
	
	public void close() throws SQLException {
		this.conn.close();
	}
}
