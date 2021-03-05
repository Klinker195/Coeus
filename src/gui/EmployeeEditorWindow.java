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

public class EmployeeEditorWindow extends GenericDialog {

	private static final long serialVersionUID = 1L;

	private Controller MainController = Controller.getInstance();
	
	private JPanel MainPanel;

	private JTextField FirstNameTextField;
	private JTextField LastNameTextField;
	private JTextField SecondLastNameTextField;
	private JTextField EmailTextField;
	private JTextField CustomSkillTextField;
	
	private JComboBox<Integer> DayJComboBox;
	private JComboBox<String> MonthJComboBox;
	private JComboBox<Integer> YearJComboBox;
	private JComboBox<String> StateOfBirthJComboBox;
	private JComboBox<String> RegionJComboBox;
	private JComboBox<String> ProvinceOfBirthJComboBox;
	private JComboBox<String> TimeZoneJComboBox;
	private JComboBox<Character> SexJComboBox;
	private JComboBox<String> DistrictOfBirthJComboBox;
	
	private JScrollPane EmployeeSkillScrollPane;
	
	private JList<String> SkillList;
	private JList<String> EmployeeSkillList;
	
	private JButton NextButton;
	private JButton ExitButton;
	private JButton AddSkillButton;
	private JButton RemoveSkillButton;
	private JButton AddCustomSkillButton;
	
	private JLabel SalaryBackgroundLabel;
	private JLabel SalaryLabel;
	private JLabel TimezoneBackgroundLabel;
	
	private JSpinner SalarySpinner;
	
	private String[] WorldStates;
	private String[] RegionNames;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RegistrationWindow frame = new RegistrationWindow(1920, 1080, false);
//					frame.setData();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	/**
	 *  Modality values guide:
	 *  Modality = 0 -> FounderMode;
	 *  Modality = 1 -> UserRegistrationMode;
	 *  Modality = 2 -> EmployeeCreationMode;
	 *  Modality = 3 -> EmployeeEditMode;
	 */
	
