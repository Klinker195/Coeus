package entities;

import java.util.LinkedList;

public class UserAdmin extends User {

	public UserAdmin(String CF, String Name, String Surname, float Salary,
			String TimeZone, LinkedList<Skill> Skill, int UserID, String Password, String Email) {
		super(CF, Name, Surname, Salary, TimeZone, Skill, UserID, Password, Email);
	}
	
}
