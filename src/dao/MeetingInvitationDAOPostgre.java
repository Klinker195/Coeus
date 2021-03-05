package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entities.Meeting;
import entities.MeetingInvitation;
import entities.OnlineMeeting;
import entities.StandardMeeting;
import enums.InviteStatus;

public class MeetingInvitationDAOPostgre extends DAOPostgre implements MeetingInvitationDAO {
	
	// FIXME Remove getMeetingInvitationStatusByID_UserCF ???
	@Override
	public String getMeetingInvitationStatusByID_UserCF(String UserCF, int MeetingID) {

		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"Status\"\r\n"
					+ "FROM public.\"MeetingInvitation\"\r\n"
					+ "WHERE \"UserEmployeeCF\" = ? AND \"MeetingID\" = ?;");
			PrepStm.setString(1, UserCF);
			PrepStm.setInt(2, MeetingID);
			ResultSet rs = PrepStm.executeQuery();
			
			String StringStatus;

			if(rs.isBeforeFirst()) {
				rs.next();
				StringStatus = rs.getString("Status");
				Conn.close();
				return StringStatus;
			} else {
				Conn.close();
				return null;
			}

		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	@Override
	public void insertMeetingInvitation(MeetingInvitation Invitation, Integer MeetingID, String UserCF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm;
			
			if(Invitation.getStatus() == InviteStatus.ACCEPTED) {
				PrepStm = Conn.prepareStatement("INSERT INTO public.\"MeetingInvitation\"(\r\n"
						+ "	\"MeetingID\", \"Status\", \"UserEmployeeCF\")\r\n"
						+ "	VALUES (?, 'ACCEPTED', ?);");
				PrepStm.setInt(1, MeetingID);
				PrepStm.setString(2, UserCF);
			} else if(Invitation.getStatus() == InviteStatus.REFUSED) {
				PrepStm = Conn.prepareStatement("INSERT INTO public.\"MeetingInvitation\"(\r\n"
						+ "	\"MeetingID\", \"Status\", \"UserEmployeeCF\")\r\n"
						+ "	VALUES (?, 'REFUSED', ?);");
				PrepStm.setInt(1, MeetingID);
				PrepStm.setString(2, UserCF);
			} else {
				PrepStm = Conn.prepareStatement("INSERT INTO public.\"MeetingInvitation\"(\r\n"
						+ "	\"MeetingID\", \"Status\", \"UserEmployeeCF\")\r\n"
						+ "	VALUES (?, 'PENDING', ?);");
				PrepStm.setInt(1, MeetingID);
				PrepStm.setString(2, UserCF);
			}

			PrepStm.executeUpdate();
			
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}
	
	@Override
	public void insertMeetingInvitationFromCFList(InviteStatus Status, Integer MeetingID, ArrayList<String> MembersCF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm;
			
			if(Status == InviteStatus.ACCEPTED) {
				PrepStm = Conn.prepareStatement("INSERT INTO public.\"MeetingInvitation\"(\r\n"
						+ "	\"MeetingID\", \"Status\", \"UserEmployeeCF\")\r\n"
						+ "	VALUES (?, 'ACCEPTED', ?);");
				PrepStm.setInt(1, MeetingID);
				for(int i = 0; i < MembersCF.size(); i++) {
					PrepStm.setString(2, MembersCF.get(i));
					PrepStm.executeUpdate();
				}
			} else if(Status == InviteStatus.REFUSED) {
				PrepStm = Conn.prepareStatement("INSERT INTO public.\"MeetingInvitation\"(\r\n"
						+ "	\"MeetingID\", \"Status\", \"UserEmployeeCF\")\r\n"
						+ "	VALUES (?, 'REFUSED', ?);");
				PrepStm.setInt(1, MeetingID);
				for(int i = 0; i < MembersCF.size(); i++) {
					PrepStm.setString(2, MembersCF.get(i));
					PrepStm.executeUpdate();
				}
			} else {
				PrepStm = Conn.prepareStatement("INSERT INTO public.\"MeetingInvitation\"(\r\n"
						+ "	\"MeetingID\", \"Status\", \"UserEmployeeCF\")\r\n"
						+ "	VALUES (?, 'PENDING', ?);");
				PrepStm.setInt(1, MeetingID);
				for(int i = 0; i < MembersCF.size(); i++) {
					PrepStm.setString(2, MembersCF.get(i));
					PrepStm.executeUpdate();
				}
			}
			
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}
	
}
