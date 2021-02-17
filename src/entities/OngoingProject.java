package entities;

import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.EmptyListException;

public class OngoingProject extends Project {
	
	public OngoingProject(String Name, String Description, LocalDate StartingDate, int MaxEmployee, Modality Modality, ArrayList<Topic> TopicList, ArrayList<User> UserList, User ProjectManager) throws EmptyListException {
		super(Name, Description, StartingDate, MaxEmployee, Modality, TopicList, UserList, ProjectManager);
	}
	
}
