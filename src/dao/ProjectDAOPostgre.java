package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import controller.Controller;
import entities.Modality;
import entities.OngoingProject;
import entities.Project;

public class ProjectDAOPostgre extends DAOPostgre {

	Controller MainController = Controller.getIstance();
	
	public ProjectDAOPostgre() {
		
	}
	
	
	public ArrayList<Project> getAllOngoingProjects() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement PrepStm = Conn.createStatement();
			ResultSet rs = PrepStm.executeQuery("SELECT * FROM public.\"Project\"\r\n"
					+ "ORDER BY \"Name\" ASC ");
			ArrayList<Project> ProjectList = new ArrayList<Project>();

			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					// FIXME Fix Ongoing Project constructor adding two DAO calls for UserList (users involved in the project) and TopicList (topics of the project)
					ProjectList.add(new OngoingProject(rs.getString("Name"), rs.getString("Description"), LocalDate.parse(rs.getString("StartingDate")), rs.getInt("MaxEmployee"), new Modality(rs.getString("Modality")), MainController.getTopicDAO().getAllTopicNamesByProjectName(rs.getString("Name")), ));
				}
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
