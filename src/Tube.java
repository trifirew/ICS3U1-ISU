import java.awt.*;

/**
 * The Tube class
 * The tubes in the game Flappy Bird which the player should avoid from hitting onto them.
 */
public class Tube {

	/**
	 * The Y-coordinate of the ground
	 */
	static final int GROUND = 613;

	int x;
	/**
	 * The Y-coordinate of the top of the gap of the Tube
	 */
	int gapY;
	/**
	 * The width of the Tube
	 */
	int width = 88;
	/**
	 * The height of the gap of the Tube
	 */
	int gapHeight;
	/**
	 * The moving speed of the Tube
	 */
	private int speed = 3;

	boolean scored, crashed;

	/**
	 * Construct a new Tube.
	 *
	 * @param x the X-coordinate where the Tube initially is at
	 */
	public Tube(int x) {
		this.x = x;
		gapHeight = 200;
		gapY = FlappyBird.rand(50, GROUND - gapHeight - 50);
		scored = false;
		crashed = false;
	}

	/**
	 * Move the Tube to the left a little bit.
	 */
	public void moveLeft() {
		x -= speed;
		if (x + width < 0) {
			x = FlappyBird.W;
			gapHeight = 200;
			gapY = FlappyBird.rand(50, GROUND - gapHeight - 50);
			scored = false;
		}
	}

	/**
	 * Draw the tube on the screen.
	 *
	 * @param g the Graphics object to draw on
	 */
	public void draw(Graphics g) {
		// Draw the tubes
		g.setColor(Color.GREEN);
		// The upper tube
		g.fillRect(x + 10, 0, width - 20, gapY - 40);
		// The nozzle of the upper tube
		g.fillRect(x, gapY - 40, width, 40);
		// The lower tube
		g.fillRect(x + 10, gapY + gapHeight + 40, width - 20, GROUND - (gapY + gapHeight + 40));
		// The nozzle of the lower tube
		g.fillRect(x, gapY + gapHeight, width, 40);
		g.setColor(Color.BLACK);
		// The upper tube
		g.drawRect(x + 10, 0, width - 20, gapY - 40);
		// The nozzle of the upper tube
		g.drawRect(x, gapY - 40, width, 40);
		// The lower tube
		g.drawRect(x + 10, gapY + gapHeight + 40, width - 20, GROUND - (gapY + gapHeight + 40));
		// The nozzle of the lower tube
		g.drawRect(x, gapY + gapHeight, width, 40);
	}

}