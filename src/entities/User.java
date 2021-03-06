package entities;

import java.util.ArrayList;

import controller.Controller;

public abstract class User {

		private String Password;
		
		private Employee Employee;
		private ArrayList<MeetingInvitation> MeetingInvitationList = new ArrayList<MeetingInvitation>();
		private ArrayList<Project> ProjectList = new ArrayList<Project>();
		private ArrayList<Project> ManagingProjectList = new ArrayList<Project>();
		
		private Controller MainController = Controller.getInstance();
		
		// ALL
		protected User(Employee Employee, String Password, ArrayList<MeetingInvitation> MeetingInvitationList, ArrayList<Project> ProjectList, ArrayList<Project> ManagingProjectList) {
			this(Employee, Password, ProjectList, ManagingProjectList);
			this.MeetingInvitationList = MeetingInvitationList;
		}
		
		// NO MEETINGINVITATIONS
		protected User(Employee Employee, String Password, ArrayList<Project> ProjectList, ArrayList<Project> ManagingProjectList) {
			this(Employee, Password);
			this.ProjectList = ProjectList;
			this.ManagingProjectList = ManagingProjectList;
		}
		
		// NO PROJECTLIST
		protected User(Employee Employee, String Password, ArrayList<MeetingInvitation> MeetingInvitationList) {
			this(Employee, Password);
			this.MeetingInvitationList = MeetingInvitationList;
		}
		
		// NO PROJECTLIST NO MEETINGINVITATIONS
		protected User(Employee Employee, String Password) {
			super();
			this.Password = Password;
			this.Employee = Employee;
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
