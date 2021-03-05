package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import controller.Controller;
import entities.Meeting;
import entities.OngoingProject;
import entities.OnlineMeeting;
import entities.Project;
import entities.StandardMeeting;

public class MeetingDAOPostgre extends DAOPostgre implements MeetingDAO {

	private Controller MainController = Controller.getInstance();
	
	public MeetingDAOPostgre() {
		
	}
	
	@Override
	public ArrayList<Meeting> getAllMeetings() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement PrepStm = Conn.createStatement();
			ResultSet rs = PrepStm.executeQuery("SELECT * FROM public.\"Meeting\"\r\n"
					+ "ORDER BY \"Date\" ASC, \"StartingTime\" ASC");
			ArrayList<Meeting> MeetingList = new ArrayList<Meeting>();

			while(rs.next()) {
				if(rs.getBoolean("Online") == true) {
					MeetingList.add(new OnlineMeeting(rs.getString("Title"), rs.getDate("Date").toLocalDate(), rs.getTime("StartingTime").toLocalTime(), rs.getTime("EndingTime").toLocalTime(), MainController.getProjectDAO().getProjectByName(rs.getString("ProjectName")),rs.getString("OnlineMeetingPlatform")));
				} else {
					MeetingList.add(new StandardMeeting(rs.getString("Title"), rs.getDate("Date").toLocalDate(), rs.getTime("StartingTime").toLocalTime(), rs.getTime("EndingTime").toLocalTime(), MainController.getProjectDAO().getProjectByName(rs.getString("ProjectName")),rs.getString("StandardMeetingRoom")));
				}
			}

			return MeetingList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	@Override
	public Integer getLatestMeetingID() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement PrepStm = Conn.createStatement();
			ResultSet rs = PrepStm.executeQuery("SELECT getnewmeetingid() AS \"ID\";");
			Integer ID;

			if(rs.isBeforeFirst()) {
				rs.next();
				ID = rs.getInt("ID");
				if(ID != 1000) ID -= 1;
				Conn.close();
				return ID;
			}

