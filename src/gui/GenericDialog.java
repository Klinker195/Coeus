package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class GenericDialog extends JDialog {

	public void setDefaultDesign(JDialog Dialog) {
		Dialog.setResizable(false);
		Dialog.setUndecorated(true);
		Dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
	
	public void setDefaultHeaderTextLabel(JLabel Label) {
		Label.setFont(new Font("Tahoma", Font.BOLD, 16));
	}
	
	public void setDefaultTextLabel(JLabel Label) {
		Label.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}
	
	public void setDefaultTextJTextPane(JTextPane TextPane) {
		TextPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}
	
	public void setDefaultBackgroundTextLabel(JLabel Label) {
		Label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Label.setForeground(new Color(122, 72, 72));
	}
	
	public String convertToWrappedText(String Text) {
		
		String InitialSpacing = "";
		String NewString = "";
		
		
		final int MaxCharactersPerLine = 50;
		int LinesQuantity;
		float MaxLines = Text.length() / MaxCharactersPerLine;
		int InitialSpacingMinimumSize = 7;
		
		if(MaxLines % 0 == 0) {
			LinesQuantity = (int)MaxLines;
		} else {
			LinesQuantity = (int)MaxLines + 1;
		}
		
		if(LinesQuantity < 1) {
			return InitialSpacing + Text;
		}
		
		if(LinesQuantity == 1) {
			for(int i = 0; i < InitialSpacingMinimumSize; i++) {
				InitialSpacing = InitialSpacing + "\n";
			}
		} else {
			for(int i = 0; i < InitialSpacingMinimumSize - LinesQuantity; i++) {
				InitialSpacing = InitialSpacing + "\n";
			}
		}
		
		for(int i = 0; i < LinesQuantity; i++) {
			if(i == LinesQuantity - 1) {
				NewString = NewString + Text.substring(i * MaxCharactersPerLine) + "\n";
				continue;
			}
			NewString = NewString + Text.substring(i * MaxCharactersPerLine, MaxCharactersPerLine + (i * MaxCharactersPerLine)) + "\n";
		}
		
		NewString = InitialSpacing + NewString;
		
		return NewString;
	}
	
}
