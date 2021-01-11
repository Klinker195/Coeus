package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public abstract class GenericFrame extends JFrame {
	
	public void setDefaultDesign(JFrame Frame) {
		Frame.setResizable(false);
		Frame.setUndecorated(true);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setDefaultBorderDesign(JPanel MainPanel) {
		MainPanel.setBorder(new LineBorder(new Color(82, 25, 25), 3));
	}
	
	public void setDefaultBackgroundDesign(JPanel Panel) {
		Panel.setBackground(new Color(166, 111, 111));
	}
	
	public void setDefaultExitButtonDesign(JButton ExitButton) {
		
		ExitButton.setText("X");
		ExitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ExitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		ExitButton.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(153, 51, 51)));
		ExitButton.setFocusable(false);
		ExitButton.setForeground(new Color(255, 255, 255));
		ExitButton.setFont(new Font("Roboto", Font.BOLD, 20));
		ExitButton.setBackground(new Color(153, 51, 51));
		ExitButton.setBounds(313, 65, 35, 35);
		ExitButton.setPreferredSize(new Dimension(35, 35));
		ExitButton.setBorderPainted(false);
		
	}
	
	public void setDefaultLineBorderButtonDesign(JButton LineBorderButton) {
		LineBorderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		LineBorderButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				LineBorderButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		LineBorderButton.setFocusable(false);
		LineBorderButton.setForeground(new Color(153, 51, 51));
		LineBorderButton.setFont(new Font("Roboto", Font.PLAIN, 26));
		LineBorderButton.setContentAreaFilled(false);
		LineBorderButton.setBorder(new CompoundBorder(new LineBorder(new Color(153, 51, 51), 2, true), new EmptyBorder(2, 10, 2, 10)));
		LineBorderButton.setBackground(new Color(153, 51, 51));
		LineBorderButton.setMargin(new Insets(100, 100, 100, 100));
	}
	
	public void setDefaultJTextFieldDesign(JTextField TextField) {
		TextField.setColumns(10);
		TextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
	}
	
	public void setDefaultHeaderTextLabel(JLabel Label) {
		Label.setFont(new Font("Tahoma", Font.BOLD, 16));
	}
	
	public void setDefaultTextLabel(JLabel Label) {
		Label.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}
	
	public void setDefaultBackgroundTextLabel(JLabel Label) {
		Label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Label.setForeground(new Color(122, 72, 72));
	}
}
