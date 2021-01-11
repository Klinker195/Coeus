package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import controller.Controller;
import entities.Employee;
import entities.Skill;

public class PersonDAOPostgre extends DAOPostgre {
	
	public PersonDAOPostgre() {
		
	}
	
//	public static void main(String args[]) {
//		
//		PersonDAOPostgre PersonDAO = new PersonDAOPostgre();
//		
//		LinkedList<Person> PersonList = PersonDAO.getAllPeople();
//		
//		for(Person p : PersonList) {
//			System.out.format(" CF: %s\n Name: %s\n Surname: %s\n Salary: %.2f\n Skill(s):\n", p.getPersonCF(), p.getPersonName(), p.getPersonSurname(), p.getPersonSalary());
//			LinkedList<Skill> tmp = p.getPersonSkillList();
//			for(Skill s : tmp) {
//				System.out.format(" - %s\n", s.getSkillName());
//			}
//		}
//	}
	
	
	// TODO MUST adjust this getter, it should be in SkillDAOPostgre, this object should call the controller to make the query from SkillDAOPostgre class.
	public LinkedList<Skill> getPersonSkillsByCF(String CF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"Skill\".\"ID\", \"Skill\".\"Name\" FROM \"EmployeeSkill\" NATURAL JOIN \"Employee\" NATURAL JOIN \"Skill\" WHERE \"CF\" = ?");
			PrepStm.setString(1, CF);
			ResultSet Rs = PrepStm.executeQuery();
			LinkedList<Skill> tmpList = new LinkedList<Skill>();
			while(Rs.next()) {
				tmpList.addLast(new Skill(Rs.getInt("ID"), Rs.getString("Name")));
			}
			Conn.close();
			return tmpList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
	}
	
	public LinkedList<Employee> getAllPeople() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement Stm = Conn.createStatement();
			ResultSet Rs = Stm.executeQuery("SELECT * FROM public.\"Employee\"");
			LinkedList<Employee> tmpList = new LinkedList<Employee>();
			while(Rs.next()) {
				// Non va bene il getter delle skill, controllare il metodo
				tmpList.addLast(new Employee(Rs.getString("CF"), Rs.getString("Name"), Rs.getString("Surname"), Rs.getFloat("Salary"), Rs.getString("TimeZone"),getPersonSkillsByCF(Rs.getString("CF"))));
			}
			Conn.close();
			return tmpList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
	}
	
}
