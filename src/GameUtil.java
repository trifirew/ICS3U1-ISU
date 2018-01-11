import java.awt.*;

/**
 * Includes some useful method in the game
 */
public class GameUtil {

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

	/**
	 * Draw a happy face.
	 *
	 * @param g            the Graphics object to draw on
	 * @param x,y          the coordinates of the top-left corner of the image
	 * @param width,height the dimension of the image
	 */
	public static void drawHappyFace(Graphics g, int x, int y, int width, int height) {
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, width, height);
		g.setColor(Color.YELLOW);
		g.fillOval((int) (x + 0.25 * width), (int) (y + 0.28 * height), width / 5, height / 5);
		g.fillOval((int) (x + 0.6 * width), (int) (y + 0.28 * height), width / 5, height / 5);
		g.drawArc((int) (x + width * 0.2), (int) (y + height * 0.4), (int) (width * 0.6), (int) (height * 0.4), -45, -90);
	}

	/**
	 * Draw a Huaji face.
	 *
	 * @param g   the Graphics object to draw on
	 * @param x,y the coordinates of the top-left corner of the image
	 */
	public static void drawHuaji(Graphics g, int x, int y) {
		g.setColor(Color.YELLOW);
		// Face
		g.fillOval(x, y, 100, 100);
		g.setColor(Color.DARK_GRAY);

		g.drawArc(x + 8, y + 4, 24, 20, 0, 180);
		g.drawArc(x + 68, y + 4, 24, 20, 0, 180);
		// Eyes
		g.drawArc(x + 4, y + 20, 36, 20, 0, 180);
		g.drawArc(x + 60, y + 20, 36, 20, 0, 180);
		g.drawArc(x + 4, y + 28, 36, 8, 0, 180);
		g.drawArc(x + 60, y + 28, 36, 8, 0, 180);
		g.fillOval(x + 8, y + 22, 8, 8);
		g.fillOval(x + 64, y + 22, 8, 8);
		g.setColor(Color.PINK);
		g.fillOval(x + 6, y + 36, 28, 12);
		g.fillOval(x + 66, y + 36, 28, 12);
		// Mouth
		g.setColor(Color.BLACK);
		g.drawArc(x + 10, y + 20, 80, 70, 0, -180);
	}
}
