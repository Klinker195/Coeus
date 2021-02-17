package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.Controller;
import entities.User;
import entities.UserFounder;

public class UserDAOPostgre extends DAOPostgre implements UserDAO {

	private Controller MainController = Controller.getIstance();
	
	public UserDAOPostgre() {
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
	public void insertStandardUser(User NewUser) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("INSERT INTO public.\"User\"(\r\n"
					+ "	\"ID\", \"Password\", \"Admin\", \"Founder\")\r\n"
					+ "	VALUES (?, ?, ?, ?);");
			PrepStm.setInt(1, NewUser.getID());
			PrepStm.setString(2, NewUser.getPassword());
			PrepStm.setBoolean(3, false);
			PrepStm.setBoolean(4, false);
			PrepStm.executeUpdate();
			
			PrepStm = Conn.prepareStatement("UPDATE public.\"Employee\"\r\n"
					+ "SET \"UserID\" = ?\r\n"
					+ "WHERE \"CF\" = ?;");
			PrepStm.setInt(1, NewUser.getID());
			PrepStm.setString(2, NewUser.getEmployee().getCF());
			PrepStm.executeUpdate();
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
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
	public String getPasswordByUserID(int UserID) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"Password\"\r\n"
					+ "FROM public.\"User\"\r\n"
					+ "WHERE \"ID\" = ?;");
			PrepStm.setInt(1, UserID);
			ResultSet rs = PrepStm.executeQuery();
			
			String Password;

			if(rs.isBeforeFirst()) {
				rs.next();
				Password = rs.getString("Password");
				Conn.close();
				return Password;
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
