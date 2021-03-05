package gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Window.Type;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import entities.Employee;
import entities.MeetingInvitation;
import entities.OngoingProject;
import entities.Project;
import entities.User;
import enums.InviteStatus;

import javax.swing.JButton;
import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MeetingUserSelectionWindow extends GenericDialog {

	private JPanel MainPanel;
	private JTable EmployeeTable;
	private JTextField NameTextField;
	private JTextField SurnameTextField;
	private JTextField EmailTextField;
	private JLabel ProjectTitleLabel;
	
	private Project Project;
	private User ConnectedUser;
	private Integer MeetingID;
	
	private Controller MainController = Controller.getInstance();
	private JTable ChosenMembersTable;
	private JTextField SkillTextField;
	private JLabel MembersNumberVariableLabel;
	
	/**
	 * Create the frame.
	 */
	public MeetingUserSelectionWindow(Project Project, Integer MeetingID, User ConnectedUser, int DisplayWidth, int DisplayHeight) {
		
		this.Project = Project;
		this.ConnectedUser = ConnectedUser;
		this.MeetingID = MeetingID;
		
		setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		setDefaultDesign(this);
		setType(Type.UTILITY);
		setBounds(DisplayWidth/2 - 575, DisplayHeight/2 - 325, 1150, 650);
		MainPanel = new JPanel();
		MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(MainPanel);
		
		setDefaultBorderDesign(MainPanel);
		MainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel TopPanel = new JPanel();
		MainPanel.add(TopPanel, BorderLayout.NORTH);
		TopPanel.setLayout(new BorderLayout(0, 0));
		
		JButton ExitButton = new JButton("X");
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainController.getCoeusProjectManagerWindow().resetActionMeetingPanelInfos();
			}
		});
		setDefaultExitButtonDesign(ExitButton);
		TopPanel.add(ExitButton, BorderLayout.EAST);
		
		ProjectTitleLabel = new JLabel("");
		TopPanel.add(ProjectTitleLabel, BorderLayout.WEST);
		
		JPanel BottomPanel = new JPanel();
		FlowLayout fl_BottomPanel = (FlowLayout) BottomPanel.getLayout();
		MainPanel.add(BottomPanel, BorderLayout.SOUTH);
		
		JButton NextButton = new JButton("Next");
		NextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(ChosenMembersTable.getModel().getRowCount() != 0) {
					
					ArrayList<String> MembersCF = new ArrayList<String>();
					
					for(int i = 0; i < ChosenMembersTable.getModel().getRowCount(); i++) {
						MembersCF.add(MainController.getEmployeeDAO().getCFByEmail(ChosenMembersTable.getModel().getValueAt(i, 1).toString()));
					}
					
					MainController.getMeetingInvitationDAO().insertMeetingInvitationFromCFList(InviteStatus.PENDING, MeetingID, MembersCF);
					
					MainController.displayMessageDialog("Success!", "The employees have been successfully added to the meeting.");
					
					dispose();
					
					MainController.getCoeusProjectManagerWindow().resetActionMeetingPanelInfos();
//					MainController.getCoeusProjectManagerWindow().setPersonalMeetingsData();
//					MainController.getCoeusProjectManagerWindow().setGeneralMeetingsData();
					
				} else {
					
					MainController.displayMessageDialog("Error!", "You have to add at least one more employee.");
					
				}
				

				
			}
		});
		setDefaultLineBorderButtonDesign(NextButton);
		BottomPanel.add(NextButton);
		
		JPanel CentralPanel = new JPanel();
		CentralPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		setDefaultBackgroundDesign(CentralPanel);
		MainPanel.add(CentralPanel, BorderLayout.CENTER);
		
		JLabel EmployeesListHeaderLabel = new JLabel("List of employees:");
		setDefaultHeaderTextLabel(EmployeesListHeaderLabel);
		
		JScrollPane EmployeesScrollPane = new JScrollPane();
		EmployeesScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setDefaultJScrollPane(EmployeesScrollPane);
		
		JLabel SearchByLabel = new JLabel("Search by:");
		setDefaultTextLabel(SearchByLabel);
		
		NameTextField = new JTextField();
		setDefaultJTextFieldDesign(NameTextField);
		NameTextField.setColumns(10);
		
		JLabel NameBackgroundLabel = new JLabel("Name");
		setDefaultBackgroundTextLabel(NameBackgroundLabel);
		
		SurnameTextField = new JTextField();
		setDefaultJTextFieldDesign(SurnameTextField);
		SurnameTextField.setColumns(10);
		
		JLabel SurnameBackgroundLabel = new JLabel("Surname");
		setDefaultBackgroundTextLabel(SurnameBackgroundLabel);
		
		EmailTextField = new JTextField();
		setDefaultJTextFieldDesign(EmailTextField);
		EmailTextField.setColumns(10);
		
		JLabel EmailBackgroundLabel = new JLabel("Email");
		setDefaultBackgroundTextLabel(EmailBackgroundLabel);
		
		JButton SearchButton = new JButton("Search");
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchEmployee(NameTextField.getText(), SurnameTextField.getText(), EmailTextField.getText(), SkillTextField.getText());
			}
		});
		setDefaultActionButtonDesign(SearchButton);
		
		JScrollPane ChosenMembersScrollPane = new JScrollPane();
		setDefaultJScrollPane(ChosenMembersScrollPane);
		ChosenMembersScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		SkillTextField = new JTextField();
		setDefaultJTextFieldDesign(SkillTextField);
		SkillTextField.setColumns(10);
		
		JLabel SkillBackgroundLabel = new JLabel("Skill");
		super.setDefaultBackgroundTextLabel(SkillBackgroundLabel);
		
		JLabel ChoosenEmployeesListHeaderLabel = new JLabel("Meeting members:");
		setDefaultHeaderTextLabel(ChoosenEmployeesListHeaderLabel);
		
		JButton AddMemberButton = new JButton("+");
		AddMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMember();
			}
		});
		setDefaultActionButtonDesign(AddMemberButton);
		
		JButton RemoveMemberButton = new JButton("-");
		RemoveMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeMember();
			}
		});
		setDefaultActionButtonDesign(RemoveMemberButton);
		
		MembersNumberVariableLabel = new JLabel("1");
		MembersNumberVariableLabel.setVisible(false);
		MembersNumberVariableLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout gl_CentralPanel = new GroupLayout(CentralPanel);
		gl_CentralPanel.setHorizontalGroup(
			gl_CentralPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_CentralPanel.createSequentialGroup()
									.addComponent(EmployeesScrollPane, GroupLayout.PREFERRED_SIZE, 494, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(AddMemberButton, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
										.addComponent(RemoveMemberButton, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
									.addGap(18))
								.addGroup(gl_CentralPanel.createSequentialGroup()
									.addComponent(EmployeesListHeaderLabel)
									.addGap(458)))
							.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(MembersNumberVariableLabel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
									.addComponent(ChosenMembersScrollPane, GroupLayout.PREFERRED_SIZE, 498, GroupLayout.PREFERRED_SIZE))
								.addComponent(ChoosenEmployeesListHeaderLabel, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)))
						.addComponent(SearchByLabel)
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addGroup(gl_CentralPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(NameBackgroundLabel, Alignment.LEADING)
								.addComponent(NameTextField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(SurnameBackgroundLabel)
								.addComponent(SurnameTextField, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(EmailTextField, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
								.addComponent(EmailBackgroundLabel))
							.addGap(18)
							.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(SkillBackgroundLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(SkillTextField, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 91, Short.MAX_VALUE))
						.addComponent(SearchButton, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_CentralPanel.setVerticalGroup(
			gl_CentralPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(EmployeesListHeaderLabel)
						.addComponent(ChoosenEmployeesListHeaderLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(AddMemberButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(EmployeesScrollPane, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_CentralPanel.createParallelGroup(Alignment.TRAILING)
							.addComponent(RemoveMemberButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addComponent(ChosenMembersScrollPane, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MembersNumberVariableLabel)
					.addGap(8)
					.addComponent(SearchByLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addGroup(gl_CentralPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(NameTextField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(SurnameTextField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_CentralPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(NameBackgroundLabel)
								.addComponent(SurnameBackgroundLabel)))
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addComponent(EmailTextField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(EmailBackgroundLabel))
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addComponent(SkillTextField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(SkillBackgroundLabel)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(SearchButton)
					.addGap(160))
		);
		
		ChosenMembersTable = new JTable();
		ChosenMembersTable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				ChosenMembersTable.getSelectionModel().clearSelection();
				
			}
		});
		ChosenMembersTable.getTableHeader().setReorderingAllowed(false);
		ChosenMembersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ChosenMembersTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name and surname", "Email", "Score"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class
			};
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		ChosenMembersTable.getColumnModel().getColumn(0).setResizable(false);
		ChosenMembersTable.getColumnModel().getColumn(1).setResizable(false);
		ChosenMembersTable.getColumnModel().getColumn(2).setResizable(false);
		ChosenMembersTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		ChosenMembersScrollPane.setViewportView(ChosenMembersTable);
		
		EmployeeTable = new JTable();
		EmployeeTable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				EmployeeTable.getSelectionModel().clearSelection();
				
			}
		});
		EmployeeTable.getTableHeader().setReorderingAllowed(false);
		EmployeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		EmployeeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name and surname", "Email", "Score"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class
			};
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		EmployeeTable.getColumnModel().getColumn(0).setResizable(false);
		EmployeeTable.getColumnModel().getColumn(1).setResizable(false);
		EmployeeTable.getColumnModel().getColumn(2).setResizable(false);
		EmployeeTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		EmployeesScrollPane.setViewportView(EmployeeTable);
		CentralPanel.setLayout(gl_CentralPanel);
		
	}
	
	public void addMember() {
		
		Integer CurrentMembers = Integer.parseInt(MembersNumberVariableLabel.getText());
		
		if(EmployeeTable.getSelectedRow() != -1) {
			boolean exists = false;
			
			for(int i = 0; i < ChosenMembersTable.getModel().getRowCount(); i++) {
				if(EmployeeTable.getModel().getValueAt(EmployeeTable.getSelectedRow(), 1) == ChosenMembersTable.getModel().getValueAt(i, 1)) {
					exists = true;
					break;
				}
			}
			
			DefaultTableModel Model = (DefaultTableModel) ChosenMembersTable.getModel();
			
			if(!exists) {
				Model.addRow(new Object[]{ EmployeeTable.getModel().getValueAt(EmployeeTable.getSelectedRow(), 0), EmployeeTable.getModel().getValueAt(EmployeeTable.getSelectedRow(), 1), EmployeeTable.getModel().getValueAt(EmployeeTable.getSelectedRow(), 2)});
				CurrentMembers++;
				MembersNumberVariableLabel.setText(CurrentMembers.toString());
			}
			
		}
		
		EmployeeTable.clearSelection();
		ChosenMembersTable.clearSelection();
		
	}
	
	public void removeMember() {
		
		DefaultTableModel Model = (DefaultTableModel) ChosenMembersTable.getModel();
		
		if(ChosenMembersTable.getSelectedRow() != -1) {
			Model.removeRow(ChosenMembersTable.getSelectedRow());
			
			Integer CurrentMembers = Integer.parseInt(MembersNumberVariableLabel.getText());
			
			if(CurrentMembers > 1) {
				CurrentMembers--;
				MembersNumberVariableLabel.setText(CurrentMembers.toString());
			}
			
		}
		
		EmployeeTable.clearSelection();
		ChosenMembersTable.clearSelection();
		
	}
	
	private void resetTableAndFields() {
		
		NameTextField.setText("");
		SurnameTextField.setText("");
		EmailTextField.setText("");
		SkillTextField.setText("");
		
		DefaultTableModel Model = (DefaultTableModel) EmployeeTable.getModel();
		
		if(Model.getRowCount() != 0) {
			Model.setRowCount(0);
		}
		
	}
	
	private void searchEmployee(String Name, String Surname, String Email, String SkillName) {
		
		NameTextField.setText("");
		SurnameTextField.setText("");
		EmailTextField.setText("");
		SkillTextField.setText("");
		
		String DBName;
		String DBSurname;
		
		ArrayList<Integer> Score = new ArrayList<Integer>();
		ArrayList<Employee> ProjectMembersList = MainController.getEmployeeDAO().getEmployeeByName_Surname_Email_SkillName_ProjectNameOrderedByScoreWithoutAlreadyMembers(Name, Surname, Email, SkillName, Score, MeetingID, Project.getName());
		
		DefaultTableModel Model = (DefaultTableModel) EmployeeTable.getModel();
		
		if(Model.getRowCount() != 0) {
			Model.setRowCount(0);
		}
		
		if(ProjectMembersList != null) {
			
			for(int i = 0; i < ProjectMembersList.size(); i++) {
				Employee Employee = ProjectMembersList.get(i);
				
				DBName = Employee.getName();
				DBSurname = Employee.getSurname();
				
				DBName = DBName.substring(0, 1).toUpperCase() + DBName.substring(1, DBName.length()).toLowerCase();
				DBSurname = DBSurname.substring(0, 1).toUpperCase() + DBSurname.substring(1, DBSurname.length()).toLowerCase();
				
				Model.addRow(new Object[]{ DBName + " " + DBSurname, Employee.getEmail(), Score.get(i)});
				
			}
			
		}
		
	}
	
	public void setData(Project Project) {
		
		this.Project = Project;
		
		searchEmployee("", "", "", "");
		
	}
}
