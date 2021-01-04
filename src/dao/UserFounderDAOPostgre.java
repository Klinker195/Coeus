package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class UserFounderDAOPostgre extends DAOPostgre implements UserFounderDAO {
	
	public UserFounderDAOPostgre() {
		
	}
	
//	public static void main(String args[]) {
//	
//		UserFounderDAOPostgre UserFounderDAO = new UserFounderDAOPostgre();
//		
//		System.out.println(UserFounderDAO.isEmpty());
//}
	
	@Override
	public boolean isEmpty() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement Stm = Conn.createStatement();
			ResultSet Rs = Stm.executeQuery("SELECT * FROM \"UserFounder\"");
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
}
