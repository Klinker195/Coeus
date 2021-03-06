package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public abstract class GenericFrame extends JFrame {
	
	protected void setDefaultDesign(JFrame Frame) {
		Frame.setUndecorated(true);
		Frame.setTitle("Coeus Project Manager");
		Frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("appIcon.png")));
		Frame.setResizable(false);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	protected void setDefaultBorderDesign(JPanel MainPanel) {
		MainPanel.setBorder(new LineBorder(new Color(6, 20, 64), 3));
	}
	
	protected void setDefaultBackgroundDesign(JPanel Panel) {
//		Panel.setBackground(new Color(166, 111, 111));
		
		Panel.setBackground(new Color(62, 100, 214));
	}
	
	protected void setDefaultExitButtonDesign(JButton ExitButton) {
		
		ExitButton.setText("X");
		ExitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ExitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		ExitButton.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(95, 126, 222)));
		ExitButton.setFocusable(false);
		ExitButton.setForeground(new Color(255, 255, 255));
		ExitButton.setFont(new Font("Roboto", Font.BOLD, 20));
		ExitButton.setBackground(new Color(95, 126, 222));
		ExitButton.setBounds(313, 65, 35, 35);
		ExitButton.setPreferredSize(new Dimension(35, 35));
		ExitButton.setBorderPainted(false);
		
	}
	
	protected void setDefaultActionButtonDesign(JButton ActionButton) {
		ActionButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ActionButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		ActionButton.setForeground(new Color(15, 39, 115));
		ActionButton.setFont(new Font("Roboto", Font.BOLD, 16));
		ActionButton.setFocusable(false);
		ActionButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(15, 39, 115)));
		ActionButton.setBackground(Color.WHITE);
		
	}
	
	protected void setDefaultLineBorderButtonDesign(JButton LineBorderButton) {
		LineBorderButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				LineBorderButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		LineBorderButton.setFocusable(false);
		LineBorderButton.setForeground(new Color(11, 28, 82));
		LineBorderButton.setFont(new Font("Roboto", Font.PLAIN, 26));
		LineBorderButton.setContentAreaFilled(false);
		LineBorderButton.setBorder(new CompoundBorder(new LineBorder(new Color(11, 28, 82), 2, true), new EmptyBorder(2, 10, 2, 10)));
		LineBorderButton.setBackground(new Color(11, 28, 82));
		LineBorderButton.setMargin(new Insets(100, 100, 100, 100));
	}
	
	protected void setDefaultJSeparator(JSeparator Separator) {
//		Separator.setBackground(new Color(122, 72, 72));
//		Separator.setForeground(new Color(122, 72, 72));
		
		Separator.setBackground(new Color(31, 60, 148));
		Separator.setForeground(new Color(31, 60, 148));
	}
	
	protected void setDefaultJComboBox(JComboBox ComboBox) {
		ComboBox.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
	}
	
	protected void setDefaultJSpinner(JSpinner Spinner) {
		Spinner.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
	}
	
	protected void setDefaultJScrollPane(JScrollPane ScrollPane) {
		ScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
	}
	
	protected void setDefaultJTextFieldDesign(JTextField TextField) {
		TextField.setColumns(10);
		TextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
	}
	
	protected void setDefaultJPasswordFieldDesign(JPasswordField PasswordField) {
		PasswordField.setColumns(10);
		PasswordField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
	}
	
	protected void setDefaultHeaderTextLabel(JLabel Label) {
		Label.setFont(new Font("Roboto", Font.BOLD, 16));
		Label.setForeground(new Color(6, 20, 64));
	}
	
	protected void setDefaultTextLabel(JLabel Label) {
		Label.setFont(new Font("Roboto", Font.PLAIN, 14));
		Label.setForeground(new Color(6, 20, 64));
	}
	
	protected void setDefaultBackgroundTextLabel(JLabel Label) {
		Label.setFont(new Font("Roboto", Font.BOLD, 11));
		
		Label.setForeground(new Color(31, 60, 148));
		
//		Label.setForeground(new Color(122, 72, 72));
	}
	
	protected void deleteFieldThreshold(JTextField TextField) {
		
		String String = TextField.getText();
		
		if(String.length() > 128) {
			String = String.substring(0, String.length() - 1);
			TextField.setText(String);
		} 
		
	}
	
	protected void deleteFieldSpaces(JTextField TextField) {
		
		String String = TextField.getText();
		
		if(String.contains(" ")) {
			String = String.substring(0, String.length() - 1);
			TextField.setText(String);
		} 
		
	}

	
}
