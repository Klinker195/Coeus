package objects;

public class Meeting {
	
	int MeetingID;
	String MettingDate;
	String MettingStartingTime;
	String MettingEndingTime;
	int MeetingProjID;
	
	public Meeting(int MeetingID, String MettingDate, String MettingStartingTime, String MettingEndingTime, int MeetingProjID) {
		super();
		this.MeetingID = MeetingID;
		this.MettingDate = MettingDate;
		this.MettingStartingTime = MettingStartingTime;
		this.MettingEndingTime = MettingEndingTime;
		this.MeetingProjID = MeetingProjID;
	}
	
	public int getMeetingID() {
		return MeetingID;
	}
	public void setMeetingID(int meetingID) {
		MeetingID = meetingID;
	}
	public String getMettingDate() {
		return MettingDate;
	}
	public void setMettingDate(String mettingDate) {
		MettingDate = mettingDate;
	}
	public String getMettingStartingTime() {
		return MettingStartingTime;
	}
	public void setMettingStartingTime(String mettingStartingTime) {
		MettingStartingTime = mettingStartingTime;
	}
	public String getMettingEndingTime() {
		return MettingEndingTime;
	}
	public void setMettingEndingTime(String mettingEndingTime) {
		MettingEndingTime = mettingEndingTime;
	}
	public int getMeetingProjID() {
		return MeetingProjID;
	}
	public void setMeetingProjID(int meetingProjID) {
		MeetingProjID = meetingProjID;
	}

}
