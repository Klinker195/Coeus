package objects;

import java.util.LinkedList;

public class Employee {

	private String CF;
	private String Name;
	private String Surname;
	private float Salary;
	private String TimeZone;
	private LinkedList<Skill> Skill;
	
	public Employee(String CF, String Name, String Surname, float Salary, String TimeZone,LinkedList<Skill> Skill) {
		super();
		this.CF = CF;
		this.Name = Name;
		this.Surname = Surname;
		this.Salary = Salary;
		this.TimeZone = TimeZone;
		this.Skill = Skill;
	}

	public String getCF() {
		return CF;
	}

	public void setCF(String CF) {
		CF = CF;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		Name = Name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String Surname) {
		Surname = Surname;
	}

	public float getSalary() {
		return Salary;
	}

	public void setSalary(float Salary) {
		Salary = Salary;
	}
	
	public String getTimeZone() {
		return TimeZone;
	}

	public void setTimeZone(float TimeZone) {
		Salary = TimeZone;
	}
	
	public LinkedList<Skill> getPersonSkillList() {
		return Skill;
	}

	public void setPersonSkillList(LinkedList<Skill> personSkills) {
		Skill = personSkills;
	}
	
}
