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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import entities.Employee;
import entities.OngoingProject;
import entities.Project;
import entities.User;

import javax.swing.JButton;
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

public class ProjectMembersListWindow extends GenericDialog {

	private JPanel MainPanel;
	private JTable MembersTable;
	private JTextField NameTextField;
	private JTextField SurnameTextField;
	private JTextField EmailTextField;
	private JLabel ProjectTitleLabel;
	
	private String ProjectName;

	private Controller MainController = Controller.getInstance();

	public ProjectMembersListWindow(int DisplayWidth, int DisplayHeight, String ProjectName, User ConnectedUser, String ProjectManagerCF) {
		
		this.ProjectName = ProjectName;
		
		setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		setDefaultDesign(this);
		setType(Type.UTILITY);
		setBounds(DisplayWidth/2 - 250, DisplayHeight/2 - 350, 500, 700);
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
				MainController.getCoeusProjectManagerWindow().resetActionProjectPanelInfos();
				setVisible(false);
			}
		});
		setDefaultExitButtonDesign(ExitButton);
		TopPanel.add(ExitButton, BorderLayout.EAST);
		
		ProjectTitleLabel = new JLabel("");
		TopPanel.add(ProjectTitleLabel, BorderLayout.WEST);
		
		JPanel BottomPanel = new JPanel();
		BottomPanel.setPreferredSize(new Dimension(500, 40));
		FlowLayout fl_BottomPanel = (FlowLayout) BottomPanel.getLayout();
		MainPanel.add(BottomPanel, BorderLayout.SOUTH);
		
		JPanel CentralPanel = new JPanel();
		CentralPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		setDefaultBackgroundDesign(CentralPanel);
		MainPanel.add(CentralPanel, BorderLayout.CENTER);
		
		JLabel ProjectMembersHeaderLabel = new JLabel("Members list:");
		setDefaultHeaderTextLabel(ProjectMembersHeaderLabel);
		
		JScrollPane MembersScrollPane = new JScrollPane();
		MembersScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setDefaultJScrollPane(MembersScrollPane);
		
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
				searchEmployee(NameTextField.getText(), SurnameTextField.getText(), EmailTextField.getText());
			}
		});
		setDefaultActionButtonDesign(SearchButton);
		GroupLayout gl_CentralPanel = new GroupLayout(CentralPanel);
		gl_CentralPanel.setHorizontalGroup(
			gl_CentralPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addComponent(ProjectMembersHeaderLabel)
					.addGap(344))
				.addComponent(MembersScrollPane, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(SurnameBackgroundLabel)
								.addComponent(SearchByLabel)
								.addComponent(NameBackgroundLabel)
								.addComponent(EmailBackgroundLabel))
							.addPreferredGap(ComponentPlacement.RELATED, 230, GroupLayout.PREFERRED_SIZE))
						.addComponent(EmailTextField, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addGroup(gl_CentralPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(NameTextField, Alignment.LEADING)
								.addComponent(SurnameTextField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 118, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addComponent(SearchButton, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 204, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_CentralPanel.setVerticalGroup(
			gl_CentralPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addComponent(ProjectMembersHeaderLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MembersScrollPane, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(SearchByLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(NameTextField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(NameBackgroundLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(SurnameTextField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(SurnameBackgroundLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(EmailTextField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(EmailBackgroundLabel)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(SearchButton)
					.addGap(5))
		);
		
		MembersTable = new JTable();
		MembersTable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				MembersTable.getSelectionModel().clearSelection();
				
			}
		});
		MembersTable.getTableHeader().setReorderingAllowed(false);
		MembersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		MembersTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name and surname", "Email"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		MembersTable.getColumnModel().getColumn(0).setResizable(false);
		MembersTable.getColumnModel().getColumn(1).setResizable(false);
		MembersScrollPane.setViewportView(MembersTable);
		CentralPanel.setLayout(gl_CentralPanel);
		
	}
	
	private void resetTableAndFields() {
		
		NameTextField.setText("");
		SurnameTextField.setText("");
		EmailTextField.setText("");
		
		DefaultTableModel Model = (DefaultTableModel) MembersTable.getModel();
		
		if(Model.getRowCount() != 0) {
			Model.setRowCount(0);
		}
		
	}
	
	private void searchEmployee(String Name, String Surname, String Email) {
		
		NameTextField.setText("");
		SurnameTextField.setText("");
		EmailTextField.setText("");
		
		String DBName;
		String DBSurname;
		
		ArrayList<Employee> ProjectMembersList = MainController.getEmployeeDAO().getEmployeeByProjectName_Name_Surname_Email(ProjectName, Name, Surname, Email);
		
		DefaultTableModel Model = (DefaultTableModel) MembersTable.getModel();
		
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
				
				Model.addRow(new Object[]{ DBName + " " + DBSurname, Employee.getEmail()});
				
			}
			
		}
		
	}
	
	public void setData(String ProjectName) {
		
		this.ProjectName = ProjectName;
		
		String TitleProjectName;
		
		if(ProjectName.length() > 60) {
			System.out.println(ProjectName);
			TitleProjectName = ProjectName.substring(0, 60) + "...";
			System.out.println(TitleProjectName);
			TitleProjectName = TitleProjectName.substring(0, 1).toUpperCase() + TitleProjectName.substring(1).toLowerCase();
			System.out.println(TitleProjectName);
		} else {
			TitleProjectName = ProjectName;
		}
		
		ProjectTitleLabel.setText(TitleProjectName);
		
		searchEmployee("", "", "");
		
	}
	
}
