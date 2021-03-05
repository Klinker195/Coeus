package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import controller.Controller;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class MessageDialog extends GenericDialog {

	private Controller MainController = Controller.getInstance();
	
	private final JPanel MainPanel = new JPanel();
	
	private JButton OkButton;
	
//	public static void main(String[] args) {
//		try {
//			
//			ErrorDialog dialog = new ErrorDialog("KFC", 1920, 1080);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public MessageDialog(String Title, String Text, int DisplayWidth, int DisplayHeight) {
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		setDefaultDesign(this);
		setBounds(DisplayWidth/2 - 226, DisplayHeight/2 - 151, 452, 302);
		getContentPane().setLayout(new BorderLayout());
		setDefaultBorderDesign(MainPanel);
		getContentPane().add(MainPanel, BorderLayout.CENTER);
		MainPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel TopPanel = new JPanel();
			MainPanel.add(TopPanel, BorderLayout.NORTH);
			TopPanel.setLayout(new BorderLayout(0, 0));
			{
				JButton ExitButton = new JButton("");
				ExitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				setDefaultExitButtonDesign(ExitButton);
				TopPanel.add(ExitButton, BorderLayout.EAST);
			}
			{
				JPanel LeftTopPanel = new JPanel();
				FlowLayout fl_LeftTopPanel = (FlowLayout) LeftTopPanel.getLayout();
				fl_LeftTopPanel.setVgap(10);
				fl_LeftTopPanel.setHgap(10);
				TopPanel.add(LeftTopPanel, BorderLayout.WEST);
				{
					JLabel TitleLabel = new JLabel("");
					LeftTopPanel.add(TitleLabel);
					TitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
					TitleLabel.setText(Title);
					setDefaultTextLabel(TitleLabel);
				}
			}
		}
		{
			JPanel CenterPanel = new JPanel();
			setDefaultBackgroundDesign(CenterPanel);
			MainPanel.add(CenterPanel, BorderLayout.CENTER);
			CenterPanel.setLayout(new BorderLayout(0, 0));
			{
				JTextPane MessageTextPane = new JTextPane();
				StyledDocument DocStyle = MessageTextPane.getStyledDocument();
				SimpleAttributeSet center = new SimpleAttributeSet();
				StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
				DocStyle.setParagraphAttributes(0, DocStyle.getLength(), center, false);
				MessageTextPane.setText(setTextToCenter(Text));
				setDefaultTextJTextPane(MessageTextPane);
				MessageTextPane.setBackground(new Color(62, 100, 214));
				MessageTextPane.setEditable(false);
				CenterPanel.add(MessageTextPane);
			}
			{
				JPanel BottomPanel = new JPanel();
				CenterPanel.add(BottomPanel, BorderLayout.SOUTH);
				BottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
				{
					OkButton = new JButton("Ok");
					OkButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					OkButton.setActionCommand("Ok");
					setDefaultLineBorderButtonDesign(OkButton);
					BottomPanel.add(OkButton);
					getRootPane().setDefaultButton(OkButton);
				}
			}
		}
	}

}
