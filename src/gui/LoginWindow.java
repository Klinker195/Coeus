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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow extends GenericFrame {

	private Controller MainController = Controller.getIstance();
	
	private JPanel MainPanel;
	
	private JTextField UsernameJTextField;
	private JPasswordField PasswordField;
	
	private JButton ExitButton;
	private JButton LoginButton;
	
	
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
		
		JButton LoginButton = new JButton("Login");
		setDefaultLineBorderButtonDesign(LoginButton);
		LoginButtonPanel.add(LoginButton);
		
		JPanel SignUpPanel = new JPanel();
		RightPanel.add(SignUpPanel, BorderLayout.CENTER);
		SignUpPanel.setLayout(new BorderLayout(10, 10));
		
		JLabel SignUpLabel = new JLabel("Sign Up");
		SignUpLabel.setFont(new Font("Roboto Lt", Font.PLAIN, 34));
		SignUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SignUpPanel.add(SignUpLabel, BorderLayout.NORTH);
		
		JPanel UserInformationsPanel = new JPanel();
		FlowLayout fl_UserInformationsPanel = (FlowLayout) UserInformationsPanel.getLayout();
		fl_UserInformationsPanel.setVgap(10);
		fl_UserInformationsPanel.setHgap(10);
		SignUpPanel.add(UserInformationsPanel, BorderLayout.CENTER);
		
		JLabel UsernameLabel = new JLabel("Username");
		UsernameLabel.setForeground(Color.DARK_GRAY);
		UsernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		UsernameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		UsernameLabel.setPreferredSize(new Dimension(200, 20));
		UserInformationsPanel.add(UsernameLabel);
		
		UsernameJTextField = new JTextField();
		UsernameJTextField.setFont(new Font("Roboto", Font.PLAIN, 14));
		UsernameJTextField.setPreferredSize(new Dimension(200, 30));
		UserInformationsPanel.add(UsernameJTextField);
		
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setForeground(Color.DARK_GRAY);
		PasswordLabel.setPreferredSize(new Dimension(200, 20));
		PasswordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		PasswordLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		UserInformationsPanel.add(PasswordLabel);
		
		PasswordField = new JPasswordField();
		PasswordField.setFont(new Font("Roboto", Font.PLAIN, 14));
		PasswordField.setPreferredSize(new Dimension(200, 30));
		UserInformationsPanel.add(PasswordField);
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
