import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The game screen
 *
 * @author Keisun, Yitian
 * @version 0
 * @since December 20, 2017
 */
public class GamePanel extends JPanel {

	private final int WIDTH = 480;
	private final int HEIGHT = 800;

	private Tube[] tubes = {new Tube(480 + 120), new Tube(480 + 240 + 44 + 120)};
	private Bird bird = new Bird();

	private Image offScreenI;
	private Graphics offScreenG;
	private Timer tubeTimer, birdTimer;

	/**
	 * Construct the panel for the actual game. Set up the tubes.
	 */
	public GamePanel() {
		setPreferredSize(new Dimension(480, 800));

		tubeTimer = new Timer(5, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (Tube tube : tubes) {
					tube.moveLeft();
					if (tube.collide(bird)) {
						tubeTimer.stop();
						birdTimer.stop();
					}
				}
				repaint();
			}
		});
		
		birdTimer = new Timer(5, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bird.fall();
				if (bird.hitBorder()) {
					tubeTimer.stop();
					birdTimer.stop();
				}
				for (Tube tube : tubes) {
					bird.earnScore(tube);
				}
			}
		});
		tubeTimer.start();
		birdTimer.start();
		
		KeyListener keyListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("Key");
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					bird.jump();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		};

		MouseListener mouseListener = new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				bird.jump();
			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		};
		
		setFocusable(true);
		addKeyListener(keyListener);
		addMouseListener(mouseListener);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (offScreenG == null) {
			offScreenI = createImage(WIDTH, HEIGHT);
			offScreenG = offScreenI.getGraphics();
		}
		offScreenG.clearRect(0, 0, WIDTH, HEIGHT);

		for (Tube tube : tubes) {
			tube.draw(offScreenG);
		}
		bird.draw(offScreenG);
		bird.drawScore(offScreenG);

		g.drawImage(offScreenI, 0, 0, this);
	}
}
