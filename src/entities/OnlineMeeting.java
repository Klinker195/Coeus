package entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class OnlineMeeting extends Meeting {

	private String Platform;
	
	public OnlineMeeting(String Title, LocalDate Date, LocalTime StartingTime, LocalTime EndingTime, Project Project, String Platform) {
		super(Title, Date, StartingTime, EndingTime, Project);
		this.Platform = Platform;
	}

	public String getPlatform() {
		return Platform;
	}

	public void setPlatform(String platform) {
		Platform = platform;
	}
	
}
