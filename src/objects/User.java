package objects;

import java.util.LinkedList;

public class User extends Person {

	private int UserID;
	private String UserPassword;
	private String UserEmail;
	
	public User(String PersonCF, String PersonName, String PersonSurname, float PersonSalary, String PersonTimeZone, LinkedList<Skill> PersonSkills, int UserID, String UserPassword, String UserEmail) {
		super(PersonCF, PersonName, PersonSurname, PersonSalary, PersonTimeZone, PersonSkills);
		this.UserID = UserID;
		this.UserPassword = UserPassword;
		this.UserEmail = UserEmail;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getUserPassword() {
		return UserPassword;
	}

	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

	public String getUserEmail() {
		return UserEmail;
	}

	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	
}
