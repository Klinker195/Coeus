package gui;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import dao.*;
import exceptions.*;
import objects.*;


public class GUIController {

	private UserFounderDAO UserFounderDAO = new UserFounderDAOPostgre();
	private WorldStatesDAO WorldStatesDAO = new WorldStatesDAOPostgre();
	private LoginWindow LoginWindow;
	private WelcomeWindow WelcomeWindow;
	private RegistrationWindow RegistrationWindow;
	private static GUIController ControllerIstance = null;
	
	private GUIController() {
		// The constructor's private so that it's impossible to create two or more istances of GUIController.
	}
	
	public static void main(String args[]) throws IntervalException {
		
		GUIController GuiController = GUIController.getIstance();
		
		GuiController.start();

		
	}
	
	public static GUIController getIstance() {
		if(ControllerIstance == null)
			ControllerIstance = new GUIController();
		
		return ControllerIstance;
	}
	
	// Opens LoginScreen or to WelcomeWindow based on 
	public void start() {
		
		if(UserFounderDAO.isEmpty()) {
			WelcomeWindow = new WelcomeWindow();
			WelcomeWindow.setVisible(true);
		} else {
			LoginWindow = new LoginWindow();
			LoginWindow.setVisible(true);
		}
		
	}
	
	public void userRegistration() throws IntervalException {
		
		RegistrationWindow RegistrationWindow = new RegistrationWindow(this.arrayListToStringArray(WorldStatesDAO.getAllStates()));
		RegistrationWindow.setVisible(true);
		
	}
	
	
	// Update the Day in a JComboBox field based on Month and Year selected
	public void updateJComboBoxDay(JComboBox JComboBox, String Month, String Year) throws IntervalException {
		
		int IntYear = Integer.parseInt(Year);
		
		switch(Month) {
		case "February":
			if(IntYear % 4 == 0 && IntYear % 100 != 0 || IntYear % 400 == 0) {
				JComboBox.setModel(new DefaultComboBoxModel(intervalToStringArray(1, 29)));
			} else {
				JComboBox.setModel(new DefaultComboBoxModel(intervalToStringArray(1, 28)));
			}
			break;
		case "April":
			JComboBox.setModel(new DefaultComboBoxModel(intervalToStringArray(1, 30)));
			break;
		case "June":
			JComboBox.setModel(new DefaultComboBoxModel(intervalToStringArray(1, 30)));
			break;
		case "September":
			JComboBox.setModel(new DefaultComboBoxModel(intervalToStringArray(1, 30)));
			break;
		case "November":
			JComboBox.setModel(new DefaultComboBoxModel(intervalToStringArray(1, 30)));
			break;
		default:
			JComboBox.setModel(new DefaultComboBoxModel(intervalToStringArray(1, 31)));
			break;
		}
		
	}
	
	public String[] intervalToStringArray(int Start, int End) throws IntervalException {
		
		if(Start > End) {
			throw new IntervalException("Starting value should be greater than ending value.");
		}
		
		ArrayList<String> StringList = new ArrayList<String>();
		
		for(Integer i = Start; i <= End; i++) {
			StringList.add(i.toString());
		}
		
		return arrayListToStringArray(StringList);
		
	}
	
	public String[] arrayListToStringArray(ArrayList<String> ArrayList) {
		
		String[] StringArray = new String[ArrayList.size()];
		
		for(int i = 0; i < ArrayList.size(); i++) {
			StringArray[i] = ArrayList.get(i);
		}
		
		return StringArray;
		
	}
	
	// TODO Add a CF calculator
//	public String calculateCF() {
//		
//	}
	
}