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
		return y + height >= Tube.GROUND;
	}

	/**
	 * Draw the Bird.
	 *
	 * @param g the Graphics object to draw on
	 */
	public void draw(Graphics g) {
		// TODO: The image of the Bird
//		g.drawRect(x, y, width, height);
		drawHappyFace(g, x, y, width, height);
	}

	/**
	 * Draw a happy face.
	 *
	 * @param g            the Graphics object to draw on
	 * @param x,y          the coordinates of the top-left corner of the image
	 * @param width,height the dimension of the image
	 */
	public void drawHappyFace(Graphics g, int x, int y, int width, int height) {
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, width, height);
		g.setColor(Color.YELLOW);
		g.fillOval((int) (x + 0.25 * width), (int) (y + 0.28 * height), width / 5, height / 5);
		g.fillOval((int) (x + 0.6 * width), (int) (y + 0.28 * height), width / 5, height / 5);
		g.drawArc((int) (x + width * 0.2), (int) (y + height * 0.4), (int) (width * 0.6), (int) (height * 0.4), -45, -90);
	}

}
