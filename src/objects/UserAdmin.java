package objects;

import java.util.LinkedList;

public class UserAdmin extends User {

	public UserAdmin(String PersonCF, String PersonName, String PersonSurname, float PersonSalary, LinkedList<Skill> PersonSkills, int UserID, String UserPassword, String UserEmail) {
		super(PersonCF, PersonName, PersonSurname, PersonSalary, PersonSkills, UserID, UserPassword, UserEmail);
	}
	
}
