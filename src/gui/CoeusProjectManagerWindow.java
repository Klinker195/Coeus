package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.border.MatteBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class CoeusProjectManagerWindow extends GenericFrame {

	private static final long serialVersionUID = 1L;
	
	private Controller MainController = Controller.getIstance();
	
	private JPanel MainPanel;

	private JButton ExitButton;
	private JTable PersonalOngoingProjectsTable;
	private JTable PersonalCompletedProjectsTable;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoeusProjectManagerWindow frame = new CoeusProjectManagerWindow(1920, 1080, 0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CoeusProjectManagerWindow(int DisplayWidth, int DisplayHeight, int Modality) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(DisplayWidth/2 - 700, DisplayHeight/2 - 400, 1400, 800);
		MainPanel = new JPanel();
		setDefaultBorderDesign(MainPanel);
		setDefaultDesign(this);
		setContentPane(MainPanel);
		MainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(64, 64, 64)));
		MainPanel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		panel.add(panel_3, BorderLayout.WEST);
		
		JLabel CoeusLabel = new JLabel("Coeus");
		CoeusLabel.setForeground(new Color(153, 51, 51));
		CoeusLabel.setFont(new Font("Tahoma", Font.PLAIN, 70));
		panel_3.add(CoeusLabel);
		
		JPanel RightPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) RightPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panel.add(RightPanel, BorderLayout.EAST);
		
		ExitButton = new JButton();
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		setDefaultExitButtonDesign(ExitButton);
		RightPanel.add(ExitButton);
		
		JPanel CentralPanel = new JPanel();
		CentralPanel.setBorder(null);
		setDefaultBackgroundDesign(CentralPanel);
		MainPanel.add(CentralPanel, BorderLayout.CENTER);
		CentralPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel CentralRightPanel = new JPanel();
		CentralPanel.add(CentralRightPanel, BorderLayout.EAST);
		CentralRightPanel.setLayout(new BorderLayout(0, 0));
		
		JLayeredPane ActionPanel = new JLayeredPane();
		CentralRightPanel.add(ActionPanel, BorderLayout.CENTER);
		ActionPanel.setLayout(new CardLayout(0, 0));
		
		JPanel ActionProjectPanel = new JPanel();
		ActionProjectPanel.setPreferredSize(new Dimension(300, 0));
		ActionPanel.add(ActionProjectPanel, "name_1134637194576300");
		
		JPanel ActionMeetingPanel = new JPanel();
		ActionMeetingPanel.setPreferredSize(new Dimension(300, 0));
		ActionPanel.add(ActionMeetingPanel, "name_1134646911861600");
		
		JPanel ActionEmployeePanel = new JPanel();
		ActionEmployeePanel.setPreferredSize(new Dimension(300, 0));
		ActionPanel.add(ActionEmployeePanel, "name_1134649427819800");
		
		JPanel panel_13 = new JPanel();
		ActionPanel.add(panel_13, "name_1134652407563900");
		
		JPanel panel_14 = new JPanel();
		ActionPanel.add(panel_14, "name_1134654759871400");
		
		JTabbedPane TabbedPanel = new JTabbedPane(JTabbedPane.TOP);
		CentralPanel.add(TabbedPanel, BorderLayout.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		TabbedPanel.addTab("Projects", null, tabbedPane, null);
		
		JPanel TabbedPersonalProjectPanel = new JPanel();
		setDefaultBackgroundDesign(TabbedPersonalProjectPanel);
		tabbedPane.addTab("Personal", null, TabbedPersonalProjectPanel, null);
		
		JScrollPane PersonalOngoingProjectsScrollPane = new JScrollPane();
		PersonalOngoingProjectsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		PersonalOngoingProjectsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel PersonalOngoingProjectsLabel = new JLabel("Ongoing Projects");
		setDefaultHeaderTextLabel(PersonalOngoingProjectsLabel);
		
		JLabel PersonalCompletedProjectsLabel = new JLabel("Completed Projects");
		setDefaultHeaderTextLabel(PersonalCompletedProjectsLabel);
		
		JScrollPane PersonalCompletedProjectsScrollPane = new JScrollPane();
		PersonalCompletedProjectsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		PersonalCompletedProjectsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout gl_TabbedPersonalProjectPanel = new GroupLayout(TabbedPersonalProjectPanel);
		gl_TabbedPersonalProjectPanel.setHorizontalGroup(
			gl_TabbedPersonalProjectPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TabbedPersonalProjectPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_TabbedPersonalProjectPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(PersonalOngoingProjectsLabel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
						.addComponent(PersonalOngoingProjectsScrollPane, GroupLayout.PREFERRED_SIZE, 1049, GroupLayout.PREFERRED_SIZE)
						.addComponent(PersonalCompletedProjectsLabel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
						.addComponent(PersonalCompletedProjectsScrollPane, GroupLayout.PREFERRED_SIZE, 1049, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_TabbedPersonalProjectPanel.setVerticalGroup(
			gl_TabbedPersonalProjectPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_TabbedPersonalProjectPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(PersonalOngoingProjectsLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PersonalOngoingProjectsScrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(PersonalCompletedProjectsLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PersonalCompletedProjectsScrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(76, Short.MAX_VALUE))
		);
		
		PersonalCompletedProjectsTable = new JTable();
		PersonalCompletedProjectsTable.getTableHeader().setReorderingAllowed(false);
		PersonalCompletedProjectsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Project Name", "Modality", "Scope", "Starting Date", "Ending Date", "Project Manager"
			}
		));
		PersonalCompletedProjectsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		PersonalCompletedProjectsScrollPane.setViewportView(PersonalCompletedProjectsTable);
		
		PersonalOngoingProjectsTable = new JTable();
		PersonalOngoingProjectsTable.getTableHeader().setReorderingAllowed(false);
		PersonalOngoingProjectsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		PersonalOngoingProjectsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Project Name", "Modality", "Scope", "Starting Date", "Project Manager"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		PersonalOngoingProjectsTable.getColumnModel().getColumn(0).setResizable(false);
		PersonalOngoingProjectsTable.getColumnModel().getColumn(0).setPreferredWidth(180);
		PersonalOngoingProjectsTable.getColumnModel().getColumn(1).setResizable(false);
		PersonalOngoingProjectsTable.getColumnModel().getColumn(1).setPreferredWidth(134);
		PersonalOngoingProjectsTable.getColumnModel().getColumn(2).setResizable(false);
		PersonalOngoingProjectsTable.getColumnModel().getColumn(2).setPreferredWidth(138);
		PersonalOngoingProjectsTable.getColumnModel().getColumn(3).setResizable(false);
		PersonalOngoingProjectsTable.getColumnModel().getColumn(3).setPreferredWidth(105);
		PersonalOngoingProjectsTable.getColumnModel().getColumn(4).setResizable(false);
		PersonalOngoingProjectsTable.getColumnModel().getColumn(4).setPreferredWidth(170);
		PersonalOngoingProjectsScrollPane.setViewportView(PersonalOngoingProjectsTable);
		TabbedPersonalProjectPanel.setLayout(gl_TabbedPersonalProjectPanel);
		
		JPanel TabbedGeneralProjectPanel = new JPanel();
		setDefaultBackgroundDesign(TabbedGeneralProjectPanel);
		tabbedPane.addTab("General", null, TabbedGeneralProjectPanel, null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 0, 0, 0, (Color) Color.DARK_GRAY));
		FlowLayout flowLayout_3 = (FlowLayout) panel_2.getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setHgap(0);
		MainPanel.add(panel_2, BorderLayout.SOUTH);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		//ComponentMover cm = new ComponentMover(JFrame.class, titleBar);
		
		
	}
}
