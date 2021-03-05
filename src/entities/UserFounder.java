package entities;

import java.util.ArrayList;

public class UserFounder extends User {

	// ALL
	public UserFounder(Employee Employee, String Password, ArrayList<MeetingInvitation> MeetingInvitationList, ArrayList<Project> ProjectList, ArrayList<Project> ManagingProjectList) {
		super(Employee, Password, MeetingInvitationList, ProjectList, ManagingProjectList);
	}
	
	// NO MEETINGINVITATIONS
	public UserFounder(Employee Employee, String Password, ArrayList<Project> ProjectList, ArrayList<Project> ManagingProjectList) {
		super(Employee, Password, ProjectList, ManagingProjectList);
	}
	
	// NO PROJECTLIST
	public UserFounder(Employee Employee, String Password, ArrayList<MeetingInvitation> MeetingInvitationList) {
		super(Employee, Password, MeetingInvitationList);
	}
	
	// NO PROJECTLIST NO MEETINGINVITATIONS
	public UserFounder(Employee Employee, String Password) {
		super(Employee, Password);
	}

}
