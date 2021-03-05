package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entities.CompletedProject;
import entities.Employee;
import entities.Meeting;
import entities.OngoingProject;
import entities.OnlineMeeting;
import entities.Project;
import entities.StandardMeeting;
import entities.User;
import exceptions.IntervalException;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.border.MatteBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.ListSelectionModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class CoeusProjectManagerWindow extends GenericFrame {

	private static final long serialVersionUID = 1L;
	
	private Controller MainController = Controller.getInstance();
	private User ConnectedUser;
	
	private JPanel MainPanel;
	
	private JPanel ActionProjectPanel;
	private JPanel ActionProjectButtonsPanel;
	private JPanel ActionProjectInfosPanel;
	private JLabel ProjectInfosTitleLabel;
	private JLabel ProjectNameSubheaderLabel;
	private JTextArea TxtProjectName;
	private JLabel ProjectDescriptionSubheaderLabel;
	private JTextArea TxtProjectDescription;
	private JScrollPane ProjectDescriptionScrollPane;
	private JLabel ProjectModalitySubheaderLabel;
	private JTextArea TxtProjectModality;
	private JLabel ProjectScopeSubheaderLabel;
	private JTextArea TxtProjectScope;
	private JLabel ProjectStartingDateSubheaderLabel;
	private JTextArea TxtProjectStartingDate;
	private JLabel ProjectEndingDateSubheaderLabel;
	private JTextArea TxtProjectEndingDate;
	private JLabel ProjectManagerSubheaderLabel;
	private JTextArea TxtProjectManager;
	private JLabel ProjectMembersSubheaderLabel;
	private JLabel ProjectMembersLinkLabel;
	private JTabbedPane GeneralTabbedPane;
	private JPanel ActionMeetingPanel;
	private JTextArea TxtMeetingTitle;
	private JTextArea TxtMeetingDate;
	private JTextArea TxtMeetingStartingTime;
	private JTextArea TxtMeetingEndingTime;
	private JTextArea TxtMeetingPlatformRoom;
	private JTextArea TxtMeetingID;
	private JTextArea TxtMeetingProjectName;
	private JPanel ActionMeetingButtonsPanel;
	private JPanel ActionEmployeePanel;
	private JTextArea TxtEmployeeCF;
	private JTextArea TxtEmployeeName;
	private JTextArea TxtEmployeeSurname;
	private JTextArea TxtEmployeeEmail;
	private JTextArea TxtEmployeeSalary;
	private JTextArea TxtEmployeeTimeZone;
	private JTextArea TxtEmployeePermission;
	private JTextArea TxtEmployeeSkills;
	private JPanel ActionEmployeeButtonsPanel;
	
	
	
	private JButton ExitButton;
	private JTable PersonalOngoingProjectsTable;
	private JTable PersonalCompletedProjectsTable;
	private JTable GeneralOngoingProjectsTable;
	private JTable GeneralCompletedProjectsTable;
	private JTable GeneralStandardMeetingTable;
	private JTable GeneralOnlineMeetingTable;
	private JTable PersonalStandardMeetingTable;
	private JTable PersonalOnlineMeetingTable;
	private JTable UserEmployeeTable;

	public CoeusProjectManagerWindow(int DisplayWidth, int DisplayHeight, User ConnectedUser, int Modality) {
		
		this.ConnectedUser = ConnectedUser;
		
//		System.out.println(ConnectedUser.toString());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(DisplayWidth/2 - 700, DisplayHeight/2 - 405, 1400, 810);
		MainPanel = new JPanel();
		setDefaultBorderDesign(MainPanel);
		setDefaultDesign(this);
		setContentPane(MainPanel);
		MainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel TopPanel = new JPanel();
		TopPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(6, 20, 64)));
		MainPanel.add(TopPanel, BorderLayout.NORTH);
		TopPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel CoeusTitlePanel = new JPanel();
		FlowLayout fl_CoeusTitlePanel = (FlowLayout) CoeusTitlePanel.getLayout();
		fl_CoeusTitlePanel.setVgap(0);
		fl_CoeusTitlePanel.setHgap(0);
		TopPanel.add(CoeusTitlePanel, BorderLayout.WEST);
		
		JLabel CoeusLabel = new JLabel("Coeus");
		CoeusLabel.setForeground(new Color(11, 28, 82));
		CoeusLabel.setFont(new Font("Roboto Bk", Font.PLAIN, 70));
		CoeusTitlePanel.add(CoeusLabel);
		
		JPanel WindowActionsPanel = new JPanel();
		FlowLayout fl_WindowActionsPanel = (FlowLayout) WindowActionsPanel.getLayout();
		fl_WindowActionsPanel.setVgap(0);
		fl_WindowActionsPanel.setHgap(0);
		TopPanel.add(WindowActionsPanel, BorderLayout.EAST);
		
		ExitButton = new JButton();
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.displayConfirmationDialog("Logout", "Are you sure you want to logout?");
				if(MainController.getConfirmationDialogValue()) {
					System.exit(0);
				}
			}
		});
		setDefaultExitButtonDesign(ExitButton);
		WindowActionsPanel.add(ExitButton);
		
		JPanel CentralPanel = new JPanel();
		CentralPanel.setBorder(null);
		setDefaultBackgroundDesign(CentralPanel);
		MainPanel.add(CentralPanel, BorderLayout.CENTER);
		CentralPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel CentralRightPanel = new JPanel();
		CentralPanel.add(CentralRightPanel, BorderLayout.EAST);
		CentralRightPanel.setLayout(new BorderLayout(0, 0));
		
		JLayeredPane ActionPanel = new JLayeredPane();
		CentralRightPanel.add(ActionPanel, BorderLayout.CENTER);
		ActionPanel.setLayout(new CardLayout(0, 0));
		
		ActionProjectPanel = new JPanel();
		ActionProjectPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ActionProjectPanel.setPreferredSize(new Dimension(300, 0));
		ActionPanel.add(ActionProjectPanel, "name_1134637194576300");
		ActionProjectPanel.setLayout(new BorderLayout(0, 0));
		
		ActionProjectButtonsPanel = new JPanel();
		ActionProjectButtonsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ActionProjectPanel.add(ActionProjectButtonsPanel, BorderLayout.NORTH);
		GridBagLayout gbl_ActionProjectButtonsPanel = new GridBagLayout();
		gbl_ActionProjectButtonsPanel.columnWidths = new int[]{140, 140, 0};
		gbl_ActionProjectButtonsPanel.rowHeights = new int[]{23, 23, 0};
		gbl_ActionProjectButtonsPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_ActionProjectButtonsPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		ActionProjectButtonsPanel.setLayout(gbl_ActionProjectButtonsPanel);
		
		JButton NewProjectButton = new JButton("New Project...");
		NewProjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.openProjectEditorWindow(ConnectedUser);
			}
		});
		setDefaultActionButtonDesign(NewProjectButton);
		GridBagConstraints gbc_NewProjectButton = new GridBagConstraints();
		gbc_NewProjectButton.fill = GridBagConstraints.BOTH;
		gbc_NewProjectButton.insets = new Insets(0, 0, 5, 5);
		gbc_NewProjectButton.gridx = 0;
		gbc_NewProjectButton.gridy = 0;
		ActionProjectButtonsPanel.add(NewProjectButton, gbc_NewProjectButton);
		
		JButton DeleteProjectButton = new JButton("Delete Project...");
		DeleteProjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int PersonalTableValue = PersonalOngoingProjectsTable.getSelectedRow();
				int GeneralTableValue = GeneralOngoingProjectsTable.getSelectedRow();
				
				if(PersonalTableValue != -1) {
					MainController.displayConfirmationDialog("Attention!", "Are you sure you want to delete this project?");
					
					if(MainController.getConfirmationDialogValue()) {
						
						MainController.getProjectDAO().deleteProjectByProjectName(PersonalOngoingProjectsTable.getModel().getValueAt(PersonalTableValue, 0).toString());
						
						resetActionProjectPanelInfos();
						setPersonalProjectsData();
						
						MainController.displayMessageDialog("Success!", "Project successfully deleted.");
						
					}
					
				} else if(GeneralTableValue != -1) {
					MainController.displayConfirmationDialog("Attention!", "Are you sure you want to delete this project?");
					
					if(MainController.getConfirmationDialogValue()) {
						
						MainController.getProjectDAO().deleteProjectByProjectName(PersonalCompletedProjectsTable.getModel().getValueAt(GeneralTableValue, 0).toString());
						
						resetActionProjectPanelInfos();
						setGeneralProjectsData();
						
						MainController.displayMessageDialog("Success!", "Project successfully deleted.");
						
					}
				} else {
					
					MainController.displayMessageDialog("Error!", "You have to select a project first!");
					
				}
				
			}
		});
		setDefaultActionButtonDesign(DeleteProjectButton);
		GridBagConstraints gbc_DeleteProjectButton = new GridBagConstraints();
		gbc_DeleteProjectButton.fill = GridBagConstraints.BOTH;
		gbc_DeleteProjectButton.insets = new Insets(0, 0, 5, 0);
		gbc_DeleteProjectButton.gridx = 1;
		gbc_DeleteProjectButton.gridy = 0;
		ActionProjectButtonsPanel.add(DeleteProjectButton, gbc_DeleteProjectButton);
		
		JButton CompleteProjectButton = new JButton("Complete Project...");
		CompleteProjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int PersonalTableValue = PersonalOngoingProjectsTable.getSelectedRow();
				
				if(PersonalTableValue != -1) {
					MainController.displayConfirmationDialog("Attention!", "Are you sure you want to complete this project?");
					
					if(MainController.getConfirmationDialogValue()) {
						
						MainController.getProjectDAO().completeOngoingProjectByProjectName(PersonalOngoingProjectsTable.getModel().getValueAt(PersonalTableValue, 0).toString());
						
						resetActionProjectPanelInfos();
						setPersonalProjectsData();
						
						MainController.displayMessageDialog("Success!", "Project successfully completed.");
						
					}
					
				} else {
					
					MainController.displayMessageDialog("Error!", "You have to select a personal ongoing project first!");
					
				}
				
			}
		});
		GridBagConstraints gbc_CompleteProjectButton = new GridBagConstraints();
		gbc_CompleteProjectButton.fill = GridBagConstraints.BOTH;
		gbc_CompleteProjectButton.insets = new Insets(0, 0, 0, 5);
		gbc_CompleteProjectButton.gridx = 0;
		gbc_CompleteProjectButton.gridy = 1;
		ActionProjectButtonsPanel.add(CompleteProjectButton, gbc_CompleteProjectButton);
		setDefaultActionButtonDesign(CompleteProjectButton);
		
		ActionProjectInfosPanel = new JPanel();
		ActionProjectPanel.add(ActionProjectInfosPanel, BorderLayout.CENTER);
		
		ProjectInfosTitleLabel = new JLabel("Project informations");
		setDefaultHeaderTextLabel(ProjectInfosTitleLabel);
		
		ProjectNameSubheaderLabel = new JLabel("Project Name:");
		setDefaultTextLabel(ProjectNameSubheaderLabel);
		
		ProjectDescriptionSubheaderLabel = new JLabel("Description:");
		setDefaultTextLabel(ProjectDescriptionSubheaderLabel);
		
		TxtProjectName = new JTextArea();
		TxtProjectName.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		TxtProjectName.setWrapStyleWord(true);
		TxtProjectName.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen");
		TxtProjectName.setEditable(false);
		TxtProjectName.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtProjectName.setLineWrap(true);
		
		ProjectModalitySubheaderLabel = new JLabel("Modality:");
		setDefaultTextLabel(ProjectModalitySubheaderLabel);
		
		TxtProjectModality = new JTextArea();
		TxtProjectModality.setWrapStyleWord(true);
		TxtProjectModality.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen");
		TxtProjectModality.setLineWrap(true);
		TxtProjectModality.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtProjectModality.setEditable(false);
		TxtProjectModality.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		
		ProjectScopeSubheaderLabel = new JLabel("Scope:");
		setDefaultTextLabel(ProjectScopeSubheaderLabel);
		
		TxtProjectScope = new JTextArea();
		TxtProjectScope.setWrapStyleWord(true);
		TxtProjectScope.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen");
		TxtProjectScope.setLineWrap(true);
		TxtProjectScope.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtProjectScope.setEditable(false);
		TxtProjectScope.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		
		ProjectStartingDateSubheaderLabel = new JLabel("Starting Date:");
		setDefaultTextLabel(ProjectStartingDateSubheaderLabel);
		
		TxtProjectStartingDate = new JTextArea();
		TxtProjectStartingDate.setWrapStyleWord(true);
		TxtProjectStartingDate.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing ");
		TxtProjectStartingDate.setLineWrap(true);
		TxtProjectStartingDate.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtProjectStartingDate.setEditable(false);
		TxtProjectStartingDate.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		
		ProjectEndingDateSubheaderLabel = new JLabel("Ending Date:");
		setDefaultTextLabel(ProjectEndingDateSubheaderLabel);
		
		TxtProjectEndingDate = new JTextArea();
		TxtProjectEndingDate.setWrapStyleWord(true);
		TxtProjectEndingDate.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing ");
		TxtProjectEndingDate.setLineWrap(true);
		TxtProjectEndingDate.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtProjectEndingDate.setEditable(false);
		TxtProjectEndingDate.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		
		ProjectManagerSubheaderLabel = new JLabel("Project Manager:");
		setDefaultTextLabel(ProjectManagerSubheaderLabel);
		
		TxtProjectManager = new JTextArea();
		TxtProjectManager.setWrapStyleWord(true);
		TxtProjectManager.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor.");
		TxtProjectManager.setLineWrap(true);
		TxtProjectManager.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtProjectManager.setEditable(false);
		TxtProjectManager.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		
		ProjectDescriptionScrollPane = new JScrollPane();
		ProjectDescriptionScrollPane.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		ProjectDescriptionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ProjectDescriptionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		ProjectMembersSubheaderLabel = new JLabel("Project Members:");
		setDefaultTextLabel(ProjectMembersSubheaderLabel);
		
		ProjectMembersLinkLabel = new JLabel("<HTML><U>Open project members list...</U></HTML>");
		ProjectMembersLinkLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(TxtProjectName.getText().isBlank()) {
					MainController.displayMessageDialog("Error!", "You have to select a project first.");
				} else {
					MainController.openProjectMembersList(ConnectedUser, TxtProjectName.getText(), TxtProjectManager.getText());
				}
				
			}
		});
		ProjectMembersLinkLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ProjectMembersLinkLabel.setForeground(new Color(11, 28, 82));
		ProjectMembersLinkLabel.setFont(new Font("Roboto", Font.PLAIN, 11));
		
		GroupLayout gl_ActionProjectInfosPanel = new GroupLayout(ActionProjectInfosPanel);
		gl_ActionProjectInfosPanel.setHorizontalGroup(
			gl_ActionProjectInfosPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ActionProjectInfosPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ActionProjectInfosPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(TxtProjectManager, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
						.addComponent(ProjectInfosTitleLabel)
						.addComponent(ProjectNameSubheaderLabel)
						.addComponent(ProjectDescriptionSubheaderLabel)
						.addComponent(ProjectModalitySubheaderLabel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(TxtProjectModality, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
						.addComponent(ProjectScopeSubheaderLabel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(TxtProjectScope, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
						.addComponent(ProjectDescriptionScrollPane, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
						.addComponent(ProjectStartingDateSubheaderLabel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(TxtProjectStartingDate, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
						.addComponent(ProjectEndingDateSubheaderLabel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(TxtProjectEndingDate, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
						.addComponent(ProjectManagerSubheaderLabel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(TxtProjectName, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
						.addComponent(ProjectMembersSubheaderLabel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(ProjectMembersLinkLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_ActionProjectInfosPanel.setVerticalGroup(
			gl_ActionProjectInfosPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ActionProjectInfosPanel.createSequentialGroup()
					.addGap(6)
					.addComponent(ProjectInfosTitleLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ProjectNameSubheaderLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TxtProjectName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ProjectDescriptionSubheaderLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ProjectDescriptionScrollPane, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ProjectModalitySubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TxtProjectModality, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ProjectScopeSubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TxtProjectScope, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ProjectStartingDateSubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TxtProjectStartingDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ProjectEndingDateSubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TxtProjectEndingDate, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ProjectManagerSubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TxtProjectManager, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ProjectMembersSubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ProjectMembersLinkLabel)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		// 
		TxtProjectDescription = new JTextArea();
		ProjectDescriptionScrollPane.setViewportView(TxtProjectDescription);
		TxtProjectDescription.setWrapStyleWord(true);
		TxtProjectDescription.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus e");
		TxtProjectDescription.setLineWrap(true);
		TxtProjectDescription.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtProjectDescription.setEditable(false);
		setDefaultBackgroundDesign(ActionProjectInfosPanel);
		ActionProjectInfosPanel.setLayout(gl_ActionProjectInfosPanel);
		
		ActionMeetingPanel = new JPanel();
		ActionMeetingPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ActionMeetingPanel.setPreferredSize(new Dimension(300, 0));
		ActionPanel.add(ActionMeetingPanel, "name_1134646911861600");
		ActionMeetingPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel ActionMeetingInfosPanel = new JPanel();
		setDefaultBackgroundDesign(ActionMeetingInfosPanel);
		ActionMeetingPanel.add(ActionMeetingInfosPanel, BorderLayout.CENTER);
		
		JLabel MeetingInfosTitleLabel = new JLabel("Meeting informations");
		setDefaultHeaderTextLabel(MeetingInfosTitleLabel);
		
		JLabel MeetingTitleSubheaderLabel = new JLabel("Meeting Title:");
		setDefaultTextLabel(MeetingTitleSubheaderLabel);
		
		JLabel MeetingDateSubheaderLabel = new JLabel("Date:");
		setDefaultTextLabel(MeetingDateSubheaderLabel);
		
		TxtMeetingDate = new JTextArea();
		TxtMeetingDate.setWrapStyleWord(true);
		TxtMeetingDate.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing ");
		TxtMeetingDate.setLineWrap(true);
		TxtMeetingDate.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtMeetingDate.setEditable(false);
		TxtMeetingDate.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		
		JLabel MeetingEndingTimeSubheaderLabel = new JLabel("Ending Time:");
		setDefaultTextLabel(MeetingEndingTimeSubheaderLabel);
		
		TxtMeetingEndingTime = new JTextArea();
		TxtMeetingEndingTime.setWrapStyleWord(true);
		TxtMeetingEndingTime.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing ");
		TxtMeetingEndingTime.setLineWrap(true);
		TxtMeetingEndingTime.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtMeetingEndingTime.setEditable(false);
		TxtMeetingEndingTime.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		
		TxtMeetingTitle = new JTextArea();
		TxtMeetingTitle.setWrapStyleWord(true);
		TxtMeetingTitle.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pen");
		TxtMeetingTitle.setLineWrap(true);
		TxtMeetingTitle.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtMeetingTitle.setEditable(false);
		TxtMeetingTitle.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		
		JLabel MeetingMembersSubheaderLabel_1 = new JLabel("Meeting Members:");
		setDefaultTextLabel(MeetingMembersSubheaderLabel_1);
		
		JLabel MeetingMembersLinkLabel = new JLabel("<HTML><U>Open meeting members list...</U></HTML>");
		MeetingMembersLinkLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(TxtMeetingTitle.getText().isBlank()) {
					MainController.displayMessageDialog("Error!", "You have to select a meeting first.");
				} else {
					MainController.openMeetingMembersList(ConnectedUser, TxtMeetingTitle.getText(), Integer.parseInt(TxtMeetingID.getText()));
				}
				
			}
		});
		MeetingMembersLinkLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		MeetingMembersLinkLabel.setForeground(new Color(11, 28, 82));
		MeetingMembersLinkLabel.setFont(new Font("Roboto", Font.PLAIN, 11));
		
		JLabel MeetingStartingTimeSubheaderLabel = new JLabel("Starting Time:");
		setDefaultTextLabel(MeetingStartingTimeSubheaderLabel);
		
		TxtMeetingStartingTime = new JTextArea();
		TxtMeetingStartingTime.setWrapStyleWord(true);
		TxtMeetingStartingTime.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing ");
		TxtMeetingStartingTime.setLineWrap(true);
		TxtMeetingStartingTime.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtMeetingStartingTime.setEditable(false);
		TxtMeetingStartingTime.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		
		JLabel MeetingPlatformRoomSubheaderLabel = new JLabel("Platform/Room:");
		setDefaultTextLabel(MeetingPlatformRoomSubheaderLabel);
		
		TxtMeetingPlatformRoom = new JTextArea();
		TxtMeetingPlatformRoom.setWrapStyleWord(true);
		TxtMeetingPlatformRoom.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing ");
		TxtMeetingPlatformRoom.setLineWrap(true);
		TxtMeetingPlatformRoom.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtMeetingPlatformRoom.setEditable(false);
		TxtMeetingPlatformRoom.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		
		JLabel MeetingIDSubheaderLabel = new JLabel("Meeting ID:");
		setDefaultTextLabel(MeetingIDSubheaderLabel);
		
		TxtMeetingID = new JTextArea();
		TxtMeetingID.setWrapStyleWord(true);
		TxtMeetingID.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing ");
		TxtMeetingID.setLineWrap(true);
		TxtMeetingID.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtMeetingID.setEditable(false);
		TxtMeetingID.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		
		JLabel MeetingProjectNameSubheaderLabel = new JLabel("Project Name:");
		setDefaultTextLabel(MeetingProjectNameSubheaderLabel);
		
		TxtMeetingProjectName = new JTextArea();
		TxtMeetingProjectName.setWrapStyleWord(true);
		TxtMeetingProjectName.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing ");
		TxtMeetingProjectName.setLineWrap(true);
		TxtMeetingProjectName.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtMeetingProjectName.setEditable(false);
		TxtMeetingProjectName.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		GroupLayout gl_ActionMeetingInfosPanel = new GroupLayout(ActionMeetingInfosPanel);
		gl_ActionMeetingInfosPanel.setHorizontalGroup(
			gl_ActionMeetingInfosPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ActionMeetingInfosPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ActionMeetingInfosPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(MeetingInfosTitleLabel)
						.addComponent(MeetingIDSubheaderLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(TxtMeetingID, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
						.addComponent(MeetingTitleSubheaderLabel)
						.addComponent(TxtMeetingTitle, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
						.addComponent(MeetingDateSubheaderLabel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(TxtMeetingDate, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
						.addComponent(MeetingStartingTimeSubheaderLabel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(TxtMeetingStartingTime, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
						.addComponent(MeetingEndingTimeSubheaderLabel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(TxtMeetingEndingTime, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
						.addComponent(MeetingPlatformRoomSubheaderLabel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(TxtMeetingPlatformRoom, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
						.addComponent(MeetingProjectNameSubheaderLabel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(TxtMeetingProjectName, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
						.addComponent(MeetingMembersSubheaderLabel_1, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(MeetingMembersLinkLabel, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_ActionMeetingInfosPanel.setVerticalGroup(
			gl_ActionMeetingInfosPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ActionMeetingInfosPanel.createSequentialGroup()
					.addGap(6)
					.addComponent(MeetingInfosTitleLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MeetingIDSubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(TxtMeetingID, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MeetingTitleSubheaderLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TxtMeetingTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MeetingDateSubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TxtMeetingDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MeetingStartingTimeSubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(TxtMeetingStartingTime, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MeetingEndingTimeSubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TxtMeetingEndingTime, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MeetingPlatformRoomSubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(TxtMeetingPlatformRoom, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MeetingProjectNameSubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TxtMeetingProjectName, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MeetingMembersSubheaderLabel_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MeetingMembersLinkLabel)
					.addContainerGap(177, Short.MAX_VALUE))
		);
		ActionMeetingInfosPanel.setLayout(gl_ActionMeetingInfosPanel);
		
		ActionMeetingButtonsPanel = new JPanel();
		ActionMeetingButtonsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ActionMeetingPanel.add(ActionMeetingButtonsPanel, BorderLayout.NORTH);
		GridBagLayout gbl_ActionMeetingButtonsPanel = new GridBagLayout();
		gbl_ActionMeetingButtonsPanel.columnWidths = new int[]{135, 0, 0};
		gbl_ActionMeetingButtonsPanel.rowHeights = new int[]{0, 0, 0};
		gbl_ActionMeetingButtonsPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_ActionMeetingButtonsPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		ActionMeetingButtonsPanel.setLayout(gbl_ActionMeetingButtonsPanel);
		
		JButton NewMeetingButton = new JButton("New Meeting...");
		NewMeetingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainController.openMeetingEditorWindow(ConnectedUser);
				
			}
		});
		setDefaultActionButtonDesign(NewMeetingButton);
		GridBagConstraints gbc_NewMeetingButton = new GridBagConstraints();
		gbc_NewMeetingButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_NewMeetingButton.insets = new Insets(0, 0, 5, 5);
		gbc_NewMeetingButton.gridx = 0;
		gbc_NewMeetingButton.gridy = 0;
		ActionMeetingButtonsPanel.add(NewMeetingButton, gbc_NewMeetingButton);
		
		JButton DeleteMeetingButton = new JButton("Delete Meeting...");
		DeleteMeetingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int PersonalTableValue1 = PersonalStandardMeetingTable.getSelectedRow();
				int PersonalTableValue2 = PersonalOnlineMeetingTable.getSelectedRow();
				int GeneralTableValue1 = GeneralStandardMeetingTable.getSelectedRow();
				int GeneralTableValue2 = GeneralOnlineMeetingTable.getSelectedRow();
				
				if(PersonalTableValue1 != -1) {
					MainController.displayConfirmationDialog("Attention!", "Are you sure you want to delete this meeting?");
					
					if(MainController.getConfirmationDialogValue()) {
						
						MainController.getMeetingDAO().deleteMeetingByID(Integer.parseInt(PersonalStandardMeetingTable.getModel().getValueAt(PersonalTableValue1, 0).toString()));
						
						resetActionMeetingPanelInfos();
						setMeetingsData();
						
						MainController.displayMessageDialog("Success!", "Meeting successfully deleted.");
						
					}
					
				} else if(PersonalTableValue2 != -1) {
					MainController.displayConfirmationDialog("Attention!", "Are you sure you want to delete this meeting?");
					
					if(MainController.getConfirmationDialogValue()) {
						
						MainController.getMeetingDAO().deleteMeetingByID(Integer.parseInt(PersonalOnlineMeetingTable.getModel().getValueAt(PersonalTableValue2, 0).toString()));
						
						resetActionMeetingPanelInfos();
						setMeetingsData();
						
						MainController.displayMessageDialog("Success!", "Meeting successfully deleted.");
						
					}
				} else if(GeneralTableValue1 != -1) {
					MainController.displayConfirmationDialog("Attention!", "Are you sure you want to delete this meeting?");
					
					if(MainController.getConfirmationDialogValue()) {
						
						MainController.getMeetingDAO().deleteMeetingByID(Integer.parseInt(GeneralStandardMeetingTable.getModel().getValueAt(GeneralTableValue1, 0).toString()));
						
						resetActionMeetingPanelInfos();
						setMeetingsData();
						
						MainController.displayMessageDialog("Success!", "Meeting successfully deleted.");
						
					}
				} else if(GeneralTableValue2 != -1) {
					MainController.displayConfirmationDialog("Attention!", "Are you sure you want to delete this meeting?");
					
					if(MainController.getConfirmationDialogValue()) {
						
						MainController.getMeetingDAO().deleteMeetingByID(Integer.parseInt(GeneralOnlineMeetingTable.getModel().getValueAt(GeneralTableValue2, 0).toString()));
						
						resetActionMeetingPanelInfos();
						setMeetingsData();
						
						MainController.displayMessageDialog("Success!", "Meeting successfully deleted.");
						
					}
				} else {
					
					MainController.displayMessageDialog("Error!", "You have to select a meeting first!");
					
				}
				
			}
		});
		setDefaultActionButtonDesign(DeleteMeetingButton);
		GridBagConstraints gbc_DeleteMeetingButton = new GridBagConstraints();
		gbc_DeleteMeetingButton.insets = new Insets(0, 0, 5, 0);
		gbc_DeleteMeetingButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_DeleteMeetingButton.gridx = 1;
		gbc_DeleteMeetingButton.gridy = 0;
		ActionMeetingButtonsPanel.add(DeleteMeetingButton, gbc_DeleteMeetingButton);
		
		JButton AddMemberButton = new JButton("Add Member...");
		AddMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int PersonalTableValue1 = PersonalStandardMeetingTable.getSelectedRow();
				int PersonalTableValue2 = PersonalOnlineMeetingTable.getSelectedRow();
				int GeneralTableValue1 = GeneralStandardMeetingTable.getSelectedRow();
				int GeneralTableValue2 = GeneralOnlineMeetingTable.getSelectedRow();
				
				
				
				if(PersonalTableValue1 != -1) {
					Integer MeetingID = Integer.valueOf(PersonalStandardMeetingTable.getModel().getValueAt(PersonalStandardMeetingTable.getSelectedRow(), 0).toString());
					
					if(MainController.getEmployeeDAO().checkAvailableEmployeesByProjectNameAndMeetingID(MeetingID, MainController.getMeetingDAO().getProjectNameByID(MeetingID))) {
						MainController.openMeetingMembersSelectionWindow(ConnectedUser, MeetingID, MainController.getProjectDAO().getProjectByName(MainController.getMeetingDAO().getProjectNameByID(MeetingID)));
					} else {
						MainController.displayMessageDialog("Attention!", "There are no avialable employees to add.");
					}
					
				} else if(PersonalTableValue2 != -1) {

					Integer MeetingID = Integer.valueOf(PersonalOnlineMeetingTable.getModel().getValueAt(PersonalOnlineMeetingTable.getSelectedRow(), 0).toString());
					
					if(MainController.getEmployeeDAO().checkAvailableEmployeesByProjectNameAndMeetingID(MeetingID, MainController.getMeetingDAO().getProjectNameByID(MeetingID))) {
						MainController.openMeetingMembersSelectionWindow(ConnectedUser, MeetingID, MainController.getProjectDAO().getProjectByName(MainController.getMeetingDAO().getProjectNameByID(MeetingID)));
					} else {
						MainController.displayMessageDialog("Attention!", "There are no avialable employees to add.");
					}
					
				} else if(GeneralTableValue1 != -1) {
					Integer MeetingID = Integer.valueOf(GeneralStandardMeetingTable.getModel().getValueAt(GeneralStandardMeetingTable.getSelectedRow(), 0).toString());
					
					if(MainController.getEmployeeDAO().checkAvailableEmployeesByProjectNameAndMeetingID(MeetingID, MainController.getMeetingDAO().getProjectNameByID(MeetingID))) {
						MainController.openMeetingMembersSelectionWindow(ConnectedUser, MeetingID, MainController.getProjectDAO().getProjectByName(MainController.getMeetingDAO().getProjectNameByID(MeetingID)));
					} else {
						MainController.displayMessageDialog("Attention!", "There are no avialable employees to add.");
					}
					
				} else if(GeneralTableValue2 != -1) {
					Integer MeetingID = Integer.valueOf(GeneralOnlineMeetingTable.getModel().getValueAt(GeneralOnlineMeetingTable.getSelectedRow(), 0).toString());
					
					if(MainController.getEmployeeDAO().checkAvailableEmployeesByProjectNameAndMeetingID(MeetingID, MainController.getMeetingDAO().getProjectNameByID(MeetingID))) {
						MainController.openMeetingMembersSelectionWindow(ConnectedUser, MeetingID, MainController.getProjectDAO().getProjectByName(MainController.getMeetingDAO().getProjectNameByID(MeetingID)));
					} else {
						MainController.displayMessageDialog("Attention!", "There are no avialable employees to add.");
					}
					
				} else {
					
					MainController.displayMessageDialog("Error!", "You have to select a meeting first!");
					
				}
				
			}
		});
		setDefaultActionButtonDesign(AddMemberButton);
		GridBagConstraints gbc_AddMemberButton = new GridBagConstraints();
		gbc_AddMemberButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_AddMemberButton.insets = new Insets(0, 0, 0, 5);
		gbc_AddMemberButton.gridx = 0;
		gbc_AddMemberButton.gridy = 1;
		ActionMeetingButtonsPanel.add(AddMemberButton, gbc_AddMemberButton);
		
		ActionEmployeePanel = new JPanel();
		ActionEmployeePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ActionEmployeePanel.setPreferredSize(new Dimension(300, 0));
		ActionPanel.add(ActionEmployeePanel, "name_1134649427819800");
		ActionEmployeePanel.setLayout(new BorderLayout(0, 0));
		
		ActionEmployeeButtonsPanel = new JPanel();
		ActionEmployeeButtonsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ActionEmployeePanel.add(ActionEmployeeButtonsPanel, BorderLayout.NORTH);
		GridBagLayout gbl_ActionEmployeeButtonsPanel = new GridBagLayout();
		gbl_ActionEmployeeButtonsPanel.columnWidths = new int[]{136, 122, 0};
		gbl_ActionEmployeeButtonsPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_ActionEmployeeButtonsPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_ActionEmployeeButtonsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		ActionEmployeeButtonsPanel.setLayout(gbl_ActionEmployeeButtonsPanel);
		
		JButton AddEmployeeButton = new JButton("Add Employee...");
		AddEmployeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MainController.employeeRegistration(2);
				} catch (IntervalException e1) {
					e1.printStackTrace();
				}
			}
		});
		setDefaultActionButtonDesign(AddEmployeeButton);
		GridBagConstraints gbc_AddEmployeeButton = new GridBagConstraints();
		gbc_AddEmployeeButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_AddEmployeeButton.insets = new Insets(0, 0, 5, 5);
		gbc_AddEmployeeButton.gridx = 0;
		gbc_AddEmployeeButton.gridy = 0;
		ActionEmployeeButtonsPanel.add(AddEmployeeButton, gbc_AddEmployeeButton);
		
		JButton DeleteEmployeeButton = new JButton("Delete Employee...");
		DeleteEmployeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int PersonalTableValue = UserEmployeeTable.getSelectedRow();

				if(PersonalTableValue != -1) {
					
					MainController.displayConfirmationDialog("Attention!", "Are you sure you want to delete this employee? The user associated with it will also be deleted.");
					
					if(MainController.getConfirmationDialogValue()) {
						
						MainController.getEmployeeDAO().deleteEmployeeByCF(UserEmployeeTable.getModel().getValueAt(PersonalTableValue, 0).toString());
						
						resetActionEmployeePanelInfos();
						setEmployeesData();
						
						MainController.displayMessageDialog("Success!", "Employee successfully deleted.");
						
					}
					
				} else {
					
					MainController.displayMessageDialog("Error!", "You have to select an employee first!");
					
				}
				
			}
		});
		setDefaultActionButtonDesign(DeleteEmployeeButton);
		GridBagConstraints gbc_DeleteEmployeeButton = new GridBagConstraints();
		gbc_DeleteEmployeeButton.insets = new Insets(0, 0, 5, 0);
		gbc_DeleteEmployeeButton.fill = GridBagConstraints.BOTH;
		gbc_DeleteEmployeeButton.gridx = 1;
		gbc_DeleteEmployeeButton.gridy = 0;
		ActionEmployeeButtonsPanel.add(DeleteEmployeeButton, gbc_DeleteEmployeeButton);
		
		JButton EditSkillsButton = new JButton("Edit Skills...");
		EditSkillsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainController.openSkillEditorWindow(ConnectedUser);
				
			}
		});
		
		JButton PromoteButton = new JButton("Promote...");
		PromoteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int PersonalTableValue = UserEmployeeTable.getSelectedRow();

				if(PersonalTableValue != -1) {
					
					MainController.displayConfirmationDialog("Attention!", "Are you sure you want to promote this employee to administrator?");
					
					if(MainController.getConfirmationDialogValue()) {
						
						if(UserEmployeeTable.getModel().getValueAt(PersonalTableValue, 7).toString().equals("Standard")) {
							
							MainController.getUserDAO().promote(UserEmployeeTable.getModel().getValueAt(PersonalTableValue, 0).toString());
							
							if(UserEmployeeTable.getModel().getValueAt(PersonalTableValue, 0).equals(ConnectedUser.getEmployee().getCF())) {
								
								MainController.displayMessageDialog("Attention!", "Your permissions have changed. The program is going to be closed.");
								
								System.exit(0);
								
							}
							
							resetActionEmployeePanelInfos();
							setEmployeesData();
							
							MainController.displayMessageDialog("Success!", "Employee successfully promoted.");
							
						} else {
							MainController.displayMessageDialog("Error!", "The selected employee is already an administrator or a founder.");
						}
						
					}
					
				} else {
					
					MainController.displayMessageDialog("Error!", "You have to select an employee first!");
					
				}
				
			}
		});
		setDefaultActionButtonDesign(PromoteButton);
		GridBagConstraints gbc_PromoteButton = new GridBagConstraints();
		gbc_PromoteButton.fill = GridBagConstraints.BOTH;
		gbc_PromoteButton.insets = new Insets(0, 0, 5, 5);
		gbc_PromoteButton.gridx = 0;
		gbc_PromoteButton.gridy = 1;
		ActionEmployeeButtonsPanel.add(PromoteButton, gbc_PromoteButton);
		
		JButton DemoteButton = new JButton("Demote...");
		DemoteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int PersonalTableValue = UserEmployeeTable.getSelectedRow();

				if(PersonalTableValue != -1) {
					
					MainController.displayConfirmationDialog("Attention!", "Are you sure you want to demote this employee?");
					
					if(MainController.getConfirmationDialogValue()) {
						
						if(UserEmployeeTable.getModel().getValueAt(PersonalTableValue, 7).toString().equals("Admin")) {
							
							MainController.getUserDAO().demote(UserEmployeeTable.getModel().getValueAt(PersonalTableValue, 0).toString());
							
							if(UserEmployeeTable.getModel().getValueAt(PersonalTableValue, 0).equals(ConnectedUser.getEmployee().getCF())) {
								
								MainController.displayMessageDialog("Attention!", "Your permissions have changed. The program is going to be closed.");
								
								System.exit(0);
								
							}
							
							resetActionEmployeePanelInfos();
							setEmployeesData();
							
							MainController.displayMessageDialog("Success!", "Employee successfully demoted.");
							
						} else {
							MainController.displayMessageDialog("Error!", "Can't demote this employee.");
						}
						
					}
					
				} else {
					
					MainController.displayMessageDialog("Error!", "You have to select an employee first!");
					
				}
				
			}
		});
		setDefaultActionButtonDesign(DemoteButton);
		GridBagConstraints gbc_DemoteButton = new GridBagConstraints();
		gbc_DemoteButton.fill = GridBagConstraints.BOTH;
		gbc_DemoteButton.insets = new Insets(0, 0, 5, 0);
		gbc_DemoteButton.gridx = 1;
		gbc_DemoteButton.gridy = 1;
		ActionEmployeeButtonsPanel.add(DemoteButton, gbc_DemoteButton);
		setDefaultActionButtonDesign(EditSkillsButton);
		GridBagConstraints gbc_EditSkillsButton = new GridBagConstraints();
		gbc_EditSkillsButton.fill = GridBagConstraints.BOTH;
		gbc_EditSkillsButton.insets = new Insets(0, 0, 0, 5);
		gbc_EditSkillsButton.gridx = 0;
		gbc_EditSkillsButton.gridy = 2;
		ActionEmployeeButtonsPanel.add(EditSkillsButton, gbc_EditSkillsButton);
		
		JPanel ActionEmployeeInfosPanel = new JPanel();
		setDefaultBackgroundDesign(ActionEmployeeInfosPanel);
		ActionEmployeePanel.add(ActionEmployeeInfosPanel, BorderLayout.CENTER);
		
		JLabel EmployeeInfosTitleLabel = new JLabel("Employee informations");
		setDefaultHeaderTextLabel(EmployeeInfosTitleLabel);
		
		JLabel EmployeeCFSubheaderLabel = new JLabel("CF:");
		setDefaultTextLabel(EmployeeCFSubheaderLabel);
		
		TxtEmployeeCF = new JTextArea();
		TxtEmployeeCF.setWrapStyleWord(true);
		TxtEmployeeCF.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing ");
		TxtEmployeeCF.setLineWrap(true);
		TxtEmployeeCF.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtEmployeeCF.setEditable(false);
		TxtEmployeeCF.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		
		JLabel EmployeeNameSubheaderLabel = new JLabel("Name:");
		setDefaultTextLabel(EmployeeNameSubheaderLabel);
		
		TxtEmployeeName = new JTextArea();
		TxtEmployeeName.setWrapStyleWord(true);
		TxtEmployeeName.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing ");
		TxtEmployeeName.setLineWrap(true);
		TxtEmployeeName.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtEmployeeName.setEditable(false);
		TxtEmployeeName.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		
		JLabel EmployeeSurnameSubheaderLabel = new JLabel("Surname:");
		setDefaultTextLabel(EmployeeSurnameSubheaderLabel);
		
		TxtEmployeeSurname = new JTextArea();
		TxtEmployeeSurname.setWrapStyleWord(true);
		TxtEmployeeSurname.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing ");
		TxtEmployeeSurname.setLineWrap(true);
		TxtEmployeeSurname.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtEmployeeSurname.setEditable(false);
		TxtEmployeeSurname.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		
		TxtEmployeeEmail = new JTextArea();
		TxtEmployeeEmail.setWrapStyleWord(true);
		TxtEmployeeEmail.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing ");
		TxtEmployeeEmail.setLineWrap(true);
		TxtEmployeeEmail.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtEmployeeEmail.setEditable(false);
		TxtEmployeeEmail.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		
		JLabel EmployeeEmailSubheaderLabel = new JLabel("Email:");
		setDefaultTextLabel(EmployeeEmailSubheaderLabel);
		
		TxtEmployeeSalary = new JTextArea();
		TxtEmployeeSalary.setWrapStyleWord(true);
		TxtEmployeeSalary.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing ");
		TxtEmployeeSalary.setLineWrap(true);
		TxtEmployeeSalary.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtEmployeeSalary.setEditable(false);
		TxtEmployeeSalary.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		
		JLabel EmployeeSalarySubheaderLabel = new JLabel("Salary:");
		setDefaultTextLabel(EmployeeSalarySubheaderLabel);
		
		TxtEmployeeTimeZone = new JTextArea();
		TxtEmployeeTimeZone.setWrapStyleWord(true);
		TxtEmployeeTimeZone.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing ");
		TxtEmployeeTimeZone.setLineWrap(true);
		TxtEmployeeTimeZone.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtEmployeeTimeZone.setEditable(false);
		TxtEmployeeTimeZone.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		
		JLabel EmployeeTimeZoneSubheaderLabel = new JLabel("TimeZone:");
		setDefaultTextLabel(EmployeeTimeZoneSubheaderLabel);
		
		JLabel EmployeePermissionSubheaderLabel = new JLabel("Permission:");
		setDefaultTextLabel(EmployeePermissionSubheaderLabel);
		
		TxtEmployeePermission = new JTextArea();
		TxtEmployeePermission.setWrapStyleWord(true);
		TxtEmployeePermission.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing ");
		TxtEmployeePermission.setLineWrap(true);
		TxtEmployeePermission.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtEmployeePermission.setEditable(false);
		TxtEmployeePermission.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		
		JLabel EmployeeSkillsSubheaderLabel = new JLabel("Skills:");
		setDefaultTextLabel(EmployeeSkillsSubheaderLabel);
		
		TxtEmployeeSkills = new JTextArea();
		TxtEmployeeSkills.setWrapStyleWord(true);
		TxtEmployeeSkills.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing ");
		TxtEmployeeSkills.setLineWrap(true);
		TxtEmployeeSkills.setFont(new Font("Roboto", Font.PLAIN, 11));
		TxtEmployeeSkills.setEditable(false);
		TxtEmployeeSkills.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(2, 2, 2, 2)));
		GroupLayout gl_ActionEmployeeInfosPanel = new GroupLayout(ActionEmployeeInfosPanel);
		gl_ActionEmployeeInfosPanel.setHorizontalGroup(
			gl_ActionEmployeeInfosPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ActionEmployeeInfosPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ActionEmployeeInfosPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(EmployeeCFSubheaderLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(TxtEmployeeCF, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
						.addComponent(EmployeeInfosTitleLabel, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_ActionEmployeeInfosPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_ActionEmployeeInfosPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(EmployeeNameSubheaderLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(TxtEmployeeName, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(gl_ActionEmployeeInfosPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ActionEmployeeInfosPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(EmployeeSurnameSubheaderLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(TxtEmployeeSurname, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_ActionEmployeeInfosPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ActionEmployeeInfosPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(EmployeeEmailSubheaderLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(TxtEmployeeEmail, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_ActionEmployeeInfosPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ActionEmployeeInfosPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(EmployeeSalarySubheaderLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(TxtEmployeeSalary, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_ActionEmployeeInfosPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ActionEmployeeInfosPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(EmployeeTimeZoneSubheaderLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(TxtEmployeeTimeZone, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_ActionEmployeeInfosPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(EmployeeSkillsSubheaderLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(200, Short.MAX_VALUE))
				.addGroup(gl_ActionEmployeeInfosPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(TxtEmployeeSkills, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_ActionEmployeeInfosPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(EmployeePermissionSubheaderLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(200, Short.MAX_VALUE))
				.addGroup(gl_ActionEmployeeInfosPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(TxtEmployeePermission, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_ActionEmployeeInfosPanel.setVerticalGroup(
			gl_ActionEmployeeInfosPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ActionEmployeeInfosPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(EmployeeInfosTitleLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(EmployeeCFSubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TxtEmployeeCF, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(EmployeeNameSubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(TxtEmployeeName, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(EmployeeSurnameSubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(TxtEmployeeSurname, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(EmployeeEmailSubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(TxtEmployeeEmail, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(EmployeeSalarySubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(TxtEmployeeSalary, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(EmployeeTimeZoneSubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(TxtEmployeeTimeZone, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(EmployeeSkillsSubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TxtEmployeeSkills, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(EmployeePermissionSubheaderLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TxtEmployeePermission, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(202, Short.MAX_VALUE))
		);
		ActionEmployeeInfosPanel.setLayout(gl_ActionEmployeeInfosPanel);
		
		JPanel EditPersonalSkillPanel = new JPanel();
		EditPersonalSkillPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ActionEmployeePanel.add(EditPersonalSkillPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_EditPersonalSkillPanel = new GridBagLayout();
		gbl_EditPersonalSkillPanel.columnWidths = new int[]{0, 0};
		gbl_EditPersonalSkillPanel.rowHeights = new int[]{0, 0};
		gbl_EditPersonalSkillPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_EditPersonalSkillPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		EditPersonalSkillPanel.setLayout(gbl_EditPersonalSkillPanel);
		
		JButton EditPersonalSkills = new JButton("Edit Personal Skills...");
		EditPersonalSkills.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainController.openPersonalSkillEditorWindow(ConnectedUser);
				
			}
		});
		setDefaultActionButtonDesign(EditPersonalSkills);
		GridBagConstraints gbc_EditPersonalSkills = new GridBagConstraints();
		gbc_EditPersonalSkills.fill = GridBagConstraints.BOTH;
		gbc_EditPersonalSkills.gridx = 0;
		gbc_EditPersonalSkills.gridy = 0;
		EditPersonalSkillPanel.add(EditPersonalSkills, gbc_EditPersonalSkills);
		
		GeneralTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GeneralTabbedPane.setFont(new Font("Roboto", Font.PLAIN, 11));
		CentralPanel.add(GeneralTabbedPane, BorderLayout.CENTER);
		
		JTabbedPane ProjectTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		ProjectTabbedPane.setFont(new Font("Roboto", Font.PLAIN, 11));
		ProjectTabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				resetActionProjectPanelInfos();
			}
		});
		GeneralTabbedPane.addTab("Projects", null, ProjectTabbedPane, null);
		
		JPanel TabbedGeneralProjectPanel = new JPanel();
		setDefaultBackgroundDesign(TabbedGeneralProjectPanel);
		ProjectTabbedPane.addTab("General", null, TabbedGeneralProjectPanel, null);
		
		JLabel GeneralOngoingProjectsLabel = new JLabel("Ongoing Projects");
		setDefaultHeaderTextLabel(GeneralOngoingProjectsLabel);
		
		JScrollPane GeneralOngoingProjectsScrollPane = new JScrollPane();
		setDefaultJScrollPane(GeneralOngoingProjectsScrollPane);
		GeneralOngoingProjectsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GeneralOngoingProjectsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel GeneralCompletedProjectsLabel = new JLabel("Completed Projects");
		setDefaultHeaderTextLabel(GeneralCompletedProjectsLabel);
		
		JScrollPane GeneralCompletedProjectsScrollPane = new JScrollPane();
		setDefaultJScrollPane(GeneralCompletedProjectsScrollPane);
		GeneralCompletedProjectsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GeneralCompletedProjectsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel GeneralProjectsRefreshPanel = new JPanel();
		setDefaultBackgroundDesign(GeneralProjectsRefreshPanel);
		GroupLayout gl_TabbedGeneralProjectPanel = new GroupLayout(TabbedGeneralProjectPanel);
		gl_TabbedGeneralProjectPanel.setHorizontalGroup(
			gl_TabbedGeneralProjectPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TabbedGeneralProjectPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_TabbedGeneralProjectPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(GeneralOngoingProjectsLabel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
						.addComponent(GeneralOngoingProjectsScrollPane, GroupLayout.DEFAULT_SIZE, 1049, Short.MAX_VALUE)
						.addComponent(GeneralCompletedProjectsLabel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
						.addComponent(GeneralCompletedProjectsScrollPane, GroupLayout.DEFAULT_SIZE, 1049, Short.MAX_VALUE)
						.addComponent(GeneralProjectsRefreshPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_TabbedGeneralProjectPanel.setVerticalGroup(
			gl_TabbedGeneralProjectPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TabbedGeneralProjectPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(GeneralOngoingProjectsLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(GeneralOngoingProjectsScrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(GeneralCompletedProjectsLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(GeneralCompletedProjectsScrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(GeneralProjectsRefreshPanel, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
					.addContainerGap())
		);
		GeneralProjectsRefreshPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton GeneralProjectsRefreshButton = new JButton("Refresh");
		GeneralProjectsRefreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionProjectPanelInfos();
				setGeneralProjectsData();
			}
		});
		setDefaultActionButtonDesign(GeneralProjectsRefreshButton);
		GeneralProjectsRefreshButton.setPreferredSize(new Dimension(100, 25));
		GeneralProjectsRefreshPanel.add(GeneralProjectsRefreshButton);
		
		GeneralCompletedProjectsTable = new JTable();
		GeneralCompletedProjectsTable.setFont(new Font("Roboto", Font.PLAIN, 11));
		GeneralCompletedProjectsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setCompletedProjectInformations(GeneralCompletedProjectsTable);
			}
		});
		GeneralCompletedProjectsTable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				GeneralCompletedProjectsTable.getSelectionModel().clearSelection();
				
			}
		});
		GeneralCompletedProjectsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GeneralCompletedProjectsTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Project Name", "Modality", "Scope", "Starting Date", "Ending Date", "Project Manager"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, LocalDate.class, LocalDate.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
						false, false, false, false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return false;
					}
			});
		GeneralCompletedProjectsScrollPane.setViewportView(GeneralCompletedProjectsTable);
		
		GeneralOngoingProjectsTable = new JTable();
		GeneralOngoingProjectsTable.setFont(new Font("Roboto", Font.PLAIN, 11));
		GeneralOngoingProjectsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setOngoingProjectInformations(GeneralOngoingProjectsTable);
			}
		});
		GeneralOngoingProjectsTable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			
				GeneralOngoingProjectsTable.getSelectionModel().clearSelection();
				
			}
		});
		GeneralOngoingProjectsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GeneralOngoingProjectsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Project Name", "Modality", "Scope", "Starting Date", "Project Manager"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, LocalDate.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return false;
				}
		});
		GeneralOngoingProjectsTable.getColumnModel().getColumn(0).setResizable(false);
		GeneralOngoingProjectsTable.getColumnModel().getColumn(0).setPreferredWidth(180);
		GeneralOngoingProjectsTable.getColumnModel().getColumn(1).setResizable(false);
		GeneralOngoingProjectsTable.getColumnModel().getColumn(1).setPreferredWidth(134);
		GeneralOngoingProjectsTable.getColumnModel().getColumn(2).setResizable(false);
		GeneralOngoingProjectsTable.getColumnModel().getColumn(2).setPreferredWidth(138);
		GeneralOngoingProjectsTable.getColumnModel().getColumn(3).setResizable(false);
		GeneralOngoingProjectsTable.getColumnModel().getColumn(3).setPreferredWidth(105);
		GeneralOngoingProjectsTable.getColumnModel().getColumn(4).setResizable(false);
		GeneralOngoingProjectsTable.getColumnModel().getColumn(4).setPreferredWidth(170);
		GeneralOngoingProjectsScrollPane.setViewportView(GeneralOngoingProjectsTable);
		TabbedGeneralProjectPanel.setLayout(gl_TabbedGeneralProjectPanel);
		
		JPanel TabbedPersonalProjectPanel = new JPanel();
		setDefaultBackgroundDesign(TabbedPersonalProjectPanel);
		ProjectTabbedPane.addTab("Personal", null, TabbedPersonalProjectPanel, null);
		
		JScrollPane PersonalOngoingProjectsScrollPane = new JScrollPane();
		setDefaultJScrollPane(PersonalOngoingProjectsScrollPane);
		PersonalOngoingProjectsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		PersonalOngoingProjectsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel PersonalOngoingProjectsLabel = new JLabel("Ongoing Projects");
		setDefaultHeaderTextLabel(PersonalOngoingProjectsLabel);
		
		JLabel PersonalCompletedProjectsLabel = new JLabel("Completed Projects");
		setDefaultHeaderTextLabel(PersonalCompletedProjectsLabel);
		
		JScrollPane PersonalCompletedProjectsScrollPane = new JScrollPane();
		setDefaultJScrollPane(PersonalCompletedProjectsScrollPane);
		PersonalCompletedProjectsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		PersonalCompletedProjectsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel PersonalProjectsRefreshPanel = new JPanel();
		setDefaultBackgroundDesign(PersonalProjectsRefreshPanel);
		GroupLayout gl_TabbedPersonalProjectPanel = new GroupLayout(TabbedPersonalProjectPanel);
		gl_TabbedPersonalProjectPanel.setHorizontalGroup(
			gl_TabbedPersonalProjectPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TabbedPersonalProjectPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_TabbedPersonalProjectPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(PersonalOngoingProjectsLabel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
						.addComponent(PersonalOngoingProjectsScrollPane, GroupLayout.DEFAULT_SIZE, 1049, Short.MAX_VALUE)
						.addComponent(PersonalCompletedProjectsLabel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
						.addComponent(PersonalCompletedProjectsScrollPane, GroupLayout.DEFAULT_SIZE, 1049, Short.MAX_VALUE)
						.addComponent(PersonalProjectsRefreshPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_TabbedPersonalProjectPanel.setVerticalGroup(
			gl_TabbedPersonalProjectPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TabbedPersonalProjectPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(PersonalOngoingProjectsLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PersonalOngoingProjectsScrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PersonalCompletedProjectsLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PersonalCompletedProjectsScrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PersonalProjectsRefreshPanel, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
					.addContainerGap())
		);
		PersonalProjectsRefreshPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton PersonalProjectsRefreshButton = new JButton("Refresh");
		PersonalProjectsRefreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionProjectPanelInfos();
				setPersonalProjectsData();
			}
		});
		setDefaultActionButtonDesign(PersonalProjectsRefreshButton);
		PersonalProjectsRefreshButton.setPreferredSize(new Dimension(100, 25));
		PersonalProjectsRefreshPanel.add(PersonalProjectsRefreshButton);
		
		PersonalCompletedProjectsTable = new JTable();
		PersonalCompletedProjectsTable.setFont(new Font("Roboto", Font.PLAIN, 11));
		PersonalCompletedProjectsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setCompletedProjectInformations(PersonalCompletedProjectsTable);
			}
		});
		PersonalCompletedProjectsTable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				PersonalCompletedProjectsTable.getSelectionModel().clearSelection();
				
			}
		});
		PersonalCompletedProjectsTable.getTableHeader().setReorderingAllowed(false);
		PersonalCompletedProjectsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Project Name", "Modality", "Scope", "Starting Date", "Ending Date", "Project Manager"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, LocalDate.class, LocalDate.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return false;
				}
		});
		PersonalCompletedProjectsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		PersonalCompletedProjectsScrollPane.setViewportView(PersonalCompletedProjectsTable);
		
		PersonalOngoingProjectsTable = new JTable();
		PersonalOngoingProjectsTable.setFont(new Font("Roboto", Font.PLAIN, 11));
		PersonalOngoingProjectsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Add project info gen
				setOngoingProjectInformations(PersonalOngoingProjectsTable);
			}
		});
		PersonalOngoingProjectsTable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				PersonalOngoingProjectsTable.getSelectionModel().clearSelection();
				
			}
		});
		PersonalOngoingProjectsTable.getTableHeader().setReorderingAllowed(false);
		PersonalOngoingProjectsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		PersonalOngoingProjectsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Project Name", "Modality", "Scope", "Starting Date", "Project Manager"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, LocalDate.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return false;
				}
		});
		PersonalOngoingProjectsTable.getColumnModel().getColumn(0).setResizable(false);
		PersonalOngoingProjectsTable.getColumnModel().getColumn(0).setPreferredWidth(180);
		PersonalOngoingProjectsTable.getColumnModel().getColumn(1).setResizable(false);
		PersonalOngoingProjectsTable.getColumnModel().getColumn(1).setPreferredWidth(134);
		PersonalOngoingProjectsTable.getColumnModel().getColumn(2).setResizable(false);
		PersonalOngoingProjectsTable.getColumnModel().getColumn(2).setPreferredWidth(138);
		PersonalOngoingProjectsTable.getColumnModel().getColumn(3).setResizable(false);
		PersonalOngoingProjectsTable.getColumnModel().getColumn(3).setPreferredWidth(105);
		PersonalOngoingProjectsTable.getColumnModel().getColumn(4).setResizable(false);
		PersonalOngoingProjectsTable.getColumnModel().getColumn(4).setPreferredWidth(170);
		PersonalOngoingProjectsScrollPane.setViewportView(PersonalOngoingProjectsTable);
		TabbedPersonalProjectPanel.setLayout(gl_TabbedPersonalProjectPanel);
		
		JTabbedPane MeetingTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		MeetingTabbedPane.setFont(new Font("Roboto", Font.PLAIN, 11));
		MeetingTabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				resetActionMeetingPanelInfos();
			}
		});
		GeneralTabbedPane.addTab("Meetings", null, MeetingTabbedPane, null);
		
		JPanel TabbedGeneralMeetingPanel = new JPanel();
		setDefaultBackgroundDesign(TabbedGeneralMeetingPanel);
		MeetingTabbedPane.addTab("General", null, TabbedGeneralMeetingPanel, null);
		
		JLabel GeneralStandardMeetingLabel = new JLabel("Standard Meetings");
		setDefaultHeaderTextLabel(GeneralStandardMeetingLabel);
		
		JScrollPane GeneralStandardMeetingScrollPane = new JScrollPane();
		setDefaultJScrollPane(GeneralStandardMeetingScrollPane);
		GeneralStandardMeetingScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GeneralStandardMeetingScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel GeneralOnlineMeetingLabel = new JLabel("Online Meetings");
		setDefaultHeaderTextLabel(GeneralOnlineMeetingLabel);
		
		JScrollPane GeneralOnlineMeetingScrollPane = new JScrollPane();
		setDefaultJScrollPane(GeneralOnlineMeetingScrollPane);
		GeneralOnlineMeetingScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GeneralOnlineMeetingScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel GeneralMeetingsRefreshPanel = new JPanel();
		setDefaultBackgroundDesign(GeneralMeetingsRefreshPanel);
		GeneralMeetingsRefreshPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton GeneralMeetingsRefreshButton = new JButton("Refresh");
		GeneralMeetingsRefreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionMeetingPanelInfos();
				setMeetingsData();
			}
		});
		setDefaultActionButtonDesign(GeneralMeetingsRefreshButton);
		GeneralMeetingsRefreshButton.setPreferredSize(new Dimension(100, 25));
		GeneralMeetingsRefreshPanel.add(GeneralMeetingsRefreshButton);
		GroupLayout gl_TabbedGeneralMeetingPanel = new GroupLayout(TabbedGeneralMeetingPanel);
		gl_TabbedGeneralMeetingPanel.setHorizontalGroup(
			gl_TabbedGeneralMeetingPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TabbedGeneralMeetingPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_TabbedGeneralMeetingPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(GeneralStandardMeetingLabel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
						.addComponent(GeneralStandardMeetingScrollPane, GroupLayout.PREFERRED_SIZE, 1049, GroupLayout.PREFERRED_SIZE)
						.addComponent(GeneralOnlineMeetingLabel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
						.addComponent(GeneralOnlineMeetingScrollPane, GroupLayout.PREFERRED_SIZE, 1049, GroupLayout.PREFERRED_SIZE)
						.addComponent(GeneralMeetingsRefreshPanel, GroupLayout.PREFERRED_SIZE, 1049, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_TabbedGeneralMeetingPanel.setVerticalGroup(
			gl_TabbedGeneralMeetingPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TabbedGeneralMeetingPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(GeneralStandardMeetingLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(GeneralStandardMeetingScrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(GeneralOnlineMeetingLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(GeneralOnlineMeetingScrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addComponent(GeneralMeetingsRefreshPanel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		GeneralOnlineMeetingTable = new JTable();
		GeneralOnlineMeetingTable.setFont(new Font("Roboto", Font.PLAIN, 11));
		GeneralOnlineMeetingTable.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				GeneralOnlineMeetingTable.getSelectionModel().clearSelection();
			}
		});
		GeneralOnlineMeetingTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setMeetingInformations(GeneralOnlineMeetingTable);
			}
		});
		GeneralOnlineMeetingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GeneralOnlineMeetingTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Title", "Date", "Starting Time", "Ending Time", "Platform", "Project Name"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, LocalDate.class, LocalTime.class, LocalTime.class, String.class, String.class
			};
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return false;
				}
		});
		GeneralOnlineMeetingTable.getColumnModel().getColumn(0).setResizable(false);
		GeneralOnlineMeetingTable.getColumnModel().getColumn(1).setResizable(false);
		GeneralOnlineMeetingTable.getColumnModel().getColumn(2).setResizable(false);
		GeneralOnlineMeetingTable.getColumnModel().getColumn(3).setResizable(false);
		GeneralOnlineMeetingTable.getColumnModel().getColumn(4).setResizable(false);
		GeneralOnlineMeetingTable.getColumnModel().getColumn(5).setResizable(false);
		GeneralOnlineMeetingTable.getColumnModel().getColumn(6).setResizable(false);
		GeneralOnlineMeetingScrollPane.setViewportView(GeneralOnlineMeetingTable);
		
		GeneralStandardMeetingTable = new JTable();
		GeneralStandardMeetingTable.setFont(new Font("Roboto", Font.PLAIN, 11));
		GeneralStandardMeetingTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setMeetingInformations(GeneralStandardMeetingTable);
			}
		});
		GeneralStandardMeetingTable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				GeneralStandardMeetingTable.getSelectionModel().clearSelection();
			}
		});
		GeneralStandardMeetingTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Title", "Date", "Starting Time", "Ending Time", "Room", "Project Name"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, LocalDate.class, LocalTime.class, LocalTime.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		GeneralStandardMeetingTable.getColumnModel().getColumn(0).setResizable(false);
		GeneralStandardMeetingTable.getColumnModel().getColumn(1).setResizable(false);
		GeneralStandardMeetingTable.getColumnModel().getColumn(2).setResizable(false);
		GeneralStandardMeetingTable.getColumnModel().getColumn(3).setResizable(false);
		GeneralStandardMeetingTable.getColumnModel().getColumn(4).setResizable(false);
		GeneralStandardMeetingTable.getColumnModel().getColumn(5).setResizable(false);
		GeneralStandardMeetingTable.getColumnModel().getColumn(6).setResizable(false);
		GeneralStandardMeetingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GeneralStandardMeetingScrollPane.setViewportView(GeneralStandardMeetingTable);
		TabbedGeneralMeetingPanel.setLayout(gl_TabbedGeneralMeetingPanel);
		
		JPanel TabbedPersonalMeetingPanel = new JPanel();
		setDefaultBackgroundDesign(TabbedPersonalMeetingPanel);
		MeetingTabbedPane.addTab("Personal", null, TabbedPersonalMeetingPanel, null);
		
		JLabel PersonalStandardMeetingLabel = new JLabel("Standard Meetings");
		setDefaultHeaderTextLabel(PersonalStandardMeetingLabel);
		
		JScrollPane PersonalStandardMeetingScrollPane = new JScrollPane();
		setDefaultJScrollPane(PersonalStandardMeetingScrollPane);
		PersonalStandardMeetingScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		PersonalStandardMeetingScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel PersonalOnlineMeetingLabel = new JLabel("Online Meetings");
		setDefaultHeaderTextLabel(PersonalOnlineMeetingLabel);
		
		JScrollPane PersonalOnlineMeetingScrollPane = new JScrollPane();
		setDefaultJScrollPane(PersonalOnlineMeetingScrollPane);
		PersonalOnlineMeetingScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		PersonalOnlineMeetingScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel PersonalMeetingsRefreshPanel = new JPanel();
		setDefaultBackgroundDesign(PersonalMeetingsRefreshPanel);
		PersonalMeetingsRefreshPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton PersonalMeetingsRefreshButton = new JButton("Refresh");
		PersonalMeetingsRefreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionMeetingPanelInfos();
				setMeetingsData();
			}
		});
		setDefaultActionButtonDesign(PersonalMeetingsRefreshButton);
		PersonalMeetingsRefreshButton.setPreferredSize(new Dimension(100, 25));
		PersonalMeetingsRefreshPanel.add(PersonalMeetingsRefreshButton);
		GroupLayout gl_TabbedPersonalMeetingPanel = new GroupLayout(TabbedPersonalMeetingPanel);
		gl_TabbedPersonalMeetingPanel.setHorizontalGroup(
			gl_TabbedPersonalMeetingPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TabbedPersonalMeetingPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_TabbedPersonalMeetingPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(PersonalStandardMeetingLabel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
						.addComponent(PersonalStandardMeetingScrollPane, GroupLayout.PREFERRED_SIZE, 1049, GroupLayout.PREFERRED_SIZE)
						.addComponent(PersonalOnlineMeetingLabel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
						.addComponent(PersonalOnlineMeetingScrollPane, GroupLayout.PREFERRED_SIZE, 1049, GroupLayout.PREFERRED_SIZE)
						.addComponent(PersonalMeetingsRefreshPanel, GroupLayout.PREFERRED_SIZE, 1049, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_TabbedPersonalMeetingPanel.setVerticalGroup(
			gl_TabbedPersonalMeetingPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TabbedPersonalMeetingPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(PersonalStandardMeetingLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(PersonalStandardMeetingScrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PersonalOnlineMeetingLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(PersonalOnlineMeetingScrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PersonalMeetingsRefreshPanel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		PersonalOnlineMeetingTable = new JTable();
		PersonalOnlineMeetingTable.setFont(new Font("Roboto", Font.PLAIN, 11));
		PersonalOnlineMeetingTable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				PersonalOnlineMeetingTable.getSelectionModel().clearSelection();
			}
		});
		PersonalOnlineMeetingTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setMeetingInformations(PersonalOnlineMeetingTable);
			}
		});
		PersonalOnlineMeetingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		PersonalOnlineMeetingTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Title", "Date", "Starting Time", "Ending Time", "Platform", "Project Name"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, LocalDate.class, LocalTime.class, LocalTime.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
				};
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		PersonalOnlineMeetingTable.getColumnModel().getColumn(0).setResizable(false);
		PersonalOnlineMeetingTable.getColumnModel().getColumn(1).setResizable(false);
		PersonalOnlineMeetingTable.getColumnModel().getColumn(2).setResizable(false);
		PersonalOnlineMeetingTable.getColumnModel().getColumn(3).setResizable(false);
		PersonalOnlineMeetingTable.getColumnModel().getColumn(4).setResizable(false);
		PersonalOnlineMeetingTable.getColumnModel().getColumn(5).setResizable(false);
		PersonalOnlineMeetingTable.getColumnModel().getColumn(6).setResizable(false);
		PersonalOnlineMeetingScrollPane.setViewportView(PersonalOnlineMeetingTable);
		
		PersonalStandardMeetingTable = new JTable();
		PersonalStandardMeetingTable.setFont(new Font("Roboto", Font.PLAIN, 11));
		PersonalStandardMeetingTable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				PersonalStandardMeetingTable.getSelectionModel().clearSelection();
			}
		});
		PersonalStandardMeetingTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setMeetingInformations(PersonalStandardMeetingTable);
			}
		});
		PersonalStandardMeetingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		PersonalStandardMeetingTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Title", "Date", "Starting Time", "Ending Time", "Room", "Project Name"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, LocalDate.class, LocalTime.class, LocalTime.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		PersonalStandardMeetingTable.getColumnModel().getColumn(0).setResizable(false);
		PersonalStandardMeetingTable.getColumnModel().getColumn(1).setResizable(false);
		PersonalStandardMeetingTable.getColumnModel().getColumn(2).setResizable(false);
		PersonalStandardMeetingTable.getColumnModel().getColumn(3).setResizable(false);
		PersonalStandardMeetingTable.getColumnModel().getColumn(4).setResizable(false);
		PersonalStandardMeetingTable.getColumnModel().getColumn(5).setResizable(false);
		PersonalStandardMeetingTable.getColumnModel().getColumn(6).setResizable(false);
		PersonalStandardMeetingScrollPane.setViewportView(PersonalStandardMeetingTable);
		TabbedPersonalMeetingPanel.setLayout(gl_TabbedPersonalMeetingPanel);
		
		JPanel EmployeePane = new JPanel();
		setDefaultBackgroundDesign(EmployeePane);
		GeneralTabbedPane.addTab("Employees", null, EmployeePane, null);
		
		JLabel UserEmployeeLabel = new JLabel("Employees");
		setDefaultHeaderTextLabel(UserEmployeeLabel);
		
		JScrollPane UserEmployeeScrollPane = new JScrollPane();
		setDefaultJScrollPane(UserEmployeeScrollPane);
		UserEmployeeScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		UserEmployeeScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel UserEmployeeRefreshPanel = new JPanel();
		setDefaultBackgroundDesign(UserEmployeeRefreshPanel);
		UserEmployeeRefreshPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton UserEmployeeRefreshButton = new JButton("Refresh");
		UserEmployeeRefreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionEmployeePanelInfos();
				setEmployeesData();
			}
		});
		setDefaultActionButtonDesign(UserEmployeeRefreshButton);
		UserEmployeeRefreshButton.setPreferredSize(new Dimension(100, 25));
		UserEmployeeRefreshPanel.add(UserEmployeeRefreshButton);
		GroupLayout gl_EmployeePane = new GroupLayout(EmployeePane);
		gl_EmployeePane.setHorizontalGroup(
			gl_EmployeePane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_EmployeePane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_EmployeePane.createParallelGroup(Alignment.LEADING)
						.addComponent(UserEmployeeScrollPane, GroupLayout.PREFERRED_SIZE, 1049, GroupLayout.PREFERRED_SIZE)
						.addComponent(UserEmployeeLabel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
						.addComponent(UserEmployeeRefreshPanel, GroupLayout.PREFERRED_SIZE, 1049, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_EmployeePane.setVerticalGroup(
			gl_EmployeePane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_EmployeePane.createSequentialGroup()
					.addContainerGap()
					.addComponent(UserEmployeeLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(UserEmployeeScrollPane, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(UserEmployeeRefreshPanel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		UserEmployeeTable = new JTable();
		UserEmployeeTable.setFont(new Font("Roboto", Font.PLAIN, 11));
		UserEmployeeTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setEmployeeInformations(UserEmployeeTable);
			}
		});
		UserEmployeeTable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				UserEmployeeTable.getSelectionModel().clearSelection();
			}
		});
		UserEmployeeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CF", "Name", "Surname", "Email", "Salary", "TimeZone", "Skills","Permission"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, Float.class, String.class, String.class, String.class
			};
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return false;
				}
		});
		UserEmployeeTable.getColumnModel().getColumn(0).setResizable(false);
		UserEmployeeTable.getColumnModel().getColumn(0).setPreferredWidth(130);
		UserEmployeeTable.getColumnModel().getColumn(0).setMinWidth(130);
		UserEmployeeTable.getColumnModel().getColumn(1).setResizable(false);
		UserEmployeeTable.getColumnModel().getColumn(1).setPreferredWidth(110);
		UserEmployeeTable.getColumnModel().getColumn(1).setMinWidth(110);
		UserEmployeeTable.getColumnModel().getColumn(2).setResizable(false);
		UserEmployeeTable.getColumnModel().getColumn(2).setPreferredWidth(110);
		UserEmployeeTable.getColumnModel().getColumn(2).setMinWidth(110);
		UserEmployeeTable.getColumnModel().getColumn(3).setResizable(false);
		UserEmployeeTable.getColumnModel().getColumn(3).setPreferredWidth(110);
		UserEmployeeTable.getColumnModel().getColumn(3).setMinWidth(110);
		UserEmployeeTable.getColumnModel().getColumn(4).setResizable(false);
		UserEmployeeTable.getColumnModel().getColumn(4).setPreferredWidth(90);
		UserEmployeeTable.getColumnModel().getColumn(4).setMinWidth(90);
		UserEmployeeTable.getColumnModel().getColumn(5).setResizable(false);
		UserEmployeeTable.getColumnModel().getColumn(5).setPreferredWidth(90);
		UserEmployeeTable.getColumnModel().getColumn(5).setMinWidth(90);
		UserEmployeeTable.getColumnModel().getColumn(6).setResizable(false);
		UserEmployeeTable.getColumnModel().getColumn(6).setPreferredWidth(90);
		UserEmployeeTable.getColumnModel().getColumn(7).setResizable(false);
		UserEmployeeTable.getColumnModel().getColumn(7).setPreferredWidth(90);
		UserEmployeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		UserEmployeeScrollPane.setViewportView(UserEmployeeTable);
		EmployeePane.setLayout(gl_EmployeePane);
		
		JPanel BottomPanel = new JPanel();
		BottomPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(6, 20, 64)));
		MainPanel.add(BottomPanel, BorderLayout.SOUTH);
		BottomPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel BottomRightPanel = new JPanel();
		BottomPanel.add(BottomRightPanel, BorderLayout.EAST);
		
		JButton LogoutButton = new JButton("Logout");
		LogoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController.displayConfirmationDialog("Logout", "Are you sure you want to logout?");
				if(MainController.getConfirmationDialogValue()) {
					System.exit(0);
				}
			}
		});
		setDefaultLineBorderButtonDesign(LogoutButton);
		BottomRightPanel.add(LogoutButton);
		// TODO Add ComponentMover
		//ComponentMover cm = new ComponentMover(JFrame.class, titleBar);
		
		GeneralTabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				resetActionProjectPanelInfos();
				resetActionMeetingPanelInfos();
				switchPanel(GeneralTabbedPane.getSelectedIndex());
			}
		});
		
		if(ConnectedUser.getClass().getSimpleName().equals("UserStandard")) {
			ActionProjectButtonsPanel.setVisible(false);
			ActionMeetingButtonsPanel.setVisible(false);
			ActionEmployeeButtonsPanel.setVisible(false);
		}
		
	}
	
	public void switchPanel(int Index) {
		
		ActionProjectPanel.setVisible(false);
		ActionMeetingPanel.setVisible(false);
		ActionEmployeePanel.setVisible(false);
		
		switch(Index) {
		
		case 0:
			setProjectsData();
			resetActionProjectPanelInfos();
			ActionProjectPanel.setVisible(true);
			break;
		case 1:
			setMeetingsData();
			resetActionMeetingPanelInfos();
			ActionMeetingPanel.setVisible(true);
			break;
		case 2:
			resetActionEmployeePanelInfos();
			setEmployeesData();
			ActionEmployeePanel.setVisible(true);
			break;
		default:
			break;
		
		}
		
	}
	
	public void setGeneralMeetingsData() {
		
		ArrayList<Meeting> GeneralStandardMeetingsList = MainController.getMeetingDAO().getAllMeetings(false);
		ArrayList<Integer> GeneralStandardMeetingsIDsList = MainController.getMeetingDAO().getAllMeetingsIDs(false);
		
		DefaultTableModel Model = (DefaultTableModel) GeneralStandardMeetingTable.getModel();
		
		if(GeneralStandardMeetingsList != null) {
			
			if(Model.getRowCount() != 0) {
				Model.setRowCount(0);
			}
			
			for(int i = 0; i < GeneralStandardMeetingsList.size(); i++) {
				StandardMeeting Meeting = (StandardMeeting)GeneralStandardMeetingsList.get(i);
				
				Model.addRow(new Object[]{ GeneralStandardMeetingsIDsList.get(i), Meeting.getTitle(), Meeting.getDate(), Meeting.getStartingTime(), Meeting.getEndingTime(), Meeting.getRoom(), Meeting.getProject().getName()});
				
			}
			
		}
		
		ArrayList<Meeting> GeneralOnlineMeetingsList = MainController.getMeetingDAO().getAllMeetings(true);
		ArrayList<Integer> GeneralOnlineMeetingsIDsList = MainController.getMeetingDAO().getAllMeetingsIDs(true);
		
		Model = (DefaultTableModel) GeneralOnlineMeetingTable.getModel();
		
		if(GeneralOnlineMeetingsList != null) {
			
			if(Model.getRowCount() != 0) {
				Model.setRowCount(0);
			}
			
			for(int i = 0; i < GeneralOnlineMeetingsList.size(); i++) {
				OnlineMeeting Meeting = (OnlineMeeting)GeneralOnlineMeetingsList.get(i);
				
				Model.addRow(new Object[]{ GeneralOnlineMeetingsIDsList.get(i), Meeting.getTitle(), Meeting.getDate(), Meeting.getStartingTime(), Meeting.getEndingTime(), Meeting.getPlatform(), Meeting.getProject().getName()});
				
			}
			
		}
		
	}
	
	public void setPersonalMeetingsData() {
		
		ArrayList<Meeting> PersonalStandardMeetingsList = MainController.getMeetingDAO().getAllPersonalMeetingsByUserCF(ConnectedUser.getEmployee().getCF(), false);
		ArrayList<Integer> PersonalStandardMeetingsIDsList = MainController.getMeetingDAO().getAllPersonalMeetingsIDsByUserCF(ConnectedUser.getEmployee().getCF(), false);
		
		DefaultTableModel Model = (DefaultTableModel) PersonalStandardMeetingTable.getModel();
		
		if(PersonalStandardMeetingsList != null) {
			
			if(Model.getRowCount() != 0) {
				Model.setRowCount(0);
			}
			
			for(int i = 0; i < PersonalStandardMeetingsList.size(); i++) {
				StandardMeeting Meeting = (StandardMeeting)PersonalStandardMeetingsList.get(i);
				
				Model.addRow(new Object[]{ PersonalStandardMeetingsIDsList.get(i), Meeting.getTitle(), Meeting.getDate(), Meeting.getStartingTime(), Meeting.getEndingTime(), Meeting.getRoom(), Meeting.getProject().getName()});
				
			}
			
		}

		ArrayList<Meeting> PersonalOnlineMeetingsList = MainController.getMeetingDAO().getAllPersonalMeetingsByUserCF(ConnectedUser.getEmployee().getCF(), true);
		ArrayList<Integer> PersonalOnlineMeetingsIDsList = MainController.getMeetingDAO().getAllPersonalMeetingsIDsByUserCF(ConnectedUser.getEmployee().getCF(), true);
		
		Model = (DefaultTableModel) PersonalOnlineMeetingTable.getModel();
		
		if(PersonalOnlineMeetingsList != null) {
			
			if(Model.getRowCount() != 0) {
				Model.setRowCount(0);
			}
			
			for(int i = 0; i < PersonalOnlineMeetingsList.size(); i++) {
				OnlineMeeting Meeting = (OnlineMeeting)PersonalOnlineMeetingsList.get(i);
				
				Model.addRow(new Object[]{ PersonalOnlineMeetingsIDsList.get(i), Meeting.getTitle(), Meeting.getDate(), Meeting.getStartingTime(), Meeting.getEndingTime(), Meeting.getPlatform(), Meeting.getProject().getName()});
				
			}
			
		}
		
	}
	
	public void setGeneralProjectsData() {
		
		String ProjectManagerCF;
		String Name;
		String Surname;
		
		ArrayList<Project> GeneralOngoingProjectsList = MainController.getProjectDAO().getAllProjects(false);
		
		DefaultTableModel Model = (DefaultTableModel) GeneralOngoingProjectsTable.getModel();
		
		if(GeneralOngoingProjectsList != null) {
			
			if(Model.getRowCount() != 0) {
				Model.setRowCount(0);
			}
			
			for(int i = 0; i < GeneralOngoingProjectsList.size(); i++) {
				OngoingProject Proj = (OngoingProject)GeneralOngoingProjectsList.get(i);
				
				ProjectManagerCF = MainController.getProjectDAO().getProjectManagerCFByProjectName(Proj.getName());
				
				Name = MainController.getEmployeeDAO().getNameByCF(ProjectManagerCF);
				Surname = MainController.getEmployeeDAO().getSurnameByCF(ProjectManagerCF);
				
				Name = Name.substring(0, 1).toUpperCase() + Name.substring(1, Name.length()).toLowerCase();
				Surname = Surname.substring(0, 1).toUpperCase() + Surname.substring(1, Surname.length()).toLowerCase();
				
				Model.addRow(new Object[]{ Proj.getName(), Proj.getModality(), Proj.getTopicList().toString(), Proj.getStartingDate(), Name + " " + Surname});
				
			}
			
		}
		
		ArrayList<Project> GeneralCompletedProjectsList = MainController.getProjectDAO().getAllProjects(true);
		
		Model = (DefaultTableModel) GeneralCompletedProjectsTable.getModel();
		
		if(GeneralCompletedProjectsList != null) {
			
			if(Model.getRowCount() != 0) {
				Model.setRowCount(0);
			}
			
			for(int i = 0; i < GeneralCompletedProjectsList.size(); i++) {
				CompletedProject Proj = (CompletedProject)GeneralCompletedProjectsList.get(i);
				
				ProjectManagerCF = MainController.getProjectDAO().getProjectManagerCFByProjectName(Proj.getName());
				
				Name = MainController.getEmployeeDAO().getNameByCF(ProjectManagerCF);
				Surname = MainController.getEmployeeDAO().getSurnameByCF(ProjectManagerCF);
				
				Name = Name.substring(0, 1).toUpperCase() + Name.substring(1, Name.length()).toLowerCase();
				Surname = Surname.substring(0, 1).toUpperCase() + Surname.substring(1, Surname.length()).toLowerCase();
				
				Model.addRow(new Object[]{ Proj.getName(), Proj.getModality(), Proj.getTopicList().toString(), Proj.getStartingDate(), Proj.getEndingDate(), Name + " " + Surname});
				
			}
			
		}
		
	}
	
	public void setPersonalProjectsData() {
		
		String ProjectManagerCF;
		String Name;
		String Surname;
		
		ArrayList<Project> PersonalOngoingProjectsList = MainController.getProjectDAO().getAllPersonalProjectsByUserCF(ConnectedUser.getEmployee().getCF(), false);
		
		DefaultTableModel Model = (DefaultTableModel) PersonalOngoingProjectsTable.getModel();
		
		if(PersonalOngoingProjectsList != null) {
			
			if(Model.getRowCount() != 0) {
				Model.setRowCount(0);
			}
			
			for(int i = 0; i < PersonalOngoingProjectsList.size(); i++) {
				OngoingProject Proj = (OngoingProject)PersonalOngoingProjectsList.get(i);
				
				ProjectManagerCF = MainController.getProjectDAO().getProjectManagerCFByProjectName(Proj.getName());
				
				Name = MainController.getEmployeeDAO().getNameByCF(ProjectManagerCF);
				Surname = MainController.getEmployeeDAO().getSurnameByCF(ProjectManagerCF);
				
				Name = Name.substring(0, 1).toUpperCase() + Name.substring(1, Name.length()).toLowerCase();
				Surname = Surname.substring(0, 1).toUpperCase() + Surname.substring(1, Surname.length()).toLowerCase();
				
				Model.addRow(new Object[]{ Proj.getName(), Proj.getModality(), Proj.getTopicList().toString(), Proj.getStartingDate(), Name + " " + Surname});
				
			}
			
		}

		ArrayList<Project> PersonalCompletedProjectsList = MainController.getProjectDAO().getAllPersonalProjectsByUserCF(ConnectedUser.getEmployee().getCF(), true);
		
		Model = (DefaultTableModel) PersonalCompletedProjectsTable.getModel();
		
		
		if(PersonalCompletedProjectsList != null) {
			
			if(Model.getRowCount() != 0) {
				Model.setRowCount(0);
			}
			
			for(int i = 0; i < PersonalCompletedProjectsList.size(); i++) {
				CompletedProject Proj = (CompletedProject)PersonalCompletedProjectsList.get(i);
				
				ProjectManagerCF = MainController.getProjectDAO().getProjectManagerCFByProjectName(Proj.getName());
				
				Name = MainController.getEmployeeDAO().getNameByCF(ProjectManagerCF);
				Surname = MainController.getEmployeeDAO().getSurnameByCF(ProjectManagerCF);
				
				Name = Name.substring(0, 1).toUpperCase() + Name.substring(1, Name.length()).toLowerCase();
				Surname = Surname.substring(0, 1).toUpperCase() + Surname.substring(1, Surname.length()).toLowerCase();
				
				Model.addRow(new Object[]{ Proj.getName(), Proj.getModality(), Proj.getTopicList().toString(), Proj.getStartingDate(), Proj.getEndingDate(), Name + " " + Surname});
				
			}
			
		}
		
	}
	
	public void setMeetingInformations(JTable Table) {
		
		int Row = Table.getSelectedRow();
		
		TxtMeetingID.setText(Table.getValueAt(Row, 0).toString());
		
		TxtMeetingTitle.setText((String)Table.getValueAt(Row, 1));
		
		TxtMeetingDate.setText(Table.getValueAt(Row, 2).toString());
		
		TxtMeetingStartingTime.setText(Table.getValueAt(Row, 3).toString());

		TxtMeetingEndingTime.setText(Table.getValueAt(Row, 4).toString());

		TxtMeetingPlatformRoom.setText((String)Table.getValueAt(Row, 5));
		
		TxtMeetingProjectName.setText((String)Table.getValueAt(Row, 6));
		
	}
	
	public void setOngoingProjectInformations(JTable Table) {
		
		int Row = Table.getSelectedRow();
		
		if(!((String)Table.getValueAt(Row, 0)).equals((String)TxtProjectName.getText())) {
			
			TxtProjectName.setText((String)Table.getValueAt(Row, 0));
			
			TxtProjectDescription.setText(MainController.getProjectDAO().getProjectDescriptionByProjectName((String)Table.getValueAt(Row, 0)));
			
			TxtProjectModality.setText((String)Table.getValueAt(Row, 1));

			TxtProjectScope.setText((String)Table.getValueAt(Row, 2));

			TxtProjectStartingDate.setText(Table.getValueAt(Row, 3).toString());

			TxtProjectEndingDate.setText("Not yet ended");

			TxtProjectManager.setText((String)Table.getValueAt(Row, 4) + "\r\n" + MainController.getEmployeeDAO().getEmailByCF(MainController.getProjectDAO().getProjectManagerCFByProjectName(TxtProjectName.getText())));
			
		}
		
	}
	
	public void setCompletedProjectInformations(JTable Table) {
		
		int Row = Table.getSelectedRow();
		
		if(!((String)Table.getValueAt(Row, 0)).equals((String)TxtProjectName.getText())) {
			
			TxtProjectName.setText((String)Table.getValueAt(Row, 0));
			
			TxtProjectDescription.setText(MainController.getProjectDAO().getProjectDescriptionByProjectName((String)Table.getValueAt(Row, 0)));
			
			TxtProjectModality.setText((String)Table.getValueAt(Row, 1));

			TxtProjectScope.setText((String)Table.getValueAt(Row, 2));

			TxtProjectStartingDate.setText(Table.getValueAt(Row, 3).toString());

			TxtProjectEndingDate.setText(Table.getValueAt(Row, 4).toString());

			TxtProjectManager.setText((String)Table.getValueAt(Row, 5) + "\r\n" + MainController.getEmployeeDAO().getEmailByCF(MainController.getProjectDAO().getProjectManagerCFByProjectName(TxtProjectName.getText())));
			
		}
		
	}
	
	public void setEmployeeInformations(JTable Table) {
		
		int Row = Table.getSelectedRow();
		
		TxtEmployeeCF.setText(Table.getValueAt(Row, 0).toString());
		
		TxtEmployeeName.setText(Table.getValueAt(Row, 1).toString());
		
		TxtEmployeeSurname.setText(Table.getValueAt(Row, 2).toString());
		
		TxtEmployeeEmail.setText(Table.getValueAt(Row, 3).toString());
		
		TxtEmployeeSalary.setText(Table.getValueAt(Row, 4).toString());
		
		TxtEmployeeTimeZone.setText(Table.getValueAt(Row, 5).toString());
		
		TxtEmployeeSkills.setText(Table.getValueAt(Row, 6).toString());
		
		TxtEmployeePermission.setText(Table.getValueAt(Row, 7).toString());
		
		
		
	}
	
	public void resetActionMeetingPanelInfos() {
		
		TxtMeetingID.setText("");
		
		TxtMeetingTitle.setText("");
		
		TxtMeetingDate.setText("");
		
		TxtMeetingStartingTime.setText("");

		TxtMeetingEndingTime.setText("");

		TxtMeetingPlatformRoom.setText("");
		
		TxtMeetingProjectName.setText("");
		
	}
	
	public void resetActionProjectPanelInfos() {
		
		TxtProjectName.setText("");

		TxtProjectDescription.setText("");
		
		TxtProjectModality.setText("");

		TxtProjectScope.setText("");

		TxtProjectStartingDate.setText("");

		TxtProjectEndingDate.setText("");

		TxtProjectManager.setText("");
		
	}
	
	public void resetActionEmployeePanelInfos() {
		
		TxtEmployeeCF.setText("");
		
		TxtEmployeeName.setText("");
		
		TxtEmployeeSurname.setText("");
		
		TxtEmployeeEmail.setText("");
		
		TxtEmployeeSalary.setText("");
		
		TxtEmployeeTimeZone.setText("");
		
		TxtEmployeeSkills.setText("");
		
		TxtEmployeePermission.setText("");
		
	}
	
	public void setProjectsData() {
		
		setPersonalProjectsData();
		
		setGeneralProjectsData();
		
	}
	
	public void setMeetingsData() {
		
		setPersonalMeetingsData();
		
		setGeneralMeetingsData();
		
	}
	
	public void setEmployeesData() {
		
		ArrayList<Employee> EmployeeList = MainController.getEmployeeDAO().getAllEmployees();
		ArrayList<User> UserList = MainController.getUserDAO().getAllUsers();
		
		DefaultTableModel Model = (DefaultTableModel) UserEmployeeTable.getModel();
		
		if(EmployeeList != null) {
			
			if(Model.getRowCount() != 0) {
				Model.setRowCount(0);
			}
			
			for(int i = 0; i < EmployeeList.size(); i++) {
				Employee Employee = EmployeeList.get(i);
				String UserPrivilege = UserList.get(i).getClass().getSimpleName();
				
				if(UserPrivilege.equals("UserFounder")) {
					UserPrivilege = "Founder";
				} else if(UserPrivilege.equals("UserAdmin")) {
					UserPrivilege = "Admin";
				} else {
					UserPrivilege = "Standard";
				}
				
				ArrayList<String> SkillNames = new ArrayList<String>();
				
				for(int j = 0; j < Employee.getSkillArray().size(); j++) {
					SkillNames.add(Employee.getSkillArray().get(j).getName());
				}
				
				Model.addRow(new Object[]{ Employee.getCF(), Employee.getName(), Employee.getSurname(), Employee.getEmail(), Employee.getSalary(), Employee.getTimeZone(), SkillNames.toString(), UserPrivilege});
				
			}
			
		}
		
	}
}
