package gamekeobuabao;

import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public PanelRun Gamepanel;
	public JPanel StartPanel = new JPanel();
	private JLabel Bakgroundlabel, highscoreLabel, Scorelabel, CopyRightLabel;
	private int score = 0;
	private JButton PlayGameButton, SoundButton, HowToPlayButton, ExitButton;
	private ImageIcon Icon = new ImageIcon(new ImageIcon("image/giang.jpg").getImage()
			.getScaledInstance(470, 685, Image.SCALE_DEFAULT));
	private ImageIcon VolumeIcon = new ImageIcon(new ImageIcon("image/volume.png").getImage()
			.getScaledInstance(40, 40, Image.SCALE_DEFAULT));

	public MainFrame() {
		super();
		this.setBounds(100, 100, 470, 685);
		this.setTitle("Rock Paper Scissors Game");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setResizable(false);

		init();

	}

	public void init() {

		StartPanel.setLayout(null);
//		menu = new JMenuBar();

		highscoreLabel = createlabel("HIGH SCORE: ");
		highscoreLabel.setBounds(24, 60, 100, 28);
		StartPanel.add(highscoreLabel);

		// create score label
		Scorelabel = createlabel("");
		Scorelabel.setText(String.valueOf(score));
		Scorelabel.setBounds(110, 60, 30, 28);
		StartPanel.add(Scorelabel);
		
		CopyRightLabel = createlabel("©CopyRight by ZaqqBeoo");
		CopyRightLabel.setBounds(290, 620, 150, 28);
		StartPanel.add(CopyRightLabel);

		// create the play new game button
		PlayGameButton = createButton("PLAY GAME");
		PlayGameButton.setBounds(170, 380, 130, 40);
		StartPanel.add(PlayGameButton);

		// create the sound button
		SoundButton = createButton("");
		SoundButton.setBounds(380, 60, 40, 40);
		SoundButton.setIcon(VolumeIcon);
		StartPanel.add(SoundButton);

		// create the how to play button
		HowToPlayButton = createButton("HOW TO PLAY");
		HowToPlayButton.setBounds(170, 440, 130, 40);
		StartPanel.add(HowToPlayButton);

		// create exit button
		ExitButton = createButton("EXIT GAME");
		ExitButton.setBounds(170, 500, 130, 40);
		StartPanel.add(ExitButton);

		

		// background is the image
		Bakgroundlabel = createlabel("");
		Bakgroundlabel.setBounds(0, 0, 470, 685);
		Bakgroundlabel.setIcon(Icon);
		StartPanel.add(Bakgroundlabel);


		// add panel
		Gamepanel = new PanelRun(this);
		add(StartPanel);
		setContentPane(StartPanel);

		ReadDatatoFile();
		// CreateMenu();
	}

	public JPanel getStartPanel() {
		return StartPanel;
	}

	public void setStartPanel(JPanel startPanel) {
		StartPanel = startPanel;
	}

	public JButton createButton(String title) {
		JButton btn = new JButton(title);
		btn.addActionListener(this);
		return btn;
	}

	// method used to create the label
	public JLabel createlabel(String title) {
		JLabel lb = new JLabel(title);
		return lb;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand();
		Gamepanel.timing = 100;
		if (name == "PLAY GAME") {
			StartPanel.setVisible(false);
			Gamepanel.setVisible(true);
			Gamepanel.CreateGame();
			Gamepanel.time = new Timer(Gamepanel.timing, new Loadtime());
			Gamepanel.truefalseLabel.setText("Let's Play New Game");
			Gamepanel.time.start();
			setContentPane(Gamepanel);
		} else if (name == "EXIT GAME") {
			System.exit(0);
		} else if (name == "HOW TO PLAY") {
			JOptionPane.showMessageDialog(null,
					"See who win and then click the button LEFT or PEACE or RIGHT to select the winner within the specified time!!! ");
		}
	}

	public class Loadtime implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Gamepanel.timing > 0) {
				Gamepanel.timing--;
				Gamepanel.TimeProgressBar.setValue(Gamepanel.timing);
			} else {
				Gamepanel.truefalseLabel.setText("OVERTIME");
				Gamepanel.PlayerLife--;
			}
		}
	}

	public void ReadDatatoFile() {
		File file = new File("OUTPUTDATA.txt");

		try (Scanner sc = new Scanner(file)) {
			int num = sc.nextInt();
			Scorelabel.setText(String.valueOf(num));
		} catch (Exception e) {
		}
	}

}
