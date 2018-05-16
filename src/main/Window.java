package main;

import java.util.Scanner;

import database.ContactManager;
import model.Contact;
import model.User;
import util.Login;
import util.Register;

public class Window {

	public void run() throws Exception {

		while (true) {
			try {
				Scanner input = new Scanner(System.in);
				System.out.println("Welcome to phonebook, choose your option: \n" + "1.Register\n" + "2.Login");

				int num = input.nextInt();
				input.nextLine();
				if (num == 1) {
					Register.registerUser();
				} else if (num == 2) {

					User user = new User();
					System.out.println("Enter username: ");
					user.setUserName(input.nextLine());

					System.out.println("Enter password:");
					user.setPassword(input.nextLine());
					boolean login = Login.loginUser(user);

					if (login) {
						System.out.println("Loged.");
						ContactManager cm = new ContactManager();
						System.out.println("Choose one of the following options:");
						System.out.println("1. Add new contact");
						System.out.println("2. Delete contact");
						System.out.println("3. Edit contact");
						System.out.println("4. Show all contacts");
						System.out.println("5. Search by name");
						int choise = input.nextInt();

						switch (choise) {
						case 1:
							cm.addNewContact(user);
							break;
						case 2:
							System.out.println("Enter the phone number you want to delete: ");
							input.nextLine();
							String phoneNumber = input.nextLine();
							cm.deleteContact(phoneNumber, user);
							break;
						case 3:
							Contact contact = new Contact();
							System.out.println("Enter phone number:");
							input.nextLine();
							contact.setPhoneNumber(input.nextLine());
							cm.editContact(contact, user);
						case 4:
							cm.showAllContacts(user);
							break;
						case 5:
							cm.searchByName(user);
							break;
						default:
							System.out.println("Invalid input.");

						}
					} else {
						System.out.println("You aren't registered.");

					}
				}
				input.close();
			} catch (Exception e) {
				System.out.println("Invalid input");
				System.exit(0);
			}

		}

	}
}