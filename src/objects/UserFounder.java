package objects;

import java.util.LinkedList;

public class UserFounder extends User{

	public UserFounder(String PersonCF, String PersonName, String PersonSurname, float PersonSalary, LinkedList<Skill> PersonSkills, int UserID, String UserPassword, String UserEmail) {
		super(PersonCF, PersonName, PersonSurname, PersonSalary, PersonSkills, UserID, UserPassword, UserEmail);
	}
	
}
