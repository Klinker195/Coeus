package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import gui.GUIController;
import objects.Person;
import objects.Skill;

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
	
	
	// TODO Assolutamente da correggere questo getter, dovrebbe stare in SkillDAOPostgre, in PersonDAOPostgre bisognerebbe dichiarare un oggetto SkillDAOPostgre per effettuare l'accesso alle skill.
	public LinkedList<Skill> getPersonSkillsByCF(String CF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"SkillID\", \"SkillName\" FROM \"PersonSkill\" NATURAL JOIN \"Person\" NATURAL JOIN \"Skill\" WHERE \"PersonCF\" = ?");
			PrepStm.setString(1, CF);
			ResultSet Rs = PrepStm.executeQuery();
			LinkedList<Skill> tmpList = new LinkedList<Skill>();
			while(Rs.next()) {
				tmpList.addLast(new Skill(Rs.getInt("SkillID"), Rs.getString("SkillName")));
			}
			Conn.close();
			return tmpList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
	}
	
	public LinkedList<Person> getAllPeople() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement Stm = Conn.createStatement();
			ResultSet Rs = Stm.executeQuery("SELECT * FROM public.\"Person\"");
			LinkedList<Person> tmpList = new LinkedList<Person>();
			while(Rs.next()) {
				// Non va bene il getter delle skill, controllare il metodo
				tmpList.addLast(new Person(Rs.getString("PersonCF"), Rs.getString("PersonName"), Rs.getString("PersonSurname"), Rs.getFloat("PersonSalary"), Rs.getString("PersonTimeZone"),getPersonSkillsByCF(Rs.getString("PersonCF"))));
			}
			Conn.close();
			return tmpList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
	}
	
}
