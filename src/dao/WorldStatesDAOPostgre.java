package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import objects.Person;
import objects.Skill;

public class WorldStatesDAOPostgre extends DAOPostgre {

	public WorldStatesDAOPostgre() {
		
	}
	
//	public static void main(String args[]) {
//		
//		WorldStatesDAOPostgre WorldStatesDAO = new WorldStatesDAOPostgre();
//		
//		LinkedList<String> StateNamesList = WorldStatesDAO.getAllStates();
//		
//		System.out.format(" [LISTA NAZIONI]\n");
//		for(String s : StateNamesList) {
//			System.out.println(" " + s);
//		}
//		
//	}
	
	
	public LinkedList<String> getAllStates() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement Stm = Conn.createStatement();
			ResultSet Rs = Stm.executeQuery("SELECT \"StateName\" FROM \"WorldStates\"");
			LinkedList<String> tmpList = new LinkedList<String>();
			while(Rs.next()) {
				tmpList.addLast(Rs.getString("StateName"));
			}
			Conn.close();
			return tmpList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
	}
	
}
