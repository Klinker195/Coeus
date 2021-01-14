package entities;

public class StandardMeeting extends Meeting {

	private String Room;
	
	public StandardMeeting(int ID, String Date, String StartingTime, String EndingTime, int ProjectID, String Room) {
		super(ID, Date, StartingTime, EndingTime, ProjectID);
		this.Room = Room;
	}

}
