package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import model.Contact;
import model.User;

public class ContactManager implements ContactInterface {

	private static Connection conn = ConnectionManager.getInstance().getConnection();

	public void addNewContact(User user) throws SQLException {
		Scanner input = new Scanner(System.in);
		String query = "INSERT INTO phonebook.contact(contactName, phoneNumber, userName) VALUES(?,?,?)";

		System.out.println("Enter contact name:");
		String contactName = input.nextLine();
		System.out.println("Enter phone number:");
		String phoneNumber = input.nextLine();
		try (PreparedStatement stm = conn.prepareStatement(query)) {

			stm.setString(1, contactName);
			stm.setString(2, phoneNumber);
			stm.setString(3, user.getUserName());

			stm.executeUpdate();

			System.out.println("Contact added.");
			input.close();
		}
	}

	public void deleteContact(String phoneNumber, User user) throws SQLException {

		String query = "DELETE FROM phonebook.contact WHERE phoneNumber = ? AND userName = ?";

		try (PreparedStatement stm = conn.prepareStatement(query)) {

			stm.setString(1, phoneNumber);
			stm.setString(2, user.getUserName());
			stm.executeUpdate();
			System.out.println("Contact deleted.");
		}
	}

	public void editContact(Contact contact, User user) throws SQLException {
		Scanner input = new Scanner(System.in);

		String query = "UPDATE phonebook.contact SET contactName = ?, phoneNumber = ? WHERE phoneNumber = ? AND userName = ?";

		System.out.println("Enter new contact name:");
		String contactName = input.nextLine();

		System.out.println("Enter new phone number:");
		String phoneNumber = input.nextLine();

		try (PreparedStatement stm = conn.prepareStatement(query)) {

			stm.setString(1, contactName);
			stm.setString(2, phoneNumber);
			stm.setString(3, contact.getPhoneNumber());
			stm.setString(4, user.getUserName());

			stm.executeUpdate();
			System.out.println("Contact updated.");
			input.close();
		}
	}

	public void showAllContacts(User user) throws SQLException {

		String query = "SELECT * FROM phonebook.contact WHERE userName = ?";
		ResultSet rs = null;

		try (PreparedStatement stm = conn.prepareStatement(query)) {

			stm.setString(1, user.getUserName());
			rs = stm.executeQuery();
			if (!rs.next()) {
				System.out.println("You don't have any contacts.\n");

			} else {
				while (rs.next()) {

					System.out.println("Contact name: " + rs.getString("contactName") + ", phoneNumber: "
							+ rs.getString("phoneNumber"));
				}
				System.out.println();
			}

		}

	}

	public void searchByName(User user) throws SQLException {
		Scanner input = new Scanner(System.in);

		String query = "SELECT * FROM phonebook.contact WHERE contactName = ? AND userName = ?";

		System.out.println("Enter contact name: ");
		String name = input.nextLine();

		ResultSet rs = null;

		try (PreparedStatement stm = conn.prepareStatement(query)) {

			stm.setString(1, name);
			stm.setString(2, user.getUserName());
			rs = stm.executeQuery();

			while (rs.next()) {
				System.out.println("Contact name: " + rs.getString("contactName") + " , phone number: "
						+ rs.getString("phoneNumber"));
				System.out.println();
			}
		}
		input.close();
	}
}
