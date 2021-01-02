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

public class WelcomeWindow extends JFrame {

	private JPanel contentPane;
	private GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int width = gd.getDisplayMode().getWidth();
	private int height = gd.getDisplayMode().getHeight();
	private GUIController GuiController = GUIController.getIstance();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeWindow frame = new WelcomeWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WelcomeWindow() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(width/2 - 275, height/2 - 175, 550, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Coeus\r\n");
		lblNewLabel.setForeground(new Color(153, 51, 51));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 80));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.SOUTH);
		
		JButton btnAvanti = new JButton("Avanti");
		btnAvanti.setPreferredSize(new Dimension(120, 30));
		btnAvanti.setForeground(new Color(153, 51, 51));
		btnAvanti.setFont(new Font("Roboto", Font.PLAIN, 26));
		btnAvanti.setContentAreaFilled(false);
		btnAvanti.setBorder(new LineBorder(new Color(153, 51, 51), 2, true));
		btnAvanti.setBackground(new Color(153, 51, 51));
		panel_3.add(btnAvanti);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		contentPane.add(panel_2, BorderLayout.NORTH);
		
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
		panel_2.add(ExitButton);
	}

}
