package entities;

import controller.Controller;
import enums.InviteStatus;

public class MeetingInvitation {

	private InviteStatus Status;
	
	private Meeting Meeting;
	
	private Controller MainController = Controller.getInstance();
	
	public MeetingInvitation(Meeting Meeting) {
		super();
		this.Meeting = Meeting;
		this.Status = InviteStatus.PENDING;
	}

	public MeetingInvitation(Meeting Meeting, InviteStatus Status) {
		super();
		this.Meeting = Meeting;
		this.Status = Status;
	}
	
	public Meeting getMeeting() {
		return Meeting;
	}

	public InviteStatus getStatus() {
		return Status;
	}

	public void setStatus(InviteStatus status) {
		Status = status;
	}
	
}
