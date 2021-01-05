package objects;

public class Meeting {
	
	int ID;
	String Date;
	String StartingTime;
	String EndingTime;
	int ProjectID;
	
	public Meeting(int ID, String Date, String StartingTime, String EndingTime, int ProjectID) {
		super();
		this.ID = ID;
		this.Date = Date;
		this.StartingTime = StartingTime;
		this.EndingTime = EndingTime;
		this.ProjectID = ProjectID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getStartingTime() {
		return StartingTime;
	}

	public void setStartingTime(String startingTime) {
		StartingTime = startingTime;
	}

	public String getEndingTime() {
		return EndingTime;
	}

	public void setEndingTime(String endingTime) {
		EndingTime = endingTime;
	}

	public int getProjectID() {
		return ProjectID;
	}

	public void setProjectID(int projectID) {
		ProjectID = projectID;
	}

}
