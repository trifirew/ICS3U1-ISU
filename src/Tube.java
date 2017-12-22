import java.awt.*;

/**
 * The Tube class
 * The tubes in the game Flappy Bird which the player should avoid from hitting onto them.
 */
public class Tube {

	int x;
	int gapY;
	int width = 88;
	private int gapHeight = 200;
	private int speed = 1;
	boolean passed;

	/**
	 * Construct a new Tube.
	 *
	 * @param x the x coordinate where the Tube initially is at
	 */
	public Tube(int x) {
		this.x = x;
		gapY = FlappyBird.rand(150, 450);
		passed = false;
	}

	/**
	 * Move the Tube to the left a little bit.
	 */
	public void moveLeft() {
		x -= speed;
		if (x + width < 0) {
			x = FlappyBird.WIDTH;
			gapY = FlappyBird.rand(150, 450);
			passed = false;
		}
	}

	/**
	 * Draw the tube on the screen.
	 *
	 * @param g the Graphics object to draw on
	 */
	public void draw(Graphics g) {
		// Draw the tubes
		int h = FlappyBird.HEIGHT;
		// The upper tube
		fillRectWithBorder(g, x + 10, 0, width - 20, gapY - 40);
		// The nozzle of the upper tube
		fillRectWithBorder(g, x, gapY - 40, width, 40);
		// The lower tube
		fillRectWithBorder(g, x + 10, gapY + gapHeight + 40, width - 20, h - (gapY + gapHeight + 40));
		// The nozzle of the lower tube
		fillRectWithBorder(g, x, gapY + gapHeight, width, 40);
	}

	/**
	 * Draw a filled rectangle with border
	 *
	 * @param g            the Graphics object to draw on
	 * @param x,y          the coordinates of the upper-left corner of the rectangle
	 * @param width,height the width and height of the rectangle
	 */
	private void fillRectWithBorder(Graphics g, int x, int y, int width, int height) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
	}
}
