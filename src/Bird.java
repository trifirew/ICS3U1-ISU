import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bird {

	int x, y;
	int width, height;
	int speed;
	int score;

	private Timer acceleration;

	/**
	 * Construct the Bird object in the game.
	 */
	public Bird() {
		width = 32;
		height = 32;
		x = 120;
		y = (FlappyBird.HEIGHT - height) / 2;
		speed = 1;
		score = 0;

//		acceleration = new Timer(500, new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				speed += 1;
//			}
//		});
//		acceleration.start();
	}

	/**
	 * Make the bird fall to the ground.
	 */
	public void fall() {
		// TODO: Acceleration
		y += speed;
	}

	/**
	 * Make the bird jumps.
	 */
	public void jump() {
		// TODO: Better jump move
		y -= 100;
	}

	/**
	 * Check if the Bird hit the border.
	 *
	 * @return true if the Bird hit the border
	 */
	public boolean hitBorder() {
		if (y <= 0 || y + height >= FlappyBird.HEIGHT)
			return true;
		return false;
	}

	/**
	 * When going pass a Tube, earn one point.
	 *
	 * @param tube the Tube to check
	 */
	public void earnScore(Tube tube) {
		if (x + width == tube.x + tube.width) {
			score++;
		}
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

	/**
	 * Draw the score on the screen.
	 *
	 * @param g the Graphics object to draw on
	 */
	public void drawScore(Graphics g) {
		// TODO: Better appearance
		g.drawString("" + score, 240, 100);
	}
}
