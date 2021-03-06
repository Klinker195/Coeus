package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import controller.Controller;
import exceptions.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomeWindow extends GenericFrame {

	private Controller MainController = Controller.getInstance();
	
	private JPanel MainPanel;
	
	private JButton ExitButton;
	private JButton StartUsingCoeusButton;
	

	public WelcomeWindow(int DisplayWidth, int DisplayHeight) {
		setDefaultDesign(this);
		setBounds(DisplayWidth/2 - 275, DisplayHeight/2 - 190, 550, 380);
		MainPanel = new JPanel();
		setDefaultBorderDesign(MainPanel);
		MainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(MainPanel);
		
		JPanel CentralPanel = new JPanel();
		MainPanel.add(CentralPanel, BorderLayout.CENTER);
		CentralPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel WelcomeToLabel = new JLabel("Welcome to\r\n");
		WelcomeToLabel.setForeground(new Color(11, 28, 82));
		WelcomeToLabel.setFont(new Font("Roboto", Font.PLAIN, 80));
		WelcomeToLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CentralPanel.add(WelcomeToLabel, BorderLayout.NORTH);
		
		JPanel ButtonPanel = new JPanel();
		CentralPanel.add(ButtonPanel, BorderLayout.SOUTH);
		
		StartUsingCoeusButton = new JButton("Start using Coeus");
		setDefaultLineBorderButtonDesign(StartUsingCoeusButton);
		StartUsingCoeusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					MainController.employeeRegistration(0);
				} catch (IntervalException e1) {
					e1.printStackTrace();
				}
			}
		});
		ButtonPanel.add(StartUsingCoeusButton);
		
		JLabel CoeusLabel = new JLabel("Coeus\r\n");
		CoeusLabel.setVerticalAlignment(SwingConstants.TOP);
		CoeusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CoeusLabel.setForeground(new Color(11, 28, 82));
		CoeusLabel.setFont(new Font("Roboto Bk", Font.PLAIN, 80));
		CentralPanel.add(CoeusLabel, BorderLayout.CENTER);
		
		JPanel TopBarPanel = new JPanel();
		FlowLayout fl_TopBarPanel = (FlowLayout) TopBarPanel.getLayout();
		fl_TopBarPanel.setAlignment(FlowLayout.RIGHT);
		fl_TopBarPanel.setHgap(0);
		fl_TopBarPanel.setVgap(0);
		MainPanel.add(TopBarPanel, BorderLayout.NORTH);
		
		ExitButton = new JButton();
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		});
		setDefaultExitButtonDesign(ExitButton);
		TopBarPanel.add(ExitButton);
	}

}
