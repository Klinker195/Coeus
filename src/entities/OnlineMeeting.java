package entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import exceptions.EmptyListException;

public class OnlineMeeting extends Meeting {

	private String Platform;
	
	public OnlineMeeting(int ID, LocalDate Date, LocalTime StartingTime, LocalTime EndingTime, String ProjectName, Project Project, ArrayList<User> User, String Platform, ArrayList<MeetingInvitation> MeetingInvitationList) throws EmptyListException {
		super(ID, Date, StartingTime, EndingTime, ProjectName, Project, User, MeetingInvitationList);
		this.Platform = Platform;
	}

	public String getPlatform() {
		return Platform;
	}

	public void setPlatform(String platform) {
		Platform = platform;
	}
	
}
