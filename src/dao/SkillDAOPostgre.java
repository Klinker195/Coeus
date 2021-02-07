package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Skill;

public class SkillDAOPostgre extends DAOPostgre implements SkillDAO {

	public SkillDAOPostgre() {
		
	}
	
	@Override
	public ArrayList<String> getAllSkillNames() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement Stm = Conn.createStatement();
			ResultSet Rs = Stm.executeQuery("SELECT \"Name\" FROM \"Skill\" GROUP BY \"Name\" ORDER BY \"Name\" ASC");
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
	
	public void insertNewSkills(ArrayList<Skill> SkillList) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("INSERT INTO public.\"Skill\"(\r\n"
					+ "	\"Name\")\r\n"
					+ "	VALUES (?);");
			for(int i = 0; i < SkillList.size(); i++) {
				PrepStm.setString(1, SkillList.get(i).getName());
				PrepStm.executeUpdate();
			}
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}
	
}
