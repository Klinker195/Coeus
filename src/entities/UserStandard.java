package entities;

import java.util.ArrayList;

public class UserStandard extends User {

	public UserStandard(int ID, String Password, ArrayList<Project> ProjectList, Employee Employee) {
		super(ID, Password, ProjectList, Employee);
	}

	public UserStandard(int ID, String Password, Employee Employee, ArrayList<MeetingInvitation> MeetingInvitationList, ArrayList<Project> ProjectList) {
		super(ID, Password, Employee, MeetingInvitationList, ProjectList);
	}

	public UserStandard(int ID, String Password, Employee Employee, ArrayList<MeetingInvitation> MeetingInvitationList) {
		super(ID, Password, Employee, MeetingInvitationList);
	}

	public UserStandard(int ID, String Password, Employee Employee) {
		super(ID, Password, Employee);
	}
	
}
