import java.awt.*;

/**
 * The Tube class
 * The tubes in the game Flappy Bird which the player should avoid from hitting onto them.
 */
public class Tube {

	int x;
	int gapY;
	int width = 88;
	int height = FlappyBird.HEIGHT;
	int speed = 1;

	/**
	 * Construct a new Tube.
	 *
	 * @param x the x coordinate where the Tube initially is at
	 */
	public Tube(int x) {
		this.x = x;
		gapY = FlappyBird.rand(150, 450);
	}

	/**
	 * Move the Tube to the left a little bit.
	 */
	public void moveLeft() {
		x -= speed;
		if (x + 88 < 0) {
			x = FlappyBird.WIDTH;
			gapY = FlappyBird.rand(150, 450);
		}
	}

	/**
	 * Check if the Tube collides with the Bird.
	 *
	 * @param bird the Bird object to check
	 * @return true if collide occurs
	 */
	public boolean collide(Bird bird) {
		if (x > bird.x + bird.width || x + width < bird.x) {
			return false;
		}
		if (bird.y <= gapY || bird.y + bird.height >= gapY + 200) {
			return true;
		}
		return false;
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
		g.fillRect(x + 10, gapY + 240, width - 20, height - (gapY + 240));
		// The nozzle of the lower tube
		g.fillRect(x, gapY + 200, width, 40);
		// Draw the border lines
		g.setColor(Color.BLACK);
		g.drawRect(x + 10, 0, width - 20, gapY - 40);
		g.drawRect(x, gapY - 40, width, 40);
		g.drawRect(x + 10, gapY + 240, width - 20, height - (gapY + 240));
		g.drawRect(x, gapY + 200, width, 40);
	}
}
