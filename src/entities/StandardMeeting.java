package entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class StandardMeeting extends Meeting {

	private String Room;
	
	public StandardMeeting(String Title, LocalDate Date, LocalTime StartingTime, LocalTime EndingTime, Project Project, String Room) {
		super(Title, Date, StartingTime, EndingTime, Project);
		this.Room = Room;
	}
	
	public String getRoom() {
		return Room;
	}

	public void setRoom(String room) {
		Room = room;
	}
	
}
