package entities;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.Controller;
import exceptions.EmptyListException;

public class CompletedProject extends Project {

	private LocalDate EndingDate;
	
	private Controller MainController = Controller.getIstance();
	
	public CompletedProject(String Name, String Description, LocalDate StartingDate, LocalDate EndingDate, int MaxEmployee, Modality Modality, ArrayList<Topic> TopicList, ArrayList<User> UserList, User ProjectManager) throws EmptyListException {
		super(Name, Description, StartingDate, MaxEmployee, Modality, TopicList, UserList, ProjectManager);
		this.setEndingDate(EndingDate);
	}

	public LocalDate getEndingDate() {
		return EndingDate;
	}

	public void setEndingDate(LocalDate endingDate) {
		EndingDate = endingDate;
	}
	
}
