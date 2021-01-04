package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import objects.Person;
import objects.Skill;

public class WorldStatesDAOPostgre extends DAOPostgre implements WorldStatesDAO {

	public WorldStatesDAOPostgre() {
		
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
	public ArrayList<String> getAllStates() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement Stm = Conn.createStatement();
			ResultSet Rs = Stm.executeQuery("SELECT \"StateName\" FROM \"WorldStates\"");
			ArrayList<String> tmpList = new ArrayList<String>();
			while(Rs.next()) {
				tmpList.add(Rs.getString("StateName"));
			}
			Conn.close();
			return tmpList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
	}
	
}
