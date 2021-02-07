package entities;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.Controller;
import exceptions.EmptyListException;

public abstract class Project {
	
	private String Name;
	private String Description = null;
	private LocalDate StartingDate;
	private int MaxEmployee;
	private String Modality;
	private ArrayList<String> TopicList;
	
	private ArrayList<Meeting> MeetingList = new ArrayList<Meeting>();
	private ArrayList<User> UserList;
	
	private Controller MainController = Controller.getIstance();
	
	
	// ALL
	protected Project(String Name, String Description, LocalDate StartingDate, int MaxEmployee, String Modality, ArrayList<String> Topic, ArrayList<Meeting> MeetingList, ArrayList<User> UserList) throws EmptyListException {
		this(Name, StartingDate, MaxEmployee, Modality, Topic, UserList);
		this.Description = Description;
		this.MeetingList = MeetingList;
	}
	
	// NO DESCRIPTION
	protected Project(String Name, LocalDate StartingDate, int MaxEmployee, String Modality, ArrayList<String> TopicList, ArrayList<Meeting> MeetingList, ArrayList<User> UserList) throws EmptyListException {
		this(Name, StartingDate, MaxEmployee, Modality, TopicList, UserList);
		this.MeetingList = MeetingList;
	}
	
	// NO MEETING
	protected Project(String Name, String Description, LocalDate StartingDate, int MaxEmployee, String Modality, ArrayList<String> TopicList, ArrayList<User> UserList) throws EmptyListException {
		this(Name, StartingDate, MaxEmployee, Modality, TopicList, UserList);
		this.Description = Description;
	}
	
	// NO DESCRIPTION NO MEETING
	protected Project(String Name, LocalDate StartingDate, int MaxEmployee, String Modality, ArrayList<String> TopicList, ArrayList<User> UserList) throws EmptyListException {
		super();
		this.Name = Name;
		this.StartingDate = StartingDate;
		this.MaxEmployee = MaxEmployee;
		this.Modality = Modality;
		setTopicList(TopicList);
		setUserList(UserList);
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

	public ArrayList<String> getTopic() {
		return TopicList;
	}

	public void setTopicList(ArrayList<String> topicList) throws EmptyListException {
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
	
	public ArrayList<User> getUserList() {
		return UserList;
	}
	
	public void setUserList(ArrayList<User> userList) throws EmptyListException {
		MainController.checkEmptyList(userList);
		UserList = userList;
	}
	
	public void addMeetingInvitation(User user) {
		UserList.add(user);
	}
	
	public void removeMeetingInvitation(User user) {
		if(UserList.size() > 1) {
			UserList.remove(user);
		}
	}
	
}
