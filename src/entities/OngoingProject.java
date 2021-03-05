package entities;

import java.time.LocalDate;
import java.util.ArrayList;


public class OngoingProject extends Project {
	
	// ALL
	public OngoingProject(String Name, String Description, LocalDate StartingDate, int MaxEmployee, String Modality, ArrayList<String> TopicList, ArrayList<Meeting> MeetingList) {
		super(Name, Description, StartingDate, MaxEmployee, Modality, TopicList, MeetingList);
	}
	
	// NO DESCRIPTION
	public OngoingProject(String Name, LocalDate StartingDate, int MaxEmployee, String Modality, ArrayList<String> TopicList, ArrayList<Meeting> MeetingList) {
		super(Name, StartingDate, MaxEmployee, Modality, TopicList, MeetingList);
	}
	
	// NO MEETING
	public OngoingProject(String Name, String Description, LocalDate StartingDate, int MaxEmployee, String Modality, ArrayList<String> TopicList) {
		super(Name, Description, StartingDate, MaxEmployee, Modality, TopicList);
	}
	
	// NO DESCRIPTION NO MEETING
	public OngoingProject(String Name, LocalDate StartingDate, int MaxEmployee, String Modality, ArrayList<String> TopicList) {
		super(Name, StartingDate, MaxEmployee, Modality, TopicList);
	}
	
}
