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

public class PersonDAOPostgre {
	
	public PersonDAOPostgre() {
		
	}
	
	public static void main(String args[]) {
		
		PersonDAOPostgre PersonDAO = new PersonDAOPostgre();
		
		LinkedList<Person> PersonList = PersonDAO.getAllPeople();
		
		for(Person p : PersonList) {
			System.out.format(" CF: %s\n Name: %s\n Surname: %s\n Salary: %.2f\n Skill(s):\n", p.getPersonCF(), p.getPersonName(), p.getPersonSurname(), p.getPersonSalary());
			LinkedList<Skill> tmp = p.getPersonSkillList();
			for(Skill s : tmp) {
				System.out.format(" - %s\n", s.getSkillName());
			}
		}
	}
	
	private void loadDriver() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) {
			System.out.println("Class Not Found:\n " + e);
		}
	}

	private Connection tryConnection() {
		try {
			Connection Conn = DriverManager.getConnection("jdbc:postgresql://coeusdb.c2p2pfdwi5ds.us-east-2.rds.amazonaws.com:5432/postgres", "postgres", "postgres");
			return Conn;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
	}
	
	public LinkedList<Skill> getPersonSkill(String CF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"SkillID\", \"SkillName\" FROM \"PersonSkillID\" NATURAL JOIN \"Person\" NATURAL JOIN \"Skill\" WHERE \"PersonCF\" = ?");
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
				LinkedList<Skill> tmpSkillIdList = getPersonSkill(Rs.getString("PersonCF"));
				tmpList.addLast(new Person(Rs.getString("PersonCF"), Rs.getString("PersonName"), Rs.getString("PersonSurname"), Rs.getFloat("PersonSalary"), tmpSkillIdList));
			}
			Conn.close();
			return tmpList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
	}
	
}
