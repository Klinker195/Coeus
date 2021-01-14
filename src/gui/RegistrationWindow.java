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

import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import controller.Controller;
import exceptions.IntervalException;

import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;

public class RegistrationWindow extends GenericFrame {

	private Controller MainController = Controller.getIstance();
	
	private JPanel MainPanel;

	private JTextField FirstNameTextField;
	private JTextField LastNameTextField;
	private JTextField CFTextField;
	
	private JComboBox<Integer> DayJComboBox;
	private JComboBox<String> MonthJComboBox;
	private JComboBox<Integer> YearJComboBox;
	private JComboBox<String> StateOfBirthJComboBox;
	private JComboBox<String> RegionJComboBox;
	private JComboBox<String> CityOfBirthJComboBox;
	private JComboBox<String> TimeZoneJComboBox;
	
	private JButton NextButton;
	private JButton ExitButton;
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RegistrationWindow frame = new RegistrationWindow();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	
	public RegistrationWindow(int DisplayWidth, int DisplayHeight, String[] WorldStates, String[] RegionNames) throws IntervalException {
		setBounds(DisplayWidth/2 - 340, DisplayHeight/2 - 450, 680, 900);
		MainPanel = new JPanel();
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
		CoeusLabel.setForeground(new Color(153, 51, 51));
		CoeusLabel.setFont(new Font("Tahoma", Font.PLAIN, 70));
		TitlePanel.add(CoeusLabel);
		
		JPanel RightPanel = new JPanel();
		FlowLayout fl_RightPanel = (FlowLayout) RightPanel.getLayout();
		fl_RightPanel.setVgap(0);
		fl_RightPanel.setHgap(0);
		TopPanel.add(RightPanel, BorderLayout.EAST);
		
		
		ExitButton = new JButton();
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		setDefaultExitButtonDesign(ExitButton);
		RightPanel.add(ExitButton);
		
		JPanel BottomPanel = new JPanel();
		MainPanel.add(BottomPanel, BorderLayout.SOUTH);
		
		NextButton = new JButton("Next");
		NextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(FirstNameTextField.getSelectedText() != null) {
					if(FirstNameTextField.getSelectedText().isBlank() || LastNameTextField.getSelectedText().isBlank() || CFTextField.getSelectedText().isBlank()) {
						MainController.displayMessageDialog("Error!", "Error: One or more text fields are missing! Please make sure to fill all the fields.");
					} else {
						System.exit(0); // Temporary
					}
				} else {
					MainController.displayMessageDialog("Error!", "Error: One or more text fields are missing! Please make sure to fill all the fields.");
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
		setDefaultJTextFieldDesign(FirstNameTextField);
		
		JLabel FirstNameLabel = new JLabel("First Name");
		setDefaultBackgroundTextLabel(FirstNameLabel);
		
		JLabel LastNameLabel = new JLabel("Last Name");
		setDefaultBackgroundTextLabel(LastNameLabel);
		
		JLabel DetailsLabel = new JLabel("Details");
		setDefaultHeaderTextLabel(DetailsLabel);
		
		LastNameTextField = new JTextField();
		setDefaultJTextFieldDesign(LastNameTextField);
		
		JLabel DataPrivacyLabel = new JLabel("None of these informations will be stored, the data will only be used to calculate your CF.");
		setDefaultBackgroundTextLabel(DataPrivacyLabel);
		
		JLabel BirthdateLabel = new JLabel("Birthdate");
		setDefaultTextLabel(BirthdateLabel);
		
		JLabel StateOfBirthLabel = new JLabel("State of birth");
		setDefaultTextLabel(StateOfBirthLabel);
		
		JLabel RegionOfBirthLabel = new JLabel("Region of birth");
		setDefaultTextLabel(RegionOfBirthLabel);
		
		JLabel CityOfBirthLabel = new JLabel("City of birth");
		setDefaultTextLabel(CityOfBirthLabel);
		
		DayJComboBox = new JComboBox<Integer>();
		DayJComboBox.setModel(new DefaultComboBoxModel<Integer>(MainController.intervalToIntegerArray(1, 31)));
		

		YearJComboBox = new JComboBox<Integer>();
		YearJComboBox.setModel(new DefaultComboBoxModel<Integer>(MainController.intervalToIntegerArray(Calendar.getInstance().get(Calendar.YEAR) - 100, Calendar.getInstance().get(Calendar.YEAR) - 19)));
		
		MonthJComboBox = new JComboBox<String>();
		MonthJComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateJComboBoxDay(DayJComboBox, (String)MonthJComboBox.getSelectedItem(), (Integer)YearJComboBox.getSelectedItem());
				} catch (IntervalException e1) {
					e1.printStackTrace();
				}
			}
		});
		MonthJComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "Decemeber"}));
		
		YearJComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateJComboBoxDay(DayJComboBox, (String)MonthJComboBox.getSelectedItem(), (Integer)YearJComboBox.getSelectedItem());
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
		StateOfBirthJComboBox.setModel(new DefaultComboBoxModel<String>(WorldStates));
		
		RegionJComboBox = new JComboBox<String>();
		RegionJComboBox.setModel(new DefaultComboBoxModel<String>(RegionNames));
		
		CityOfBirthJComboBox = new JComboBox<String>();
		
		
		JSeparator BottomSeparator = new JSeparator();
		BottomSeparator.setBackground(Color.DARK_GRAY);
		BottomSeparator.setForeground(Color.DARK_GRAY);
		
		JSeparator TopSeparator = new JSeparator();
		TopSeparator.setForeground(Color.DARK_GRAY);
		TopSeparator.setBackground(Color.DARK_GRAY);
		
		JLabel AdditionalInformationLabel = new JLabel("Additional informations");
		setDefaultHeaderTextLabel(AdditionalInformationLabel);
		
		CFTextField = new JTextField();
		CFTextField.setEnabled(false);
		CFTextField.setEditable(false);
		setDefaultJTextFieldDesign(CFTextField);
		
		JLabel AutomaticallyCalculatedCFLabel = new JLabel("Automatically calculated CF");
		setDefaultBackgroundTextLabel(AutomaticallyCalculatedCFLabel);
		
		JLabel CFLabel = new JLabel("CF");
		setDefaultTextLabel(CFLabel);
		
		JLabel TimeZoneLabel = new JLabel("Timezone");
		setDefaultTextLabel(TimeZoneLabel);
		
		TimeZoneJComboBox = new JComboBox<String>();
		TimeZoneJComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"GMT+00:00", "GMT+01:00", "GMT+02:00", "GMT+03:00", "GMT+04:00", "GMT+05:00", "GMT+07:00", "GMT+08:00", "GMT-01:00", "GMT-02:00", "GMT-03:00", "GMT-04:00", "GMT-05:00", "GMT-07:00", "GMT-08:00"}));
		GroupLayout gl_CentralPanel = new GroupLayout(CentralPanel);
		gl_CentralPanel.setHorizontalGroup(
			gl_CentralPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_CentralPanel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_CentralPanel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_CentralPanel.createSequentialGroup()
										.addGap(11)
										.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_CentralPanel.createSequentialGroup()
												.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
													.addComponent(FirstNameTextField, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
													.addComponent(FirstNameLabel))
												.addGap(74)
												.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
													.addComponent(LastNameLabel)
													.addComponent(LastNameTextField, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)))
											.addComponent(FullNameLabel)))
									.addComponent(TopSeparator, GroupLayout.PREFERRED_SIZE, 680, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_CentralPanel.createSequentialGroup()
								.addGap(21)
								.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_CentralPanel.createSequentialGroup()
										.addGap(11)
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
											.addComponent(DataPrivacyLabel, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE)
											.addComponent(DetailsLabel)
											.addComponent(BirthdateLabel)
											.addComponent(StateOfBirthLabel)
											.addComponent(CityOfBirthJComboBox, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
											.addComponent(CityOfBirthLabel, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
											.addComponent(RegionOfBirthLabel, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
											.addGroup(gl_CentralPanel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(RegionJComboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(StateOfBirthJComboBox, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(ComponentPlacement.RELATED, 130, Short.MAX_VALUE))
									.addComponent(BottomSeparator, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE))))
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(AdditionalInformationLabel, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
								.addComponent(CFLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_CentralPanel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(AutomaticallyCalculatedCFLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(CFTextField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
									.addGroup(Alignment.LEADING, gl_CentralPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(TimeZoneJComboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(TimeZoneLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))))
							.addGap(422)))
					.addContainerGap())
		);
		gl_CentralPanel.setVerticalGroup(
			gl_CentralPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addGap(27)
					.addComponent(FullNameLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addComponent(FirstNameTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(FirstNameLabel))
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addComponent(LastNameTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(LastNameLabel)))
					.addGap(18)
					.addComponent(TopSeparator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(DetailsLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(DataPrivacyLabel)
					.addGap(8)
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
						.addComponent(MonthLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(StateOfBirthLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(StateOfBirthJComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(RegionOfBirthLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(RegionJComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(CityOfBirthLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(CityOfBirthJComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(BottomSeparator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(AdditionalInformationLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(CFLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(CFTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(AutomaticallyCalculatedCFLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(TimeZoneLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TimeZoneJComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(132, Short.MAX_VALUE))
		);
		CentralPanel.setLayout(gl_CentralPanel);
	}
	
	// Update the Day in JComboBoxDay field based on Month and Year selected
	public void updateJComboBoxDay(JComboBox<Integer> JComboBox, String Month, Integer Year) throws IntervalException {
		
		switch(Month) {
		case "February":
			if(Year % 4 == 0 && Year % 100 != 0 || Year % 400 == 0) {
				JComboBox.setModel(new DefaultComboBoxModel<Integer>(MainController.intervalToIntegerArray(1, 29)));
			} else {
				JComboBox.setModel(new DefaultComboBoxModel<Integer>(MainController.intervalToIntegerArray(1, 28)));
			}
			break;
		case "April":
			JComboBox.setModel(new DefaultComboBoxModel<Integer>(MainController.intervalToIntegerArray(1, 30)));
			break;
		case "June":
			JComboBox.setModel(new DefaultComboBoxModel<Integer>(MainController.intervalToIntegerArray(1, 30)));
			break;
		case "September":
			JComboBox.setModel(new DefaultComboBoxModel<Integer>(MainController.intervalToIntegerArray(1, 30)));
			break;
		case "November":
			JComboBox.setModel(new DefaultComboBoxModel<Integer>(MainController.intervalToIntegerArray(1, 30)));
			break;
		default:
			JComboBox.setModel(new DefaultComboBoxModel<Integer>(MainController.intervalToIntegerArray(1, 31)));
			break;
		}
		
	}
	

	
	// TODO Add a CF calculator
//	public String calculateCF() {
//		
//	}
	
}
