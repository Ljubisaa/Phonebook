package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.ConnectionManager;
import model.User;

public class Login {

	private static Connection conn = ConnectionManager.getInstance().getConnection();

	public static boolean loginUser(User user) throws Exception {

		String query = "SELECT * FROM phonebook.user WHERE userName =? AND password =?";
		ResultSet rs = null;

		try (PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPassword());

			rs = stmt.executeQuery();
			if (rs.next()) {

				if (user.getUserName().equals(rs.getString("userName"))
						&& user.getPassword().equals(rs.getString("password"))) {
					rs.close();
					return true;
				}
			} else {
				System.out.println("Incorrect username/password.");
				return false;
			}
			return true;
		}
	}

}
