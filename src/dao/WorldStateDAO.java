package dao;

import java.util.ArrayList;

public interface WorldStateDAO {

	ArrayList<String> getAllStateNames();

	String getStateCodeByStateName(String StateName);
	
}