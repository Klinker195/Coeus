package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entities.Employee;
import entities.User;
import entities.UserFounder;
import entities.UserStandard;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;

public class RegistrationAuthDataWindow extends GenericDialog {

	private Controller MainController = Controller.getInstance();
	
	private JPanel MainPanel;
	
	private JButton NextButton;
	private JButton ExitButton;
	private JPasswordField ConfirmationPasswordTextField;
	private JPasswordField PasswordTextField;

	public RegistrationAuthDataWindow(Employee NewEmployee, boolean IsFounder, int DisplayWidth, int DisplayHeight) {
		setBounds(DisplayWidth/2 - 340, DisplayHeight/2 - 188, 680, 376);
		setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		setType(Type.UTILITY);
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
		CoeusLabel.setForeground(new Color(11, 28, 82));
		CoeusLabel.setFont(new Font("Roboto Bk", Font.PLAIN, 70));
		TitlePanel.add(CoeusLabel);
		
		JPanel RightPanel = new JPanel();
		FlowLayout fl_RightPanel = (FlowLayout) RightPanel.getLayout();
		fl_RightPanel.setVgap(0);
		fl_RightPanel.setHgap(0);
		TopPanel.add(RightPanel, BorderLayout.EAST);
		
		JPanel CentralPanel = new JPanel();
		CentralPanel.setBorder(null);
		setDefaultBackgroundDesign(CentralPanel);
		MainPanel.add(CentralPanel, BorderLayout.CENTER);
		
		JLabel SetAPasswordLabel = new JLabel("Set a password for your account");
		setDefaultHeaderTextLabel(SetAPasswordLabel);
		
		JLabel PasswordLabel = new JLabel("Password");
		setDefaultBackgroundTextLabel(PasswordLabel);
		
		JLabel PasswordConfirmationLabel = new JLabel("Password confirmation");
		setDefaultBackgroundTextLabel(PasswordConfirmationLabel);
		
		ConfirmationPasswordTextField = new JPasswordField();
		setDefaultJPasswordFieldDesign(ConfirmationPasswordTextField);
		
		PasswordTextField = new JPasswordField();
		setDefaultJPasswordFieldDesign(PasswordTextField);
		
		JLabel AuthInformationsDescriptionLabel = new JLabel("The password, along with your email address, grants you access to the account.");
		setDefaultBackgroundTextLabel(AuthInformationsDescriptionLabel);
		
		JLabel PasswordInformationsLabel = new JLabel("Admitted special characters for password: \" ! # % & ' * + - / = ? ^ _ . { | \"");
		setDefaultBackgroundTextLabel(PasswordInformationsLabel);
		
		GroupLayout gl_CentralPanel = new GroupLayout(CentralPanel);
		gl_CentralPanel.setHorizontalGroup(
			gl_CentralPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(SetAPasswordLabel)
						.addComponent(PasswordInformationsLabel, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(PasswordConfirmationLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(ConfirmationPasswordTextField)
							.addComponent(PasswordTextField)
							.addComponent(PasswordLabel, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))
						.addComponent(AuthInformationsDescriptionLabel, GroupLayout.PREFERRED_SIZE, 553, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_CentralPanel.setVerticalGroup(
			gl_CentralPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(SetAPasswordLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(AuthInformationsDescriptionLabel)
					.addGap(4)
					.addComponent(PasswordInformationsLabel)
					.addGap(18)
					.addComponent(PasswordTextField, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PasswordLabel)
					.addGap(18)
					.addComponent(ConfirmationPasswordTextField, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PasswordConfirmationLabel)
					.addContainerGap(152, Short.MAX_VALUE))
		);
		CentralPanel.setLayout(gl_CentralPanel);

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
				
				
				if (!checkAuthRegistrationFieldsValidity(PasswordTextField, ConfirmationPasswordTextField)){
					MainController.displayMessageDialog("Error!", "Error: There are errors in some of the fields!");
				} else {
					if(MainController.getConfirmationDialogValue()) {
						if(IsFounder) {
							User NewFounder = new UserFounder(NewEmployee, String.copyValueOf(PasswordTextField.getPassword()));
							MainController.getUserDAO().insertUserFounder(NewFounder);
							dispose();
							MainController.displayMessageDialog("Success!", "Your account has been successfully created!");
							MainController.start();
						} else {
							User NewUser = new UserStandard(NewEmployee, String.copyValueOf(PasswordTextField.getPassword()));
							MainController.getUserDAO().insertStandardUser(NewUser);
							dispose();
							MainController.displayMessageDialog("Success!", "Your account has been successfully created!");
						}
					}
				}
				
			}
		});
		setDefaultLineBorderButtonDesign(NextButton);
		BottomPanel.add(NextButton);
	}
}
