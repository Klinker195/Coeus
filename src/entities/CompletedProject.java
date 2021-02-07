package entities;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.Controller;
import exceptions.EmptyListException;

public class CompletedProject extends Project {

	private LocalDate EndingDate;
	
	private Controller MainController = Controller.getIstance();
	
	public CompletedProject(String Name, LocalDate StartingDate, LocalDate EndingDate, int MaxEmployee, String Modality, ArrayList<String> TopicList, ArrayList<User> UserList) throws EmptyListException {
		super(Name, StartingDate, MaxEmployee, Modality, TopicList, UserList);
		this.setEndingDate(EndingDate);
	}

	public LocalDate getEndingDate() {
		return EndingDate;
	}

	public void setEndingDate(LocalDate endingDate) {
		EndingDate = endingDate;
	}
	
}
