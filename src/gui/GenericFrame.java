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
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public void setDefaultActionButtonDesign(JButton ActionButton) {
		ActionButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ActionButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		ActionButton.setForeground(new Color(153, 51, 51));
		ActionButton.setFont(new Font("Roboto", Font.BOLD, 16));
		ActionButton.setFocusable(false);
		ActionButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(153, 51, 51)));
		ActionButton.setBackground(Color.WHITE);
		
	}
	
	public void setDefaultLineBorderButtonDesign(JButton LineBorderButton) {
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
	
	public void setDefaultJSeparator(JSeparator Separator) {
		Separator.setBackground(new Color(122, 72, 72));
		Separator.setForeground(new Color(122, 72, 72));
	}
	
	public void setDefaultActionButton(JButton ActionButton) {
		ActionButton.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(64, 64, 64)), new EmptyBorder(6, 6, 6, 6)));
	}
	
	public void setDefaultJComboBox(JComboBox ComboBox) {
		ComboBox.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
	}
	
	public void setDefaultJSpinner(JSpinner Spinner) {
		Spinner.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
	}
	
	public void setDefaultJScrollPane(JScrollPane ScrollPane) {
		ScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
	}
	
	public void setDefaultJTextFieldDesign(JTextField TextField) {
		TextField.setColumns(10);
		TextField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
	}
	
	public void setDefaultJPasswordFieldDesign(JPasswordField PasswordField) {
		PasswordField.setColumns(10);
		PasswordField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
	}
	
	public void setDefaultHeaderTextLabel(JLabel Label) {
		Label.setFont(new Font("Tahoma", Font.BOLD, 16));
	}
	
	public void setDefaultTextLabel(JLabel Label) {
		Label.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}
	
	public void setDefaultBackgroundTextLabel(JLabel Label) {
		Label.setFont(new Font("Tahoma", Font.BOLD, 11));
		Label.setForeground(new Color(122, 72, 72));
	}
	
	public void deleteFieldThreshold(JTextField TextField) {
		
		String String = TextField.getText();
		
		if(String.length() > 128) {
			String = String.substring(0, String.length() - 1);
			TextField.setText(String);
		} 
		
	}
	
	public void deleteFieldSpaces(JTextField TextField) {
		
		String String = TextField.getText();
		
		if(String.contains(" ")) {
			String = String.substring(0, String.length() - 1);
			TextField.setText(String);
		} 
		
	}
	

	
	public boolean checkPasswordCharacters(char[] PasswordCharArray) {
		
		final char[] ValidCharacters = {'!', '#', '%', '&', '\'', '*', '+', '-', '/', '=', '?', '^', '_', '.', '{', '|', 'b', 'Y', 'f', 'M',
    			'k', 'A', 'x', 'W', 'o', 'L', 'h', 'R', 't', 'G', 'l', 'P', 'a', 'Z', 'g', 'J', 'z', 'D', 's', 'U', 'w', 'X', 'i', 'N', 'r', 'K',
    			'c', 'F', 'v', 'B', 'u', 'S', 'm', 'H', 'y', 'E', 'd', 'V', 'e', 'T', 'p', 'Q', 'j', 'O', 'n', 'I', 'q', 'C', '4', '8', '3', '5',
    			'0', '1', '2', '9', '7', '6'};
		
		boolean valid;
		
		for(int i = 0; i < PasswordCharArray.length; i++) {
			valid = false;
			for(int j = 0; j < ValidCharacters.length; j++) {
				if(PasswordCharArray[i] == ValidCharacters[j]) {
					valid = true;
					break;
				}
			}
			if(!valid) {
				return false;
			}
		}
		return true;
		
	}
	
	public boolean checkAuthRegistrationFieldsValidity(JPasswordField PasswordField, JPasswordField ConfirmationPasswordField) {
		
		char[] PasswordCharArray = PasswordField.getPassword();
		char[] ConfirmationPasswordCharArray = ConfirmationPasswordField.getPassword();
		
		if(!Arrays.equals(PasswordCharArray, ConfirmationPasswordCharArray) || !checkPasswordCharacters(PasswordCharArray)) {
			
			if(!checkPasswordCharacters(PasswordCharArray)) {
				PasswordField.setBorder(new MatteBorder(1, 1, 1, 1, Color.red));
			} else {
				PasswordField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
			}
			
			if(!Arrays.equals(PasswordCharArray, ConfirmationPasswordCharArray)) {
				PasswordField.setBorder(new MatteBorder(1, 1, 1, 1, Color.red));
				ConfirmationPasswordField.setBorder(new MatteBorder(1, 1, 1, 1, Color.red));
			} else {
				PasswordField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
				ConfirmationPasswordField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
			}
			
			Arrays.fill(PasswordCharArray, '0');
			Arrays.fill(ConfirmationPasswordCharArray, '0');
			return false;
		} else {
			PasswordField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
			ConfirmationPasswordField.setBorder(new MatteBorder(1, 1, 1, 1, Color.darkGray));
			return true;
		}
		
	}
	
}
