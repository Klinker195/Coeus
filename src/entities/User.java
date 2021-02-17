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
		private ArrayList<Project> ManagingProjectList = new ArrayList<Project>();
		
		private Controller MainController = Controller.getIstance();


		
		// ALL
		protected User(int ID, String Password, Employee Employee, ArrayList<MeetingInvitation> MeetingInvitationList, ArrayList<Project> ProjectList, ArrayList<Project> ManagingProjectList) {
			this(ID, Password, Employee, ProjectList, ManagingProjectList);
			this.MeetingInvitationList = MeetingInvitationList;
		}
		
		// NO MEETINGINVITATIONS
		protected User(int ID, String Password, Employee Employee, ArrayList<Project> ProjectList, ArrayList<Project> ManagingProjectList) {
			this(ID, Password, Employee);
			this.ProjectList = ProjectList;
			this.ManagingProjectList = ManagingProjectList;
		}
		
		// NO PROJECTLIST
		protected User(int ID, String Password, Employee Employee, ArrayList<MeetingInvitation> MeetingInvitationList) {
			this(ID, Password, Employee);
			this.MeetingInvitationList = MeetingInvitationList;
		}
		
		// NO PROJECTLIST NO MEETINGINVITATIONS
		protected User(int ID, String Password, Employee Employee) {
			super();
			this.ID = ID;
			this.Password = Password;
			this.Employee = Employee;
		}
		
		// USER WITH DEFAULT ID
		protected User(String Password, Employee Employee) {
			this.ID = 0;
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
		
		public ArrayList<Project> getManagingProjectList() {
			return ManagingProjectList;
		}
		
		public void setManagingProjectList(ArrayList<Project> managingProjectList) {
			ManagingProjectList = managingProjectList;
		}
		
		public void addManagingProject(Project managingProject) {
			ManagingProjectList.add(managingProject);
		}
		
		public void removeManagingProject(Project managingProject) {
			ManagingProjectList.remove(managingProject);
		}
		
}
