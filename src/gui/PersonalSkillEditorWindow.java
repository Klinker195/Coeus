package gui;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;

import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import controller.Controller;
import entities.Employee;
import entities.Skill;
import entities.User;
import exceptions.IntervalException;

import java.awt.GridLayout;
import java.awt.Window.Type;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.border.CompoundBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class PersonalSkillEditorWindow extends GenericDialog {


	private Controller MainController = Controller.getInstance();
	
	private JPanel MainPanel;
	private JTextField CustomSkillTextField;
	
	private JScrollPane EmployeeSkillScrollPane;
	
	private JList<String> SkillList;
	private JList<String> EmployeeSkillList;
	
	private JButton DoneButton;
	private JButton ExitButton;
	private JButton AddSkillButton;
	private JButton RemoveSkillButton;
	private JButton AddCustomSkillButton;
	
	private User ConnectedUser;
	
	public PersonalSkillEditorWindow(int DisplayWidth, int DisplayHeight, User ConnectedUser) {
		
		this.ConnectedUser = ConnectedUser;
		
		setBounds(DisplayWidth/2 - 340, DisplayHeight/2 - 120, 680, 240);
		
		MainPanel = new JPanel();
		setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		setType(Type.UTILITY);
		setDefaultBorderDesign(MainPanel);
		setDefaultDesign(this);
		setContentPane(MainPanel);
		MainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel TopPanel = new JPanel();
		MainPanel.add(TopPanel, BorderLayout.NORTH);
		TopPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel TitlePanel = new JPanel();
		FlowLayout fl_TitlePanel = (FlowLayout) TitlePanel.getLayout();
		fl_TitlePanel.setVgap(0);
		fl_TitlePanel.setHgap(0);
		TopPanel.add(TitlePanel, BorderLayout.WEST);
		
		JPanel RightPanel = new JPanel();
		FlowLayout fl_RightPanel = (FlowLayout) RightPanel.getLayout();
		fl_RightPanel.setVgap(0);
		fl_RightPanel.setHgap(0);
		TopPanel.add(RightPanel, BorderLayout.EAST);
		
		
		ExitButton = new JButton();
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainController.displayConfirmationDialog("Attention!", "Are you sure to exit the personal skill editor without saving changes?");
				
				if(MainController.getConfirmationDialogValue()) {
					dispose();
				}
				
			}
		});
		setDefaultExitButtonDesign(ExitButton);
		RightPanel.add(ExitButton);
		
		JPanel BottomPanel = new JPanel();
		MainPanel.add(BottomPanel, BorderLayout.SOUTH);
		
		DoneButton = new JButton("Done");
		DoneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainController.displayConfirmationDialog("Attention!", "Do you want to save the edits?");
				
				if(MainController.getConfirmationDialogValue()) {
					
					MainController.getEmployeeDAO().deleteEmployeeSkillsByCF(ConnectedUser.getEmployee().getCF());
					
					ArrayList<String> EmployeeSkillNames = new ArrayList<String>();
					
					for(int i = 0; i < EmployeeSkillList.getModel().getSize(); i++) {
						EmployeeSkillNames.add(EmployeeSkillList.getModel().getElementAt(i).toString());
					}
					
					MainController.getEmployeeDAO().insertEmployeeSkillsByCF(EmployeeSkillNames, ConnectedUser.getEmployee().getCF());
					
					dispose();
					
					MainController.displayMessageDialog("Success!", "Personal skill edit successful.");
					
					MainController.getCoeusProjectManagerWindow().resetActionEmployeePanelInfos();
					MainController.getCoeusProjectManagerWindow().setEmployeesData();
					
				} else {
					
					dispose();
					
				}
				
			}
		});
		setDefaultLineBorderButtonDesign(DoneButton);
		BottomPanel.add(DoneButton);
		
		JPanel CentralPanel = new JPanel();
		CentralPanel.setBorder(null);
		setDefaultBackgroundDesign(CentralPanel);
		MainPanel.add(CentralPanel, BorderLayout.CENTER);
		
		JLabel PersonalSkillLabel = new JLabel("Personal Skills");
		setDefaultHeaderTextLabel(PersonalSkillLabel);
		
		JScrollPane SkillScrollPane = new JScrollPane();
		SkillScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setDefaultJScrollPane(SkillScrollPane);
		
		AddSkillButton = new JButton("+");
		AddSkillButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addSkillList();
			}
		});
		setDefaultActionButtonDesign(AddSkillButton);
		
		EmployeeSkillScrollPane = new JScrollPane();
		EmployeeSkillScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setDefaultJScrollPane(EmployeeSkillScrollPane);
		
		RemoveSkillButton = new JButton("-");
		RemoveSkillButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeSkillList();
			}
		});
		setDefaultActionButtonDesign(RemoveSkillButton);
		
		JLabel SkillsBackgroundLabel = new JLabel("General skills");
		setDefaultBackgroundTextLabel(SkillsBackgroundLabel);
		
		CustomSkillTextField = new JTextField();
		CustomSkillTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				deleteFieldSpaces(CustomSkillTextField);
				deleteFieldThreshold(CustomSkillTextField);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				deleteFieldSpaces(CustomSkillTextField);
				deleteFieldThreshold(CustomSkillTextField);
			}
		});
		setDefaultJTextFieldDesign(CustomSkillTextField);
		
		AddCustomSkillButton = new JButton("Add custom skill");
		AddCustomSkillButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomSkill();
			}
		});
		setDefaultActionButtonDesign(AddCustomSkillButton);
		
		JLabel PersonalSkillsBackgroundLabel = new JLabel("Personal skills");
		setDefaultBackgroundTextLabel(PersonalSkillsBackgroundLabel);
		
		GroupLayout gl_CentralPanel = new GroupLayout(CentralPanel);
		gl_CentralPanel.setHorizontalGroup(
			gl_CentralPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(PersonalSkillLabel)
								.addGroup(gl_CentralPanel.createSequentialGroup()
									.addComponent(SkillScrollPane, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(gl_CentralPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(AddSkillButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(RemoveSkillButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(PersonalSkillsBackgroundLabel, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_CentralPanel.createSequentialGroup()
											.addComponent(EmployeeSkillScrollPane, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addGroup(gl_CentralPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(AddCustomSkillButton, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
												.addComponent(CustomSkillTextField, 254, 254, 254))))))
							.addContainerGap(38, Short.MAX_VALUE))
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addComponent(SkillsBackgroundLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(519))))
		);
		gl_CentralPanel.setVerticalGroup(
			gl_CentralPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(PersonalSkillLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(RemoveSkillButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(SkillScrollPane, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addComponent(AddSkillButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_CentralPanel.createSequentialGroup()
								.addComponent(CustomSkillTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addGap(29)
								.addComponent(AddCustomSkillButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addComponent(EmployeeSkillScrollPane, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(SkillsBackgroundLabel)
						.addComponent(PersonalSkillsBackgroundLabel))
					.addGap(12))
		);
		
		EmployeeSkillList = new JList<String>();
		EmployeeSkillList.setValueIsAdjusting(true);
		EmployeeSkillScrollPane.setViewportView(EmployeeSkillList);
		
		SkillList = new JList<String>();
		SkillList.setValueIsAdjusting(true);
		SkillScrollPane.setViewportView(SkillList);
		CentralPanel.setLayout(gl_CentralPanel);
	}
	
	public void addSkillList() {
		
		List<String> SelectedSkills = SkillList.getSelectedValuesList();
		
		List<String> CurrentSkills = new ArrayList<String>();
		
		for(int i = 0; i < EmployeeSkillList.getModel().getSize(); i++) {
			CurrentSkills.add(EmployeeSkillList.getModel().getElementAt(i));
		}
				
		for(String Skill : SelectedSkills) {
			if(!CurrentSkills.contains(Skill)) {
				CurrentSkills.add(Skill);
			}
		}
		
		
		EmployeeSkillList.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = MainController.arrayListToStringArray((ArrayList<String>)CurrentSkills);
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		
		SkillList.clearSelection();
		EmployeeSkillList.clearSelection();
		
	}
	
	public void removeSkillList() {
		
		List<String> SelectedSkills = EmployeeSkillList.getSelectedValuesList();
		
		List<String> CurrentSkills = new ArrayList<String>();
		
		for(int i = 0; i < EmployeeSkillList.getModel().getSize(); i++) {
			CurrentSkills.add(EmployeeSkillList.getModel().getElementAt(i));
		}
		
		for(String Skill : SelectedSkills) {
			if(CurrentSkills.contains(Skill)) {
				CurrentSkills.remove(Skill);
			}
		}
		
		EmployeeSkillList.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = MainController.arrayListToStringArray((ArrayList<String>)CurrentSkills);
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		
		SkillList.clearSelection();
		EmployeeSkillList.clearSelection();
		
	}
	
	public void addCustomSkill() {
		
		if(!CustomSkillTextField.getText().isBlank()) {
			
			List<String> CurrentSkills = new ArrayList<String>();
			
			for(int i = 0; i < EmployeeSkillList.getModel().getSize(); i++) {
				CurrentSkills.add(EmployeeSkillList.getModel().getElementAt(i));
			}
			
			String NewSkill = CustomSkillTextField.getText();
			
			if(NewSkill != null) {
				NewSkill = NewSkill.substring(0, 1).toUpperCase() + NewSkill.substring(1).toLowerCase();
				if(!CurrentSkills.contains(NewSkill)) {
					CurrentSkills.add(NewSkill);
				}
			}
			
			EmployeeSkillList.setModel(new AbstractListModel<String>() {
				private static final long serialVersionUID = 1L;
				String[] values = MainController.arrayListToStringArray((ArrayList<String>)CurrentSkills);
				public int getSize() {
					return values.length;
				}
				public String getElementAt(int index) {
					return values[index];
				}
			});
			
			CustomSkillTextField.setText("");
			
		}
		
		SkillList.clearSelection();
		EmployeeSkillList.clearSelection();
		
	}
	
	public void setData(User ConnectedUser) {
		
		this.ConnectedUser = ConnectedUser;
		
		SkillList.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = MainController.arrayListToStringArray(MainController.getSkillDAO().getAllSkillNames());
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		
		ArrayList<Skill> EmployeeSkills = MainController.getSkillDAO().getEmployeeSkillsByCF(ConnectedUser.getEmployee().getCF());
		ArrayList<String> EmployeeSkillNames = new ArrayList<String>();
		
		for(int i = 0; i < EmployeeSkills.size(); i++) {
			EmployeeSkillNames.add(EmployeeSkills.get(i).getName());
		}
		
		EmployeeSkillList.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = MainController.arrayListToStringArray(EmployeeSkillNames);
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		
	}
}

