package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.Controller;
import entities.Employee;
import entities.Skill;
import entities.User;


public class EmployeeDAOPostgre extends DAOPostgre implements EmployeeDAO {

	private Controller MainController = Controller.getIstance();
	
	public EmployeeDAOPostgre() {
		
	}
	
	/**
	 * Checks the existance of at least one employee in the database table.
	 * @author CoeusDevTeam
	 * @return True if at least one employee exists, otherwise false.
	 */
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
	
	/**
	 * Checks the existance of a certain employee in the database based on the inserted CF.
	 * @author CoeusDevTeam
	 * @param CF -> A string representing the employee's CF.
	 * @return True if the employee exists, otherwise false.
	 */
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
	
	/**
	 * Fetch an user ID based on an Email.
	 * @author CoeusDevTeam
	 * @param Email -> A string representing the employee's Email.
	 * @return An int which represents the user ID related to the Email.
	 */
	@Override
	public int getUserIDByEmail(String Email) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"UserID\"\r\n"
					+ "FROM public.\"Employee\"\r\n"
					+ "WHERE \"Email\" = ?;");
			PrepStm.setString(1, Email);
			ResultSet rs = PrepStm.executeQuery();
			
			int UserID;

			if(rs.isBeforeFirst()) {
				rs.next();
				UserID = rs.getInt("UserID");
				Conn.close();
				return UserID;
			} else {
				Conn.close();
				return 0;
			}

		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return 0;
		
	}
	
	/**
	 * Fetch an user ID based on a CF.
	 * @author CoeusDevTeam
	 * @param CF -> A string representing the employee's CF.
	 * @return An int which represents the user ID related to the CF.
	 */
	@Override
	public int getUserIDByCF(String CF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"UserID\"\r\n"
					+ "FROM public.\"Employee\"\r\n"
					+ "WHERE \"CF\" = ?;");
			PrepStm.setString(1, CF);
			ResultSet rs = PrepStm.executeQuery();
			
			int UserID;

			if(rs.isBeforeFirst()) {
				rs.next();
				UserID = rs.getInt("UserID");
				Conn.close();
				return UserID;
			} else {
				Conn.close();
				return 0;
			}

		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return 0;
		
	}
	
	/**
	 * Inserts an employee in the database.
	 * @author CoeusDevTeam
	 * @param NewEmployee -> An Employee object which contains all the informations related to an employee.
	 * @see Employee
	 */
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
	
	/**
	 * Updates the employee in the database based on NewEmployee CF without editing the salary.
	 * @author CoeusDevTeam
	 * @param NewEmployee -> An Employee object which contains all the informations related to an employee.
	 * @see Employee
	 */
	@Override
	public void updateEmployeeByCFNoSalary(Employee NewEmployee) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("UPDATE public.\"Employee\"\r\n"
					+ "SET \"Email\"= ?, \"TimeZone\"= ?\r\n"
					+ "WHERE \"CF\" = ?;");
			PrepStm.setString(1, NewEmployee.getEmail());
			PrepStm.setString(2, NewEmployee.getTimeZone());
			PrepStm.setString(3, NewEmployee.getCF());
			PrepStm.executeUpdate();
			PrepStm = Conn.prepareStatement("DELETE FROM public.\"EmployeeSkill\"\r\n"
					+ "WHERE \"EmployeeCF\" = ?;");
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
	
	/**
	 * Overwrite a certain employee based on the CF of the NewEmployee object.
	 * @author CoeusDevTeam
	 * @param NewEmployee -> An Employee object which contains all the informations related to an employee.
	 * @see Employee
	 */
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
	
	/**
	 * Delete all the employees in the table.
	 * @author CoeusDevTeam
	 */
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
