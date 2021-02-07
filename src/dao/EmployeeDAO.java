package dao;

import entities.Employee;

public interface EmployeeDAO {
	
	boolean exists();
	
	boolean existsByCF(String CF);
	
	void insertEmployee(Employee NewEmployee);
	
	void overwriteEmployee(Employee NewEmployee);
	
	void deleteAllEmployees();
	
}
