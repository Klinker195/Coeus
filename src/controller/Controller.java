package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import dao.*;
import entities.*;
import exceptions.*;
import gui.*;


public class Controller {

	private static Controller ControllerIstance = null;
	
	private GraphicsDevice GraphicDisplay = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int DisplayWidth = GraphicDisplay.getDisplayMode().getWidth();
	private int DisplayHeight = GraphicDisplay.getDisplayMode().getHeight();
	
	private UserFounderDAO UserFounderDAO = new UserFounderDAOPostgre();
	private WorldStateDAO WorldStatesDAO = new WorldStateDAOPostgre();
	
	private LoginWindow LoginWindow;
	private WelcomeWindow WelcomeWindow;
	private RegistrationWindow RegistrationWindow;
	
	private MessageDialog MessageDialog;
	private ConfirmationDialog ConfirmationDialog;

	
	private Controller() {
		// The constructor's private so that it's impossible to create two or more instances of Controller.
	}
	
	public static void main(String args[]) throws IntervalException {
		
		Controller MainController = Controller.getIstance();
		
		MainController.start();
		
	}
	
	public static Controller getIstance() {
		if(ControllerIstance == null)
			ControllerIstance = new Controller();
		
		return ControllerIstance;
	}
	
	// Opens LoginScreen or to WelcomeWindow based on Founder existence
	public void start() {
		
		if(UserFounderDAO.isEmpty()) {
			WelcomeWindow = new WelcomeWindow(DisplayWidth, DisplayHeight);
			WelcomeWindow.setVisible(true);
		} else {
			LoginWindow = new LoginWindow(DisplayWidth, DisplayHeight);
			LoginWindow.setVisible(true);
		}
		
	}
	
	public void userRegistration() throws IntervalException {
		
		if(RegistrationWindow == null) {
			RegistrationWindow RegistrationWindow = new RegistrationWindow(DisplayWidth, DisplayHeight, this.arrayListToStringArray(WorldStatesDAO.getAllStates()));
			RegistrationWindow.setVisible(true);
		} else {
			if(RegistrationWindow.isVisible()) {
				RegistrationWindow.toFront();
				System.out.println("RegistrationWindow is already visible!");
			} else {
				RegistrationWindow.setVisible(true);
			}
		}
		
	}
	
	public Integer[] intervalToIntegerArray(int Start, int End) throws IntervalException {
		
		if(Start > End) {
			throw new IntervalException("Starting value should be greater than ending value.");
		}
		
		ArrayList<Integer> IntegerList = new ArrayList<Integer>();
		
		for(Integer i = Start; i <= End; i++) {
			IntegerList.add(i);
		}
		
		return arrayListToIntegerArray(IntegerList);
		
	}
	
	public Integer[] arrayListToIntegerArray(ArrayList<Integer> ArrayList) {
		
		Integer[] IntegerArray = new Integer[ArrayList.size()];
		
		for(int i = 0; i < ArrayList.size(); i++) {
			IntegerArray[i] = ArrayList.get(i);
		}
		
		return IntegerArray;
		
	}
	
	public String[] arrayListToStringArray(ArrayList<String> ArrayList) {
		
		String[] StringArray = new String[ArrayList.size()];
		
		for(int i = 0; i < ArrayList.size(); i++) {
			StringArray[i] = ArrayList.get(i);
		}
		
		return StringArray;
		
	}
	
	public boolean getConfirmationDialogValue() {
		return ConfirmationDialog.isCheck();
	}
	
	public void displayConfirmationDialog(String Title, String Text) {
		ConfirmationDialog = new ConfirmationDialog(Title, Text, DisplayWidth, DisplayHeight);
		ConfirmationDialog.setVisible(true);
	}
	
	public void displayMessageDialog(String Title, String Text) {
		MessageDialog = new MessageDialog(Title, Text, DisplayWidth, DisplayHeight);
		MessageDialog.setVisible(true);
	}
	
}