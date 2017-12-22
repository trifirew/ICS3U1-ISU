import java.awt.*;

/**
 * The Bird class
 * The bird in the game that is jumping and falling to avoid the tubes.
 */
public class Bird {

	int x, y;
	int width, height;
	float speed;
	private float jumpSpeed = -7.2f;
	private float gravity = 0.256f;
	
	/**
	 * Construct the Bird object in the game.
	 */
	public Bird() {
		width = 32;
		height = 32;
		x = 120;
		y = (FlappyBird.H - height) / 2;
		speed = 0;
	}

	/**
	 * Make the Bird fall to the ground.
	 */
	public void fall() {
		y += speed;
		speed += gravity;
	}

	/**
	 * Make the Bird jump.
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
		return y + height >= FlappyBird.H;
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
