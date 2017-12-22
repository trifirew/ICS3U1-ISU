import java.awt.*;

/**
 * The Bird class
 * The bird in the game that is jumping and falling to avoid the tubes.
 */
public class Bird {

	int x, y;
	int width, height;
	double speed;
	private double jumpSpeed = -6;
	private double gravity = 0.225;
	
	/**
	 * Construct the Bird object in the game.
	 */
	public Bird() {
		width = 32;
		height = 32;
		x = 120;
		y = (FlappyBird.HEIGHT - height) / 2;
		speed = 0;
	}

	/**
	 * Make the bird fall to the ground.
	 */
	public void fall() {
		y += speed;
		speed += gravity;
	}

	/**
	 * Make the bird jumps.
	 */
	public void jump() {
		speed = jumpSpeed;
	}

	/**
	 * Check if the Bird hit the border.
	 *
	 * @return true if the Bird hit the border
	 */
	public boolean hitBorder() {
		if (y + height >= FlappyBird.HEIGHT)
			return true;
		return false;
	}

	/**
	 * Draw the Bird.
	 *
	 * @param g the Graphics object to draw on
	 */
	public void draw(Graphics g) {
		// TODO: The image of the Bird
		g.drawRect(x, y, width, height);
	}
}
