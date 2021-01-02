package objects;

import java.util.LinkedList;

public class Person {

	private String PersonCF;
	private String PersonName;
	private String PersonSurname;
	private float PersonSalary;
	LinkedList<Skill> PersonSkills;
	
	public Person(String PersonCF, String PersonName, String PersonSurname, float PersonSalary, LinkedList<Skill> PersonSkills) {
		super();
		this.PersonCF = PersonCF;
		this.PersonName = PersonName;
		this.PersonSurname = PersonSurname;
		this.PersonSalary = PersonSalary;
		this.PersonSkills = PersonSkills;
	}

	public String getPersonCF() {
		return PersonCF;
	}

	public void setPersonCF(String personCF) {
		PersonCF = personCF;
	}

	public String getPersonName() {
		return PersonName;
	}

	public void setPersonName(String personName) {
		PersonName = personName;
	}

	public String getPersonSurname() {
		return PersonSurname;
	}

	public void setPersonSurname(String personSurname) {
		PersonSurname = personSurname;
	}

	public float getPersonSalary() {
		return PersonSalary;
	}

	public void setPersonSalary(float personSalary) {
		PersonSalary = personSalary;
	}
	
	public LinkedList<Skill> getPersonSkillList() {
		return PersonSkills;
	}

	public void setPersonSkillList(LinkedList<Skill> personSkills) {
		PersonSkills = personSkills;
	}
	
}
