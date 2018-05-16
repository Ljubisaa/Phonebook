package database;

import java.sql.SQLException;

import model.Contact;
import model.User;

public interface ContactInterface {

	// method to add new contact
	void addNewContact(User user) throws SQLException;

	// method to delete contact
	void deleteContact(String phoneNumber, User user) throws SQLException;

	// method to edit contact
	void editContact(Contact contact, User user) throws SQLException;

	// method to show all contacts
	void showAllContacts(User user) throws SQLException;

	// method to search contact by name
	void searchByName(User user) throws SQLException;
}
