package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import entities.Skill;
import entities.User;
import entities.UserFounder;

public class UserFounderDAOPostgre extends DAOPostgre implements UserFounderDAO {
	
	public UserFounderDAOPostgre() {
		super();
	}
	
	@Override
	public boolean searchFounder() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement Stm = Conn.createStatement();
			ResultSet Rs = Stm.executeQuery("SELECT * FROM \"User\" WHERE \"Founder\" = true");
			if(!Rs.next()) {
				Conn.close();
				return true;
			} else {
				Conn.close();
				return false;
			}
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return false;
	}
	
	@Override
	public void insertUserFounder(User NewFounder) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("INSERT INTO public.\"User\"(\r\n"
					+ "	\"ID\", \"Password\", \"Admin\", \"Founder\")\r\n"
					+ "	VALUES (?, ?, ?, ?);");
			PrepStm.setInt(1, NewFounder.getID());
			PrepStm.setString(2, NewFounder.getPassword());
			PrepStm.setBoolean(3, false);
			PrepStm.setBoolean(4, true);
			PrepStm.executeUpdate();
			
			PrepStm = Conn.prepareStatement("UPDATE public.\"Employee\"\r\n"
					+ "SET \"UserID\" = ?\r\n"
					+ "WHERE \"CF\" = ?;");
			PrepStm.setInt(1, NewFounder.getID());
			PrepStm.setString(2, NewFounder.getEmployee().getCF());
			PrepStm.executeUpdate();
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}
	
	@Override
	public int getNewUserID() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT getnewuserid() AS newID;");
			ResultSet rs = PrepStm.executeQuery();
			
			rs.next();
			return rs.getInt("newid");
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return 0;
		
	}
	
}