			Conn.close();
			return null;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	@Override
	public ArrayList<Meeting> getAllMeetings(boolean Online) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT * FROM public.\"Meeting\"\r\n"
					+ "WHERE \"Online\" = ?\r\n"
					+ "ORDER BY \"Date\" ASC, \"StartingTime\" ASC");
			PrepStm.setBoolean(1, Online);
			ResultSet rs = PrepStm.executeQuery();
			
			ArrayList<Meeting> MeetingList = new ArrayList<Meeting>();

			while(rs.next()) {
				if(Online) {
					MeetingList.add(new OnlineMeeting(rs.getString("Title"), rs.getDate("Date").toLocalDate(), rs.getTime("StartingTime").toLocalTime(), rs.getTime("EndingTime").toLocalTime(), MainController.getProjectDAO().getProjectByName(rs.getString("ProjectName")),rs.getString("OnlineMeetingPlatform")));
				} else {
					MeetingList.add(new StandardMeeting(rs.getString("Title"), rs.getDate("Date").toLocalDate(), rs.getTime("StartingTime").toLocalTime(), rs.getTime("EndingTime").toLocalTime(), MainController.getProjectDAO().getProjectByName(rs.getString("ProjectName")),rs.getString("StandardMeetingRoom")));
				}
			}

			Conn.close();
			return MeetingList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	@Override
	public ArrayList<Integer> getAllMeetingsIDs(boolean Online) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"ID\" FROM public.\"Meeting\"\r\n"
					+ "WHERE \"Online\" = ?\r\n"
					+ "ORDER BY \"Date\" ASC, \"StartingTime\" ASC");
			PrepStm.setBoolean(1, Online);
			ResultSet rs = PrepStm.executeQuery();
			
			ArrayList<Integer> MeetingIDsList = new ArrayList<Integer>();

			while(rs.next()) {
				MeetingIDsList.add(rs.getInt("ID"));
			}
			
			Conn.close();
			return MeetingIDsList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}

	@Override
	public ArrayList<Meeting> getAllPersonalMeetingsByUserCF(String UserCF, boolean Online) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT * FROM public.\"UserMeeting\"\r\n"
					+ "WHERE \"Online\" = ? AND \"EmployeeCF\" = ?\r\n"
					+ "ORDER BY \"Date\" ASC, \"StartingTime\" ASC");
			PrepStm.setBoolean(1, Online);
			PrepStm.setString(2, UserCF);
			ResultSet rs = PrepStm.executeQuery();
			
			ArrayList<Meeting> MeetingList = new ArrayList<Meeting>();

			while(rs.next()) {
				if(Online) {
					MeetingList.add(new OnlineMeeting(rs.getString("Title"), rs.getDate("Date").toLocalDate(), rs.getTime("StartingTime").toLocalTime(), rs.getTime("EndingTime").toLocalTime(), MainController.getProjectDAO().getProjectByName(rs.getString("ProjectName")), rs.getString("OnlineMeetingPlatform")));
				} else {
					MeetingList.add(new StandardMeeting(rs.getString("Title"), rs.getDate("Date").toLocalDate(), rs.getTime("StartingTime").toLocalTime(), rs.getTime("EndingTime").toLocalTime(), MainController.getProjectDAO().getProjectByName(rs.getString("ProjectName")), rs.getString("StandardMeetingRoom")));
				}
			}

			Conn.close();
			return MeetingList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	@Override
	public ArrayList<Meeting> getAllMeetingsByDate(String Date) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT * FROM public.\"Meeting\"\r\n"
					+ "WHERE \"Date\" = ?::date\r\n"
					+ "ORDER BY \"Date\" ASC, \"StartingTime\" ASC");
			PrepStm.setString(1, Date);
			ResultSet rs = PrepStm.executeQuery();
			
			ArrayList<Meeting> MeetingList = new ArrayList<Meeting>();

			while(rs.next()) {
				if(rs.getBoolean("Online") == true) {
					MeetingList.add(new OnlineMeeting(rs.getString("Title"), rs.getDate("Date").toLocalDate(), rs.getTime("StartingTime").toLocalTime(), rs.getTime("EndingTime").toLocalTime(), MainController.getProjectDAO().getProjectByName(rs.getString("ProjectName")), rs.getString("OnlineMeetingPlatform")));
				} else {
					MeetingList.add(new StandardMeeting(rs.getString("Title"), rs.getDate("Date").toLocalDate(), rs.getTime("StartingTime").toLocalTime(), rs.getTime("EndingTime").toLocalTime(), MainController.getProjectDAO().getProjectByName(rs.getString("ProjectName")), rs.getString("StandardMeetingRoom")));
				}
			}

			Conn.close();
			return MeetingList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	@Override
	public ArrayList<Meeting> getAllMeetingsByDateAndRoom(String Date, String Room) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT * FROM public.\"Meeting\"\r\n"
					+ "WHERE \"Date\" = ?::date AND \"StandardMeetingRoom\" = ?\r\n"
					+ "ORDER BY \"Date\" ASC, \"StartingTime\" ASC");
			PrepStm.setString(1, Date);
			PrepStm.setString(2, Room);
			ResultSet rs = PrepStm.executeQuery();
			
			ArrayList<Meeting> MeetingList = new ArrayList<Meeting>();

			while(rs.next()) {
				MeetingList.add(new StandardMeeting(rs.getString("Title"), rs.getDate("Date").toLocalDate(), rs.getTime("StartingTime").toLocalTime(), rs.getTime("EndingTime").toLocalTime(), MainController.getProjectDAO().getProjectByName(rs.getString("ProjectName")), rs.getString("StandardMeetingRoom")));
			}

			Conn.close();
			return MeetingList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	@Override
	public ArrayList<Integer> getAllPersonalMeetingsIDsByUserCF(String UserCF, boolean Online) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"ID\" FROM public.\"UserMeeting\"\r\n"
					+ "WHERE \"Online\" = ? AND \"EmployeeCF\" = ?\r\n"
					+ "ORDER BY \"Date\" ASC, \"StartingTime\" ASC");
			PrepStm.setBoolean(1, Online);
			PrepStm.setString(2, UserCF);
			ResultSet rs = PrepStm.executeQuery();
			
			ArrayList<Integer> MeetingIDsList = new ArrayList<Integer>();

			while(rs.next()) {
				MeetingIDsList.add(rs.getInt("ID"));
			}

			Conn.close();
			return MeetingIDsList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	// FIXME Remove getMeetingIDByMeetingUserCF ???
	@Override
	public int getMeetingIDByMeetingUserCF(String UserCF, Meeting Meeting) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"ID\" FROM public.\"UserMeeting\"\r\n"
					+ "WHERE \"Title\" = ? AND \"Date\" = ? AND \"StartingTime\" = ? AND \"EndingTime\" = ? AND \"EmployeeCF\" = ?\r\n"
					+ "ORDER BY \"Date\" ASC, \"StartingTime\" ASC");
			PrepStm.setString(1, Meeting.getTitle());
			PrepStm.setString(2, Meeting.getDate().toString());
			PrepStm.setString(3, Meeting.getStartingTime().toString());
			PrepStm.setString(4, Meeting.getEndingTime().toString());
			PrepStm.setString(5, UserCF);
			ResultSet rs = PrepStm.executeQuery();
			
			int MeetingID = 0;

			if(rs.isBeforeFirst()) {
				MeetingID = rs.getInt("ID");
				Conn.close();
				return MeetingID;
			} else {
				Conn.close();
				return 0;
			}
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return 0;
		
	}

	@Override
	public void deleteMeetingByID(Integer MeetingID) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("DELETE FROM public.\"Meeting\" WHERE \"ID\" = ?");
			PrepStm.setInt(1, MeetingID);
			
			PrepStm.executeUpdate();
			
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}
	
	@Override
	public boolean insertMeeting(Meeting NewMeeting, boolean Online) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm;
			
			if(Online) {
				PrepStm = Conn.prepareStatement("INSERT INTO public.\"Meeting\"(\r\n"
						+ "	\"ID\", \"Title\", \"Date\", \"Online\", \"StartingTime\", \"EndingTime\", \"OnlineMeetingPlatform\", \"ProjectName\")\r\n"
						+ "	VALUES (getnewmeetingid(), ?, ?::date, true, ?::time, ?::time, ?, ?);");
				
				OnlineMeeting NewOnlineMeeting = (OnlineMeeting)NewMeeting;
				
				PrepStm.setString(1, NewOnlineMeeting.getTitle());
				PrepStm.setString(2, NewOnlineMeeting.getDate().toString());
				PrepStm.setString(3, NewOnlineMeeting.getStartingTime().toString());
				PrepStm.setString(4, NewOnlineMeeting.getEndingTime().toString());
				PrepStm.setString(5, NewOnlineMeeting.getPlatform());
				PrepStm.setString(6, NewOnlineMeeting.getProject().getName());
				
			} else {
				PrepStm = Conn.prepareStatement("INSERT INTO public.\"Meeting\"(\r\n"
						+ "	\"ID\", \"Title\", \"Date\", \"Online\", \"StartingTime\", \"EndingTime\", \"StandardMeetingRoom\", \"ProjectName\")\r\n"
						+ "	VALUES (getnewmeetingid(), ?, ?::date, false, ?::time, ?::time, ?, ?);");
				
				StandardMeeting NewStandardMeeting = (StandardMeeting)NewMeeting;
				
				PrepStm.setString(1, NewStandardMeeting.getTitle());
				PrepStm.setString(2, NewStandardMeeting.getDate().toString());
				PrepStm.setString(3, NewStandardMeeting.getStartingTime().toString());
				PrepStm.setString(4, NewStandardMeeting.getEndingTime().toString());
				PrepStm.setString(5, NewStandardMeeting.getRoom());
				PrepStm.setString(6, NewStandardMeeting.getProject().getName());
				
			}

			PrepStm.executeUpdate();
			Conn.close();
			return true;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return false;
		
	}
	
	@Override
	public ArrayList<String> getAllPlatformsRooms(boolean Online) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm;
			
			if(Online) {
				PrepStm = Conn.prepareStatement("SELECT * FROM public.\"OnlineMeetingPlatform\"");
			} else {
				PrepStm = Conn.prepareStatement("SELECT * FROM public.\"StandardMeetingRoom\"");
			}
			ResultSet rs = PrepStm.executeQuery();

			ArrayList<String> tmpList = new ArrayList<String>();
			
			while(rs.next()) {
				tmpList.add(rs.getString("Name"));
			}
			
			Conn.close();
			return tmpList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
		
	}
	
	public String getProjectNameByID(Integer MeetingID) {

		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"ProjectName\" FROM public.\"Meeting\"\r\n"
					+ "WHERE \"ID\" = ?\r\n"
					+ "ORDER BY \"Date\" ASC, \"StartingTime\" ASC");
			PrepStm.setInt(1, MeetingID);
			ResultSet rs = PrepStm.executeQuery();
			
			String ProjectName;

			if(rs.isBeforeFirst()) {
				rs.next();
				ProjectName = rs.getString("ProjectName");
				Conn.close();
				return ProjectName;
			} else {
				Conn.close();
				return null;
			}
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	
}
