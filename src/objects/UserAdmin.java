package objects;

import java.util.LinkedList;

public class UserAdmin extends User {

	public UserAdmin(String PersonCF, String PersonName, String PersonSurname, float PersonSalary,
			String PersonTimeZone, LinkedList<Skill> PersonSkills, int UserID, String UserPassword, String UserEmail) {
		super(PersonCF, PersonName, PersonSurname, PersonSalary, PersonTimeZone, PersonSkills, UserID, UserPassword, UserEmail);
	}
	
}
