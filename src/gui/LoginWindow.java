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

public class LoginWindow extends JFrame {


	private JPanel contentPane;
	private JTextField UsernameTextField;
	private JPasswordField passwordField;
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int width = gd.getDisplayMode().getWidth();
	int height = gd.getDisplayMode().getHeight();
	GUIController GuiController = GUIController.getIstance();
	

	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setBounds(width/2 - 300, height/2 - 200, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 102, 204));
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setIcon(new ImageIcon(LoginWindow.class.getResource("/loginAuth.png")));
		panel.add(lblNewLabel, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Authentication");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(lblNewLabel_1, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		flowLayout_1.setHgap(0);
		flowLayout_1.setVgap(0);
		panel_1.add(panel_2, BorderLayout.NORTH);
		
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
		ExitButton.setBackground(new Color(102, 102, 204));
		ExitButton.setHorizontalAlignment(SwingConstants.CENTER);
		ExitButton.setFont(new Font("Roboto Bk", Font.BOLD, 22));
		ExitButton.setForeground(new Color(255, 255, 255));
		ExitButton.setPreferredSize(new Dimension(35, 35));
		ExitButton.setOpaque(true);
		panel_2.add(ExitButton);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setVgap(20);
		flowLayout_3.setHgap(20);
		panel_1.add(panel_3, BorderLayout.SOUTH);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.setForeground(new Color(102, 102, 204));
		LoginButton.setFont(new Font("Roboto", Font.PLAIN, 26));
		LoginButton.setBackground(new Color(102, 102, 204));
		LoginButton.setBorder(new LineBorder(new Color(102, 102, 204), 2, true));
		LoginButton.setContentAreaFilled(false);
		LoginButton.setPreferredSize(new Dimension(120, 30));
		panel_3.add(LoginButton);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(10, 10));
		
		JLabel SignUpLabel = new JLabel("Sign Up");
		SignUpLabel.setFont(new Font("Roboto Lt", Font.PLAIN, 34));
		SignUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(SignUpLabel, BorderLayout.NORTH);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
		flowLayout_2.setVgap(10);
		flowLayout_2.setHgap(10);
		panel_4.add(panel_5, BorderLayout.CENTER);
		
		JLabel UsernameLabel = new JLabel("Username");
		UsernameLabel.setForeground(Color.DARK_GRAY);
		UsernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		UsernameLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		UsernameLabel.setPreferredSize(new Dimension(200, 20));
		panel_5.add(UsernameLabel);
		
		UsernameTextField = new JTextField();
		UsernameTextField.setFont(new Font("Roboto", Font.PLAIN, 14));
		UsernameTextField.setPreferredSize(new Dimension(200, 30));
		panel_5.add(UsernameTextField);
		
		JLabel PasswordLabel = new JLabel("Password");
		PasswordLabel.setForeground(Color.DARK_GRAY);
		PasswordLabel.setPreferredSize(new Dimension(200, 20));
		PasswordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		PasswordLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		panel_5.add(PasswordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Roboto", Font.PLAIN, 14));
		passwordField.setPreferredSize(new Dimension(200, 30));
		panel_5.add(passwordField);
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
