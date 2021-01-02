package gui;

import java.util.LinkedList;

import dao.PersonDAOPostgre;
import objects.Person;
import objects.Skill;

public class GUIController {

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
		
		// if UserFounder resultset .next() returns false then the program doesn't have a founder so it has to do an extra step.
		
		LoginWindow LoginScreen = new LoginWindow();
		LoginScreen.setVisible(true);
	}
	
	
	public String calculateCF() {
		
	}
	
}