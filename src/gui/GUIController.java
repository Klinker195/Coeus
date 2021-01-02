package gui;

import java.util.LinkedList;

import dao.PersonDAOPostgre;
import dao.UserFounderDAOPostgre;
import objects.Person;
import objects.Skill;
import dao.UserFounderDAO;

public class GUIController {

	private static UserFounderDAO UserFounderDAO = new UserFounderDAOPostgre();
	
	private static GUIController ControllerIstance = null;
	
	private GUIController() {
		// The constructor's private so that it's impossible to create two or more istances of GUIController.
	}
	
	public static void main(String args[]) {
		
		GUIController GuiController = GUIController.getIstance();
		
		GuiController.start();

		
	}
	
	public static GUIController getIstance() {
		if(ControllerIstance == null)
			ControllerIstance = new GUIController();
		
		return ControllerIstance;
	}
	
	public void start() {
		
		if(UserFounderDAO.isEmpty()) {
			WelcomeWindow WelcomeWindow = new WelcomeWindow();
			WelcomeWindow.setVisible(true);
		}
		
		LoginWindow LoginScreen = new LoginWindow();
		LoginScreen.setVisible(true);
	}
	
	// TODO Add a CF calculator
//	public String calculateCF() {
//		
//	}
	
}