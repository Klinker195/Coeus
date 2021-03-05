package entities;

import enums.InviteStatus;

public class MeetingInvitation {

	private Meeting Meeting;
	private InviteStatus Status;
	
//	public static void main(String[] args) throws EmptyListException {
//		
//		ArrayList<Skill> TestUserSkills = new ArrayList<Skill>();
//		TestUserSkills.add(new Skill("C#"));
//		User TestUser = new UserStandard(1,"cazzo", new Employee("VSCGLC00E19F839V", "Gianluca", "Viscardi", "gianlucaviscardi@gmail.com", 10000f, "GMT+01:00", TestUserSkills));
//		
//		ArrayList<User> UserList = new ArrayList<User>();
//		UserList.add(TestUser);
//		
//		ArrayList<String> Topic = new ArrayList<String>();
//		Topic.add("TopicTest");
//		
//		Meeting MeetingOnline = new OnlineMeeting(23, LocalDate.now(), LocalTime.now(), LocalTime.now(), "Cavalli pazzi", new Project("Progetto sui cavalli", LocalDate.now(), 30, "Test", Topic, TestUser), UserList, "Skype");
//		
//		ArrayList<Meeting> MeetingList = new ArrayList<Meeting>();
//		MeetingList.add(MeetingOnline);
//		
//		MeetingInvitation MeetingInvite = new MeetingInvitation(UserList, MeetingList, InviteStatus.PENDING);
//		
//		System.out.println(MeetingInvite.getStatus());
//		
//		MeetingInvite.setStatus(InviteStatus.ACCEPTED);
//		
//		System.out.println(MeetingInvite.getStatus());
//		
//	}
	
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
