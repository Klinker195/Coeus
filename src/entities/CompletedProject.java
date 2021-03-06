package entities;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.Controller;

public class CompletedProject extends Project {

	private LocalDate EndingDate;
	
	// ALL
	public CompletedProject(String Name, String Description, LocalDate StartingDate, LocalDate EndingDate, int MaxEmployee, String Modality, ArrayList<String> TopicList, ArrayList<Meeting> MeetingList) {
		super(Name, Description, StartingDate, MaxEmployee, Modality, TopicList, MeetingList);
		this.EndingDate = EndingDate;
	}
	
	// NO DESCRIPTION
	public CompletedProject(String Name, LocalDate StartingDate, LocalDate EndingDate, int MaxEmployee, String Modality, ArrayList<String> TopicList, ArrayList<Meeting> MeetingList) {
		super(Name, StartingDate, MaxEmployee, Modality, TopicList, MeetingList);
		this.EndingDate = EndingDate;
	}
	
	// NO MEETING
	public CompletedProject(String Name, String Description, LocalDate StartingDate, LocalDate EndingDate, int MaxEmployee, String Modality, ArrayList<String> TopicList) {
		super(Name, Description, StartingDate, MaxEmployee, Modality, TopicList);
		this.EndingDate = EndingDate;
	}
	
	// NO DESCRIPTION NO MEETING
	public CompletedProject(String Name, LocalDate StartingDate, LocalDate EndingDate, int MaxEmployee, String Modality, ArrayList<String> TopicList) {
		super(Name, StartingDate, MaxEmployee, Modality, TopicList);
		this.EndingDate = EndingDate;
	}
	
	public LocalDate getEndingDate() {
		return EndingDate;
	}

	public void setEndingDate(LocalDate endingDate) {
		EndingDate = endingDate;
	}
	
}
