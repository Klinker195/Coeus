package entities;

import java.util.ArrayList;

import controller.Controller;
import exceptions.EmptyListException;

public class Employee {

	private String CF;
	private String Name;
	private String Surname;
	private String Email;
	private float Salary;
	private String TimeZone;
	
	private ArrayList<Skill> Skill;
	
	private Controller MainController = Controller.getIstance();
	
	// ALL
	public Employee(String CF, String Name, String Surname, String Email, float Salary, String TimeZone, ArrayList<Skill> Skill) throws EmptyListException {
		this.CF = CF;
		this.Name = Name;
		this.Surname = Surname;
		this.Email = Email;
		this.Salary = Salary;
		this.TimeZone = TimeZone;
		setSkillList(Skill);
	}
	

	public String getCF() {
		return CF;
	}

	public String getName() {
		return Name;
	}

	public String getSurname() {
		return Surname;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
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
	
	public void setSkillList(ArrayList<Skill> skill) throws EmptyListException {
		MainController.checkEmptyList(skill);
		Skill = skill;
	}
	
	public ArrayList<Skill> getSkillArray() {
		return Skill;
	}
	
	public void addSkill(Skill skill) {
		Skill.add(skill);
	}
	
	public void removeSkill(Skill skill) {
		if(Skill.size() > 1) {
			Skill.remove(skill);
		}
	}
	
}
