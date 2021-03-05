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
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SkillEditorWindow extends GenericDialog {

	private JPanel MainPanel;
	private JTextField NameTextField;
	private JLabel ProjectTitleLabel;
	private JComboBox<String> SkillComboBox;
	
	private Controller MainController = Controller.getInstance();

	public SkillEditorWindow(int DisplayWidth, int DisplayHeight, User ConnectedUser) {

		
		setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		setDefaultDesign(this);
		setType(Type.UTILITY);
		setBounds(DisplayWidth/2 - 250, DisplayHeight/2 - 180, 500, 360);
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
				MainController.getCoeusProjectManagerWindow().resetActionEmployeePanelInfos();
				MainController.getCoeusProjectManagerWindow().setEmployeesData();
			}
		});
		setDefaultExitButtonDesign(ExitButton);
		TopPanel.add(ExitButton, BorderLayout.EAST);
		
		ProjectTitleLabel = new JLabel("");
		TopPanel.add(ProjectTitleLabel, BorderLayout.WEST);
		
		JPanel BottomPanel = new JPanel();
		FlowLayout fl_BottomPanel = (FlowLayout) BottomPanel.getLayout();
		MainPanel.add(BottomPanel, BorderLayout.SOUTH);
		
		JButton DoneButton = new JButton("Done");
		DoneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainController.getCoeusProjectManagerWindow().resetActionEmployeePanelInfos();
				MainController.getCoeusProjectManagerWindow().setEmployeesData();
			}
		});
		setDefaultLineBorderButtonDesign(DoneButton);
		BottomPanel.add(DoneButton);
		
		JPanel CentralPanel = new JPanel();
		CentralPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		setDefaultBackgroundDesign(CentralPanel);
		MainPanel.add(CentralPanel, BorderLayout.CENTER);
		
		JLabel ProjectMembersHeaderLabel = new JLabel("Skill Editor");
		setDefaultHeaderTextLabel(ProjectMembersHeaderLabel);
		
		JLabel CustomSkillLabel = new JLabel("Search by:");
		setDefaultTextLabel(CustomSkillLabel);
		
		NameTextField = new JTextField();
		NameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				deleteFieldThreshold(NameTextField);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				deleteFieldThreshold(NameTextField);
			}
		});
		setDefaultJTextFieldDesign(NameTextField);
		NameTextField.setColumns(10);
		
		JLabel CustomSkillBackgroundLabel = new JLabel("Custom Skill Name");
		setDefaultBackgroundTextLabel(CustomSkillBackgroundLabel);
		
		JButton AddCustomButton = new JButton("Add custom skill");
		AddCustomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!NameTextField.getText().isBlank()) {
					
					if(!MainController.getSkillDAO().existsByName(NameTextField.getText())) {
						MainController.getSkillDAO().insertNewSkill(NameTextField.getText());
					} else {
						MainController.displayConfirmationDialog("Error!", "The skill already exists.");
					}
					
				} else {
					MainController.displayMessageDialog("Error!", "You have to write a skill name before adding it into the database.");
				}
				
			}
		});
		setDefaultActionButtonDesign(AddCustomButton);
		
		JLabel SkillListLabel = new JLabel("Skills list:");
		setDefaultTextLabel(SkillListLabel);
		
		SkillComboBox = new JComboBox<String>();
		setDefaultJComboBox(SkillComboBox);
		
		JLabel SkillNameBackgroundLabel = new JLabel("Skill Name");
		setDefaultBackgroundTextLabel(SkillNameBackgroundLabel);
		
		JButton DeleteButton = new JButton("Delete");
		DeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainController.displayConfirmationDialog("Attention.", "Are you sure you want to delete this skill?");
				
				if(MainController.getConfirmationDialogValue()) {
					MainController.getSkillDAO().deleteSkillByName(SkillComboBox.getSelectedItem().toString());
					
					MainController.displayMessageDialog("Success!", "Skill deleted successfully.");
					
					setData();
				}
				
			}
		});
		setDefaultActionButtonDesign(DeleteButton);
		GroupLayout gl_CentralPanel = new GroupLayout(CentralPanel);
		gl_CentralPanel.setHorizontalGroup(
			gl_CentralPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(ProjectMembersHeaderLabel))
						.addGroup(Alignment.TRAILING, gl_CentralPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(SkillComboBox, 0, 434, Short.MAX_VALUE)
								.addComponent(SkillListLabel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
								.addComponent(SkillNameBackgroundLabel, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
								.addComponent(DeleteButton, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(CustomSkillLabel))
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(NameTextField)
								.addComponent(CustomSkillBackgroundLabel)
								.addComponent(AddCustomButton, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_CentralPanel.setVerticalGroup(
			gl_CentralPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addComponent(ProjectMembersHeaderLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(SkillListLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(SkillComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(SkillNameBackgroundLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(DeleteButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(CustomSkillLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(NameTextField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(CustomSkillBackgroundLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(AddCustomButton)
					.addContainerGap(356, Short.MAX_VALUE))
		);
		CentralPanel.setLayout(gl_CentralPanel);
		
	}
	
	public void setData() {
		
		SkillComboBox.setModel(new DefaultComboBoxModel<String>(MainController.arrayListToStringArray(MainController.getSkillDAO().getAllSkillNames())));
		
	}
}
