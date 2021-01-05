package objects;

import java.util.LinkedList;

public class UserFounder extends User{

	public UserFounder(String CF, String Name, String Surname, float Salary,
			String TimeZone, LinkedList<Skill> Skill, int UserID, String Password, String Email) {
		super(CF, Name, Surname, Salary, TimeZone, Skill, UserID, Password, Email);
	}
	
}
