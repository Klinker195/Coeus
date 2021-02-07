package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import entities.Employee;
import entities.Skill;

public class WorldStateDAOPostgre extends DAOPostgre implements WorldStateDAO {

	public WorldStateDAOPostgre() {
		
	}
	
//	public static void main(String args[]) {
//		
//		WorldStatesDAO WorldStatesDAO = new WorldStatesDAOPostgre();
//		
//		ArrayList<String> StateNamesList = WorldStatesDAO.getAllStates();
//		
//		System.out.format(" [LISTA NAZIONI]\n");
//		for(String s : StateNamesList) {
//			System.out.println(" " + s);
//		}
//		
//	}
	
	
	@Override
	public ArrayList<String> getAllStateNames() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement Stm = Conn.createStatement();
			ResultSet Rs = Stm.executeQuery("SELECT * FROM public.\"WorldState\"\r\n"
					+ "ORDER BY \"StateID\" ASC ");
			ArrayList<String> tmpList = new ArrayList<String>();
			while(Rs.next()) {
				tmpList.add(Rs.getString("Name"));
			}
			Conn.close();
			return tmpList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
	}
	
	@Override
	public String getStateCodeByStateName(String StateName) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"ID\"\r\n"
					+ "FROM public.\"WorldState\"\r\n"
					+ "WHERE \"Name\" = ?");
			PrepStm.setString(1, StateName);
			ResultSet Rs = PrepStm.executeQuery();
			String StateCode = "";
			while(Rs.next()) {
				StateCode = Rs.getString("ID");
			}
			Conn.close();
			return StateCode;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
	}
	
}
