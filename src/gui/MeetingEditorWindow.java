package gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

import controller.Controller;
import entities.CompletedProject;
import entities.Meeting;
import entities.MeetingInvitation;
import entities.OngoingProject;
import entities.OnlineMeeting;
import entities.Project;
import entities.StandardMeeting;
import entities.User;
import enums.InviteStatus;

import java.awt.Color;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SpinnerModel;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class MeetingEditorWindow extends GenericDialog {

	private JPanel MainPanel = new JPanel();
	private JTextField MeetingTitleTextField;
	private JSpinner StartingTimeSpinner;
	private JSpinner EndingTimeSpinner;
	private JComboBox<String> PlatformRoomComboBox;
	private JComboBox<String> ProjectComboBox;
	private SimpleDateFormat DateSpinnerModel;
	private JSpinner DateSpinner;
	private JCheckBox OnlineCheckBox;
	private String HourFormat;
	private SimpleDateFormat TimeSpinnerModel;
	private JTable MeetingTimesTable;
	
	private Controller MainController = Controller.getInstance();
	private User ConnectedUser;

	public MeetingEditorWindow(User ConnectedUser, int DisplayWidth, int DisplayHeight) {
		
		this.ConnectedUser = ConnectedUser;
		
		setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		setDefaultDesign(this);
		setType(Type.UTILITY);
		setBounds(DisplayWidth/2 - 225, DisplayHeight/2 - 280, 450, 560);
		MainPanel = new JPanel();
		MainPanel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		setContentPane(MainPanel);
		setDefaultBorderDesign(MainPanel);
		MainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel TopPanel = new JPanel();
		MainPanel.add(TopPanel, BorderLayout.NORTH);
		TopPanel.setLayout(new BorderLayout(0, 0));
		
		JButton ExitButton = new JButton("New button");
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		setDefaultExitButtonDesign(ExitButton);
		TopPanel.add(ExitButton, BorderLayout.EAST);
		
		JPanel BottomPanel = new JPanel();
		MainPanel.add(BottomPanel, BorderLayout.SOUTH);
		BottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton NextButton = new JButton("Next");
		NextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(checkFieldsValidity()) {

					Meeting NewMeeting;
					
					String TmpStartingTimeString = StartingTimeSpinner.getModel().getValue().toString().substring(11, 16);
					String TmpEndingTimeString = EndingTimeSpinner.getModel().getValue().toString().substring(11, 16);
					
					LocalDateTime TmpStartingTime = LocalDateTime.parse(TmpStartingTimeString, DateTimeFormat.forPattern(HourFormat));
					LocalDateTime TmpEndingTime = LocalDateTime.parse(TmpEndingTimeString, DateTimeFormat.forPattern(HourFormat));
					
					LocalTime StartingTime = LocalTime.parse(TmpStartingTime.toLocalTime().toString(DateTimeFormat.forPattern(HourFormat)));
					LocalTime EndingTime = LocalTime.parse(TmpEndingTime.toLocalTime().toString(DateTimeFormat.forPattern(HourFormat)));
					
					SimpleDateFormat DateSpinnerModel = new SimpleDateFormat("yyyy-MM-dd");
					
					if(OnlineCheckBox.isSelected()) {
						NewMeeting = new OnlineMeeting(MeetingTitleTextField.getText(), LocalDate.parse(DateSpinnerModel.format(DateSpinner.getModel().getValue())), StartingTime, EndingTime, MainController.getProjectDAO().getProjectByName(ProjectComboBox.getSelectedItem().toString()), PlatformRoomComboBox.getSelectedItem().toString());
					} else {
						NewMeeting = new StandardMeeting(MeetingTitleTextField.getText(), LocalDate.parse(DateSpinnerModel.format(DateSpinner.getModel().getValue())), StartingTime, EndingTime, MainController.getProjectDAO().getProjectByName(ProjectComboBox.getSelectedItem().toString()), PlatformRoomComboBox.getSelectedItem().toString());
					}
					
					if(MainController.getMeetingDAO().insertMeeting(NewMeeting, OnlineCheckBox.isSelected())) {
						MainController.getMeetingInvitationDAO().insertMeetingInvitation(new MeetingInvitation(NewMeeting, InviteStatus.ACCEPTED), MainController.getMeetingDAO().getLatestMeetingID(), ConnectedUser.getEmployee().getCF());
					}
					
					dispose();
					
					MainController.displayMessageDialog("Success!", "Meeting successfully created!");
					
					MainController.getCoeusProjectManagerWindow().setMeetingsData();
					
				} else {
					MainController.displayMessageDialog("Error!", "There are errors in some of the fields. Make sure to choose an unoccupied time interval if the meeting is not online.");
				}			
			}
		});
		setDefaultLineBorderButtonDesign(NextButton);
		BottomPanel.add(NextButton);
		
		JPanel CentralPanel = new JPanel();
		setDefaultBackgroundDesign(CentralPanel);
		MainPanel.add(CentralPanel, BorderLayout.CENTER);
		
		JLabel MeetingInformationsLabel = new JLabel("Meeting informations");
		setDefaultHeaderTextLabel(MeetingInformationsLabel);
		
		MeetingTitleTextField = new JTextField();
		MeetingTitleTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				deleteFieldThreshold(MeetingTitleTextField);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				deleteFieldThreshold(MeetingTitleTextField);
			}
		});
		setDefaultJTextFieldDesign(MeetingTitleTextField);
		MeetingTitleTextField.setColumns(10);
		
		JLabel DateTextLabel = new JLabel("Date");
		setDefaultTextLabel(DateTextLabel);
		
		JLabel ExistingMeetingsTextLabel = new JLabel("Existing meetings");
		setDefaultTextLabel(ExistingMeetingsTextLabel);
		
		JLabel MeetingTitleTextLabel = new JLabel("Title");
		setDefaultTextLabel(MeetingTitleTextLabel);
		
		JLabel StartingTimeTextLabel = new JLabel("Starting Time");
		setDefaultTextLabel(StartingTimeTextLabel);
		
		JScrollPane MeetingTimesScrollPane = new JScrollPane();
		setDefaultJScrollPane(MeetingTimesScrollPane);
		MeetingTimesScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		DateSpinnerModel = new SimpleDateFormat("dd/MM/yyyy");
		Date earliestDate = LocalDateTime.now().minusDays(1).toDate();
		Date initDate = LocalDateTime.now().toDate();
		DateSpinner = new JSpinner(new SpinnerDateModel(initDate, earliestDate, null, Calendar.DAY_OF_YEAR));
		DateSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				SimpleDateFormat DateSpinnerModel = new SimpleDateFormat("yyyy-MM-dd");
				setMeetingsTimesData(DateSpinnerModel.format(DateSpinner.getModel().getValue()));
			}
		});
		DateSpinner.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				try {
					DateSpinner.commitEdit();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		DateSpinner.setEditor(new JSpinner.DateEditor(DateSpinner, DateSpinnerModel.toPattern()));
		setDefaultJSpinner(DateSpinner);
		
		HourFormat = "HH:mm";
		TimeSpinnerModel = new SimpleDateFormat(HourFormat);
		StartingTimeSpinner = new JSpinner(new SpinnerDateModel(LocalDateTime.parse("08:00", DateTimeFormat.forPattern(HourFormat)).toDate(), LocalDateTime.parse("07:59", DateTimeFormat.forPattern(HourFormat)).toDate(), LocalDateTime.parse("22:00", DateTimeFormat.forPattern(HourFormat)).toDate(), Calendar.HOUR_OF_DAY));
		StartingTimeSpinner.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				try {
					StartingTimeSpinner.commitEdit();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		StartingTimeSpinner.setEditor(new JSpinner.DateEditor(StartingTimeSpinner, TimeSpinnerModel.toPattern()));
		setDefaultJSpinner(StartingTimeSpinner);
		
		JLabel EndingTimeTextLabel = new JLabel("Ending Time");
		setDefaultTextLabel(EndingTimeTextLabel);
		
		EndingTimeSpinner = new JSpinner(new SpinnerDateModel(LocalDateTime.parse("08:00", DateTimeFormat.forPattern(HourFormat)).toDate(), LocalDateTime.parse("07:59", DateTimeFormat.forPattern(HourFormat)).toDate(), LocalDateTime.parse("22:00", DateTimeFormat.forPattern(HourFormat)).toDate(), Calendar.HOUR_OF_DAY));
		EndingTimeSpinner.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				try {
					EndingTimeSpinner.commitEdit();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		EndingTimeSpinner.setEditor(new JSpinner.DateEditor(EndingTimeSpinner, TimeSpinnerModel.toPattern()));
		setDefaultJSpinner(EndingTimeSpinner);
		
		JLabel OnlineTextLabel = new JLabel("Online?");
		setDefaultTextLabel(OnlineTextLabel);
		
		OnlineCheckBox = new JCheckBox("");
		OnlineCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(OnlineCheckBox.isSelected()) {
					setPlatformRoomData(true);
				} else {
					setPlatformRoomData(false);
				}
				
			}
		});
		OnlineCheckBox.setBackground(new Color(62, 100, 214));
		
		JLabel PlatformRoomTextLabel = new JLabel("Platform/Room");
		setDefaultTextLabel(PlatformRoomTextLabel);
		
		PlatformRoomComboBox = new JComboBox<String>();
		setDefaultJComboBox(PlatformRoomComboBox);
		
		JLabel ProjectTextLabel = new JLabel("Project");
		setDefaultTextLabel(ProjectTextLabel);
		
		ProjectComboBox = new JComboBox<String>();
		setDefaultJComboBox(ProjectComboBox);
		
		GroupLayout gl_CentralPanel = new GroupLayout(CentralPanel);
		gl_CentralPanel.setHorizontalGroup(
			gl_CentralPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(MeetingTimesScrollPane, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
						.addComponent(MeetingInformationsLabel)
						.addComponent(DateTextLabel)
						.addComponent(MeetingTitleTextLabel, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
						.addComponent(MeetingTitleTextField, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
						.addComponent(DateSpinner, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
						.addComponent(ExistingMeetingsTextLabel)
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addComponent(OnlineTextLabel, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(OnlineCheckBox))
						.addComponent(PlatformRoomTextLabel, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
						.addComponent(ProjectTextLabel, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_CentralPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(ProjectComboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(PlatformRoomComboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(Alignment.LEADING, gl_CentralPanel.createSequentialGroup()
								.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(StartingTimeSpinner)
									.addComponent(StartingTimeTextLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(35)
								.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(EndingTimeSpinner, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addComponent(EndingTimeTextLabel, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		gl_CentralPanel.setVerticalGroup(
			gl_CentralPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(MeetingInformationsLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MeetingTitleTextLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MeetingTitleTextField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(DateTextLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(DateSpinner, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ExistingMeetingsTextLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MeetingTimesScrollPane, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(StartingTimeTextLabel)
						.addComponent(EndingTimeTextLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(StartingTimeSpinner, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(EndingTimeSpinner, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(OnlineCheckBox, 0, 0, Short.MAX_VALUE)
						.addComponent(OnlineTextLabel, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PlatformRoomTextLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PlatformRoomComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ProjectTextLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ProjectComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(55))
		);
		
		MeetingTimesTable = new JTable();
		MeetingTimesTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Starting Time", "Ending Time", "Platform/Room"
			}
		) {
			Class[] columnTypes = new Class[] {
				LocalTime.class, LocalTime.class, String.class
			};
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		MeetingTimesTable.getColumnModel().getColumn(0).setResizable(false);
		MeetingTimesTable.getColumnModel().getColumn(1).setResizable(false);
		MeetingTimesTable.getColumnModel().getColumn(2).setResizable(false);
		MeetingTimesScrollPane.setViewportView(MeetingTimesTable);
		
		
		CentralPanel.setLayout(gl_CentralPanel);
		
	}
	
	public boolean checkFieldsValidity() {
		
		String TmpStartingTimeString = StartingTimeSpinner.getModel().getValue().toString().substring(11, 16);
		String TmpEndingTimeString = EndingTimeSpinner.getModel().getValue().toString().substring(11, 16);
		
		LocalDateTime TmpStartingTime = LocalDateTime.parse(TmpStartingTimeString, DateTimeFormat.forPattern(HourFormat));
		LocalDateTime TmpEndingTime = LocalDateTime.parse(TmpEndingTimeString, DateTimeFormat.forPattern(HourFormat));
		
		LocalTime StartingTime = LocalTime.parse(TmpStartingTime.toLocalTime().toString(DateTimeFormat.forPattern(HourFormat)));
		LocalTime EndingTime = LocalTime.parse(TmpEndingTime.toLocalTime().toString(DateTimeFormat.forPattern(HourFormat)));
		
		boolean TimeIntervalCheck;
		
		if(OnlineCheckBox.isSelected()) {
			TimeIntervalCheck = true;
		} else {
			TimeIntervalCheck = checkTimeIntervalValidity(StartingTime, EndingTime);
		}
		
		if(MeetingTitleTextField.getText().isBlank() || StartingTime.isAfter(EndingTime) || StartingTime.compareTo(EndingTime) == 0 || !TimeIntervalCheck) {
			
			if(MeetingTitleTextField.getText().isBlank()) {
				MeetingTitleTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.red));
			} else {
				MeetingTitleTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
			}
			
			if(StartingTime.isAfter(EndingTime) || StartingTime.compareTo(EndingTime) == 0 || !TimeIntervalCheck) {
				StartingTimeSpinner.setBorder(new MatteBorder(1, 1, 1, 1, Color.red));
				EndingTimeSpinner.setBorder(new MatteBorder(1, 1, 1, 1, Color.red));
			} else {
				StartingTimeSpinner.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
				EndingTimeSpinner.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
			}
			
			return false;
			
		}
		
		return true;
		
	}
	
	public boolean checkTimeIntervalValidity(LocalTime NewStartingTime, LocalTime NewEndingTime) {
		
//		SimpleDateFormat DateSpinnerModel = new SimpleDateFormat("MM/dd/yyyy");
		
		SimpleDateFormat DateSpinnerModel = new SimpleDateFormat("yyyy-MM-dd");
		
//		System.out.println(DateSpinnerModel.format(DateSpinner.getModel().getValue()) + " + " + PlatformRoomComboBox.getSelectedItem().toString());
		
		ArrayList<Meeting> MeetingTimesList = MainController.getMeetingDAO().getAllMeetingsByDateAndRoom(DateSpinnerModel.format(DateSpinner.getModel().getValue()), PlatformRoomComboBox.getSelectedItem().toString());
		ArrayList<LocalTime> ExistingStartingTimeList = new ArrayList<LocalTime>(MeetingTimesList.size());
		ArrayList<LocalTime> ExistingEndingTimeList = new ArrayList<LocalTime>(MeetingTimesList.size());
		
		
		for(int i = 0; i < MeetingTimesList.size(); i++) {
			ExistingStartingTimeList.add(MeetingTimesList.get(i).getStartingTime());
			ExistingEndingTimeList.add(MeetingTimesList.get(i).getEndingTime());
		}
		
		if(NewStartingTime.isAfter(NewEndingTime)) {
			return false;
		}
		
		if(ExistingStartingTimeList.size() != 0 && ExistingEndingTimeList.size() != 0) {
			
			LocalTime MinimumStartingTime = LocalTime.parse("07:59:00");
			LocalTime MaximumEndingTime = LocalTime.parse("22:01:00");
			
//			LocalTime NearestEndingTime = LocalTime.parse("07:59:00");
//			LocalTime NearestStartingTime = LocalTime.parse("22:01:00");
			
			boolean check = false;
			
			for(int i = 0, j = 0; i < ExistingStartingTimeList.size(); i++) {

				if(i == 0 && j == ExistingEndingTimeList.size() - 1) {
					
//					System.out.println(NewStartingTime + " > " + MinimumStartingTime + " && " + NewEndingTime + " < " + ExistingStartingTimeList.get(i));
//					System.out.println(NewStartingTime.isAfter(MinimumStartingTime) && NewEndingTime.isBefore(ExistingStartingTimeList.get(i)));
//					System.out.println(NewStartingTime + " > " + ExistingEndingTimeList.get(j) + " && " + NewEndingTime + " < " + MaximumEndingTime);
//					System.out.println(NewStartingTime.isAfter(ExistingEndingTimeList.get(j)) && NewEndingTime.isBefore(MaximumEndingTime));
					
					
					if(NewStartingTime.isAfter(MinimumStartingTime) && NewEndingTime.isBefore(ExistingStartingTimeList.get(i)) || NewStartingTime.isAfter(ExistingEndingTimeList.get(j)) && NewEndingTime.isBefore(MaximumEndingTime)) {
						check = true;
						break;
					}
					
				} else if(i == 0) {
					
//					System.out.println(NewStartingTime + " > " + MinimumStartingTime + " && " + NewEndingTime + " < " + ExistingStartingTimeList.get(i));
//					System.out.println(NewStartingTime.isAfter(MinimumStartingTime) && NewEndingTime.isBefore(ExistingStartingTimeList.get(i)));
					
					if(NewStartingTime.isAfter(MinimumStartingTime) && NewEndingTime.isBefore(ExistingStartingTimeList.get(i))) {
						check = true;
						break;
					}
				} else if(j == ExistingEndingTimeList.size() - 1) {

//					System.out.println(NewStartingTime + " > " + ExistingEndingTimeList.get(j) + " && " + NewEndingTime + " < " + MaximumEndingTime);
//					System.out.println(NewStartingTime.isAfter(ExistingEndingTimeList.get(j)) && NewEndingTime.isBefore(MaximumEndingTime));
					
					if(NewStartingTime.isAfter(ExistingEndingTimeList.get(j)) && NewEndingTime.isBefore(MaximumEndingTime)) {
						check = true;
						break;
					}
				} else {
					
//					System.out.println(NewStartingTime + " > " + ExistingEndingTimeList.get(j) + " && " + NewEndingTime + " < " + ExistingStartingTimeList.get(i));
//					System.out.println(NewStartingTime.isAfter(ExistingEndingTimeList.get(j)) && NewEndingTime.isBefore(ExistingStartingTimeList.get(i)));
					
					if(NewStartingTime.isAfter(ExistingEndingTimeList.get(j)) && NewEndingTime.isBefore(ExistingStartingTimeList.get(i))) {
						check = true;
						break;
					}
					j++;
				}
			}
			
			
			if(check) {
				return true;
			} else {
				return false;
			}
			
			
		} else {
			return true;
		}
		
	}

	public void setMeetingsTimesData(String Date) {
		
		ArrayList<Meeting> MeetingTimesList = MainController.getMeetingDAO().getAllMeetingsByDate(Date);
		DefaultTableModel Model = (DefaultTableModel) MeetingTimesTable.getModel();
		
//		for(int i = 0; i < MeetingTimesList.size(); i++) {
//			System.out.println(MeetingTimesList.get(i).getStartingTime() + " " + MeetingTimesList.get(i).getEndingTime());
//		}
		
		if(MeetingTimesList != null) {
			
			if(Model.getRowCount() != 0) {
				Model.setRowCount(0);
			}
			
			for(int i = 0; i < MeetingTimesList.size(); i++) {
				
				OnlineMeeting OnlineMeeting;
				StandardMeeting StandardMeeting;
				
				if(MeetingTimesList.get(i).getClass().getSimpleName().equals("OnlineMeeting")) {
					OnlineMeeting = (OnlineMeeting)MeetingTimesList.get(i);
					
//					System.out.println(OnlineMeeting.getTitle() + " -> " + OnlineMeeting.getStartingTime() + " " + OnlineMeeting.getEndingTime());
					
					Model.addRow(new Object[]{ OnlineMeeting.getStartingTime(), OnlineMeeting.getEndingTime(), OnlineMeeting.getPlatform() });
				} else {
					StandardMeeting = (StandardMeeting)MeetingTimesList.get(i);
					
//					System.out.println(StandardMeeting.getTitle() + " -> " + StandardMeeting.getStartingTime() + " " + StandardMeeting.getEndingTime());
					
					Model.addRow(new Object[]{ StandardMeeting.getStartingTime(), StandardMeeting.getEndingTime(), StandardMeeting.getRoom() });
				}
				
			}
			
		}
		
	}
	
	public void setPlatformRoomData(boolean Online) {
		
		PlatformRoomComboBox.setModel(new DefaultComboBoxModel<String>(MainController.arrayListToStringArray(MainController.getMeetingDAO().getAllPlatformsRooms(Online))));
		
	}
	
	public void setProjectsData() {
		
		ProjectComboBox.setModel(new DefaultComboBoxModel<String>(MainController.arrayListToStringArray(MainController.getProjectDAO().getAllPersonalOngoingProjectNamesByUserCF(ConnectedUser.getEmployee().getCF()))));
		
	}
	
	public void setData() {
		
		setPlatformRoomData(false);
		
		Date NowDate = LocalDateTime.now().toDate();
		
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		setMeetingsTimesData(DateFormat.format(NowDate));
		
		setProjectsData();
		
	}
}
