package entities;

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

	public void setCF(String cF) {
		CF = cF;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public float getSalary() {
		return Salary;
	}

	public void setSalary(float salary) {
		Salary = salary;
	}

	public String getTimeZone() {
		return TimeZone;
	}

	public void setTimeZone(String timeZone) {
		TimeZone = timeZone;
	}

	public LinkedList<Skill> getSkill() {
		return Skill;
	}

	public void setSkill(LinkedList<Skill> skill) {
		Skill = skill;
	}

	public LinkedList<Skill> getPersonSkillList() {
		return Skill;
	}

	public void setPersonSkillList(LinkedList<Skill> personSkills) {
		Skill = personSkills;
	}
	
}