	public EmployeeEditorWindow(int DisplayWidth, int DisplayHeight, int Modality) throws IntervalException {
		
		setBounds(DisplayWidth/2 - 340, DisplayHeight/2 - 450, 680, 900);
		
		if(Modality == 0) {
			setBounds(DisplayWidth/2 - 340, DisplayHeight/2 - 450, 680, 900);
		} else if(Modality == 1) {
			setBounds(DisplayWidth/2 - 340, DisplayHeight/2 - 400, 680, 800);
		}
		
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
		
		JLabel CoeusLabel = new JLabel("Coeus");
		CoeusLabel.setForeground(new Color(15, 39, 115));
		CoeusLabel.setFont(new Font("Roboto Bk", Font.PLAIN, 70));
		TitlePanel.add(CoeusLabel);
		
		JPanel RightPanel = new JPanel();
		FlowLayout fl_RightPanel = (FlowLayout) RightPanel.getLayout();
		fl_RightPanel.setVgap(0);
		fl_RightPanel.setHgap(0);
		TopPanel.add(RightPanel, BorderLayout.EAST);
		
		
		ExitButton = new JButton();
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		setDefaultExitButtonDesign(ExitButton);
		RightPanel.add(ExitButton);
		
		JPanel BottomPanel = new JPanel();
		MainPanel.add(BottomPanel, BorderLayout.SOUTH);
		
		NextButton = new JButton("Next");
		NextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (!checkRegistrationFieldsValidity()){
					MainController.displayMessageDialog("Error!", "Error: There are errors in some of the fields!");
				} else {
					String CF = calculateCF();
					MainController.displayConfirmationDialog("Automatically calculated CF", "The CF is equal to:\n" + CF + "\nMake sure the CF is correct, then click 'Ok' to continue. ");
					if(MainController.getConfirmationDialogValue()) {
						
						try {
							SalarySpinner.commitEdit();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						
						if(Modality == 0) {
							
							// Adjust employee informations and save them into temporary variables
							String Name = FirstNameTextField.getText().toUpperCase();
							String Surname = LastNameTextField.getText().toUpperCase() + " " + SecondLastNameTextField.getText().toUpperCase();
							String Email = EmailTextField.getText().toLowerCase();
							float Salary = (float)SalarySpinner.getValue();
							String TimeZone = (String)TimeZoneJComboBox.getSelectedItem();
							ArrayList<Skill> SkillArrayList = new ArrayList<Skill>();
							ArrayList<Skill> NewSkillArrayList = new ArrayList<Skill>();
							
							
							// Fill SkillArrayList with the skills chosen by the creator of this employee
							for(int i = 0; i < EmployeeSkillList.getModel().getSize(); i++) {
								SkillArrayList.add(new Skill(EmployeeSkillList.getModel().getElementAt(i)));
							}
								
							// Exists variable to check if a certain skill is found
							boolean exists = false;
								
							// All the new custom skills are added to NewSkillArrayList so that they can be added to the DB
							for(int i = 0; i < EmployeeSkillList.getModel().getSize(); i++) {
								for(int j = 0; j < SkillList.getModel().getSize(); j++) {
									if(SkillArrayList.get(i).getName().toUpperCase() == SkillList.getModel().getElementAt(j).toUpperCase()) {
										exists = true;
										break;
									}
									exists = false;
								}
								if(!exists) {
									NewSkillArrayList.add(SkillArrayList.get(i));
								}
							}
							
							// Creation of Employee object based on the informations given
							Employee NewEmployee = new Employee(CF, Name, Surname, Email, Salary, TimeZone, SkillArrayList);
							
							// If NewSkillArrayList contains at least one new custom skill then it's added to the DB
							if(NewSkillArrayList.size() != 0) {
								MainController.getSkillDAO().insertNewSkills(NewSkillArrayList);
							}
							
							// When in Founder mode the employees are deleted before Founder Employee insertion
							if(MainController.getEmployeeDAO().exists()) {
								MainController.getEmployeeDAO().deleteAllEmployees();
							}
							
							// The Employee is inserted in the DB
							MainController.getEmployeeDAO().insertEmployee(NewEmployee);

							// EmployeeEditorWindow is closed
							dispose();
								
							// UserRegistrationWindow is opened to let the Employee link to an User
							MainController.userRegistration(NewEmployee, true);
						} else if(Modality == 1) {
							
							// Adjust employee informations and save them into temporary variables
							String Name = FirstNameTextField.getText().toUpperCase();
							String Surname = LastNameTextField.getText().toUpperCase() + " " + SecondLastNameTextField.getText().toUpperCase();
							String Email = EmailTextField.getText().toLowerCase();
							float Salary = (float)SalarySpinner.getValue();
							String TimeZone = (String)TimeZoneJComboBox.getSelectedItem();
							ArrayList<Skill> SkillArrayList = new ArrayList<Skill>();
							ArrayList<Skill> NewSkillArrayList = new ArrayList<Skill>();
							
							// Fill SkillArrayList with the skills chosen by the employee
							for(int i = 0; i < EmployeeSkillList.getModel().getSize(); i++) {
								SkillArrayList.add(new Skill(EmployeeSkillList.getModel().getElementAt(i)));
							}
							
							// Exists variable to check if a certain skill is found
							boolean exists = false;
							
							// All the new custom skills are added to NewSkillArrayList so that they can be added to the DB
							for(int i = 0; i < EmployeeSkillList.getModel().getSize(); i++) {
								for(int j = 0; j < SkillList.getModel().getSize(); j++) {
									if(SkillArrayList.get(i).getName().toUpperCase().equals(SkillList.getModel().getElementAt(j).toUpperCase())) {
										exists = true;
										break;
									}
									exists = false;
								}
								if(!exists) {
									NewSkillArrayList.add(SkillArrayList.get(i));
								}
							}
								
							// Creation of Employee object based on the informations given
							Employee NewEmployee = new Employee(CF, Name, Surname, Email, Salary, TimeZone, SkillArrayList);
							
							if(!MainController.getEmployeeDAO().existsByCF(NewEmployee.getCF())) {
								dispose();
								MainController.displayMessageDialog("Error!", "You're not an employee of this corporation!");
								MainController.start();
							} else {
								// If the account doesn't exists then create the account, otherwise notify the user with an error and return to login screen
								if(!MainController.getUserDAO().existsByCF(CF)) {
									// If NewSkillArrayList contains at least one new custom skill then it's added to the DB
									if(NewSkillArrayList.size() != 0) {
										MainController.getSkillDAO().insertNewSkills(NewSkillArrayList);
									}
									
									// The Employee is updated in the DB by CF without updating salary
									MainController.getEmployeeDAO().updateEmployeeByCFNoSalary(NewEmployee);
									
									// EmployeeEditorWindow is closed
									dispose();
									
									// UserRegistrationWindow is opened to let the Employee link to an User
									MainController.userRegistration(NewEmployee, false);
								} else {
									dispose();
									MainController.displayMessageDialog("Error!", "There is already an user associated with these informations!");
									MainController.start();
								}
							}		
						} else if(Modality == 2) {
							
							// Adjust employee informations and save them into temporary variables
							String Name = FirstNameTextField.getText().toUpperCase();
							String Surname = LastNameTextField.getText().toUpperCase() + " " + SecondLastNameTextField.getText().toUpperCase();
							String Email = EmailTextField.getText().toLowerCase();
							float Salary = (float)SalarySpinner.getValue();
							String TimeZone = (String)TimeZoneJComboBox.getSelectedItem();
							ArrayList<Skill> SkillArrayList = new ArrayList<Skill>();
							ArrayList<Skill> NewSkillArrayList = new ArrayList<Skill>();
							
							
							// Fill SkillArrayList with the skills chosen by the creator of this employee
							for(int i = 0; i < EmployeeSkillList.getModel().getSize(); i++) {
								SkillArrayList.add(new Skill(EmployeeSkillList.getModel().getElementAt(i)));
							}
								
							// Exists variable to check if a certain skill is found
							boolean exists = false;
								
							// All the new custom skills are added to NewSkillArrayList so that they can be added to the DB
							for(int i = 0; i < EmployeeSkillList.getModel().getSize(); i++) {
								for(int j = 0; j < SkillList.getModel().getSize(); j++) {
									if(SkillArrayList.get(i).getName().toUpperCase() == SkillList.getModel().getElementAt(j).toUpperCase()) {
										exists = true;
										break;
									}
									exists = false;
								}
								if(!exists) {
									NewSkillArrayList.add(SkillArrayList.get(i));
								}
							}
							
							// Creation of Employee object based on the informations given
							Employee NewEmployee = new Employee(CF, Name, Surname, Email, Salary, TimeZone, SkillArrayList);
							
							// If NewSkillArrayList contains at least one new custom skill then it's added to the DB
							if(NewSkillArrayList.size() != 0) {
								MainController.getSkillDAO().insertNewSkills(NewSkillArrayList);
							}
							
							// The Employee is inserted in the DB
							MainController.getEmployeeDAO().insertEmployee(NewEmployee);

							// EmployeeEditorWindow is closed
							dispose();
							
							MainController.displayConfirmationDialog("Success!", "The employee has been created successfully!");
							
							MainController.getCoeusProjectManagerWindow().resetActionEmployeePanelInfos();
							MainController.getCoeusProjectManagerWindow().setEmployeesData();
							
						}
					}
				}
			}
		});
		setDefaultLineBorderButtonDesign(NextButton);
		BottomPanel.add(NextButton);
		
		JPanel CentralPanel = new JPanel();
		CentralPanel.setBorder(null);
		setDefaultBackgroundDesign(CentralPanel);
		MainPanel.add(CentralPanel, BorderLayout.CENTER);
		
		JLabel FullNameLabel = new JLabel("Full Name");
		setDefaultHeaderTextLabel(FullNameLabel);
		
		FirstNameTextField = new JTextField();
		FirstNameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				deleteFieldThreshold(FirstNameTextField);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				deleteFieldThreshold(FirstNameTextField);
			}
		});
		setDefaultJTextFieldDesign(FirstNameTextField);
		
		JLabel FirstNameLabel = new JLabel("First Name");
		setDefaultBackgroundTextLabel(FirstNameLabel);
		
		JLabel LastNameLabel = new JLabel("Last Name");
		setDefaultBackgroundTextLabel(LastNameLabel);
		
		JLabel DetailsLabel = new JLabel("Details");
		setDefaultHeaderTextLabel(DetailsLabel);
		
		LastNameTextField = new JTextField();
		LastNameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				deleteFieldThreshold(FirstNameTextField);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				deleteFieldThreshold(FirstNameTextField);
			}
		});
		setDefaultJTextFieldDesign(LastNameTextField);
		
		JLabel DataPrivacyLabel = new JLabel("None of these informations will be stored, the data will only be used to calculate the CF.");
		setDefaultBackgroundTextLabel(DataPrivacyLabel);
		
		JLabel BirthdateLabel = new JLabel("Birthdate");
		setDefaultTextLabel(BirthdateLabel);
		
		JLabel StateOfBirthLabel = new JLabel("State of birth");
		setDefaultTextLabel(StateOfBirthLabel);
		
		JLabel RegionOfBirthLabel = new JLabel("Region of birth");
		setDefaultTextLabel(RegionOfBirthLabel);
		
		JLabel ProvinceOfBirthLabel = new JLabel("Province of birth");
		setDefaultTextLabel(ProvinceOfBirthLabel);
		
		DayJComboBox = new JComboBox<Integer>();
		setDefaultJComboBox(DayJComboBox);
		
		YearJComboBox = new JComboBox<Integer>();
		setDefaultJComboBox(YearJComboBox);
		
		MonthJComboBox = new JComboBox<String>();
		setDefaultJComboBox(MonthJComboBox);
		MonthJComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateJComboBoxDay((String)MonthJComboBox.getSelectedItem(), (Integer)YearJComboBox.getSelectedItem());
				} catch (IntervalException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		YearJComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateJComboBoxDay((String)MonthJComboBox.getSelectedItem(), (Integer)YearJComboBox.getSelectedItem());
				} catch (IntervalException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JLabel MonthLabel = new JLabel("Month");
		setDefaultBackgroundTextLabel(MonthLabel);
		
		JLabel DayLabel = new JLabel("Day");
		setDefaultBackgroundTextLabel(DayLabel);
		
		JLabel YearLabel = new JLabel("Year");
		setDefaultBackgroundTextLabel(YearLabel);
		
		StateOfBirthJComboBox = new JComboBox<String>();
		setDefaultJComboBox(StateOfBirthJComboBox);
		StateOfBirthJComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateJComboBoxBirthInformations((String)StateOfBirthJComboBox.getSelectedItem(), (String)RegionJComboBox.getSelectedItem());
			}
		});
		
		RegionJComboBox = new JComboBox<String>();
		setDefaultJComboBox(RegionJComboBox);
		RegionJComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateJComboBoxBirthInformations((String)StateOfBirthJComboBox.getSelectedItem(), (String)RegionJComboBox.getSelectedItem());
			}
		});
		
		ProvinceOfBirthJComboBox = new JComboBox<String>();
		ProvinceOfBirthJComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DistrictOfBirthJComboBox.setModel(new DefaultComboBoxModel<String>(MainController.arrayListToStringArray(MainController.getItalianDistrictDAO().getAllDistrictsByProvinceName((String)ProvinceOfBirthJComboBox.getSelectedItem()))));
			}
		});
		setDefaultJComboBox(ProvinceOfBirthJComboBox);
		
		JSeparator BottomSeparator = new JSeparator();
		setDefaultJSeparator(BottomSeparator);
		
		JSeparator TopSeparator = new JSeparator();
		setDefaultJSeparator(TopSeparator);
		
		JLabel AdditionalInformationLabel = new JLabel("Additional informations");
		setDefaultHeaderTextLabel(AdditionalInformationLabel);
		
		JLabel EmailLabel = new JLabel("Email address");
		setDefaultTextLabel(EmailLabel);
		
		TimeZoneJComboBox = new JComboBox<String>();
		setDefaultJComboBox(TimeZoneJComboBox);
		
		SalaryLabel = new JLabel("Salary");
		setDefaultTextLabel(SalaryLabel);
		
		SalaryBackgroundLabel = new JLabel("Salary");
		
		if(Modality == 0) {
			SalaryBackgroundLabel.setText("Insert your monthly salary in the current corporation");
		} else {
			SalaryBackgroundLabel.setText("Insert the monthly salary of this person in the current corporation");
		}
		setDefaultBackgroundTextLabel(SalaryBackgroundLabel);
		
		SalarySpinner = new JSpinner();
		setDefaultJSpinner(SalarySpinner);
		
		if(Modality != 0 || Modality != 2) {
			SalarySpinner.setVisible(false);
			SalaryBackgroundLabel.setVisible(false);
			SalaryLabel.setVisible(false);
		}
		
		SecondLastNameTextField = new JTextField();
		SecondLastNameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				deleteFieldThreshold(FirstNameTextField);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				deleteFieldThreshold(FirstNameTextField);
			}
		});
		setDefaultJTextFieldDesign(SecondLastNameTextField);
		
		JLabel SecondLastNameLabel = new JLabel("Second Last Name");
		setDefaultBackgroundTextLabel(SecondLastNameLabel);
		
		JSeparator VerticalSeparator = new JSeparator();
		VerticalSeparator.setOrientation(SwingConstants.VERTICAL);
		setDefaultJSeparator(VerticalSeparator);
		
		JLabel SexLabel = new JLabel("Sex");
		setDefaultTextLabel(SexLabel);
		
		SexJComboBox = new JComboBox<Character>();
		setDefaultJComboBox(SexJComboBox);
		
		JLabel DistrictOfBirth = new JLabel("District of birth");
		setDefaultTextLabel(DistrictOfBirth);
		
		DistrictOfBirthJComboBox = new JComboBox<String>();
		setDefaultJComboBox(DistrictOfBirthJComboBox);
		
		TimezoneBackgroundLabel = new JLabel("Current time based on selected timezone: ");
		setDefaultBackgroundTextLabel(TimezoneBackgroundLabel);
		
		ActionListener updateClockAction = new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  LocalTime Now = LocalTime.now(ZoneId.of((String)TimeZoneJComboBox.getSelectedItem()));
				  
				  TimezoneBackgroundLabel.setText("Current time based on selected timezone: " + DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(Now));
			    }
			};
			
		Timer Timer = new Timer(100, updateClockAction);
		Timer.start();
		
		JLabel SkillLabel = new JLabel("Skills");
		setDefaultTextLabel(SkillLabel);
		
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
		
		JSeparator VerticalSeparator_1 = new JSeparator();
		VerticalSeparator_1.setOrientation(SwingConstants.VERTICAL);
		setDefaultJSeparator(VerticalSeparator_1);
		
		JLabel TimeZoneLabel = new JLabel("Timezone");
		setDefaultTextLabel(TimeZoneLabel);
		
		JLabel EmailBackgroundLabel = new JLabel("Email (it will be used for login)");
		setDefaultBackgroundTextLabel(EmailBackgroundLabel);
		
		EmailTextField = new JTextField();
		EmailTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				deleteFieldThreshold(EmailTextField);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				deleteFieldThreshold(EmailTextField);
			}
		});
		setDefaultJTextFieldDesign(EmailTextField);
		
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
		
		JLabel SkillsBackgroundLabel = new JLabel("Use + and - buttons to add and remove skills. It's also possible to create custom skills");
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
		
		GroupLayout gl_CentralPanel = new GroupLayout(CentralPanel);
		gl_CentralPanel.setHorizontalGroup(
			gl_CentralPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(SalaryBackgroundLabel, GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
						.addComponent(SalarySpinner, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
						.addComponent(SalaryLabel, GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addGap(0)
							.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(SkillLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addComponent(AdditionalInformationLabel, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
								.addComponent(BottomSeparator, GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
								.addGroup(gl_CentralPanel.createSequentialGroup()
									.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_CentralPanel.createSequentialGroup()
											.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(MonthJComboBox, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
												.addComponent(MonthLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
											.addGap(18)
											.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(DayJComboBox, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
												.addComponent(DayLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
											.addGap(18)
											.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(YearLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
												.addComponent(YearJComboBox, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))
										.addComponent(BirthdateLabel))
									.addGap(18)
									.addComponent(VerticalSeparator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(SexLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
										.addComponent(SexJComboBox, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
								.addComponent(DataPrivacyLabel, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE)
								.addComponent(DetailsLabel)
								.addComponent(TopSeparator, GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
								.addGroup(gl_CentralPanel.createSequentialGroup()
									.addComponent(FirstNameLabel)
									.addGap(141)
									.addComponent(LastNameLabel)
									.addGap(142)
									.addComponent(SecondLastNameLabel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_CentralPanel.createSequentialGroup()
									.addComponent(FirstNameTextField, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(LastNameTextField, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(SecondLastNameTextField, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
								.addComponent(FullNameLabel)
								.addGroup(gl_CentralPanel.createSequentialGroup()
									.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_CentralPanel.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(StateOfBirthLabel, Alignment.LEADING)
											.addComponent(StateOfBirthJComboBox, Alignment.LEADING, 0, 176, Short.MAX_VALUE)
											.addComponent(ProvinceOfBirthJComboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(ProvinceOfBirthLabel, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
									.addGap(21)
									.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(DistrictOfBirth, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING, false)
											.addComponent(RegionOfBirthLabel, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
											.addComponent(RegionJComboBox, 0, 173, Short.MAX_VALUE))
										.addComponent(DistrictOfBirthJComboBox, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_CentralPanel.createSequentialGroup()
									.addComponent(SkillScrollPane, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(AddSkillButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(RemoveSkillButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addComponent(EmployeeSkillScrollPane, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(AddCustomSkillButton, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
										.addComponent(CustomSkillTextField, 254, 254, 254)))
								.addGroup(gl_CentralPanel.createSequentialGroup()
									.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(EmailBackgroundLabel, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
										.addComponent(EmailLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
										.addComponent(EmailTextField, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE))
									.addGap(26)
									.addComponent(VerticalSeparator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
									.addGap(26)
									.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(TimeZoneJComboBox, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
										.addComponent(TimezoneBackgroundLabel)
										.addComponent(TimeZoneLabel, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
								.addComponent(SkillsBackgroundLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(38)))
					.addContainerGap())
		);
		gl_CentralPanel.setVerticalGroup(
			gl_CentralPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(FullNameLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(FirstNameTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(LastNameTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(SecondLastNameTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(FirstNameLabel)
						.addComponent(LastNameLabel)
						.addComponent(SecondLastNameLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(TopSeparator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(DetailsLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(DataPrivacyLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addComponent(BirthdateLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_CentralPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(MonthJComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(DayJComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(YearJComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_CentralPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(YearLabel)
								.addComponent(DayLabel)
								.addComponent(MonthLabel)))
						.addComponent(VerticalSeparator, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addComponent(SexLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(SexJComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(StateOfBirthLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(RegionOfBirthLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(StateOfBirthJComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(RegionJComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(ProvinceOfBirthLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(DistrictOfBirth, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(ProvinceOfBirthJComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(DistrictOfBirthJComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(BottomSeparator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addComponent(AdditionalInformationLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(EmailLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(EmailTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(25))
						.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_CentralPanel.createSequentialGroup()
								.addGap(62)
								.addComponent(EmailBackgroundLabel))
							.addGroup(gl_CentralPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(VerticalSeparator_1, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_CentralPanel.createSequentialGroup()
									.addComponent(TimeZoneLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(TimeZoneJComboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(TimezoneBackgroundLabel)))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(SkillLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addComponent(AddSkillButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(RemoveSkillButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(SkillScrollPane, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addComponent(EmployeeSkillScrollPane, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addComponent(CustomSkillTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(AddCustomSkillButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(SkillsBackgroundLabel)
					.addGap(11)
					.addComponent(SalaryLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(SalarySpinner, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(SalaryBackgroundLabel)
					.addGap(44))
		);
		
		EmployeeSkillList = new JList<String>();
		EmployeeSkillList.setValueIsAdjusting(true);
		EmployeeSkillScrollPane.setViewportView(EmployeeSkillList);
		
		SkillList = new JList<String>();
		SkillList.setValueIsAdjusting(true);
		SkillScrollPane.setViewportView(SkillList);
		CentralPanel.setLayout(gl_CentralPanel);
	}
	
	
	// Updates the Day in JComboBoxDay field based on Month and Year selected (leap-years included). The Day is still equal to the current user selection if it's still in the interval.
	public void updateJComboBoxDay(String Month, Integer Year) throws IntervalException {
		
		Integer[] DayInterval;
		int SelectedDay = (int)DayJComboBox.getSelectedItem();
		
		switch(Month) {
		case "February":
			if(Year % 4 == 0 && Year % 100 != 0 || Year % 400 == 0) {
				
				DayInterval = MainController.intervalToIntegerArray(1, 29);
				
				if(MainController.isInInterval(DayInterval, SelectedDay)) {
					DayJComboBox.setModel(new DefaultComboBoxModel<Integer>(DayInterval));
					DayJComboBox.setSelectedItem(SelectedDay);
				} else {
					DayJComboBox.setModel(new DefaultComboBoxModel<Integer>(DayInterval));
				}
				
			} else {
				
				DayInterval = MainController.intervalToIntegerArray(1, 28);
				
				if(MainController.isInInterval(DayInterval, SelectedDay)) {
					DayJComboBox.setModel(new DefaultComboBoxModel<Integer>(DayInterval));
					DayJComboBox.setSelectedItem(SelectedDay);
				} else {
					DayJComboBox.setModel(new DefaultComboBoxModel<Integer>(DayInterval));
				}
				
			}
			break;
		case "April":
			
			DayInterval = MainController.intervalToIntegerArray(1, 30);
			
			if(MainController.isInInterval(DayInterval, SelectedDay)) {
				DayJComboBox.setModel(new DefaultComboBoxModel<Integer>(DayInterval));
				DayJComboBox.setSelectedItem(SelectedDay);
			} else {
				DayJComboBox.setModel(new DefaultComboBoxModel<Integer>(DayInterval));
			}
			
			break;
		case "June":
			
			DayInterval = MainController.intervalToIntegerArray(1, 30);
			
			if(MainController.isInInterval(DayInterval, SelectedDay)) {
				DayJComboBox.setModel(new DefaultComboBoxModel<Integer>(DayInterval));
				DayJComboBox.setSelectedItem(SelectedDay);
			} else {
				DayJComboBox.setModel(new DefaultComboBoxModel<Integer>(DayInterval));
			}
			
			break;
		case "September":
			
			DayInterval = MainController.intervalToIntegerArray(1, 30);
			
			if(MainController.isInInterval(DayInterval, SelectedDay)) {
				DayJComboBox.setModel(new DefaultComboBoxModel<Integer>(DayInterval));
				DayJComboBox.setSelectedItem(SelectedDay);
			} else {
				DayJComboBox.setModel(new DefaultComboBoxModel<Integer>(DayInterval));
			}
			
			break;
		case "November":
			
			DayInterval = MainController.intervalToIntegerArray(1, 30);
			
			if(MainController.isInInterval(DayInterval, SelectedDay)) {
				DayJComboBox.setModel(new DefaultComboBoxModel<Integer>(DayInterval));
				DayJComboBox.setSelectedItem(SelectedDay);
			} else {
				DayJComboBox.setModel(new DefaultComboBoxModel<Integer>(DayInterval));
			}
			
			break;
		default:
			
			DayInterval = MainController.intervalToIntegerArray(1, 31);
			
			if(MainController.isInInterval(DayInterval, SelectedDay)) {
				DayJComboBox.setModel(new DefaultComboBoxModel<Integer>(DayInterval));
				DayJComboBox.setSelectedItem(SelectedDay);
			} else {
				DayJComboBox.setModel(new DefaultComboBoxModel<Integer>(DayInterval));
			}
			
			break;
		}
		
	}
	
	// Updates the data in the ComboBoxes related to birthplace based on State and Region selected. If the State is equal to Italy
	// then the methods enables the Region and the City ComboBoxes, otherwise they will be disabled.
	public void updateJComboBoxBirthInformations(String StateName, String RegionName) {
		
		if(StateName.equals("Italy")) {
			RegionJComboBox.setEnabled(true);
			ProvinceOfBirthJComboBox.setEnabled(true);
			DistrictOfBirthJComboBox.setEnabled(true);
			ProvinceOfBirthJComboBox.setModel(new DefaultComboBoxModel<String>(MainController.arrayListToStringArray(MainController.getItalianDistrictDAO().getAllProvinceNameByRegionName(RegionName))));
			DistrictOfBirthJComboBox.setModel(new DefaultComboBoxModel<String>(MainController.arrayListToStringArray(MainController.getItalianDistrictDAO().getAllDistrictsByProvinceName((String)ProvinceOfBirthJComboBox.getSelectedItem()))));
		} else {
			RegionJComboBox.setEnabled(false);
			ProvinceOfBirthJComboBox.setEnabled(false);
			DistrictOfBirthJComboBox.setEnabled(false);
		}
		
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
	
	public String calculateCF() {
		
		if(FirstNameTextField.getText() != null && LastNameTextField.getText() != null) {
			if(FirstNameTextField.getText().isBlank() || LastNameTextField.getText().isBlank()) {
				MainController.displayMessageDialog("Error!", "Error: The CF cannot be calculated without filling all of the required fields.");
			} else {
				StringBuilder CF = new StringBuilder();
				
				CF.append(extractCFSurnameInitials(LastNameTextField.getText(), SecondLastNameTextField.getText()));
				CF.append(extractCFNameInitials(FirstNameTextField.getText()));
				CF.append(extractCFBirthdateCode((String)MonthJComboBox.getSelectedItem(), (Integer)DayJComboBox.getSelectedItem(), (Integer)YearJComboBox.getSelectedItem(), (Character)SexJComboBox.getSelectedItem()));
				
				if(StateOfBirthJComboBox.getSelectedItem().equals("Italy")) {
					CF.append(MainController.getItalianDistrictDAO().getDistrictCodeByProvinceAndDistrictName((String)ProvinceOfBirthJComboBox.getSelectedItem(), (String)DistrictOfBirthJComboBox.getSelectedItem()));
				} else {
					CF.append(MainController.getWorldStateDAO().getStateCodeByStateName((String)StateOfBirthJComboBox.getSelectedItem()));
				}
				
				CF.append(extractCIN(CF.toString()));
				
				return CF.toString();
			}
		} else {
			MainController.displayMessageDialog("Error!", "Error: The CF cannot be calculated without filling all of the required fields.");
			return null;
		}
		return null;
		
	}
	
	private String extractCFSurnameInitials(String Surname, String SecondSurname) {
		
		Surname = Surname.toUpperCase();
		Surname = Surname.replace(" ", "");
		Surname = Surname.replace("'", "");
		if(SecondSurname != null) {
			if(!SecondSurname.isBlank()) {
				SecondSurname = SecondSurname.toUpperCase();
				SecondSurname = SecondSurname.replace(" ", "");
				SecondSurname = SecondSurname.replace("'", "");
				Surname = Surname.concat(SecondSurname);
			}
		}
		StringBuilder Initials = new StringBuilder();
		StringBuilder Consonants = new StringBuilder();
		StringBuilder Vowels = new StringBuilder();
		String ConsonantsString;
		String VowelsString;
		
		for(int i = 0; i < Surname.length(); i++) {
			if(this.isVowel(Surname.charAt(i))) {
				Vowels.append(Surname.charAt(i));
			} else {
				Consonants.append(Surname.charAt(i));
			}
		}
		
		ConsonantsString = Consonants.toString();
		
		if(Consonants.length() >= 4) {
			for(int i = 0; i < ConsonantsString.length(); i++) {
				
				Initials.append(ConsonantsString.charAt(i));
				
				if(i == 2) {
					return Initials.toString();
				}
				
			}
		} else if(Consonants.length() == 3) {
			for(int i = 0; i < ConsonantsString.length(); i++) {
				
				Initials.append(ConsonantsString.charAt(i));
				
				if(i == 2) {
					return Initials.toString();
				}
				
			}
		} else {
			for(int i = 0; i < Consonants.length(); i++) {
				Initials.append(Consonants.charAt(i));
			}
			
			VowelsString = Vowels.toString();
			for(int i = 0; i < VowelsString.length(); i++) {
				
				Initials.append(VowelsString.charAt(i));
				
				if(Initials.length() == 3) return Initials.toString();
				
			}
		}
		
		if(Initials.length() < 3) {
			for(int i = 0; i <= 3 - Initials.length(); i++) {
				Initials.append('X');
			}
		}
		
		
		return Initials.toString();
	}
	
	private String extractCFNameInitials(String Name) {
		
		Name = Name.toUpperCase();
		Name = Name.replace(" ", "");
		StringBuilder Initials = new StringBuilder();
		StringBuilder Consonants = new StringBuilder();
		StringBuilder Vowels = new StringBuilder();
		String ConsonantsString;
		String VowelsString;
		
		for(int i = 0; i < Name.length(); i++) {
			if(this.isVowel(Name.charAt(i))) {
				Vowels.append(Name.charAt(i));
			} else {
				Consonants.append(Name.charAt(i));
			}
		}
		
		ConsonantsString = Consonants.toString();
		
		if(Consonants.length() >= 4) {
			for(int i = 0; i < ConsonantsString.length(); i++) {
				
				if(i == 1) continue;
				
				Initials.append(ConsonantsString.charAt(i));
				
				if(i == 3) {
					return Initials.toString();
				}
				
			}
		} else if(Consonants.length() == 3) {
			for(int i = 0; i < ConsonantsString.length(); i++) {
				
				Initials.append(ConsonantsString.charAt(i));
				
				if(i == 2) {
					return Initials.toString();
				}
				
			}
		} else {
			for(int i = 0; i < Consonants.length(); i++) {
				Initials.append(Consonants.charAt(i));
			}
			
			VowelsString = Vowels.toString();
			for(int i = 0; i < VowelsString.length(); i++) {
				
				Initials.append(VowelsString.charAt(i));
				
				if(Initials.length() == 3) return Initials.toString();
				
			}
		}
		
		if(Initials.length() < 3) {
			for(int i = 0; i <= 3 - Initials.length(); i++) {
				Initials.append('X');
			}
		}
		
		
		return Initials.toString();
	}
	
	private String extractCFBirthdateCode(String BirthdateMonth, Integer BirthdateDay, Integer BirthdateYear, Character Sex) {
		
		StringBuilder BirthdateCode = new StringBuilder();
		
		
		String BirthdateYearString = BirthdateYear.toString();
		BirthdateYearString = BirthdateYearString.substring(2);
		
		BirthdateCode.append(BirthdateYearString);
		
		switch(BirthdateMonth) {
			case "January":
				BirthdateCode.append('A');
				break;
			case "February":
				BirthdateCode.append('B');
				break;
			case "March":
				BirthdateCode.append('C');
				break;
			case "April":
				BirthdateCode.append('D');
				break;
			case "May":
				BirthdateCode.append('E');
				break;
			case "June":
				BirthdateCode.append('H');
				break;
			case "July":
				BirthdateCode.append('L');
				break;
			case "August":
				BirthdateCode.append('M');
				break;
			case "September":
				BirthdateCode.append('P');
				break;
			case "October":
				BirthdateCode.append('R');
				break;
			case "November":
				BirthdateCode.append('S');
				break;
			case "December":
				BirthdateCode.append('T');
				break;
		}
		
		if(Sex == 'F') BirthdateDay += 40;
		
		if(BirthdateDay < 10) BirthdateCode.append(0);
		
		BirthdateCode.append(BirthdateDay);
		
		return BirthdateCode.toString();
	}
	
	private Character extractCIN(String InitialCF) {
		
		StringBuilder EvenCharacters = new StringBuilder();
		StringBuilder OddCharacters = new StringBuilder();
		
		for(int i = 1; i <= InitialCF.length(); i++) {
			if(i % 2 == 0) {
				EvenCharacters.append(InitialCF.charAt(i - 1));
			} else {
				OddCharacters.append(InitialCF.charAt(i - 1));
			}
		}
		
		int Sum = 0;
		
		for(int i = 0; i < EvenCharacters.length(); i++) {
			switch(EvenCharacters.toString().charAt(i)) {
				case '0':
					Sum += 0;
					break;
				case '1':
					Sum += 1;
					break;
				case '2':
					Sum += 2;
					break;
				case '3':
					Sum += 3;
					break;
				case '4':
					Sum += 4;
					break;
				case '5':
					Sum += 5;
					break;
				case '6':
					Sum += 6;
					break;
				case '7':
					Sum += 7;
					break;
				case '8':
					Sum += 8;
					break;
				case '9':
					Sum += 9;
					break;
				case 'A':
					Sum += 0;
					break;
				case 'B':
					Sum += 1;
					break;
				case 'C':
					Sum += 2;
					break;
				case 'D':
					Sum += 3;
					break;
				case 'E':
					Sum += 4;
					break;
				case 'F':
					Sum += 5;
					break;
				case 'G':
					Sum += 6;
					break;
				case 'H':
					Sum += 7;
					break;
				case 'I':
					Sum += 8;
					break;
				case 'J':
					Sum += 9;
					break;
				case 'K':
					Sum += 10;
					break;
				case 'L':
					Sum += 11;
					break;
				case 'M':
					Sum += 12;
					break;
				case 'N':
					Sum += 13;
					break;
				case 'O':
					Sum += 14;
					break;
				case 'P':
					Sum += 15;
					break;
				case 'Q':
					Sum += 16;
					break;
				case 'R':
					Sum += 17;
					break;
				case 'S':
					Sum += 18;
					break;
				case 'T':
					Sum += 19;
					break;
				case 'U':
					Sum += 20;
					break;
				case 'V':
					Sum += 21;
					break;
				case 'W':
					Sum += 22;
					break;
				case 'X':
					Sum += 23;
					break;
				case 'Y':
					Sum += 24;
					break;
				case 'Z':
					Sum += 25;
					break;
			}
		}
		
		for(int i = 0; i < OddCharacters.length(); i++) {
			switch(OddCharacters.toString().charAt(i)) {
				case '0':
					Sum += 1;
					break;
				case '1':
					Sum += 0;
					break;
				case '2':
					Sum += 5;
					break;
				case '3':
					Sum += 7;
					break;
				case '4':
					Sum += 9;
					break;
				case '5':
					Sum += 13;
					break;
				case '6':
					Sum += 15;
					break;
				case '7':
					Sum += 17;
					break;
				case '8':
					Sum += 19;
					break;
				case '9':
					Sum += 21;
					break;
				case 'A':
					Sum += 1;
					break;
				case 'B':
					Sum += 0;
					break;
				case 'C':
					Sum += 5;
					break;
				case 'D':
					Sum += 7;
					break;
				case 'E':
					Sum += 9;
					break;
				case 'F':
					Sum += 13;
					break;
				case 'G':
					Sum += 15;
					break;
				case 'H':
					Sum += 17;
					break;
				case 'I':
					Sum += 19;
					break;
				case 'J':
					Sum += 21;
					break;
				case 'K':
					Sum += 2;
					break;
				case 'L':
					Sum += 4;
					break;
				case 'M':
					Sum += 18;
					break;
				case 'N':
					Sum += 20;
					break;
				case 'O':
					Sum += 11;
					break;
				case 'P':
					Sum += 3;
					break;
				case 'Q':
					Sum += 6;
					break;
				case 'R':
					Sum += 8;
					break;
				case 'S':
					Sum += 12;
					break;
				case 'T':
					Sum += 14;
					break;
				case 'U':
					Sum += 16;
					break;
				case 'V':
					Sum += 10;
					break;
				case 'W':
					Sum += 22;
					break;
				case 'X':
					Sum += 25;
					break;
				case 'Y':
					Sum += 24;
					break;
				case 'Z':
					Sum += 23;
					break;
			}
		}
		
		int IdentifierValue = Sum % 26;
		Character IdentifierLetter = null;
		
		switch(IdentifierValue) {
			case 0:
				IdentifierLetter = 'A';
			break;
			case 1:
				IdentifierLetter = 'B';
			break;
			case 2:
				IdentifierLetter = 'C';
			break;
			case 3:
				IdentifierLetter = 'D';
			break;
			case 4:
				IdentifierLetter = 'E';
			break;
			case 5:
				IdentifierLetter = 'F';
			break;
			case 6:
				IdentifierLetter = 'G';
			break;
			case 7:
				IdentifierLetter = 'H';
			break;
			case 8:
				IdentifierLetter = 'I';
			break;
			case 9:
				IdentifierLetter = 'J';
			break;
			case 10:
				IdentifierLetter = 'K';
			break;
			case 11:
				IdentifierLetter = 'L';
			break;
			case 12:
				IdentifierLetter = 'M';
			break;
			case 13:
				IdentifierLetter = 'N';
			break;
			case 14:
				IdentifierLetter = 'O';
			break;
			case 15:
				IdentifierLetter = 'P';
			break;
			case 16:
				IdentifierLetter = 'Q';
			break;
			case 17:
				IdentifierLetter = 'R';
			break;
			case 18:
				IdentifierLetter = 'S';
			break;
			case 19:
				IdentifierLetter = 'T';
			break;
			case 20:
				IdentifierLetter = 'U';
			break;
			case 21:
				IdentifierLetter = 'V';
			break;
			case 22:
				IdentifierLetter = 'W';
			break;
			case 23:
				IdentifierLetter = 'X';
			break;
			case 24:
				IdentifierLetter = 'Y';
			break;
			case 25:
				IdentifierLetter = 'Z';
			break;
		}
		
		return IdentifierLetter;
	}
	
	public boolean checkRegistrationFieldsValidity() {
		
		
		String Name = FirstNameTextField.getText();
		String Surname = LastNameTextField.getText();
		String SecondSurname = SecondLastNameTextField.getText();
		String Email = EmailTextField.getText();
		
		Pattern NamePattern = Pattern.compile("[a-zA-Z]+", Pattern.CASE_INSENSITIVE);
		Pattern SurnamePattern = Pattern.compile("[a-zA-Z]+\s*[a-zA-Z]*", Pattern.CASE_INSENSITIVE);
		Pattern SecondSurnamePattern = Pattern.compile("[a-zA-Z]*\s*[a-zA-Z]*", Pattern.CASE_INSENSITIVE);
		Pattern EmailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", Pattern.CASE_INSENSITIVE);
		
		Matcher NameMatcher = NamePattern.matcher(Name);
		Matcher SurnameMatcher = SurnamePattern.matcher(Surname);
		Matcher SecondSurnameMatcher = SecondSurnamePattern.matcher(SecondSurname);
		Matcher EmailMatcher = EmailPattern.matcher(Email);
		
		boolean SkillPresence;
		if(EmployeeSkillList.getModel().getSize() == 0) {
			SkillPresence = false;
		} else {
			SkillPresence = true;
		}
		
		if(!NameMatcher.matches() || !SurnameMatcher.matches() || !SecondSurnameMatcher.matches() || !EmailMatcher.matches() || !SkillPresence) {
			
			if(!NameMatcher.matches()) {
				FirstNameTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.red));
			} else {
				FirstNameTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
			}
			
			if(!SurnameMatcher.matches()) {
				LastNameTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.red));
			} else {
				LastNameTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
			}
			
			if(!SecondSurnameMatcher.matches()) {
				SecondLastNameTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.red));
			} else {
				SecondLastNameTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
			}
			
			if(!EmailMatcher.matches()) {
				EmailTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.red));
			} else {
				EmailTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
			}
			
			if(!SkillPresence) {
				EmployeeSkillScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.red));
			} else {
				EmployeeSkillScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
			}
			
			return false;
		} else {
			FirstNameTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
			LastNameTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
			SecondLastNameTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
			EmailTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
			EmployeeSkillScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
			return true;
		}
		
	}
	
	private boolean isVowel(char Letter) {
		
		final char[] Vowels = {'A', 'E', 'I', 'O', 'U'};
		
		for(int i = 0; i < Vowels.length; i++) {
			if(Letter == Vowels[i]) return true;
		}
		return false;
		
	}
	
	public void setData() throws IntervalException {
		WorldStates = MainController.arrayListToStringArray(MainController.getWorldStateDAO().getAllStateNames());
		RegionNames = MainController.arrayListToStringArray(MainController.getItalianDistrictDAO().getAllRegionNames());
		
		TimeZoneJComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"GMT+00:00", "GMT+01:00", "GMT+02:00", "GMT+03:00", "GMT+04:00", "GMT+05:00", "GMT+06:00", "GMT+07:00", "GMT+08:00", "GMT-01:00", "GMT-02:00", "GMT-03:00", "GMT-04:00", "GMT-05:00", "GMT-06:00", "GMT-07:00", "GMT-08:00"}));
		
		SexJComboBox.setModel(new DefaultComboBoxModel<Character>(new Character[] {'M', 'F'}));
		
		StateOfBirthJComboBox.setModel(new DefaultComboBoxModel<String>(WorldStates));
		RegionJComboBox.setModel(new DefaultComboBoxModel<String>(RegionNames));
		
		YearJComboBox.setModel(new DefaultComboBoxModel<Integer>(MainController.intervalToIntegerArray(Calendar.getInstance().get(Calendar.YEAR) - 100, Calendar.getInstance().get(Calendar.YEAR) - 19)));
		MonthJComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "Decemeber"}));
		DayJComboBox.setModel(new DefaultComboBoxModel<Integer>(MainController.intervalToIntegerArray(1, 31)));
		
		SalarySpinner.setModel(new SpinnerNumberModel(new Float(600), new Float(0), new Float(50000), new Float(1)));
		
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
		
		updateJComboBoxBirthInformations((String)StateOfBirthJComboBox.getSelectedItem(), (String)RegionJComboBox.getSelectedItem());
	}
}
