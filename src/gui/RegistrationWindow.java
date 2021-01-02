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
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;

public class RegistrationWindow extends JFrame {

	private JPanel contentPane;
	private GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int width = gd.getDisplayMode().getWidth();
	private int height = gd.getDisplayMode().getHeight();
	private GUIController GuiController = GUIController.getIstance();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationWindow frame = new RegistrationWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public RegistrationWindow() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(width/2 - 340, height/2 - 450, 680, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.WEST);
		
		JLabel lblNewLabel = new JLabel("Coeus");
		lblNewLabel.setForeground(new Color(153, 51, 51));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 70));
		panel_3.add(lblNewLabel);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panel.add(panel_4, BorderLayout.EAST);
		
		JLabel ExitButton = new JLabel("X");
		ExitButton.setPreferredSize(new Dimension(35, 35));
		ExitButton.setOpaque(true);
		ExitButton.setHorizontalAlignment(SwingConstants.CENTER);
		ExitButton.setForeground(Color.WHITE);
		ExitButton.setFont(new Font("Roboto Bk", Font.BOLD, 22));
		ExitButton.setBackground(new Color(153, 51, 51));
		panel_4.add(ExitButton);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnAvanti = new JButton("Next");
		btnAvanti.setPreferredSize(new Dimension(120, 30));
		btnAvanti.setForeground(new Color(153, 51, 51));
		btnAvanti.setFont(new Font("Roboto", Font.PLAIN, 26));
		btnAvanti.setContentAreaFilled(false);
		btnAvanti.setBorder(new LineBorder(new Color(153, 51, 51), 2, true));
		btnAvanti.setBackground(new Color(153, 51, 51));
		panel_1.add(btnAvanti);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(204, 153, 153));
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new MigLayout("", "[100px,grow,fill][20][100px,grow][100px,grow][100px,grow,fill]", "[][][15.00][][15.00][][][][]"));
		
		JLabel lblNewLabel_1 = new JLabel("Full Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(lblNewLabel_1, "cell 0 0");
		
		textField = new JTextField();
		panel_2.add(textField, "cell 0 1,growx");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		panel_2.add(textField_1, "cell 2 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("First Name");
		panel_2.add(lblNewLabel_2, "cell 0 2");
		
		JLabel lblNewLabel_2_1 = new JLabel("Last Name");
		panel_2.add(lblNewLabel_2_1, "cell 2 2");
		
		textField_2 = new JTextField();
		textField_2.setText("01/01/1990");
		textField_2.setToolTipText("01/01/1990");
		textField_2.setColumns(10);
		panel_2.add(textField_2, "cell 0 3,growx");
		
		JLabel lblNewLabel_2_2 = new JLabel("Birthdate");
		panel_2.add(lblNewLabel_2_2, "cell 0 4");
	}

}
