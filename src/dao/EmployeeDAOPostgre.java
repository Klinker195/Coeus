package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Employee;
import entities.Skill;

public class EmployeeDAOPostgre extends DAOPostgre implements EmployeeDAO {

	public EmployeeDAOPostgre() {
		
	}
	
	@Override
	public boolean exists() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement Stm = Conn.createStatement();
			ResultSet rs = Stm.executeQuery("SELECT * FROM public.\"Employee\"");
			
			if(rs.isBeforeFirst()) {
				Conn.close();
				return true;
			} else {
				Conn.close();
				return false;
			}

		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
		return false;
		
	}
	
	@Override
	public boolean existsByCF(String CF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT * FROM public.\"Employee\"\r\n"
					+ "WHERE \"CF\" = ?\r\n"
					+ "ORDER BY \"CF\" ASC ");
			PrepStm.setString(1, CF);
			ResultSet rs = PrepStm.executeQuery();
			
			if(rs.isBeforeFirst()) {
				Conn.close();
				return true;
			} else {
				Conn.close();
				return false;
			}

		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
		return false;
		
	}
	
	@Override
	public void insertEmployee(Employee NewEmployee) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("INSERT INTO public.\"Employee\"(\r\n"
					+ "	\"CF\", \"Name\", \"Surname\", \"Email\", \"Salary\", \"TimeZone\")\r\n"
					+ "	VALUES (?, ?, ?, ?, ?, ?);");
			PrepStm.setString(1, NewEmployee.getCF());
			PrepStm.setString(2, NewEmployee.getName());
			PrepStm.setString(3, NewEmployee.getSurname());
			PrepStm.setString(4, NewEmployee.getEmail());
			PrepStm.setFloat(5, NewEmployee.getSalary());
			PrepStm.setString(6, NewEmployee.getTimeZone());
			PrepStm.executeUpdate();
			PrepStm = Conn.prepareStatement("INSERT INTO public.\"EmployeeSkill\"(\r\n"
					+ "	\"EmployeeCF\", \"SkillName\")\r\n"
					+ "	VALUES (?, ?);");
			PrepStm.setString(1, NewEmployee.getCF());
			ArrayList<Skill> EmployeeSkillList = NewEmployee.getSkillArray();
			for(int i = 0; i < EmployeeSkillList.size(); i++) {
				PrepStm.setString(2, EmployeeSkillList.get(i).getName());
				PrepStm.executeUpdate();
			}
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
	
	}
	
	
	@Override
	public void overwriteEmployee(Employee NewEmployee) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("UPDATE public.\"Employee\"\r\n"
					+ "	SET \"Name\"= ?, \"Surname\"= ?, \"Email\"= ?, \"Salary\"= ?, \"TimeZone\"= ?\r\n"
					+ "	WHERE \"CF\" = ?;");
			PrepStm.setString(1, NewEmployee.getName());
			PrepStm.setString(2, NewEmployee.getSurname());
			PrepStm.setString(3, NewEmployee.getEmail());
			PrepStm.setFloat(4, NewEmployee.getSalary());
			PrepStm.setString(5, NewEmployee.getTimeZone());
			PrepStm.setString(6, NewEmployee.getCF());
			PrepStm.executeUpdate();
			
			PrepStm = Conn.prepareStatement("DELETE FROM public.\"EmployeeSkill\"\r\n"
					+ "	WHERE \"EmployeeCF\" = ?;");
			PrepStm.setString(1, NewEmployee.getCF());
			PrepStm.executeUpdate();
			PrepStm = Conn.prepareStatement("INSERT INTO public.\"EmployeeSkill\"(\r\n"
					+ "	\"EmployeeCF\", \"SkillName\")\r\n"
					+ "	VALUES (?, ?);");
			PrepStm.setString(1, NewEmployee.getCF());
			ArrayList<Skill> EmployeeSkillList = NewEmployee.getSkillArray();
			for(int i = 0; i < EmployeeSkillList.size(); i++) {
				PrepStm.setString(2, EmployeeSkillList.get(i).getName());
				PrepStm.executeUpdate();
			}
			
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}
	
	@Override
	public void deleteAllEmployees() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement Stm = Conn.createStatement();
			Stm.executeUpdate("DELETE FROM public.\"Employee\" *;");
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}
	
}
