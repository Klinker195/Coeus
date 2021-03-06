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

import controller.Controller;
import entities.OngoingProject;
import entities.Project;
import entities.User;

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
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.time.LocalDate;

public class ProjectEditorWindow extends GenericDialog {

	private JPanel MainPanel = new JPanel();
	private JTextField ProjectNameTextField;
	private JTextPane ProjectDescriptionTextPane;
	private JTextField CustomTopicTextField;
	private JList<String> ChosenTopicList;
	private JList<String> TopicList;
	private JButton AddCustomTopicButton;
	private JComboBox<String> ProjectModalityJComboBox;
	private JScrollPane ChosenTopicScrollPane;
	private JSpinner MaxEmployeeSpinner;

	private Controller MainController = Controller.getInstance();

	public ProjectEditorWindow(User ConnectedUser, int DisplayWidth, int DisplayHeight) {
		
		setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		setDefaultDesign(this);
		setType(Type.UTILITY);
		setBounds(DisplayWidth/2 - 312, DisplayHeight/2 - 310, 614, 620);
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
		
		MaxEmployeeSpinner = new JSpinner();
		MaxEmployeeSpinner.setModel(new SpinnerNumberModel(2, 2, 500, 1));
		setDefaultJSpinner(MaxEmployeeSpinner);
		
		JButton NextButton = new JButton("Next");
		NextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(MainController.getProjectDAO().existsByProjectName(ProjectNameTextField.getText())) {
					
					MainController.displayMessageDialog("Error!", "A project with this name already exists!");
					
				} else {
					
					if(checkFieldsValidity()) {
						
						Project NewProject;
						
						ArrayList<String> TopicArrayList = new ArrayList<String>();
						
						// Fill TopicArrayList with the topics chosen by the creator of this project
						for(int i = 0; i < ChosenTopicList.getModel().getSize(); i++) {
							TopicArrayList.add(new String(ChosenTopicList.getModel().getElementAt(i)));
						}
						
						try {
							MaxEmployeeSpinner.commitEdit();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						
						if(ProjectDescriptionTextPane.getText().isBlank()) {						
							NewProject = new OngoingProject(ProjectNameTextField.getText(), LocalDate.now(), (int)MaxEmployeeSpinner.getValue(), (String)ProjectModalityJComboBox.getSelectedItem(), TopicArrayList);
						} else {
							NewProject = new OngoingProject(ProjectNameTextField.getText(), ProjectDescriptionTextPane.getText(), LocalDate.now(), (int)MaxEmployeeSpinner.getValue(), (String)ProjectModalityJComboBox.getSelectedItem(), TopicArrayList);
						}
						
						ArrayList<String> NewTopicArrayList = new ArrayList<String>();
							
						// Exists variable to check if a certain topic is found
						boolean exists = false;
							
						// All the new custom topics are added to NewTopicArrayList so that they can be added to the DB
						for(int i = 0; i < ChosenTopicList.getModel().getSize(); i++) {
							for(int j = 0; j < TopicList.getModel().getSize(); j++) {
								if(TopicArrayList.get(i).toUpperCase().equals(TopicList.getModel().getElementAt(j).toUpperCase())) {
									exists = true;
									break;
								}
								exists = false;
							}
							if(!exists) {
								NewTopicArrayList.add(TopicArrayList.get(i));
							}
						}
						
						
						// If NewTopicArrayList contains at least one new custom topic then it's added to the DB
						if(NewTopicArrayList.size() != 0) {
							MainController.getProjectDAO().insertNewTopics(NewTopicArrayList);
						}
						
						MainController.getProjectDAO().insertOngoingProject((OngoingProject)NewProject, ConnectedUser);
						
						dispose();
						
						MainController.displayMessageDialog("Success!", "The project has been created.");
						
//						if(NewProject.getMaxEmployee() != 1) {
//							MainController.openProjectMembersSelectionWindow(ConnectedUser, NewProject);
//						}
						
						MainController.openProjectMembersSelectionWindow(ConnectedUser, NewProject);
						
					} else {
						MainController.displayMessageDialog("Error!", "There are errors in some of the fields.");
					}
					
				}
				
				
			}
		});
		setDefaultLineBorderButtonDesign(NextButton);
		BottomPanel.add(NextButton);
		
		JPanel CentralPanel = new JPanel();
		setDefaultBackgroundDesign(CentralPanel);
		MainPanel.add(CentralPanel, BorderLayout.CENTER);
		
		JLabel ProjectInformationsLabel = new JLabel("Project informations");
		setDefaultHeaderTextLabel(ProjectInformationsLabel);
		
		ProjectNameTextField = new JTextField();
		ProjectNameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				deleteFieldThreshold(ProjectNameTextField);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				deleteFieldThreshold(ProjectNameTextField);
			}
		});
		setDefaultJTextFieldDesign(ProjectNameTextField);
		ProjectNameTextField.setColumns(10);
		
		JLabel DescriptionTextLabel = new JLabel("Description");
		setDefaultTextLabel(DescriptionTextLabel);
		
		ProjectDescriptionTextPane = new JTextPane();
		ProjectDescriptionTextPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				deleteFieldThreshold(ProjectDescriptionTextPane);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				deleteFieldThreshold(ProjectDescriptionTextPane);
			}
		});
		ProjectDescriptionTextPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel ProjectModalityTextLabel = new JLabel("Modality");
		setDefaultTextLabel(ProjectModalityTextLabel);
		
		ProjectModalityJComboBox = new JComboBox();
		setDefaultJComboBox(ProjectModalityJComboBox);
		
		JLabel ProjectMaxEmployeesTitleLabel = new JLabel("Maximum of employees");
		setDefaultTextLabel(ProjectMaxEmployeesTitleLabel);
		
		JLabel ProjectNameTextLabel = new JLabel("Title");
		setDefaultTextLabel(ProjectNameTextLabel);
		
		JLabel TopicListTextLabel = new JLabel("Topics");
		setDefaultTextLabel(TopicListTextLabel);
		
		JScrollPane TopicListScrollPane = new JScrollPane();
		setDefaultJScrollPane(TopicListScrollPane);
		TopicListScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		ChosenTopicScrollPane = new JScrollPane();
		setDefaultJScrollPane(ChosenTopicScrollPane);
		ChosenTopicScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JButton AddTopicButton = new JButton("+");
		AddTopicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTopicList();
			}
		});
		setDefaultActionButtonDesign(AddTopicButton);
		
		JButton RemoveTopicButton = new JButton("-");
		RemoveTopicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeTopicList();
			}
		});
		setDefaultActionButtonDesign(RemoveTopicButton);
		
		CustomTopicTextField = new JTextField();
		CustomTopicTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				deleteFieldThreshold(CustomTopicTextField);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				deleteFieldThreshold(CustomTopicTextField);
			}
		});
		setDefaultJTextFieldDesign(CustomTopicTextField);
		CustomTopicTextField.setColumns(10);
		
		AddCustomTopicButton = new JButton("Add custom topic");
		AddCustomTopicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomTopic();
			}
		});
		setDefaultActionButtonDesign(AddCustomTopicButton);
		
		JLabel TopicBackgroundLabel = new JLabel("The maximum number of topics per project is 3.");
		setDefaultBackgroundTextLabel(TopicBackgroundLabel);
		GroupLayout gl_CentralPanel = new GroupLayout(CentralPanel);
		gl_CentralPanel.setHorizontalGroup(
			gl_CentralPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addComponent(ProjectModalityJComboBox, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_CentralPanel.createSequentialGroup()
								.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(MaxEmployeeSpinner, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
									.addComponent(ProjectInformationsLabel)
									.addComponent(DescriptionTextLabel)
									.addComponent(ProjectNameTextField, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
									.addComponent(ProjectModalityTextLabel)
									.addComponent(ProjectMaxEmployeesTitleLabel)
									.addComponent(ProjectNameTextLabel, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
									.addComponent(TopicListTextLabel)
									.addGroup(gl_CentralPanel.createSequentialGroup()
										.addGroup(gl_CentralPanel.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(CustomTopicTextField, Alignment.LEADING)
											.addComponent(TopicListScrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
										.addGap(18)
										.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_CentralPanel.createSequentialGroup()
												.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING)
													.addComponent(AddTopicButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
													.addComponent(RemoveTopicButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
												.addGap(18)
												.addComponent(ChosenTopicScrollPane, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE))
											.addComponent(AddCustomTopicButton, GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)))
									.addComponent(ProjectDescriptionTextPane, GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE))
								.addGap(13))
							.addGroup(gl_CentralPanel.createSequentialGroup()
								.addComponent(TopicBackgroundLabel)
								.addContainerGap(371, Short.MAX_VALUE)))))
		);
		gl_CentralPanel.setVerticalGroup(
			gl_CentralPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CentralPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(ProjectInformationsLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ProjectNameTextLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ProjectNameTextField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(DescriptionTextLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ProjectDescriptionTextPane, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ProjectModalityTextLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ProjectModalityJComboBox, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ProjectMaxEmployeesTitleLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MaxEmployeeSpinner, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TopicListTextLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(TopicListScrollPane, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_CentralPanel.createSequentialGroup()
							.addComponent(AddTopicButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(RemoveTopicButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addComponent(ChosenTopicScrollPane, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_CentralPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(AddCustomTopicButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(CustomTopicTextField, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TopicBackgroundLabel)
					.addGap(155))
		);
		
		ChosenTopicList = new JList<String>();
		ChosenTopicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ChosenTopicScrollPane.setViewportView(ChosenTopicList);
		
		TopicList = new JList<String>();
		TopicList.setValueIsAdjusting(true);
		TopicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TopicListScrollPane.setViewportView(TopicList);
		
		
		CentralPanel.setLayout(gl_CentralPanel);
		
	}

	public void addTopicList() {
		
		if(ChosenTopicList.getModel().getSize() >= 3) {
			MainController.displayMessageDialog("Error!", "You cannot choose more than 3 topics.");
			return;
		}
		
		List<String> SelectedTopics = TopicList.getSelectedValuesList();
		
		List<String> CurrentTopics = new ArrayList<String>();
		
		for(int i = 0; i < ChosenTopicList.getModel().getSize(); i++) {
			CurrentTopics.add(ChosenTopicList.getModel().getElementAt(i));
		}
				
		for(String Topic : SelectedTopics) {
			if(!CurrentTopics.contains(Topic)) {
				CurrentTopics.add(Topic);
			}
		}
		
		
		ChosenTopicList.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = MainController.arrayListToStringArray((ArrayList<String>)CurrentTopics);
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		
		TopicList.clearSelection();
		ChosenTopicList.clearSelection();
		
	}
	
	public void removeTopicList() {
		
		List<String> SelectedTopics = ChosenTopicList.getSelectedValuesList();
		
		List<String> CurrentTopics = new ArrayList<String>();
		
		for(int i = 0; i < ChosenTopicList.getModel().getSize(); i++) {
			CurrentTopics.add(ChosenTopicList.getModel().getElementAt(i));
		}
		
		for(String Topic : SelectedTopics) {
			if(CurrentTopics.contains(Topic)) {
				CurrentTopics.remove(Topic);
			}
		}
		
		ChosenTopicList.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = MainController.arrayListToStringArray((ArrayList<String>)CurrentTopics);
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		
		TopicList.clearSelection();
		ChosenTopicList.clearSelection();
		
	}
	
	public void addCustomTopic() {
		
		if(ChosenTopicList.getModel().getSize() >= 3) {
			MainController.displayMessageDialog("Error!", "You cannot choose more than 3 topics.");
			return;
		}
		
		if(!CustomTopicTextField.getText().isBlank()) {
			
			List<String> CurrentTopics = new ArrayList<String>();
			
			for(int i = 0; i < ChosenTopicList.getModel().getSize(); i++) {
				CurrentTopics.add(ChosenTopicList.getModel().getElementAt(i));
			}
			
			String NewTopic = CustomTopicTextField.getText();
			
			if(NewTopic != null) {
				NewTopic = NewTopic.toUpperCase();
				if(!CurrentTopics.contains(NewTopic)) {
					CurrentTopics.add(NewTopic);
				}
			}
			
			ChosenTopicList.setModel(new AbstractListModel<String>() {
				private static final long serialVersionUID = 1L;
				String[] values = MainController.arrayListToStringArray((ArrayList<String>)CurrentTopics);
				public int getSize() {
					return values.length;
				}
				public String getElementAt(int index) {
					return values[index];
				}
			});
			
			CustomTopicTextField.setText("");
			
		}
		
		TopicList.clearSelection();
		ChosenTopicList.clearSelection();
		
	}
	
	public boolean checkFieldsValidity() {
		
		if(ProjectNameTextField.getText().isBlank() || ChosenTopicList.getModel().getSize() == 0) {
			
			if(ProjectNameTextField.getText().isBlank()) {
				ProjectNameTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.red));
			} else {
				ProjectNameTextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
			}
			
			if(ChosenTopicList.getModel().getSize() == 0) {
				ChosenTopicScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.red));
			} else {
				ChosenTopicScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
			}
			
			return false;
			
		}
		
		return true;
		
	}

	public void setData() {
		
		ProjectModalityJComboBox.setModel(new DefaultComboBoxModel<String>(MainController.arrayListToStringArray(MainController.getProjectDAO().getAllModalityNames())));
		
		TopicList.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] values = MainController.arrayListToStringArray(MainController.getProjectDAO().getAllTopicNames());
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		
	}
	
}
