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

public class RegistrationWindow extends JFrame {

	private JPanel contentPane;
	private GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int width = gd.getDisplayMode().getWidth();
	private int height = gd.getDisplayMode().getHeight();
	private GUIController GuiController = GUIController.getIstance();
	private JTextField textField_2;
	private JTextField FirstNameTextField;
	private JTextField LastNameTextField;
	private JTextField textField_3;
	
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

	
	public RegistrationWindow(String[] WorldStates) throws IntervalException {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(width/2 - 340, height/2 - 450, 680, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel TopPanel = new JPanel();
		contentPane.add(TopPanel, BorderLayout.NORTH);
		TopPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		TopPanel.add(panel_3, BorderLayout.WEST);
		
		JLabel lblNewLabel = new JLabel("Coeus");
		lblNewLabel.setForeground(new Color(153, 51, 51));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 70));
		panel_3.add(lblNewLabel);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		TopPanel.add(panel_4, BorderLayout.EAST);
		
		JLabel ExitButton = new JLabel("X");
		ExitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ExitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		ExitButton.setPreferredSize(new Dimension(35, 35));
		ExitButton.setOpaque(true);
		ExitButton.setHorizontalAlignment(SwingConstants.CENTER);
		ExitButton.setForeground(Color.WHITE);
		ExitButton.setFont(new Font("Roboto Bk", Font.BOLD, 22));
		ExitButton.setBackground(new Color(153, 51, 51));
		panel_4.add(ExitButton);
		
		JPanel BottomPanel = new JPanel();
		contentPane.add(BottomPanel, BorderLayout.SOUTH);
		
		JButton btnAvanti = new JButton("Next");
		btnAvanti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAvanti.setPreferredSize(new Dimension(120, 30));
		btnAvanti.setForeground(new Color(153, 51, 51));
		btnAvanti.setFont(new Font("Roboto", Font.PLAIN, 26));
		btnAvanti.setContentAreaFilled(false);
		btnAvanti.setBorder(new LineBorder(new Color(153, 51, 51), 2, true));
		btnAvanti.setBackground(new Color(153, 51, 51));
		BottomPanel.add(btnAvanti);
		
		JPanel CentralPanel = new JPanel();
		CentralPanel.setBorder(null);
		CentralPanel.setBackground(new Color(166, 111, 111));
		contentPane.add(CentralPanel, BorderLayout.EAST);
		
		JLabel FullNameLabel = new JLabel("Full Name");
		FullNameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		FirstNameTextField = new JTextField();
		FirstNameTextField.setColumns(10);
		FirstNameTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
		
		JLabel FirstNameLabel = new JLabel("First Name");
		FirstNameLabel.setForeground(new Color(122, 72, 72));
		
		JLabel LastNameLabel = new JLabel("Last Name");
		LastNameLabel.setForeground(new Color(122, 72, 72));
		
		JLabel DetailsLabel = new JLabel("Details");
		DetailsLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		LastNameTextField = new JTextField();
		LastNameTextField.setColumns(10);
		LastNameTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
		
		JLabel DataPrivacyLabel = new JLabel("None of these informations will be stored, the data will only be used to calculate your CF.");
		DataPrivacyLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		DataPrivacyLabel.setForeground(new Color(122, 72, 72));
		
		JLabel BirthdateLabel = new JLabel("Birthdate");
		BirthdateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel StateOfBirthLabel = new JLabel("State of birth");
		StateOfBirthLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel RegionOfBirthLabel = new JLabel("Region of birth");
		RegionOfBirthLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel CityOfBirthLabel = new JLabel("City of birth");
		CityOfBirthLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JComboBox DayJComboBox = new JComboBox();
		DayJComboBox.setModel(new DefaultComboBoxModel(GuiController.intervalToStringArray(1, 31)));
		

		JComboBox YearJComboBox = new JComboBox();

		YearJComboBox.setModel(new DefaultComboBoxModel(GuiController.intervalToStringArray(Calendar.getInstance().get(Calendar.YEAR) - 100, Calendar.getInstance().get(Calendar.YEAR) - 19)));
		
		JComboBox MonthJComboBox = new JComboBox();
		MonthJComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GuiController.updateJComboBoxDay(DayJComboBox, (String)MonthJComboBox.getSelectedItem(), (String)YearJComboBox.getSelectedItem());
					
				} catch (IntervalException e1) {
					e1.printStackTrace();
				}
			}
		});
		MonthJComboBox.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "Decemeber"}));
		
		YearJComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GuiController.updateJComboBoxDay(DayJComboBox, (String)MonthJComboBox.getSelectedItem(), (String)YearJComboBox.getSelectedItem());
					
				} catch (IntervalException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JLabel MonthLabel = new JLabel("Month");
		MonthLabel.setForeground(new Color(122, 72, 72));
		
		JLabel DayLabel = new JLabel("Day");
		DayLabel.setForeground(new Color(122, 72, 72));
		
		JLabel YearLabel = new JLabel("Year");
		YearLabel.setForeground(new Color(122, 72, 72));
		
		JComboBox StateOfBirthJComboBox = new JComboBox();
		StateOfBirthJComboBox.setModel(new DefaultComboBoxModel(WorldStates));
		
		JComboBox RegionJComboBox = new JComboBox();
		
		JComboBox CityOfBirthJComboBox = new JComboBox();
		
		JSeparator BottomSeparator = new JSeparator();
		BottomSeparator.setBackground(Color.DARK_GRAY);
		BottomSeparator.setForeground(Color.DARK_GRAY);
		
		JSeparator TopSeparator = new JSeparator();
		TopSeparator.setForeground(Color.DARK_GRAY);
		TopSeparator.setBackground(Color.DARK_GRAY);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Additional informations");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
		
		JLabel lblNewLabel_2_4_2 = new JLabel("Automatically calculated CF");
		lblNewLabel_2_4_2.setForeground(new Color(122, 72, 72));
		
		JLabel lblNewLabel_1_2_1_2 = new JLabel("CF");
		lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1_2_1_2_1 = new JLabel("Timezone");
		lblNewLabel_1_2_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JComboBox comboBox_1_1_1_1_1 = new JComboBox();
		comboBox_1_1_1_1_1.setModel(new DefaultComboBoxModel(new String[] {"GMT+00:00", "GMT+01:00", "GMT+02:00", "GMT+03:00", "GMT+04:00", "GMT+05:00", "GMT+07:00", "GMT+08:00", "GMT-01:00", "GMT-02:00", "GMT-03:00", "GMT-04:00", "GMT-05:00", "GMT-07:00", "GMT-08:00"}));
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
								.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_2_1_2, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_CentralPanel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblNewLabel_2_4_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(textField_3, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
									.addGroup(Alignment.LEADING, gl_CentralPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(comboBox_1_1_1_1_1, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_1_2_1_2_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))))
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
					.addComponent(lblNewLabel_1_1_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1_2_1_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2_4_2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1_2_1_2_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(132, Short.MAX_VALUE))
		);
		CentralPanel.setLayout(gl_CentralPanel);
	}
}
