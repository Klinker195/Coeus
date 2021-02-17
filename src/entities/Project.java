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
	private Modality Modality;
	private ArrayList<Topic> TopicList;
	
	private User ProjectManager;
	private ArrayList<Meeting> MeetingList = new ArrayList<Meeting>();
	private ArrayList<User> UserList;
	
	private Controller MainController = Controller.getIstance();
	
	
	// ALL
	protected Project(String Name, String Description, LocalDate StartingDate, int MaxEmployee, Modality Modality, ArrayList<Topic> TopicList, ArrayList<Meeting> MeetingList, ArrayList<User> UserList, User ProjectManager) throws EmptyListException {
		this(Name, StartingDate, MaxEmployee, Modality, TopicList, UserList, ProjectManager);
		this.Description = Description;
		this.MeetingList = MeetingList;
	}
	
	// NO DESCRIPTION
	protected Project(String Name, LocalDate StartingDate, int MaxEmployee, Modality Modality, ArrayList<Topic> TopicList, ArrayList<Meeting> MeetingList, ArrayList<User> UserList, User ProjectManager) throws EmptyListException {
		this(Name, StartingDate, MaxEmployee, Modality, TopicList, UserList, ProjectManager);
		this.MeetingList = MeetingList;
	}
	
	// NO MEETING
	protected Project(String Name, String Description, LocalDate StartingDate, int MaxEmployee, Modality Modality, ArrayList<Topic> TopicList, ArrayList<User> UserList, User ProjectManager) throws EmptyListException {
		this(Name, StartingDate, MaxEmployee, Modality, TopicList, UserList, ProjectManager);
		this.Description = Description;
	}
	
	// NO DESCRIPTION NO MEETING
	protected Project(String Name, LocalDate StartingDate, int MaxEmployee, Modality Modality, ArrayList<Topic> TopicList, ArrayList<User> UserList, User ProjectManager) throws EmptyListException {
		super();
		this.Name = Name;
		this.StartingDate = StartingDate;
		this.MaxEmployee = MaxEmployee;
		this.Modality = Modality;
		this.setProjectManager(ProjectManager);
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

	public Modality getModality() {
		return Modality;
	}

	public void setModality(Modality modality) {
		Modality = modality;
	}

	public ArrayList<Topic> getTopic() {
		return TopicList;
	}

	public void setTopicList(ArrayList<Topic> topicList) throws EmptyListException {
		MainController.checkEmptyList(topicList);
		TopicList = topicList;
	}

	public void addTopic(Topic topic) {
		TopicList.add(topic);
	}
	
	public void removeTopic(Topic topic) {
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

	public User getProjectManager() {
		return ProjectManager;
	}

	public void setProjectManager(User projectManager) {
		ProjectManager = projectManager;
	}
	
}
