package entities;

import java.util.ArrayList;

import controller.Controller;
import exceptions.EmptyListException;

public abstract class User {

		private int ID = 0;
		private String Password;
		
		private Employee Employee;
		private ArrayList<MeetingInvitation> MeetingInvitationList = new ArrayList<MeetingInvitation>();
		private ArrayList<Project> ProjectList = new ArrayList<Project>();
		
		private Controller MainController = Controller.getIstance();

		// USER WITH DEFAULT ID
		protected User(String Password, Employee Employee, ArrayList<MeetingInvitation> MeetingInvitationList, ArrayList<Project> ProjectList) {
			this.ID = 0;
			this.Password = Password;
			this.Employee = Employee;
			this.MeetingInvitationList = MeetingInvitationList;
			this.ProjectList = ProjectList;
		}
		
		// ALL
		protected User(int ID, String Password, Employee Employee, ArrayList<MeetingInvitation> MeetingInvitationList, ArrayList<Project> ProjectList) {
			this(ID, Password, Employee, MeetingInvitationList);
			this.ProjectList = ProjectList;
		}
		
		// NO PROJECTLIST
		protected User(int ID, String Password, Employee Employee, ArrayList<MeetingInvitation> MeetingInvitationList) {
			this(ID, Password, Employee);
			this.MeetingInvitationList = MeetingInvitationList;
		}
		
		// NO MEETINGINVITATIONS
		protected User(int ID, String Password, ArrayList<Project> ProjectList, Employee Employee) {
			this(ID, Password, Employee);
			this.ProjectList = ProjectList;
		}
		
		// NO PROJECTLIST NO MEETINGINVITATIONS
		protected User(int ID, String Password, Employee Employee) {
			super();
			this.ID = ID;
			this.Password = Password;
			this.Employee = Employee;
		}
		
		
		
		public int getID() {
			return ID;
		}

		public void setID(int iD) {
			ID = iD;
		}

		public String getPassword() {
			return Password;
		}

		public void setPassword(String password) {
			Password = password;
		}
	
		public Employee getEmployee() {
			return Employee;
		}
		
		public ArrayList<MeetingInvitation> getMeetingInvitationList() {
			return MeetingInvitationList;
		}
		
		public void setMeetingInvitationList(ArrayList<MeetingInvitation> meetingInvitationList) {
			MeetingInvitationList = meetingInvitationList;
		}
		
		public void addMeetingInvitation(MeetingInvitation meetingInvitation) {
			MeetingInvitationList.add(meetingInvitation);
		}
		
		public void removeMeetingInvitation(MeetingInvitation meetingInvitation) {
			MeetingInvitationList.remove(meetingInvitation);
		}
		
		public ArrayList<Project> getProjectList() {
			return ProjectList;
		}
		
		public void setProjectList(ArrayList<Project> projectList) {
			ProjectList = projectList;
		}
		
		public void addProject(Project project) {
			ProjectList.add(project);
		}
		
		public void removeProject(Project project) {
			ProjectList.remove(project);
		}
		
}
