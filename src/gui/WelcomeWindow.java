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
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import exceptions.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomeWindow extends JFrame {

	private JPanel WelcomeWindowContentPane;
	private GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int width = gd.getDisplayMode().getWidth();
	private int height = gd.getDisplayMode().getHeight();
	private GUIController GuiController = GUIController.getIstance();

	public WelcomeWindow() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(width/2 - 275, height/2 - 190, 550, 380);
		WelcomeWindowContentPane = new JPanel();
		WelcomeWindowContentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		WelcomeWindowContentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(WelcomeWindowContentPane);
		
		JPanel MainPanel = new JPanel();
		WelcomeWindowContentPane.add(MainPanel, BorderLayout.CENTER);
		MainPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel WelcomeToLabel = new JLabel("Welcome to\r\n");
		WelcomeToLabel.setForeground(new Color(153, 51, 51));
		WelcomeToLabel.setFont(new Font("Tahoma", Font.PLAIN, 80));
		WelcomeToLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MainPanel.add(WelcomeToLabel, BorderLayout.NORTH);
		
		JPanel ButtonPanel = new JPanel();
		MainPanel.add(ButtonPanel, BorderLayout.SOUTH);
		
		JButton StartUsingCoeusButton = new JButton("Start using Coeus");
		StartUsingCoeusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					GuiController.userRegistration();
				} catch (IntervalException e1) {
					e1.printStackTrace();
				}
			}
		});
		StartUsingCoeusButton.setPreferredSize(new Dimension(250, 30));
		StartUsingCoeusButton.setForeground(new Color(153, 51, 51));
		StartUsingCoeusButton.setFont(new Font("Roboto", Font.PLAIN, 26));
		StartUsingCoeusButton.setContentAreaFilled(false);
		StartUsingCoeusButton.setBorder(new LineBorder(new Color(153, 51, 51), 2, true));
		StartUsingCoeusButton.setBackground(new Color(153, 51, 51));
		ButtonPanel.add(StartUsingCoeusButton);
		
		JLabel CoeusLabel = new JLabel("Coeus\r\n");
		CoeusLabel.setVerticalAlignment(SwingConstants.TOP);
		CoeusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CoeusLabel.setForeground(new Color(153, 51, 51));
		CoeusLabel.setFont(new Font("Tahoma", Font.PLAIN, 80));
		MainPanel.add(CoeusLabel, BorderLayout.CENTER);
		
		JPanel TopBarPanel = new JPanel();
		FlowLayout fl_TopBarPanel = (FlowLayout) TopBarPanel.getLayout();
		fl_TopBarPanel.setAlignment(FlowLayout.RIGHT);
		fl_TopBarPanel.setHgap(0);
		fl_TopBarPanel.setVgap(0);
		WelcomeWindowContentPane.add(TopBarPanel, BorderLayout.NORTH);
		
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
		TopBarPanel.add(ExitButton);
	}

}
