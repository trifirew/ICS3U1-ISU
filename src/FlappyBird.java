/*
Keisun Wu, Yitian Zhao
December 20, 2017

ICS3U1 - ISU
Flappy Bird
This is a Flappy Bird game made using JFrame.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

/**
 * The main game class of Flappy Bird
 *
 * @author Keisun, Yitian
 * @since December 20, 2017
 */
public class FlappyBird extends JFrame {
	/**
	 * The dimension of the game window
	 */
	public static final int W = 480, H = 800;

	// The frame of the game
	private static FlappyBird frame;
	// The panels used in the game as different screens
	public static MainMenuPanel mainMenuPanel;
	public static GamePanel gamePanel;
	public static ScorePanel scorePanel;

	/**
	 * Base font of the game, used to derive different sizes
	 */
	public static Font fontBase;
	/**
	 * The background image of the game
	 */
	public static Image bg;

	/**
	 * Construct a new frame for the game.
	 */
	public FlappyBird() {
		super("Flappy Bird");
		// Prevent player from changing the size of the window
		setResizable(false);
		// Set action when player tries to close the window
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO: Action on window closing
				String[] options = {"Yes", "No"};
				int prompt = JOptionPane.showOptionDialog(FlappyBird.this,
						"Do you really want to exit?", "Exit",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						null,
						options, options[1]);
				if (prompt == 0)
					System.exit(0);
			}
		});
		// Set icon of the window
		Toolkit tk = Toolkit.getDefaultToolkit();
		setIconImage(tk.getImage("res/icon.png"));
		Dimension screenSize = tk.getScreenSize();
		// Set the size of the window and move it to the centre of the screen
		setBounds((screenSize.width - W) / 2, (screenSize.height - H) / 2, W, H);
	}

	/**
	 * Load the base fonts from font resource files. (Source: Internet)
	 */
	private static void loadFonts() {
		try {
			fontBase = Font.createFont(Font.TRUETYPE_FONT, new File("res/minecraftia.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(fontBase);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load the background image of the game.
	 * Adapted from ConnectFour assignment.
	 */
	private static void loadBackground() {
		MediaTracker tracker = new MediaTracker(frame);
		bg = Toolkit.getDefaultToolkit().getImage("res/bg.png");
		tracker.addImage(bg, 0);

		//  Wait until all of the images are loaded
		try {
			tracker.waitForAll();
		} catch (InterruptedException ignored) {
		}
	}

	/**
	 * Show a specific panel on the frame.
	 *
	 * @param panel the panel to show on the frame.
	 */
	public static void showPanel(JPanel panel) {
		frame.add(panel);
		frame.pack();
	}

	/**
	 * Change from a panel to another panel.
	 *
	 * @param close the panel to close
	 * @param open the panel to open
	 */
	public static void changePanel(JPanel close, JPanel open) {
		frame.remove(close);
		frame.add(open);
		frame.validate();
		frame.repaint();
	}

	public static void main(String[] args) {
		frame = new FlappyBird();
		loadFonts();
		loadBackground();

		mainMenuPanel = new MainMenuPanel();
		gamePanel = new GamePanel();
		scorePanel = new ScorePanel();
		frame.setVisible(true);
		showPanel(mainMenuPanel);
	}
}
