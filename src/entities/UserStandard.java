package entities;

import java.util.ArrayList;

public class UserStandard extends User {

	// ALL
	public UserStandard(int ID, String Password, Employee Employee, ArrayList<MeetingInvitation> MeetingInvitationList, ArrayList<Project> ProjectList, ArrayList<Project> ManagingProjectList) {
		super(ID, Password, Employee, MeetingInvitationList, ProjectList, ManagingProjectList);
	}
	
	// NO MEETINGINVITATIONS
	public UserStandard(int ID, String Password, Employee Employee, ArrayList<Project> ProjectList, ArrayList<Project> ManagingProjectList) {
		super(ID, Password, Employee, ProjectList, ManagingProjectList);
	}
	
	// NO PROJECTLIST
	public UserStandard(int ID, String Password, Employee Employee, ArrayList<MeetingInvitation> MeetingInvitationList) {
		super(ID, Password, Employee, MeetingInvitationList);
	}
	
	// NO PROJECTLIST NO MEETINGINVITATIONS
	public UserStandard(int ID, String Password, Employee Employee) {
		super(ID, Password, Employee);
	}
	
	// USER WITH DEFAULT ID
	public UserStandard(String Password, Employee Employee) {
		super(Password, Employee);
	}
	
}
