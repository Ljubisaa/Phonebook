package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import database.ConnectionManager;

public class Register {

	private static Connection conn = ConnectionManager.getInstance().getConnection();

	public static void registerUser() throws SQLException {
		String query = "INSERT INTO phonebook.user(fullName, userName, password) VALUES(?, ?, ?)";
		Scanner input = new Scanner(System.in);

		System.out.println("Enter fullname: ");
		String fullName = input.nextLine();
		System.out.println("Enter username:");
		String userName = input.nextLine();
		System.out.println("Enter password: ");
		String password = input.nextLine();

		try (PreparedStatement stm = conn.prepareStatement(query);) {

			stm.setString(1, fullName);
			stm.setString(2, userName);
			stm.setString(3, password);

			stm.executeUpdate();
			System.out.println("Registration successful.");
		}
		input.close();
	}
}
