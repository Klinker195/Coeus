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

	private Controller MainController = Controller.getInstance();
	
	public EmployeeDAOPostgre() {
		
	}
	
	/**
	 * Checks the existance of at least one employee in the database table.
	 * @author CoeusDevTeam
	 * @return Boolean -> True if at least one employee exists, otherwise false.
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
	 * Checks if an employee has a Skill based on the employee CF and the skill name.
	 * @author CoeusDevTeam
	 * @param EmployeeCF -> A String representing the employee's CF.
	 * @param SkillName -> A String representing 
	 * @return True if the employee exists, otherwise false.
	 */
	@Override
	public boolean hasSkill(String EmployeeCF, String SkillName) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT * FROM public.\"EmployeeSkill\" AS ES WHERE ES.\"EmployeeCF\" = ? AND ES.\"SkillName\" ~ UPPER(?) ");
			PrepStm.setString(1, EmployeeCF);
			PrepStm.setString(2, SkillName);
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
	 * Checks the existance of a certain employee in the database based on the inserted CF.
	 * @author CoeusDevTeam
	 * @param CF -> A String representing the employee's CF.
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
	 * Gets an employee based on a CF.
	 * @author CoeusDevTeam
	 * @param CF -> A String representing the employee's CF.
	 * @return An Employee object.
	 * @see Employee
	 */
	@Override
	public Employee getEmployeeByCF(String CF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT *\r\n"
					+ "FROM public.\"Employee\"\r\n"
					+ "WHERE \"CF\" = ?;");
			PrepStm.setString(1, CF);
			ResultSet rs = PrepStm.executeQuery();
			
			Conn.close();
			if(rs.isBeforeFirst()) {
				rs.next();
				return new Employee(CF, rs.getString("Name"), rs.getString("Surname"), rs.getString("Email"), rs.getFloat("Salary"), rs.getString("TimeZone"), MainController.getSkillDAO().getEmployeeSkillsByCF(CF));
			} else {
				Conn.close();
				return null;
			}
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	/**
	 * Gets a surname based on a CF.
	 * @author CoeusDevTeam
	 * @param CF -> A String representing the employee's CF.
	 * @return A String representing the employee's surname.
	 */
	@Override
	public String getSurnameByCF(String CF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"Surname\"\r\n"
					+ "FROM public.\"Employee\"\r\n"
					+ "WHERE \"CF\" = ?;");
			PrepStm.setString(1, CF);
			ResultSet rs = PrepStm.executeQuery();
			
			String Surname;

			if(rs.isBeforeFirst()) {
				rs.next();
				Surname = rs.getString("Surname");
				Conn.close();
				return Surname;
			} else {
				Conn.close();
				return null;
			}

		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	/**
	 * Gets a name based on a CF.
	 * @author CoeusDevTeam
	 * @param CF -> A String representing the employee's CF.
	 * @return A String representing the employee's name.
	 */
	@Override
	public String getNameByCF(String CF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"Name\"\r\n"
					+ "FROM public.\"Employee\"\r\n"
					+ "WHERE \"CF\" = ?;");
			PrepStm.setString(1, CF);
			ResultSet rs = PrepStm.executeQuery();
			
			String Name;

			if(rs.isBeforeFirst()) {
				rs.next();
				Name = rs.getString("Name");
				Conn.close();
				return Name;
			} else {
				Conn.close();
				return null;
			}

		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	/**
	 * Fetch a CF based on an Email.
	 * @author CoeusDevTeam
	 * @param Email -> A String representing the employee's Email.
	 * @return An int which represents the CF related to the Email.
	 */
	@Override
	public String getCFByEmail(String Email) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"CF\"\r\n"
					+ "FROM public.\"Employee\"\r\n"
					+ "WHERE \"Email\" = ?;");
			PrepStm.setString(1, Email);
			ResultSet rs = PrepStm.executeQuery();
			
			String CF;

			if(rs.isBeforeFirst()) {
				rs.next();
				CF = rs.getString("CF");
				Conn.close();
				return CF;
			} else {
				Conn.close();
				return null;
			}

		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}

	/**
	 * Gets a surname based on a CF.
	 * @author CoeusDevTeam
	 * @param CF -> A String representing the employee's email.
	 * @return A String representing the employee's email.
	 */
	@Override
	public String getEmailByCF(String CF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"Email\"\r\n"
					+ "FROM public.\"Employee\"\r\n"
					+ "WHERE \"CF\" = ?;");
			PrepStm.setString(1, CF);
			ResultSet rs = PrepStm.executeQuery();
			
			String Email;

			if(rs.isBeforeFirst()) {
				rs.next();
				Email = rs.getString("Email");
				Conn.close();
				return Email;
			} else {
				Conn.close();
				return null;
			}

		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	/**
	 * Gets a list of employees, discarding the project manager, based on name, surname, email and skill name. The list is ordered by a score system. 
	 * @author CoeusDevTeam
	 * @param Name -> A String representing the employee's name. If blank then it's overwritten with the String "(.*?)".
	 * @param Surname -> A String representing the employee's surname. If blank then it's overwritten with the String "(.*?)".
	 * @param Email -> A String representing the employee's email. If blank then it's overwritten with the String "(.*?)".
	 * @param SkillName -> A String representing the employee's skill name. If blank then it's overwritten with the String "(.*?)".
	 * @param Score -> An ArrayList of Integers that will be filled with the employee scores.
	 * @param ProjectManagerCF -> A String representing the CF of a project manager which will be discarded from the list.
	 * @return An ArrayList of Employee objects.
	 * @see Employee
	 */
	@Override
	public ArrayList<Employee> getEmployeeByName_Surname_Email_SkillNameOrderedByScoreWithoutProjectManager(String Name, String Surname, String Email, String SkillName, ArrayList<Integer> Score, String ProjectManagerCF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			
			if(Name.isBlank()) Name = "(.*?)";
			if(Surname.isBlank()) Surname = "(.*?)";
			if(Email.isBlank()) Email = "(.*?)";
			if(SkillName.isBlank()) SkillName = "(.*?)";
			
			if(!Score.isEmpty()) {
				Score.clear();
			}
			
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT *\r\n"
					+ "FROM public.\"EmployeeUserTotalScore\" AS E\r\n"
					+ "WHERE E.\"Name\" ~ UPPER(?) AND E.\"Surname\" ~ UPPER(?) AND E.\"Email\" ~ LOWER(?) AND E.\"CF\" <> ?;");
			PrepStm.setString(1, Name);
			PrepStm.setString(2, Surname);
			PrepStm.setString(3, Email);
			PrepStm.setString(4, ProjectManagerCF);
			ResultSet rs = PrepStm.executeQuery();
			
			ArrayList<Employee> tmpList = new ArrayList<Employee>();

			Conn.close();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					if(hasSkill(rs.getString("CF"), SkillName)) {
						tmpList.add(new Employee(rs.getString("CF"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Email"), rs.getFloat("Salary"), rs.getString("TimeZone"), MainController.getSkillDAO().getEmployeeSkillsByCF(rs.getString("CF"))));
						Score.add(rs.getInt("TotalScore"));
					}
				}
				return tmpList;
			} else {
				Conn.close();
				return null;
			}

		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	/**
	 * Gets a list of employees, discarding the already existing members, based on name, surname, email and skill name. The list is ordered by a score system.
	 * @author CoeusDevTeam
	 * @param Name -> A String representing the employee's name. If blank then it's overwritten with the String "(.*?)".
	 * @param Surname -> A String representing the employee's surname. If blank then it's overwritten with the String "(.*?)".
	 * @param Email -> A String representing the employee's email. If blank then it's overwritten with the String "(.*?)".
	 * @param SkillName -> A String representing the employee's skill name. If blank then it's overwritten with the String "(.*?)".
	 * @param Score -> An ArrayList of Integers that will be filled with the employee scores.
	 * @param MeetingID -> An Integer object representing a meeting ID.
	 * @param ProjectName -> A String representing a project name.
	 * @return An ArrayList of Employee objects.
	 * @see Employee
	 */
	@Override
	public ArrayList<Employee> getEmployeeByName_Surname_Email_SkillName_ProjectNameOrderedByScoreWithoutAlreadyMembers(String Name, String Surname, String Email, String SkillName, ArrayList<Integer> Score, Integer MeetingID, String ProjectName) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			
			if(Name.isBlank()) Name = "(.*?)";
			if(Surname.isBlank()) Surname = "(.*?)";
			if(Email.isBlank()) Email = "(.*?)";
			if(SkillName.isBlank()) SkillName = "(.*?)";
			
			if(!Score.isEmpty()) {
				Score.clear();
			}
			
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT *\r\n"
					+ "FROM public.\"EmployeeUserTotalScore\" AS E\r\n"
					+ "JOIN public.\"UserProject\" AS UP ON UP.\"UserCF\" = E.\"CF\"\r\n"
					+ "WHERE E.\"Name\" ~ UPPER(?) AND E.\"Surname\" ~ UPPER(?) AND E.\"Email\" ~ LOWER(?) AND UP.\"ProjectName\" = ? AND E.\"CF\" NOT IN ( SELECT \"UserEmployeeCF\"\r\n"
					+ "														   	FROM public.\"MeetingInvitation\"\r\n"
					+ "														    WHERE \"MeetingID\" = ?)\r\n"
					+ "ORDER BY \"TotalScore\" DESC");
			PrepStm.setString(1, Name);
			PrepStm.setString(2, Surname);
			PrepStm.setString(3, Email);
			PrepStm.setString(4, ProjectName);
			PrepStm.setInt(5, MeetingID);
			ResultSet rs = PrepStm.executeQuery();
			
			ArrayList<Employee> tmpList = new ArrayList<Employee>();

			Conn.close();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					if(hasSkill(rs.getString("CF"), SkillName)) {
						tmpList.add(new Employee(rs.getString("CF"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Email"), rs.getFloat("Salary"), rs.getString("TimeZone"), MainController.getSkillDAO().getEmployeeSkillsByCF(rs.getString("CF"))));
						Score.add(rs.getInt("TotalScore"));
					}
				}
				return tmpList;
			} else {
				Conn.close();
				return null;
			}

		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	/**
	 * Gets a list of employees based name, surname, email and a meeting ID.
	 * @author CoeusDevTeam
	 * @param MeetingID -> An Integer object representing a meeting ID.
	 * @param Name -> A String representing the employee's name. If blank then it's overwritten with the String "(.*?)".
	 * @param Surname -> A String representing the employee's surname. If blank then it's overwritten with the String "(.*?)".
	 * @param Email -> A String representing the employee's email. If blank then it's overwritten with the String "(.*?)".
	 * @return An ArrayList of Employee objects.
	 * @see Employee
	 */
	@Override
	public ArrayList<Employee> getEmployeeByMeetingID_Name_Surname_Email(Integer MeetingID, String Name, String Surname, String Email) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			
			if(Name.isBlank()) Name = "(.*?)";
			if(Surname.isBlank()) Surname = "(.*?)";
			if(Email.isBlank()) Email = "(.*?)";
			
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT E.*\r\n"
					+ "FROM public.\"Employee\" AS E JOIN public.\"UserMeeting\" AS UM\r\n"
					+ "ON E.\"CF\" = UM.\"EmployeeCF\"\r\n"
					+ "WHERE UM.\"ID\" = ? AND E.\"Name\" ~ UPPER(?) AND E.\"Surname\" ~ UPPER(?) AND E.\"Email\" ~ LOWER(?);");
			PrepStm.setInt(1, MeetingID);
			PrepStm.setString(2, Name);
			PrepStm.setString(3, Surname);
			PrepStm.setString(4, Email);
			ResultSet rs = PrepStm.executeQuery();
			
			ArrayList<Employee> tmpList = new ArrayList<Employee>();

			Conn.close();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					tmpList.add(new Employee(rs.getString("CF"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Email"), rs.getFloat("Salary"), rs.getString("TimeZone"), MainController.getSkillDAO().getEmployeeSkillsByCF(rs.getString("CF"))));
				}
				return tmpList;
			} else {
				Conn.close();
				return null;
			}

		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	/**
	 * Gets a list of employees based name, surname, email and a project name.
	 * @author CoeusDevTeam
	 * @param ProjectName -> A String representing a project name.
	 * @param Name -> A String representing the employee's name. If blank then it's overwritten with the String "(.*?)".
	 * @param Surname -> A String representing the employee's surname. If blank then it's overwritten with the String "(.*?)".
	 * @param Email -> A String representing the employee's email. If blank then it's overwritten with the String "(.*?)".
	 * @return An ArrayList of Employee objects.
	 * @see Employee
	 */
	@Override
	public ArrayList<Employee> getEmployeeByProjectName_Name_Surname_Email(String ProjectName, String Name, String Surname, String Email) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			
			if(Name.isBlank()) Name = "(.*?)";
			if(Surname.isBlank()) Surname = "(.*?)";
			if(Email.isBlank()) Email = "(.*?)";
			
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT E.*, UP.\"ProjectName\"\r\n"
					+ "FROM public.\"Employee\" AS E JOIN public.\"UserProject\" AS UP\r\n"
					+ "ON E.\"CF\" = UP.\"UserCF\"\r\n"
					+ "WHERE UPPER(UP.\"ProjectName\") = UPPER(?) AND E.\"Name\" ~ UPPER(?) AND E.\"Surname\" ~ UPPER(?) AND E.\"Email\" ~ LOWER(?);");
			PrepStm.setString(1, ProjectName);
			PrepStm.setString(2, Name);
			PrepStm.setString(3, Surname);
			PrepStm.setString(4, Email);
			ResultSet rs = PrepStm.executeQuery();
			
			ArrayList<Employee> tmpList = new ArrayList<Employee>();

			Conn.close();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					tmpList.add(new Employee(rs.getString("CF"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Email"), rs.getFloat("Salary"), rs.getString("TimeZone"), MainController.getSkillDAO().getEmployeeSkillsByCF(rs.getString("CF"))));
				}
				return tmpList;
			} else {
				Conn.close();
				return null;
			}

		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	/**
	 * Inserts an employee into the database.
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
	 * Checks if there are employees that have not yet been choosed for the selected meeting.
	 * @author CoeusDevTeam
	 * @param MeetingID -> An Integer object representing a meeting ID.
	 * @param ProjectName -> A String representing a project name.
	 */
	@Override
	public boolean checkAvailableEmployeesByProjectNameAndMeetingID(Integer MeetingID, String ProjectName) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT *\r\n"
					+ "FROM public.\"EmployeeUserTotalScore\" AS E\r\n"
					+ "JOIN public.\"UserProject\" AS UP ON UP.\"UserCF\" = E.\"CF\"\r\n"
					+ "WHERE UP.\"ProjectName\" = ? AND E.\"CF\" NOT IN ( SELECT \"UserEmployeeCF\"\r\n"
					+ "											   FROM public.\"MeetingInvitation\"\r\n"
					+ "											   WHERE \"MeetingID\" = ?)\r\n"
					+ "ORDER BY \"TotalScore\" DESC");
			PrepStm.setString(1, ProjectName);
			PrepStm.setInt(2, MeetingID);
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
	 * Gets all the employees.
	 * @author CoeusDevTeam
	 * @return An ArrayList of Employee objects.
	 * @see Employee
	 */
	@Override
	public ArrayList<Employee> getAllEmployees() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT * FROM public.\"EmployeeUser\"");
			ResultSet rs = PrepStm.executeQuery();
			
			ArrayList<Employee> tmpList = new ArrayList<Employee>();

			Conn.close();
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					tmpList.add(new Employee(rs.getString("CF"), rs.getString("Name"), rs.getString("Surname"), rs.getString("Email"), rs.getFloat("Salary"), rs.getString("TimeZone"), MainController.getSkillDAO().getEmployeeSkillsByCF(rs.getString("CF"))));
				}
				return tmpList;
			} else {
				return null;
			}

		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	/**
	 * Delete a certain employee based on a CF.
	 * @author CoeusDevTeam
	 * @param CF -> A String representing an employee's CF.
	 */
	@Override
	public void deleteEmployeeByCF(String CF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("DELETE FROM public.\"Employee\"\r\n"
					+ "	WHERE \"CF\" = ?;");
			PrepStm.setString(1, CF);
			
			PrepStm.executeUpdate();
			
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

	/**
	 * Delete all the skills of an employee based on a CF.
	 * @author CoeusDevTeam
	 * @param EmployeeCF -> A String representing an employee's CF.
	 */
	@Override
	public void deleteEmployeeSkillsByCF(String EmployeeCF) {

		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("DELETE FROM public.\"EmployeeSkill\" WHERE \"EmployeeCF\" = ?;");
			PrepStm.setString(1, EmployeeCF);

			PrepStm.executeUpdate();
			
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}
	
	/**
	 * Insert skills to an employee.
	 * @author CoeusDevTeam
	 * @param SkillNames -> An ArrayList of String objects representing skill names.
	 * @param EmployeeCF -> A String representing an employee's CF.
	 */
	@Override
	public void insertEmployeeSkillsByCF(ArrayList<String> SkillNames, String EmployeeCF) {

		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("INSERT INTO public.\"EmployeeSkill\"(\r\n"
					+ "	\"EmployeeCF\", \"SkillName\")\r\n"
					+ "	VALUES (?, ?);");
			PrepStm.setString(1, EmployeeCF);

			for(int i = 0; i < SkillNames.size(); i++) {
				PrepStm.setString(2, SkillNames.get(i));
				PrepStm.executeUpdate();
			}
			
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}
	
}
