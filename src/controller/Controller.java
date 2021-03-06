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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.SwingWorker;

import dao.*;
import entities.*;
import exceptions.*;
import gui.*;


public class Controller {

	private static Controller ControllerInstance = null;
	
	private GraphicsDevice GraphicDisplay;
	private int DisplayWidth;
	private int DisplayHeight;
	private SwingWorker<Boolean, Integer> SwingWorker;
	
	private UserDAO UserDAO;
	private WorldStateDAO WorldStateDAO;
	private ItalianDistrictDAO ItalianDistrictDAO;
	private SkillDAO SkillDAO;
	private EmployeeDAO EmployeeDAO;
	private MeetingDAO MeetingDAO;
	private ProjectDAO ProjectDAO;
	private MeetingInvitationDAO MeetingInvitationDAO;
	
	private LoginWindow LoginWindow;
	private WelcomeWindow WelcomeWindow;
	private EmployeeEditorWindow EmployeeRegistrationWindow;
	private RegistrationAuthDataWindow AuthDataRegistrationWindow;
	private CoeusProjectManagerWindow CoeusProjectManagerWindow;
	private LoadingSplashWindow LoadingSplashWindow;
	private ProjectMembersListWindow ProjectMembersListWindow;
	private ProjectEditorWindow ProjectEditorWindow;
	private ProjectUserSelectionWindow ProjectUserSelectionWindow;
	private MeetingMembersListWindow MeetingMembersListWindow;
	private MeetingEditorWindow MeetingEditorWindow;
	private MeetingUserSelectionWindow MeetingUserSelectionWindow;
	private SkillEditorWindow SkillEditorWindow;
	private PersonalSkillEditorWindow PersonalSkillEditorWindow;
	
	
	private MessageDialog MessageDialog;
	private ConfirmationDialog ConfirmationDialog;

	
	private Controller() {

	}
	
	public static void main(String args[]) throws IntervalException {
		
		Controller MainController = Controller.getInstance();
		
		MainController.init();
		
		MainController.start();
		
	}
	
	public static Controller getInstance() {
		if(ControllerInstance == null)
			ControllerInstance = new Controller();
		
		return ControllerInstance;
	}
	
	// Opens LoginScreen or to WelcomeWindow based on Founder existence
	public void start() {
		
//		System.out.println(this.getClass().getSimpleName());
		
		if(UserDAO.searchFounder()) {
			WelcomeWindow = new WelcomeWindow(DisplayWidth, DisplayHeight);
			WelcomeWindow.setVisible(true);
		} else {
			LoginWindow = new LoginWindow(DisplayWidth, DisplayHeight);
			LoginWindow.setVisible(true);
		}
		
	}
	
	public void openDashboard(User ConnectedUser, int Modality) {
		
		
		SwingWorker = new SwingWorker<Boolean, Integer>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				
				CoeusProjectManagerWindow.setProjectsData();
				
				return true;
			}
			
