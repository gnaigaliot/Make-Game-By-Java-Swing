package gamekeobuabao;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class PanelRun extends JPanel implements ActionListener, CheckGame {

	private static final long serialVersionUID = 1L;
	MainFrame main;
	private Computer computer1, computer2;
	private JButton LeftButton, MiddleButton, RightButton, homeButton;
	public JLabel Score_label, LifeLabel, L_Computer_Label, R_Computer_Label, ScoreLabel, truefalseLabel,
			BackgroundLabel, TheImageOfLife;
	public JProgressBar TimeProgressBar = new JProgressBar();
	public Timer time;
	public int timing;
	public int PlayerLife = 5;
	private int check;
	private int mark = 0;
	private ImageIcon Icon = new ImageIcon(new ImageIcon("image/giang.jpg").getImage()
			.getScaledInstance(470, 685, Image.SCALE_DEFAULT));
	private ImageIcon rockIcon = new ImageIcon(new ImageIcon("image/rock.png").getImage()
			.getScaledInstance(180, 180, Image.SCALE_DEFAULT));
	private ImageIcon paperIcon = new ImageIcon(new ImageIcon("image/paper.png").getImage()
			.getScaledInstance(180, 180, Image.SCALE_DEFAULT));
	private ImageIcon scissorsIcon = new ImageIcon(new ImageIcon("image/scissors.png").getImage()
			.getScaledInstance(180, 180, Image.SCALE_DEFAULT));
	private ImageIcon HeartIcon = new ImageIcon(new ImageIcon("image/Heart.png").getImage()
			.getScaledInstance(60, 35, Image.SCALE_DEFAULT));
	private ImageIcon HomeIcon = new ImageIcon(new ImageIcon("image/home.png").getImage()
			.getScaledInstance(45, 45, Image.SCALE_DEFAULT));
	private JMenuBar menu;
	private JMenu FileMenu, ActionMenu, HelpMenu;
	private JMenuItem NewGameItem, ExitItem, AboutItem, PauseGame, ResumeGame;
	

	public PanelRun(MainFrame main) {
		super();
		this.main = main;
		init();	
	}

	public PanelRun() {
		super();
		init();
	}

	public void init() {
		this.setLayout(null);
		menu = new JMenuBar();
		this.add(menu);
	

		// create the image of life
		TheImageOfLife = createlabel("");
		TheImageOfLife.setBounds(12, 45, 80, 80);
		TheImageOfLife.setIcon(HeartIcon);
		this.add(TheImageOfLife);

		// create left
		LeftButton = createButton("LEFT WIN");
		LeftButton.setBounds(20, 500, 130, 130);
		this.add(LeftButton);

		// create middle
		MiddleButton = createButton("PEACE");
		MiddleButton.setBounds(170, 500, 130, 130);
		this.add(MiddleButton);

		// create right
		RightButton = createButton("RIGHT WIN");
		RightButton.setBounds(320, 500, 130, 130);
		this.add(RightButton);

		// create life
		LifeLabel = createlabel("");
		LifeLabel.setForeground(Color.DARK_GRAY);
		LifeLabel.setBackground(Color.GRAY);
		LifeLabel.setBounds(70, 60, 50, 50);
		LifeLabel.setText(String.valueOf(PlayerLife));
		this.add(LifeLabel);

		//create button to left home
		homeButton = createButton("");
		homeButton.setBounds(380, 60, 50, 50);
		homeButton.setIcon(HomeIcon);
		this.add(homeButton);
		
		// create Left computer
		L_Computer_Label = createlabel("LEFT COMPUTER");
		L_Computer_Label.setHorizontalAlignment(SwingConstants.CENTER);
		L_Computer_Label.setBounds(0, 240, 180, 180);
		this.add(L_Computer_Label);

		// create right computer
		R_Computer_Label = createlabel("RIGHT COMPUTER");
		R_Computer_Label.setHorizontalAlignment(SwingConstants.CENTER);
		R_Computer_Label.setBounds(290, 240, 180, 180);
		this.add(R_Computer_Label);

		// create progress time
		TimeProgressBar.setBounds(0, 460, 470, 20);
		this.add(TimeProgressBar);

		// create score
		Score_label = createlabel("SCORE: ");
		Score_label.setBounds(200, 70, 50, 35);
		this.add(Score_label);

		// num of score
		ScoreLabel = createlabel("");
		ScoreLabel.setBounds(250, 70, 110, 35);
		ScoreLabel.setText(String.valueOf(mark));
		this.add(ScoreLabel);

		// create true false
		truefalseLabel = createlabel("Start Game");
		truefalseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		truefalseLabel.setBounds(170, 120, 120, 50);
		this.add(truefalseLabel);

		// create background label
		BackgroundLabel = createlabel("");
		BackgroundLabel.setBounds(0, 0, 470, 685);
		BackgroundLabel.setIcon(Icon);
		this.add(BackgroundLabel);

		CreateMenu();
	}

	// method used to create the button
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
		String nameButton = e.getActionCommand();
		timing = 100;
		if (nameButton == "LEFT WIN") {
			int checkgame = 1;
			RefundGame(checkgame);
		} else if (nameButton == "PEACE") {
			int checkgame = 2;
			RefundGame(checkgame);
		} else if (nameButton == "RIGHT WIN") {
			int checkgame = 3;
			RefundGame(checkgame);
		} else if (nameButton == "New Game") {
			NewGame();
		} else if (nameButton == "Exit") {
			System.exit(0);
		} else if (nameButton == "About") {
			PauseGame();
			JOptionPane.showMessageDialog(null,
					"This game created by ZaqqBeoo. Please do not reup. Thank you very much!!! \n Contact me by email: hoanggiangcnttutc@gmail.com or phone number: 0362947187 ");
			ResumeGame();
		} else if (nameButton == "Pause Game") {
			PauseGame();
		} else if(nameButton == "Resume Game") {
			ResumeGame();
		} else if(nameButton == ""){
			this.setVisible(false);
			EndGame();
		}
	}

	public void ResumeGame() {
		//timing = 100;
		CreateGame();
		RightButton.setEnabled(true);
		LeftButton.setEnabled(true);
		MiddleButton.setEnabled(true);
		}
	
	public void NewGame() {
		this.PlayerLife = 5;
		this.mark = 0;
		this.ScoreLabel.setText(String.valueOf(this.mark));
		this.LifeLabel.setText(String.valueOf(this.PlayerLife));
		this.truefalseLabel.setText("Let's Play New Game");
		CreateGame();
	}
	
	public void RefundGame(int checkgame) {
		if (checkgame == checkValue(computer1.Choose, computer2.Choose)) {
			mark++;
			CreateGame();
			ScoreLabel.setText(String.valueOf(mark));
			truefalseLabel.setText("TRUE");

		} else {
			CreateGame();
			truefalseLabel.setText("FALSE");
			ScoreLabel.setText(String.valueOf(mark));
			PlayerLife--;
			LifeLabel.setText(String.valueOf(PlayerLife));
		}
	}

	public void CreateGame() {
		if (checkGame()) {
			WriteDatatoFile();
			timing = Integer.MAX_VALUE;
			EndGame();
		}
		computer1 = new Computer();
		computer2 = new Computer();
		
		time = new Timer(timing, new Loadtime());
		time.start();
		Paint(computer1.Choose, L_Computer_Label);
		Paint(computer2.Choose, R_Computer_Label);
	}

	public void EndGame() {
		main.dispose();
		main = new MainFrame();
		main.setVisible(true);
	}

	public class Loadtime implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (timing > 0) {
				//timing--;
				TimeProgressBar.setValue(timing);
			//	TimeProgressBar.setMaximum(100);
			} else {
				timing = 100;
				CreateGame();
				truefalseLabel.setText("OVERTIME");
				PlayerLife--;
				LifeLabel.setText(String.valueOf(PlayerLife));
			}
		}
	}

	
	@Override
	public boolean checkGame() {
		if (PlayerLife < 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int checkValue(int value1, int value2) {
		if ((value1 - value2) == 0) {
			check = 2;
		} else if ((value1 - value2) == 1 || (value1 - value2) == -2) {
			check = 1;
		} else if ((value1 - value2) == 2 || (value1 - value2) == -1) {
			check = 3;
		}
		
		return check;
	}

	public void Paint(int num, JLabel label) {
		if (num == 1) {
			label.setIcon(paperIcon);
		//	label.setText("1");
		} else if (num == 2) {
			label.setIcon(scissorsIcon);
			//label.setText("2");
		} else if (num == 3) {
			label.setIcon(rockIcon);
		//	label.setText("3");
		} 
	}

	public void WriteDatatoFile() {
		File file = new File("OUTPUTDATA.txt");
		if (file.length() > 0) {
			try (Scanner sc = new Scanner(file)) {
				int num = sc.nextInt();
				if (num < mark) {
					try (PrintWriter pw = new PrintWriter(file)) {
						pw.println(mark);
					} catch (Exception e) {
						System.out.println("Write to file is not successful");
					}
				}
			} catch (Exception e) {
			}
		} else {
			try (PrintWriter pw = new PrintWriter(file)) {
				pw.println(mark);
			} catch (Exception e) {
				System.out.println("Write to file is not successful");
			}
		}
	}

	public JMenuItem createMenuitem(String title) {
		JMenuItem menuitem = new JMenuItem(title);
		menuitem.addActionListener(this);
		return menuitem;
	}

	public JMenu createMenu(String title) {
		JMenu menu = new JMenu(title);
		menu.addActionListener(this);
		return menu;
	}

	public void CreateMenu() {

		menu.setBounds(0, 0, 470, 30);
		FileMenu = createMenu("File");
		menu.add(FileMenu);

		ActionMenu = createMenu("Action");
		menu.add(ActionMenu);

		HelpMenu = createMenu("Help");
		menu.add(HelpMenu);

		NewGameItem = createMenuitem("New Game");
		FileMenu.add(NewGameItem);

		ExitItem = createMenuitem("Exit");
		FileMenu.add(ExitItem);

		AboutItem = createMenuitem("About");
		HelpMenu.add(AboutItem);

		PauseGame = createMenuitem("Pause Game");
		ActionMenu.add(PauseGame);

		ResumeGame = createMenuitem("Resume Game");
		ActionMenu.add(ResumeGame);
		
	}
	
	public void PauseGame() {
		timing = Integer.MAX_VALUE;
		TimeProgressBar.setValue(timing);
		RightButton.setEnabled(false);
		LeftButton.setEnabled(false);
		MiddleButton.setEnabled(false);
		L_Computer_Label.setIcon(null);
		R_Computer_Label.setIcon(null);
		truefalseLabel.setText("Pause");
	}
	
}
