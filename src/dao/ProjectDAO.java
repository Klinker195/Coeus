package dao;

import java.util.ArrayList;

import entities.Modality;
import entities.OngoingProject;
import entities.Project;
import entities.User;

public interface ProjectDAO {

	ArrayList<String> getAllTopicNamesByProjectName(String ProjectName);

	String getProjectManagerCFByProjectName(String ProjectName);

	ArrayList<Project> getAllPersonalProjectsByUserCF(String UserCF, boolean Complete);

	ArrayList<Project> getAllProjects(boolean Complete);

	String getProjectDescriptionByProjectName(String ProjectName);

	void insertNewModalities(ArrayList<String> ModalityList);

	ArrayList<String> getAllModalityNames();

	void insertNewTopics(ArrayList<String> TopicList);

	ArrayList<String> getAllTopicNames();

	void insertOngoingProject(OngoingProject NewProject, User ConnectedUser);

	boolean existsByProjectName(String ProjectName);

	void insertProjectMembers(OngoingProject Project, ArrayList<String> MembersCF);

	void deleteProjectByProjectName(String ProjectName);

	void completeOngoingProjectByProjectName(String ProjectName);

	Project getProjectByName(String ProjectName);

	ArrayList<String> getAllOngoingProjectNames();

	ArrayList<String> getAllPersonalOngoingProjectNamesByUserCF(String UserCF);

}