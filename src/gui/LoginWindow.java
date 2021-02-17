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

public class LoginWindow extends GenericFrame {

	private Controller MainController = Controller.getIstance();
	
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
		LeftPanel.setBackground(new Color(153, 51, 51));
		MainPanel.add(LeftPanel);
		LeftPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel AuthImageLabel = new JLabel("");
		AuthImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AuthImageLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		AuthImageLabel.setIcon(new ImageIcon(LoginWindow.class.getResource("/loginAuth.png")));
		LeftPanel.add(AuthImageLabel, BorderLayout.CENTER);
		
		JLabel AuthenticationLabel = new JLabel("Authentication");
		AuthenticationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		AuthenticationLabel.setForeground(Color.WHITE);
		AuthenticationLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
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
		SignInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Add StandardUserRegistration call from MainController
				try {
					MainController.employeeRegistration(1);
					dispose();
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
				checkPassword(EmailTextField.getText(), MainController.encrypt(PasswordTextField.getPassword()));
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
		PasswordTextField.setFont(new Font("Roboto", Font.PLAIN, 14));
		PasswordTextField.setPreferredSize(new Dimension(200, 30));
		UserInformationsPanel.add(PasswordTextField);
	}
	
	public boolean checkPassword(String Email, String Password) {
		
		int UserID = MainController.getEmployeeDAO().getUserIDByEmail(Email);
		
		if(UserID == 0) {
			MainController.displayMessageDialog("Error!", "There is no user associated with this email.");
			return false;
		} else {
			if(Password.equals(MainController.getUserDAO().getPasswordByUserID(UserID))) {
				// TODO Remove MessageDialog and go directly to software main page
				MainController.displayMessageDialog("Success!", "Login successful!");
				return true;
			} else {
				MainController.displayMessageDialog("Error!", "Incorrect password.");
				return false;
			}
		}
		
	}
	
	private Image getImage(String filename) {
	    try {
	        return ImageIO.read(getClass().getResourceAsStream(
	                "/" + filename));
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}
