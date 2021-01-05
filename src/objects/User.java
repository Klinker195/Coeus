package objects;

import java.util.LinkedList;

public class User extends Employee {

	private int UserID;
	private String Password;
	private String Email;
	
	public User(String CF, String Name, String Surname, float Salary, String TimeZone, LinkedList<Skill> Skill, int UserID, String Password, String Email) {
		super(CF, Name, Surname, Salary, TimeZone, Skill);
		this.UserID = UserID;
		this.Password = Password;
		this.Email = Email;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	
}
