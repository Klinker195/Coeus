package entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import controller.Controller;

public abstract class Meeting {

	private String Title;
	private LocalDate Date;
	private LocalTime StartingTime;
	private LocalTime EndingTime;
	
	private Project Project;
	
	private Controller MainController = Controller.getInstance();
	
	protected Meeting(String Title, LocalDate Date, LocalTime StartingTime, LocalTime EndingTime, Project Project) {
		super();
		this.setTitle(Title);
		this.Date = Date;
		this.StartingTime = StartingTime;
		this.EndingTime = EndingTime;
		this.setProject(Project);
	}


	public LocalDate getDate() {
		return Date;
	}

	public void setDate(LocalDate date) {
		Date = date;
	}

	public LocalTime getStartingTime() {
		return StartingTime;
	}

	public void setStartingTime(LocalTime startingTime) {
		StartingTime = startingTime;
	}

	public LocalTime getEndingTime() {
		return EndingTime;
	}

	public void setEndingTime(LocalTime endingTime) {
		EndingTime = endingTime;
	}



	public String getTitle() {
		return Title;
	}



	public void setTitle(String title) {
		Title = title;
	}


	public Project getProject() {
		return Project;
	}


	public void setProject(Project project) {
		Project = project;
	}

}
