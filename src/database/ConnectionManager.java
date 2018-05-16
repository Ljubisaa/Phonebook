package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static ConnectionManager instance = null;

	private static final String USERNAME = "root";
	private static final String PASSWORD = "ljubisa1";
	private static final String CONN_STRING = "jdbc:mysql://localhost/students?useSSL=false&serverTimezone=UTC";

	private Connection conn = null;

	private ConnectionManager() {

	}

	public static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	private boolean openConnection() {
		try {
			conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	public Connection getConnection() {
		if (conn == null) {
			if (openConnection()) {
				System.out.println("Konekcija otvorena.");
				return conn;
			} else {
				return null;
			}
		}
		return conn;
	}

	public void close() {
		System.out.println("Konekcija zatvorena.");
		try {
			conn.close();
			conn = null;
		} catch (Exception e) {

		}
	}
}
