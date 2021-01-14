package entities;

public class OnlineMeeting extends Meeting {
	
	String Platform;

	public OnlineMeeting(int ID, String Date, String StartingTime, String EndingTime, int ProjectID, String Platform) {
		super(ID, Date, StartingTime, EndingTime, ProjectID);
		this.Platform = Platform;
	}
	

}
