package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.Controller;
import exceptions.IntervalException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginWindow extends GenericFrame {

	private Controller MainController = Controller.getInstance();
	
	private JPanel MainPanel;
	
	private JTextField EmailTextField;
	private JPasswordField PasswordTextField;
	
	private JButton ExitButton;
	private JButton LoginButton;
	private JButton SignInButton;
	
	
	public LoginWindow(int DisplayWidth, int DisplayHeight) {
		setDefaultDesign(this);
		setBounds(DisplayWidth/2 - 300, DisplayHeight/2 - 200, 600, 400);
		MainPanel = new JPanel();
		setDefaultBorderDesign(MainPanel);
		setContentPane(MainPanel);
		MainPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel LeftPanel = new JPanel();
		LeftPanel.setBackground(new Color(62, 100, 214));
		MainPanel.add(LeftPanel);
		LeftPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel AuthImageLabel = new JLabel("");
		AuthImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AuthImageLabel.setFont(new Font("Roboto", Font.PLAIN, 11));
		AuthImageLabel.setIcon(new ImageIcon(LoginWindow.class.getResource("/loginAuth.png")));
		LeftPanel.add(AuthImageLabel, BorderLayout.CENTER);
		
		JLabel AuthenticationLabel = new JLabel("Authentication");
		AuthenticationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AuthenticationLabel.setForeground(Color.WHITE);
		AuthenticationLabel.setFont(new Font("Roboto", Font.PLAIN, 24));
		LeftPanel.add(AuthenticationLabel, BorderLayout.SOUTH);
		
		JPanel RightPanel = new JPanel();
		MainPanel.add(RightPanel);
		RightPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel TopRightBarPanel = new JPanel();
		FlowLayout fl_TopRightBarPanel = (FlowLayout) TopRightBarPanel.getLayout();
		fl_TopRightBarPanel.setAlignment(FlowLayout.RIGHT);
		fl_TopRightBarPanel.setHgap(0);
		fl_TopRightBarPanel.setVgap(0);
		RightPanel.add(TopRightBarPanel, BorderLayout.NORTH);
		
		ExitButton = new JButton();
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		});
		setDefaultExitButtonDesign(ExitButton);
		TopRightBarPanel.add(ExitButton);
		
		JPanel LoginButtonPanel = new JPanel();
		FlowLayout fl_LoginButtonPanel = (FlowLayout) LoginButtonPanel.getLayout();
		fl_LoginButtonPanel.setVgap(20);
		fl_LoginButtonPanel.setHgap(20);
		RightPanel.add(LoginButtonPanel, BorderLayout.SOUTH);
		
		SignInButton = new JButton("Sign In");
		SignInButton.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				try {
					MainController.employeeRegistration(1);
				} catch (IntervalException e1) {
					e1.printStackTrace();
				}
			}
		});
		setDefaultLineBorderButtonDesign(SignInButton);
		LoginButtonPanel.add(SignInButton);
		
		LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Add login check using Employee and User DAOs
				if(!EmailTextField.getText().isBlank() && !(PasswordTextField.getPassword().length == 0)) {
					checkPassword(EmailTextField.getText(), MainController.encrypt(PasswordTextField.getPassword()));
				} else {
					MainController.displayMessageDialog("Error!", "There are some missing fields.");
				}
			}
		});
		setDefaultLineBorderButtonDesign(LoginButton);
		LoginButtonPanel.add(LoginButton);
		
		JPanel SignUpPanel = new JPanel();
		RightPanel.add(SignUpPanel, BorderLayout.CENTER);
		SignUpPanel.setLayout(new BorderLayout(10, 10));
		
		JLabel LoginLabel = new JLabel("Log In");
		LoginLabel.setFont(new Font("Roboto Lt", Font.PLAIN, 34));
		LoginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SignUpPanel.add(LoginLabel, BorderLayout.NORTH);
		
		JPanel UserInformationsPanel = new JPanel();
		FlowLayout fl_UserInformationsPanel = (FlowLayout) UserInformationsPanel.getLayout();
		fl_UserInformationsPanel.setVgap(10);
		fl_UserInformationsPanel.setHgap(10);
		SignUpPanel.add(UserInformationsPanel, BorderLayout.CENTER);
		
		JLabel EmailLabel = new JLabel("Email");
		EmailLabel.setForeground(Color.DARK_GRAY);
		EmailLabel.setHorizontalAlignment(SwingConstants.LEFT);
		EmailLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		EmailLabel.setPreferredSize(new Dimension(200, 20));
		UserInformationsPanel.add(EmailLabel);
		
		EmailTextField = new JTextField();
		EmailTextField.setFont(new Font("Roboto", Font.PLAIN, 14));
		EmailTextField.setPreferredSize(new Dimension(200, 30));
		UserInformationsPanel.add(EmailTextField);
		
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setForeground(Color.DARK_GRAY);
		PasswordLabel.setPreferredSize(new Dimension(200, 20));
		PasswordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		PasswordLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		UserInformationsPanel.add(PasswordLabel);
		
		PasswordTextField = new JPasswordField();
		PasswordTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(!EmailTextField.getText().isBlank() && !(PasswordTextField.getPassword().length == 0)) {
						checkPassword(EmailTextField.getText(), MainController.encrypt(PasswordTextField.getPassword()));
					} else {
						MainController.displayMessageDialog("Error!", "There are some missing fields.");
					}
				}
			}
		});
		PasswordTextField.setFont(new Font("Roboto", Font.PLAIN, 14));
		PasswordTextField.setPreferredSize(new Dimension(200, 30));
		UserInformationsPanel.add(PasswordTextField);
	}
	
	public boolean checkPassword(String Email, String Password) {
		
		String EmployeeCF = MainController.getEmployeeDAO().getCFByEmail(Email);
		
		if(EmployeeCF == null) {
			MainController.displayMessageDialog("Error!", "There is no user associated with this email.");
			return false;
		} else {
			if(Password.equals(MainController.getUserDAO().getPasswordByEmployeeCF(EmployeeCF))) {
				// TODO Remove MessageDialog and go directly to software main page
				dispose();
				
				MainController.getLoadingSplashWindow().setVisible(true);
				
				if(MainController.getUserDAO().isFounder(EmployeeCF)) {
					MainController.openDashboard(MainController.getUserDAO().getUserByCF(EmployeeCF), 0);
				} else if(MainController.getUserDAO().isAdmin(EmployeeCF)) {
					MainController.openDashboard(MainController.getUserDAO().getUserByCF(EmployeeCF), 2);
				} else {
					MainController.openDashboard(MainController.getUserDAO().getUserByCF(EmployeeCF), 1);
				}
				
				
				return true;
			} else {
				MainController.displayMessageDialog("Error!", "Incorrect password.");
				return false;
			}
		}
		
	}

}
