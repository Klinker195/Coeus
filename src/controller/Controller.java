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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private WorldStateDAO WorldStateDAO = new WorldStateDAOPostgre();
	private ItalianDistrictDAO ItalianDistrictDAO = new ItalianDistrictDAOPostgre();
	private SkillDAO SkillDAO = new SkillDAOPostgre();
	private EmployeeDAO EmployeeDAO = new EmployeeDAOPostgre();
	
	private LoginWindow LoginWindow;
	private WelcomeWindow WelcomeWindow;
	private EmployeeEditorWindow EmployeeRegistrationWindow;
	private RegistrationAuthDataWindow AuthDataRegistrationWindow;
	
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
		
		if(UserFounderDAO.searchFounder()) {
			WelcomeWindow = new WelcomeWindow(DisplayWidth, DisplayHeight);
			WelcomeWindow.setVisible(true);
		} else {
			LoginWindow = new LoginWindow(DisplayWidth, DisplayHeight);
			LoginWindow.setVisible(true);
		}
		
	}
	
	/**
	 *  Modality values guide:
	 *  Modality = 0 -> FounderMode;
	 *  Modality = 1 -> UserRegistrationMode;
	 *  Modality = 2 -> EmployeeCreationMode;
	 */
	public void employeeRegistration(int Modality) throws IntervalException {
		
		if(EmployeeRegistrationWindow == null) {
			EmployeeEditorWindow RegistrationWindow = new EmployeeEditorWindow(DisplayWidth, DisplayHeight, Modality);
			RegistrationWindow.setData();
			RegistrationWindow.setVisible(true);
		} else {
			if(EmployeeRegistrationWindow.isVisible()) {
				EmployeeRegistrationWindow.toFront();
				System.out.println("RegistrationWindow is already visible!");
			} else {
				EmployeeRegistrationWindow.setVisible(true);
			}
		}
		
	}
	
	public void userRegistration(Employee NewEmployee, boolean IsFounder) {
		
		if(AuthDataRegistrationWindow == null) {
			
			RegistrationAuthDataWindow AuthDataRegistrationWindow = new RegistrationAuthDataWindow(NewEmployee, IsFounder, DisplayWidth, DisplayHeight);
			AuthDataRegistrationWindow.setVisible(true);
		} else {
			if(AuthDataRegistrationWindow.isVisible()) {
				AuthDataRegistrationWindow.toFront();
				System.out.println("AuthDataRegistrationWindow is already visible!");
			} else {
				AuthDataRegistrationWindow.setVisible(true);
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
		return ConfirmationDialog.isConfirmed();
	}
	
	public void displayConfirmationDialog(String Title, String Text) {
		ConfirmationDialog = new ConfirmationDialog(Title, Text, DisplayWidth, DisplayHeight);
		ConfirmationDialog.setVisible(true);
	}
	
	public void displayMessageDialog(String Title, String Text) {
		MessageDialog = new MessageDialog(Title, Text, DisplayWidth, DisplayHeight);
		MessageDialog.setVisible(true);
	}

	public boolean isInInterval(Integer[] Array, int Number) {
		
		for(int i = 0; i < Array.length; i++) {
			if(Number == Array[i]) {
				return true;
			}
		}
		
		return false;
		
	}
	
	/* TODO Are these checks necessary?
	 
	public void checkTextMaxLength(String Text, int MaxLength) throws InvalidTextException {
		if(Text.length() > MaxLength) {
			throw new InvalidTextException();
		}
	}
	
	public void checkTextLength(String Text, int RequiredLength) throws InvalidTextException {
		if(Text.length() != RequiredLength) {
			throw new InvalidTextException();
		} 
	}
	
	public void checkEmailValidity(String Email) throws InvalidTextException {
		checkTextMaxLength(Email, 128);
		
		final String EmailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern EmailPattern = Pattern.compile(EmailRegex);
		Matcher EmailMatcher = EmailPattern.matcher(Email);
		
		if(!EmailMatcher.matches()) {
			throw new InvalidTextException();
		}
		
	}
	*/
	public <E> boolean checkEmptyList(ArrayList<E> List) throws EmptyListException {
		
		if(List != null) {
			if(!List.isEmpty()) {
				return true;
			} else {
				throw new EmptyListException();
			}
		} else {
			throw new EmptyListException();
		}
		
	}
	
    public String encrypt(char[] Password) {

    	
    	final char[] RotAlphabet = {'!', '#', '%', '&', '\'', '*', '+', '-', '/', '=', '?', '^', '_', '.', '{', '|', 'b', 'Y', 'f', 'M',
    			'k', 'A', 'x', 'W', 'o', 'L', 'h', 'R', 't', 'G', 'l', 'P', 'a', 'Z', 'g', 'J', 'z', 'D', 's', 'U', 'w', 'X', 'i', 'N', 'r', 'K',
    			'c', 'F', 'v', 'B', 'u', 'S', 'm', 'H', 'y', 'E', 'd', 'V', 'e', 'T', 'p', 'Q', 'j', 'O', 'n', 'I', 'q', 'C', '4', '8', '3', '5',
    			'0', '1', '2', '9', '7', '6'};
    	
        for (int i = 0; i < Password.length; i++) {
            char letter = Password[i];
            
            int j = 0;
            
            for(j = 0; i < RotAlphabet.length; j++) {
            	if(letter == RotAlphabet[j]) break;
            }
            
            if(j > RotAlphabet.length/2) {
            	j -= RotAlphabet.length/2;
            } else {
            	j += RotAlphabet.length/2;
            	if(j >= RotAlphabet.length) {
            		j -= RotAlphabet.length/2;
            	}
            }
            
            
            Password[i] = RotAlphabet[j];
        }
        // Convert array to a new String.
        return new String(Password);
    }
	
	public UserFounderDAO getUserFounderDAO() {
		return UserFounderDAO;
	}

	public WorldStateDAO getWorldStateDAO() {
		return WorldStateDAO;
	}

	public ItalianDistrictDAO getItalianDistrictDAO() {
		return ItalianDistrictDAO;
	}
	
	public SkillDAO getSkillDAO() {
		return SkillDAO;
	}

	public EmployeeDAO getEmployeeDAO() {
		return EmployeeDAO;
	}
	
}