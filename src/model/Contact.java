package model;

public class Contact {

	private String contactName;
	private String phoneNumber;

	public String getFullName() {
		return contactName;
	}

	public void setFullName(String fullName) {
		this.contactName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Contact [fullName=" + contactName + ", phoneNumber=" + phoneNumber + "]";
	}

}
