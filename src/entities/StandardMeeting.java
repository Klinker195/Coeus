package entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import exceptions.EmptyListException;

public class StandardMeeting extends Meeting {

	private String Room;
	
	public StandardMeeting(int ID, LocalDate Date, LocalTime StartingTime, LocalTime EndingTime, String ProjectName, Project Project, ArrayList<User> User, String Room, ArrayList<MeetingInvitation> MeetingInvitationList) throws EmptyListException {
		super(ID, Date, StartingTime, EndingTime, ProjectName, Project, User, MeetingInvitationList);
		this.Room = Room;
	}

	
	public String getRoom() {
		return Room;
	}

	public void setRoom(String room) {
		Room = room;
	}
	
}
