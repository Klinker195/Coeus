package entities;

import java.util.ArrayList;

public class UserFounder extends User {

	public UserFounder(int ID, String Password, ArrayList<Project> ProjectList, Employee Employee) {
		super(ID, Password, ProjectList, Employee);
	}

	public UserFounder(int ID, String Password, Employee Employee, ArrayList<MeetingInvitation> MeetingInvitationList, ArrayList<Project> ProjectList) {
		super(ID, Password, Employee, MeetingInvitationList, ProjectList);
	}

	public UserFounder(int ID, String Password, Employee Employee, ArrayList<MeetingInvitation> MeetingInvitationList) {
		super(ID, Password, Employee, MeetingInvitationList);
	}

	public UserFounder(int ID, String Password, Employee Employee) {
		super(ID, Password, Employee);
	}

}
