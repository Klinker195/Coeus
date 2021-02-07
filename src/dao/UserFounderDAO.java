package dao;

import entities.User;
import entities.UserFounder;

public interface UserFounderDAO {

	boolean searchFounder();
	
	void insertUserFounder(User NewFounder);
	
	int getNewUserID();

}