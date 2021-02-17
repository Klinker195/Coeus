package dao;

import entities.User;

public interface UserDAO {

	boolean searchFounder();

	void insertStandardUser(User NewUser);
	
	void insertUserFounder(User NewFounder);

	int getNewUserID();

	String getPasswordByUserID(int UserID);

}