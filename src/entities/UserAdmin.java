package entities;

import java.util.ArrayList;

public class UserAdmin extends User {
	
	// ALL
	public UserAdmin(Employee Employee, String Password, ArrayList<MeetingInvitation> MeetingInvitationList, ArrayList<Project> ProjectList, ArrayList<Project> ManagingProjectList) {
		super(Employee, Password, MeetingInvitationList, ProjectList, ManagingProjectList);
	}
	
	// NO MEETINGINVITATIONS
	public UserAdmin(Employee Employee, String Password, ArrayList<Project> ProjectList, ArrayList<Project> ManagingProjectList) {
		super(Employee, Password, ProjectList, ManagingProjectList);
	}
	
	// NO PROJECTLIST
	public UserAdmin(Employee Employee, String Password, ArrayList<MeetingInvitation> MeetingInvitationList) {
		super(Employee, Password, MeetingInvitationList);
	}
	
	// NO PROJECTLIST NO MEETINGINVITATIONS
	public UserAdmin(Employee Employee, String Password) {
		super(Employee, Password);
	}
	
}
