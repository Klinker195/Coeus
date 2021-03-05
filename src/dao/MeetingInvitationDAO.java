package dao;

import java.util.ArrayList;

import entities.MeetingInvitation;
import enums.InviteStatus;

public interface MeetingInvitationDAO {

	String getMeetingInvitationStatusByID_UserCF(String UserCF, int MeetingID);

	void insertMeetingInvitation(MeetingInvitation Invitation, Integer MeetingID, String UserCF);

	void insertMeetingInvitationFromCFList(InviteStatus Status, Integer MeetingID, ArrayList<String> MembersCF);

}