package dao;

import java.util.ArrayList;

import entities.Meeting;

public interface MeetingDAO {

	ArrayList<Meeting> getAllMeetings();

	ArrayList<Meeting> getAllMeetings(boolean Online);

	ArrayList<Meeting> getAllPersonalMeetingsByUserCF(String UserCF, boolean Online);

	int getMeetingIDByMeetingUserCF(String UserCF, Meeting Meeting);

	ArrayList<Integer> getAllMeetingsIDs(boolean Online);

	ArrayList<Integer> getAllPersonalMeetingsIDsByUserCF(String UserCF, boolean Online);

	void deleteMeetingByID(Integer MeetingID);

	ArrayList<String> getAllPlatformsRooms(boolean Online);

	ArrayList<Meeting> getAllMeetingsByDate(String Date);

	ArrayList<Meeting> getAllMeetingsByDateAndRoom(String Date, String Room);

	boolean insertMeeting(Meeting NewMeeting, boolean Online);

	Integer getLatestMeetingID();

	String getProjectNameByID(Integer meetingID);

}