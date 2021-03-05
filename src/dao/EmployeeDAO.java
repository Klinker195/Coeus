package dao;

import java.util.ArrayList;

import entities.Employee;
import entities.Skill;

public interface EmployeeDAO {
	
	boolean exists();
	
	boolean existsByCF(String CF);
	
	void insertEmployee(Employee NewEmployee);
	
	void overwriteEmployee(Employee NewEmployee);
	
	void deleteAllEmployees();
	
	String getCFByEmail(String Email);
	
	void updateEmployeeByCFNoSalary(Employee NewEmployee);

	String getNameByCF(String ProjectManagerCF);

	String getSurnameByCF(String ProjectManagerCF);

	Employee getEmployeeByCF(String CF);

	ArrayList<Employee> getEmployeeByProjectName_Name_Surname_Email(String ProjectName, String Name, String Surname,
			String Email);

	String getEmailByCF(String CF);

	ArrayList<Employee> getEmployeeByName_Surname_Email_SkillNameOrderedByScoreWithoutProjectManager(String Name,
			String Surname, String Email, String Skill, ArrayList<Integer> Score, String ProjectManagerCF);

	boolean hasSkill(String EmployeeCF, String SkillName);

	ArrayList<Employee> getEmployeeByMeetingID_Name_Surname_Email(Integer MeetingID, String Name, String Surname,
			String Email);

	ArrayList<Employee> getEmployeeByName_Surname_Email_SkillName_ProjectNameOrderedByScoreWithoutAlreadyMembers(
			String Name, String Surname, String Email, String SkillName, ArrayList<Integer> Score, Integer MeetingID, String ProjectName);

	boolean checkAvailableEmployeesByProjectNameAndMeetingID(Integer MeetingID, String ProjectName);

	ArrayList<Employee> getAllEmployees();

	void deleteEmployeeByCF(String EmployeeCF);

	void deleteEmployeeSkillsByCF(String EmployeeCF);

	void insertEmployeeSkillsByCF(ArrayList<String> SkillNames, String EmployeeCF);
	
}
