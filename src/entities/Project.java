package entities;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.Controller;

public abstract class Project {
	
	private String Name;
	private String Description = null;
	private LocalDate StartingDate;
	private int MaxEmployee;
	private String Modality;
	private ArrayList<String> TopicList;
	
	private ArrayList<Meeting> MeetingList = new ArrayList<Meeting>();
	
	private Controller MainController = Controller.getInstance();
	
	// ALL
	protected Project(String Name, String Description, LocalDate StartingDate, int MaxEmployee, String Modality, ArrayList<String> TopicList, ArrayList<Meeting> MeetingList) {
		this(Name, StartingDate, MaxEmployee, Modality, TopicList);
		this.Description = Description;
		this.MeetingList = MeetingList;
	}
	
	// NO DESCRIPTION
	protected Project(String Name, LocalDate StartingDate, int MaxEmployee, String Modality, ArrayList<String> TopicList, ArrayList<Meeting> MeetingList) {
		this(Name, StartingDate, MaxEmployee, Modality, TopicList);
		this.MeetingList = MeetingList;
	}
	
	// NO MEETING
	protected Project(String Name, String Description, LocalDate StartingDate, int MaxEmployee, String Modality, ArrayList<String> TopicList) {
		this(Name, StartingDate, MaxEmployee, Modality, TopicList);
		this.Description = Description;
	}
	
	// NO DESCRIPTION NO MEETING
	protected Project(String Name, LocalDate StartingDate, int MaxEmployee, String Modality, ArrayList<String> TopicList) {
		super();
		this.Name = Name;
		this.StartingDate = StartingDate;
		this.MaxEmployee = MaxEmployee;
		this.Modality = Modality;
		setTopicList(TopicList);
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public LocalDate getStartingDate() {
		return StartingDate;
	}

	public void setStartingDate(LocalDate startingDate) {
		StartingDate = startingDate;
	}

	public int getMaxEmployee() {
		return MaxEmployee;
	}

	public void setMaxEmployee(int maxEmployee) {
		MaxEmployee = maxEmployee;
	}

	public String getModality() {
		return Modality;
	}

	public void setModality(String modality) {
		Modality = modality;
	}

	public ArrayList<String> getTopicList() {
		return TopicList;
	}

	public void setTopicList(ArrayList<String> topicList) {
		MainController.checkEmptyList(topicList);
		TopicList = topicList;
	}

	public void addTopic(String topic) {
		TopicList.add(topic);
	}
	
	public void removeTopic(String topic) {
		if(TopicList.size() != 1) {
			TopicList.remove(topic);
		}
	}
	
	public ArrayList<Meeting> getMeetingList() {
		return MeetingList;
	}

	public void setMeetingList(ArrayList<Meeting> meetingList) {
		MeetingList = meetingList;
	}

	public void addMeeting(Meeting meeting) {
		MeetingList.add(meeting);
	}
	
	public void removeMeeting(Meeting meeting) {
		MeetingList.remove(meeting);
	}
	
}
