/*
Keisun Wu, Yitian Zhao
December 20, 2017

ICS3U1 - ISU
Flappy Bird
This is a Flappy Bird game made using JFrame.
 */

/**
 * The main game class of Flappy Bird
 *
 * @author Keisun, Yitian
 * @version 0.1
 * @since December 20, 2017
 */
public class FlappyBird {

	public static FlappyBirdFrame frame;
	public static MainMenuPanel mainMenuPanel;
	public static GamePanel gamePanel;
	public static ScorePanel scorePanel;

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static void main(String[] args) {
		frame = new FlappyBirdFrame();
		mainMenuPanel = new MainMenuPanel();
		gamePanel = new GamePanel();
		scorePanel = new ScorePanel();
		frame.setVisible(true);
		frame.showPanel(mainMenuPanel);
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
}
