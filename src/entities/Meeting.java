package entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import controller.Controller;
import exceptions.EmptyListException;

public abstract class Meeting {

	private int ID;
	private LocalDate Date;
	private LocalTime StartingTime;
	private LocalTime EndingTime;
	private String ProjectName;
	
	private Project Project;
	private ArrayList<User> UserList;
	private ArrayList<MeetingInvitation> MeetingInvitationList;
	
	private Controller MainController = Controller.getIstance();
	
	protected Meeting(int ID, LocalDate Date, LocalTime StartingTime, LocalTime EndingTime, String ProjectName, Project Project, ArrayList<User> User, ArrayList<MeetingInvitation> MeetingInvitationList) throws EmptyListException {
		super();
		this.ID = ID;
		this.Date = Date;
		this.StartingTime = StartingTime;
		this.EndingTime = EndingTime;
		this.ProjectName = ProjectName;
		this.Project = Project;
		this.setUserList(User);
		this.setMeetingInvitationList(MeetingInvitationList);
	}

	
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public LocalDate getDate() {
		return Date;
	}

	public void setDate(LocalDate date) {
		Date = date;
	}

	public LocalTime getStartingTime() {
		return StartingTime;
	}

	public void setStartingTime(LocalTime startingTime) {
		StartingTime = startingTime;
	}

	public LocalTime getEndingTime() {
		return EndingTime;
	}

	public void setEndingTime(LocalTime endingTime) {
		EndingTime = endingTime;
	}

	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	public Project getProject() {
		return Project;
	}

	public void setProject(Project project) {
		Project = project;
	}

	public ArrayList<User> getUserArray() {
		return UserList;
	}

	public ArrayList<MeetingInvitation> getMeetingInvitationList() {
		return MeetingInvitationList;
	}
	
	public void setMeetingInvitationList(ArrayList<MeetingInvitation> meetingInvitationList) throws EmptyListException {
		MainController.checkEmptyList(meetingInvitationList);
		MeetingInvitationList = meetingInvitationList;
	}
	
	public void addMeetingInvitation(MeetingInvitation meetingInvitation) {
		MeetingInvitationList.add(meetingInvitation);
	}
	
	public void removeMeetingInvitation(MeetingInvitation meetingInvitation) {
		if(MeetingInvitationList.size() > 1) {
			MeetingInvitationList.remove(meetingInvitation);
		}
	}
	
	public ArrayList<User> getUserList() {
		return UserList;
	}
	
	public void setUserList(ArrayList<User> userList) throws EmptyListException {
		MainController.checkEmptyList(userList);
		UserList = userList;
	}
	
	public void addUser(User user) {
		UserList.add(user);
	}
	
	public void removeUser(User user) {
		if(UserList.size() > 1) {
			UserList.remove(user);
		}
	}
}