			@Override
			protected void done() {
				
				LoadingSplashWindow.setVisible(false);
				CoeusProjectManagerWindow.setVisible(true);
			}
			
		};
		
		if(CoeusProjectManagerWindow == null) {
			CoeusProjectManagerWindow = new CoeusProjectManagerWindow(DisplayWidth, DisplayHeight, ConnectedUser, Modality);
			SwingWorker.execute();
			SwingWorker = null;
		} else {
			if(CoeusProjectManagerWindow.isVisible()) {
				CoeusProjectManagerWindow.toFront();
				System.out.println("RegistrationWindow is already visible!");
			} else {
				CoeusProjectManagerWindow.setVisible(true);
			}
		}
		
	}
	
	/**
	 *  Modality values guide:
	 *  Modality = 0 -> FounderMode;
	 *  Modality = 1 -> UserRegistrationMode; -> StandardUserMode
	 *  Modality = 2 -> EmployeeCreationMode; -> When not for creation is for AdminMode
	 */
	public void employeeRegistration(int Modality) throws IntervalException {
		
		EmployeeRegistrationWindow = new EmployeeEditorWindow(DisplayWidth, DisplayHeight, Modality);
		EmployeeRegistrationWindow.setData();
		EmployeeRegistrationWindow.setVisible(true);
		
	}
	
	public void userRegistration(Employee NewEmployee, boolean IsFounder) {
		
		if(AuthDataRegistrationWindow == null) {
			
			AuthDataRegistrationWindow = new RegistrationAuthDataWindow(NewEmployee, IsFounder, DisplayWidth, DisplayHeight);
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
	
	public void openPersonalSkillEditorWindow(User ConnectedUser) {
		
		PersonalSkillEditorWindow = new PersonalSkillEditorWindow(DisplayWidth, DisplayHeight, ConnectedUser);
		PersonalSkillEditorWindow.setData(ConnectedUser);
		PersonalSkillEditorWindow.setVisible(true);
		
	}
	
	public void openSkillEditorWindow(User ConnectedUser) {
		
		SkillEditorWindow = new SkillEditorWindow(DisplayWidth, DisplayHeight, ConnectedUser);
		SkillEditorWindow.setData();
		SkillEditorWindow.setVisible(true);
		
	}
	
	public void openProjectEditorWindow(User ConnectedUser) {
		
		ProjectEditorWindow = new ProjectEditorWindow(ConnectedUser, DisplayWidth, DisplayHeight);
		ProjectEditorWindow.setData();
		ProjectEditorWindow.setVisible(true);
		
	}
	
	public void openMeetingEditorWindow(User ConnectedUser) {
		
		MeetingEditorWindow = new MeetingEditorWindow(ConnectedUser, DisplayWidth, DisplayHeight);
		MeetingEditorWindow.setData();
		MeetingEditorWindow.setVisible(true);
		
	}
	
	public void openProjectMembersSelectionWindow(User ConnectedUser, Project Project) {
		
		ProjectUserSelectionWindow = new ProjectUserSelectionWindow(Project, ConnectedUser, DisplayWidth, DisplayHeight);
		ProjectUserSelectionWindow.setData();
		ProjectUserSelectionWindow.setVisible(true);
	}
	
	public void openMeetingMembersSelectionWindow(User ConnectedUser, Integer MeetingID, Project Project) {
		
		MeetingUserSelectionWindow = new MeetingUserSelectionWindow(Project, MeetingID, ConnectedUser, DisplayWidth, DisplayHeight);
		MeetingUserSelectionWindow.setData(Project);
		MeetingUserSelectionWindow.setVisible(true);
	}
	
	public void openProjectMembersList(User ConnectedUser, String ProjectName, String ProjectManagerCF) {
		
		if(ProjectMembersListWindow == null) {
			
			ProjectMembersListWindow = new ProjectMembersListWindow(DisplayWidth, DisplayHeight, ProjectName, ConnectedUser, ProjectManagerCF);
			ProjectMembersListWindow.setData(ProjectName);
			ProjectMembersListWindow.setVisible(true);
		} else {
			if(ProjectMembersListWindow.isVisible()) {
				ProjectMembersListWindow.toFront();
				System.out.println("AuthDataRegistrationWindow is already visible!");
			} else {
				ProjectMembersListWindow.setData(ProjectName);
				ProjectMembersListWindow.setVisible(true);
			}
		}
		
	}
	
	public void openMeetingMembersList(User ConnectedUser, String ProjectName, Integer MeetingID) {
		
		if(MeetingMembersListWindow == null) {
			
			MeetingMembersListWindow = new MeetingMembersListWindow(DisplayWidth, DisplayHeight, MeetingID, ProjectName, ConnectedUser);
			MeetingMembersListWindow.setData(MeetingID, ProjectName);
			MeetingMembersListWindow.setVisible(true);
		} else {
			if(MeetingMembersListWindow.isVisible()) {
				MeetingMembersListWindow.toFront();
				System.out.println("AuthDataRegistrationWindow is already visible!");
			} else {
				MeetingMembersListWindow.setData(MeetingID, ProjectName);
				MeetingMembersListWindow.setVisible(true);
			}
		}
		
	}
	
	public Integer[] intervalToIntegerArray(int Start, int End) {
		
		try {
			
			if(Start > End) {
				throw new IntervalException("Starting value should be greater than ending value.");
			}
			
			Integer[] IntegerArray = new Integer[End - Start + 1];
			
			for(Integer i = Start; i <= End; i++) {
				IntegerArray[i - Start] = i;
			}
			
			return IntegerArray;
			
		} catch(IntervalException e) {
			System.out.println("IntervalException: " + e);
		}
		return null;
		
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
	
	public <E> boolean checkEmptyList(ArrayList<E> List) {
		
		if(List != null) {
			if(!List.isEmpty()) {
				return true;
			}
		}
		return false;
		
	}
	
    public String encrypt(char[] Password) {

    	
    	final char[] RotAlphabet = {'!', '#', '%', '&', '\'', '*', '+', '-', '/', '=', '?', '^', '_', '.', '{', '|', 'b', 'Y', 'f', 'M',
    			'k', 'A', 'x', 'W', 'o', 'L', 'h', 'R', 't', 'G', 'l', 'P', 'a', 'Z', 'g', 'J', 'z', 'D', 's', 'U', 'w', 'X', 'i', 'N', 'r', 'K',
    			'c', 'F', 'v', 'B', 'u', 'S', 'm', 'H', 'y', 'E', 'd', 'V', 'e', 'T', 'p', 'Q', 'j', 'O', 'n', 'I', 'q', 'C', '4', '8', '3', '5',
    			'0', '1', '2', '9', '7', '6'};
    	
        for (int i = 0; i < Password.length; i++) {
            char letter = Password[i];
            
            int j = 0;
            
            for(j = 0; j < RotAlphabet.length; j++) {
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
	
    public void init() {
		GraphicDisplay = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		DisplayWidth = GraphicDisplay.getDisplayMode().getWidth();
		DisplayHeight = GraphicDisplay.getDisplayMode().getHeight();
		
		LoadingSplashWindow = new LoadingSplashWindow(DisplayWidth, DisplayHeight);
		
		UserDAO = new UserDAOPostgre();
		WorldStateDAO = new WorldStateDAOPostgre();
		ItalianDistrictDAO = new ItalianDistrictDAOPostgre();
		SkillDAO = new SkillDAOPostgre();
		EmployeeDAO = new EmployeeDAOPostgre();
		MeetingDAO = new MeetingDAOPostgre();
		ProjectDAO = new ProjectDAOPostgre();
		MeetingInvitationDAO = new MeetingInvitationDAOPostgre();
		
    }
    
	public UserDAO getUserDAO() {
		return UserDAO;
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

	public MeetingDAO getMeetingDAO() {
		return MeetingDAO;
	}

	public void setMeetingDAO(MeetingDAO meetingDAO) {
		MeetingDAO = meetingDAO;
	}

	public ProjectDAO getProjectDAO() {
		return ProjectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		ProjectDAO = projectDAO;
	}

	public CoeusProjectManagerWindow getCoeusProjectManagerWindow() {
		return CoeusProjectManagerWindow;
	}

	public ProjectUserSelectionWindow getProjectUserSelectionWindow() {
		return ProjectUserSelectionWindow;
	}

	public MeetingInvitationDAO getMeetingInvitationDAO() {
		return MeetingInvitationDAO;
	}
	
	public LoadingSplashWindow getLoadingSplashWindow() {
		return LoadingSplashWindow;
	}
	
}