package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextPane;

public class LoadingSplashWindow extends GenericFrame {

	private JPanel MainPanel;
	private String[] ManagementPhrases = {
			"Plans are worthless. Planning is essential. ~ Dwight D. Eisenhower",
			"It's a bad plan that admits of no modification. ~ Publilius Syrus",
			"The key to successful leadership today is influence not authority. ~ Kenneth Blanchard",
			"Management must manage! ~ Harold Geneen",
			"Goals are dreams with deadlines. ~ Diana Scharf"
	};
	
	public LoadingSplashWindow(int DisplayWidth, int DisplayHeight) {
		
		setDefaultDesign(this);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(DisplayWidth/2 - 150, DisplayHeight/2 - 100, 300, 200);
		setBounds(DisplayWidth/2 - 240, DisplayHeight/2 - 135, 480, 270);
		MainPanel = new JPanel();
		MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(MainPanel);
		
		setDefaultBorderDesign(MainPanel);
		MainPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel LoadingGifLabel = new JLabel("");
		LoadingGifLabel.setBackground(Color.WHITE);
		LoadingGifLabel.setHorizontalAlignment(SwingConstants.CENTER);
		LoadingGifLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		LoadingGifLabel.setIcon(new ImageIcon(LoginWindow.class.getResource("/loadingGif.gif")));
		MainPanel.add(LoadingGifLabel, BorderLayout.CENTER);
		
		JPanel PhrasePanel = new JPanel();
		MainPanel.add(PhrasePanel, BorderLayout.SOUTH);
		
		JTextPane PhraseTextPane = new JTextPane();
		PhraseTextPane.setBackground(new Color(240, 240, 240));
		PhraseTextPane.setForeground(new Color(6, 20, 64));
		PhraseTextPane.setFont(new Font("Roboto", Font.PLAIN, 11));
		PhraseTextPane.setEditable(false);
		PhraseTextPane.setText(getRandomPhrase());
		PhrasePanel.add(PhraseTextPane);
	}
	
	private String getRandomPhrase() {
		return ManagementPhrases[ThreadLocalRandom.current().nextInt(0, ManagementPhrases.length)];
	}

}
