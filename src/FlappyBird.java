/*
Keisun Wu, Yitian Zhao
December 20, 2017

ICS3U1 - ISU
Flappy Bird
This is a Flappy Bird game made using JFrame.
 */

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * The main game class of Flappy Bird
 *
 * @author Keisun, Yitian
 * @since December 20, 2017
 */
public class FlappyBird {
	/**
	 * The dimension of the game window
	 */
	public static final int W = 480, H = 800;

	// The panels used in the game as different screens
	public static FlappyBirdFrame frame;
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

	public static void main(String[] args) {
		frame = new FlappyBirdFrame();

		loadFonts();
		loadBackground();

		mainMenuPanel = new MainMenuPanel();
		gamePanel = new GamePanel();
		scorePanel = new ScorePanel();
		frame.setVisible(true);
		frame.showPanel(mainMenuPanel);
	}

	/**
	 * Load the base fonts from font resource files. (Source: Internet)
	 */
	private static void loadFonts() {
		try {
			fontBase = Font.createFont(Font.TRUETYPE_FONT, new File("res/minecraftia.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(fontBase);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load the background image of the game.
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
	 * Delay for a given number of milliseconds.
	 *
	 * @param ms time period to delay
	 */
	public static void delay(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ignored) {
		}
	}

	/**
	 * Generate a random integer between a and b inclusively.
	 *
	 * @param a lower bound of the random integer
	 * @param b higher bound of the random integer
	 * @return a random integer
	 */
	public static int rand(int a, int b) {
		return (int) (Math.random() * (b - a + 1) + a);
	}
}
