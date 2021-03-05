package dao;

import java.util.ArrayList;

import entities.User;

public interface UserDAO {

	boolean existsByCF(String CF);
	
	boolean searchFounder();

	void insertStandardUser(User NewUser);
	
	void insertUserFounder(User NewFounder);

	String getPasswordByEmployeeCF(String EmployeeCF);

	boolean isAdmin(String EmployeeCF);

	boolean isFounder(String EmployeeCF);

	User getUserByCF(String EmployeeCF);

	ArrayList<User> getAllUsers();

	void promote(String EmployeeCF);

	void demote(String EmployeeCF);

}