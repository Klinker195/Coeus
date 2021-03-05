package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import controller.Controller;
import entities.CompletedProject;
import entities.Modality;
import entities.OngoingProject;
import entities.Project;
import entities.User;

public class ProjectDAOPostgre extends DAOPostgre implements ProjectDAO {

	private Controller MainController = Controller.getInstance();
	
	public ProjectDAOPostgre() {
		
	}
	
	@Override
	public boolean existsByProjectName(String ProjectName) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT * FROM public.\"Project\" WHERE \"Name\" = ?");
			PrepStm.setString(1, ProjectName);
			ResultSet rs = PrepStm.executeQuery();
			
			if(rs.isBeforeFirst()) {
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
	public ArrayList<String> getAllTopicNamesByProjectName(String ProjectName) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"ProjectTopicName\" FROM \"ProjectScope\" WHERE \"ProjectName\" = ?");
			PrepStm.setString(1, ProjectName);
			ResultSet Rs = PrepStm.executeQuery();
			ArrayList<String> tmpList = new ArrayList<String>();
			while(Rs.next()) {
				tmpList.add(Rs.getString("ProjectTopicName"));
			}
			Conn.close();
			return tmpList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
	}
	
	@Override
	public ArrayList<Project> getAllPersonalProjectsByUserCF(String UserCF, boolean Complete) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"Name\", \"Description\", \"StartingDate\", \"MaxEmployee\", \"Modality\", \"EndingDate\", \"Complete\", \"ProjectManager\" FROM public.\"UserProject\" AS UsrProj\r\n"
					+ "JOIN public.\"Project\" AS Proj\r\n"
					+ "ON Proj.\"Name\" = UsrProj.\"ProjectName\"\r\n"
					+ "WHERE Proj.\"Complete\" = ? AND UsrProj.\"UserCF\" = ?");
			PrepStm.setBoolean(1, Complete);
			PrepStm.setString(2, UserCF);
			ResultSet rs = PrepStm.executeQuery();
			ArrayList<Project> ProjectList = new ArrayList<Project>();

			while(rs.next()) {
				if(Complete) {
					ProjectList.add(new CompletedProject(rs.getString("Name"), rs.getDate("StartingDate").toLocalDate(), rs.getDate("EndingDate").toLocalDate(), rs.getInt("MaxEmployee"), rs.getString("Modality"), getAllTopicNamesByProjectName(rs.getString("Name"))));
				} else {
					ProjectList.add(new OngoingProject(rs.getString("Name"), rs.getDate("StartingDate").toLocalDate(), rs.getInt("MaxEmployee"), rs.getString("Modality"), getAllTopicNamesByProjectName(rs.getString("Name"))));
				}
			}
			return ProjectList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	@Override
	public ArrayList<String> getAllPersonalOngoingProjectNamesByUserCF(String UserCF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"Name\" FROM public.\"UserProject\" AS UsrProj\r\n"
					+ "JOIN public.\"Project\" AS Proj\r\n"
					+ "ON Proj.\"Name\" = UsrProj.\"ProjectName\"\r\n"
					+ "WHERE Proj.\"Complete\" = false AND UsrProj.\"UserCF\" = ?");
			PrepStm.setString(1, UserCF);
			ResultSet rs = PrepStm.executeQuery();
			ArrayList<String> ProjectList = new ArrayList<String>();

			while(rs.next()) {
				ProjectList.add(rs.getString("Name"));
			}
			return ProjectList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	@Override
	public ArrayList<String> getAllOngoingProjectNames() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement Stm = Conn.createStatement();
			ResultSet rs = Stm.executeQuery("SELECT * FROM public.\"Project\" WHERE \"Complete\" = false\r\n"
					+ "ORDER BY \"Name\" ASC ");
			
			ArrayList<String> tmpList = new ArrayList<String>();
			
			while(rs.next()) {
				tmpList.add(rs.getString("Name"));
			}
			
			Conn.close();
			return tmpList;
			
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}
	
	@Override
	public Project getProjectByName(String ProjectName) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT * FROM public.\"Project\" WHERE \"Name\" = ?\r\n"
					+ "ORDER BY \"Name\" ASC ");
			PrepStm.setString(1, ProjectName);
			ResultSet rs = PrepStm.executeQuery();

			if(rs.isBeforeFirst()) {
				rs.next();
				if(rs.getBoolean("Complete") == true) {
					Conn.close();
					return new CompletedProject(rs.getString("Name"), rs.getDate("StartingDate").toLocalDate(), rs.getDate("EndingDate").toLocalDate(), rs.getInt("MaxEmployee"), rs.getString("Modality"), getAllTopicNamesByProjectName(rs.getString("Name")));
				} else {
					Conn.close();
					return new OngoingProject(rs.getString("Name"), rs.getDate("StartingDate").toLocalDate(), rs.getInt("MaxEmployee"), rs.getString("Modality"), getAllTopicNamesByProjectName(rs.getString("Name")));
					
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
	
	@Override
	public ArrayList<Project> getAllProjects(boolean Complete) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT * FROM public.\"Project\" WHERE \"Complete\" = ?\r\n"
					+ "ORDER BY \"Name\" ASC ");
			PrepStm.setBoolean(1, Complete);
			ResultSet rs = PrepStm.executeQuery();
			ArrayList<Project> ProjectList = new ArrayList<Project>();

			while(rs.next()) {
				if(Complete) {
					ProjectList.add(new CompletedProject(rs.getString("Name"), rs.getDate("StartingDate").toLocalDate(), rs.getDate("EndingDate").toLocalDate(), rs.getInt("MaxEmployee"), rs.getString("Modality"), getAllTopicNamesByProjectName(rs.getString("Name"))));
				} else {
					ProjectList.add(new OngoingProject(rs.getString("Name"), rs.getDate("StartingDate").toLocalDate(), rs.getInt("MaxEmployee"), rs.getString("Modality"), getAllTopicNamesByProjectName(rs.getString("Name"))));
				}
			}
			return ProjectList;
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		return null;
		
	}

	@Override
	public String getProjectDescriptionByProjectName(String ProjectName) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"Description\"\r\n"
					+ "FROM public.\"Project\"\r\n"
					+ "WHERE \"Name\" = ?;");
			PrepStm.setString(1, ProjectName);
			ResultSet rs = PrepStm.executeQuery();
			
			String Description;

			if(rs.isBeforeFirst()) {
				rs.next();
				Description = rs.getString("Description");
				Conn.close();
				return Description;
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
	public String getProjectManagerCFByProjectName(String ProjectName) {

		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("SELECT \"ProjectManager\"\r\n"
					+ "FROM public.\"Project\"\r\n"
					+ "WHERE \"Name\" = ?;");
			PrepStm.setString(1, ProjectName);
			ResultSet rs = PrepStm.executeQuery();
			
			String CF;

			if(rs.isBeforeFirst()) {
				rs.next();
				CF = rs.getString("ProjectManager");
				Conn.close();
				return CF;
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
	public void insertNewModalities(ArrayList<String> ModalityList) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("INSERT INTO public.\"Modality\"(\r\n"
					+ "	\"Name\")\r\n"
					+ "	VALUES (?);");
			for(int i = 0; i < ModalityList.size(); i++) {
				PrepStm.setString(1, ModalityList.get(i));
				PrepStm.executeUpdate();
			}
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}
	
	@Override
	public ArrayList<String> getAllModalityNames() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement Stm = Conn.createStatement();
			ResultSet Rs = Stm.executeQuery("SELECT \"Name\" FROM \"Modality\" GROUP BY \"Name\" ORDER BY \"Name\" ASC");
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
	public void insertNewTopics(ArrayList<String> TopicList) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("INSERT INTO public.\"Topic\"(\r\n"
					+ "	\"Name\")\r\n"
					+ "	VALUES (?);");
			for(int i = 0; i < TopicList.size(); i++) {
				PrepStm.setString(1, TopicList.get(i));
				PrepStm.executeUpdate();
			}
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}
	
	@Override
	public ArrayList<String> getAllTopicNames() {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			Statement Stm = Conn.createStatement();
			ResultSet Rs = Stm.executeQuery("SELECT \"Name\" FROM \"Topic\" GROUP BY \"Name\" ORDER BY \"Name\" ASC");
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
	public void insertProjectMembers(OngoingProject Project, ArrayList<String> MembersCF) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("INSERT INTO public.\"UserProject\"(\r\n"
					+ "	\"UserCF\", \"ProjectName\")\r\n"
					+ "	VALUES (?, ?);");
			PrepStm.setString(2, Project.getName());
			
			for(int i = 0; i < MembersCF.size(); i++) {
				PrepStm.setString(1, MembersCF.get(i));
				PrepStm.executeUpdate();
			}
			
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}
	
	@Override
	public void deleteProjectByProjectName(String ProjectName) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("DELETE FROM public.\"Project\" WHERE \"Name\" = ?");
			PrepStm.setString(1, ProjectName);
			
			PrepStm.executeUpdate();
			
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}

	@Override
	public void completeOngoingProjectByProjectName(String ProjectName) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm = Conn.prepareStatement("UPDATE public.\"Project\"\r\n"
					+ "	SET \"Complete\" = true, \"EndingDate\" = CURRENT_DATE\r\n"
					+ "	WHERE \"Name\" = ?;");
			PrepStm.setString(1, ProjectName);
			
			PrepStm.executeUpdate();
			
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}
	
	@Override
	public void insertOngoingProject(OngoingProject Project, User ConnectedUser) {
		
		loadDriver();
		
		try {
			Connection Conn = tryConnection();
			PreparedStatement PrepStm;
			
			if(Project.getDescription() != null) {
				PrepStm = Conn.prepareStatement("INSERT INTO public.\"Project\"(\r\n"
						+ "	\"Name\", \"Description\", \"StartingDate\", \"MaxEmployee\", \"Modality\", \"Complete\", \"ProjectManager\")\r\n"
						+ "	VALUES (?, ?, ?, ?, ?, false, ?);");
				PrepStm.setString(1, Project.getName());
				PrepStm.setString(2, Project.getDescription());
				PrepStm.setDate(3, Date.valueOf(Project.getStartingDate()));
				PrepStm.setInt(4, Project.getMaxEmployee());
				PrepStm.setString(5, Project.getModality());
				PrepStm.setString(6, ConnectedUser.getEmployee().getCF());
			} else {
				PrepStm = Conn.prepareStatement("INSERT INTO public.\"Project\"(\r\n"
						+ "	\"Name\", \"StartingDate\", \"MaxEmployee\", \"Modality\", \"Complete\", \"ProjectManager\")\r\n"
						+ "	VALUES (?, ?, ?, ?, false, ?);");
				PrepStm.setString(1, Project.getName());
				PrepStm.setDate(2, Date.valueOf(Project.getStartingDate()));
				PrepStm.setInt(3, Project.getMaxEmployee());
				PrepStm.setString(4, Project.getModality());
				PrepStm.setString(5, ConnectedUser.getEmployee().getCF());
			}
			
			PrepStm.executeUpdate();
			
			PrepStm = Conn.prepareStatement("INSERT INTO public.\"ProjectScope\"(\r\n"
					+ "	\"ProjectName\", \"ProjectTopicName\")\r\n"
					+ "	VALUES (?, ?);");
			PrepStm.setString(1, Project.getName());
			
			for(int i = 0; i < Project.getTopicList().size(); i++) {
				PrepStm.setString(2, Project.getTopicList().get(i));
				PrepStm.executeUpdate();
			}
			
			Conn.close();
		} catch(SQLException e) {
			System.out.println("SQL Exception:\n" + e);
		}
		
	}
	
}
