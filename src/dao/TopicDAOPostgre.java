package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Topic;

public class TopicDAOPostgre extends DAOPostgre implements TopicDAO {

	public TopicDAOPostgre() {
		
	}
	
	@Override
	public ArrayList<Topic> getAllTopicNamesByProjectName(String ProjectName) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"ProjectTopicName\" FROM public.\"ProjectScope\"\r\n"
					+ "WHERE \"ProjectName\" = ?");
			PrepStm.setString(1, ProjectName);
			ResultSet Rs = PrepStm.executeQuery();
			ArrayList<Topic> tmpList = new ArrayList<Topic>();
			while(Rs.next()) {
				tmpList.add(new Topic(Rs.getString("ProjectTopicName")));
			}
			Conn.close();
			return tmpList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
	}
	
}
