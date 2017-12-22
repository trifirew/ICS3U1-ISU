import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * The game screen
 *
 * @author Keisun, Yitian
 * @version 0.4
 * @since December 20, 2017
 */
public class GamePanel extends JPanel {

	private final int WIDTH = FlappyBird.WIDTH;
	private final int HEIGHT = FlappyBird.HEIGHT;

	private GameControl game;

	private Image offScreenI;
	private Graphics offScreenG;
	
	JLabel scoreLabel;

	private Font fontScore = FlappyBird.fontBase.deriveFont(36f);
	private Font fontHint = FlappyBird.fontBase.deriveFont(18f);
	
	/**
	 * Construct the panel for the actual game. Set up the tubes.
	 */
	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		scoreLabel = new JLabel("Press SPACE to start");
		scoreLabel.setFont(fontHint);
		layout.putConstraint(SpringLayout.NORTH, scoreLabel, 96, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scoreLabel, 0, SpringLayout.HORIZONTAL_CENTER, this);
		add(scoreLabel);
		// Set KeyListener
		setFocusable(true);
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (!game.started) {
						game.startMoving();
						scoreLabel.setFont(fontScore);
					}
					game.bird.jump();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
	}

	public void start() {
		game = new GameControl();
		requestFocusInWindow();
		// Display score when playing
		scoreLabel.setVisible(true);
		scoreLabel.setText("Press SPACE to start");
		scoreLabel.setFont(fontHint);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (offScreenG == null) {
			offScreenI = createImage(WIDTH, HEIGHT);
			offScreenG = offScreenI.getGraphics();
		}
		offScreenG.clearRect(0, 0, WIDTH, HEIGHT);

		for (Tube tube : game.tubes) {
			tube.draw(offScreenG);
		}
		game.bird.draw(offScreenG);

		g.drawImage(offScreenI, 0, 0, this);
	}

	public BufferedImage createBI() {
		BufferedImage bi = new BufferedImage(FlappyBird.WIDTH, FlappyBird.HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		paint(g);
		return bi;
	}
}

/**
 * GameControl class
 * This class controls the moving of the objects on the screen, and checks for collision and other events in the game.
 * 
 * @author Keisun, Yitian
 * @version 0.4
 * @since December 21, 2017
 */
class GameControl {

	Tube[] tubes = {new Tube(480 + 120), new Tube(480 + 240 + 44 + 120)};
	Bird bird = new Bird();
	int score;
	boolean started;

	// Set the Timer for the motion of the Tubes
	private Timer tubeTimer = new Timer(5, e -> {
		for (Tube tube : tubes) {
			tube.moveLeft();
			if (collide(bird, tube)) {
				gameover();
			}
		}
		checkScore();
		FlappyBird.gamePanel.repaint();
	});

	// Set the Timer for the motion of the Bird
	private Timer birdTimer = new Timer(10, e -> {
		bird.fall();
		System.out.println(bird.speed);
		if (bird.hitBorder()) {
			GameControl.this.gameover();
		}
	});

	/**
	 * Start a new game.
	 */
	GameControl() {
		score = 0;
		started = false;
	}

	/**
	 * Start moving the bird and the tubes after player press SPACE for the first time.
	 */
	public void startMoving() {
		tubeTimer.start();
		birdTimer.start();
		started = true;
	}

	/**
	 * Check if the Tube collides with the Bird.
	 *
	 * @param bird the Bird object to check
	 * @return true if collide occurs
	 */
	private boolean collide(Bird bird, Tube tube) {
		if (tube.x > bird.x + bird.width || tube.x + tube.width < bird.x) {
			return false;
		}
		if (bird.y <= tube.gapY || bird.y + bird.height >= tube.gapY + 200) {
			return true;
		}
		return false;
	}

	/**
	 * Check if the Bird pass a tube.
	 */
	private void checkScore() {
		for (Tube tube : tubes) {
			if (!tube.passed && bird.x + bird.width >= tube.x + tube.width) {
				tube.passed = true;
				score++;
			}
		}
		FlappyBird.gamePanel.scoreLabel.setText(Integer.toString(score));
	}

	/**
	 * When the Bird hits something, the game is over.
	 * Perform actions after the game is over.
	 */
	private void gameover() {
		tubeTimer.stop();
		birdTimer.stop();
		FlappyBird.gamePanel.scoreLabel.setVisible(false);
		FlappyBird.frame.changePanel(FlappyBird.gamePanel, FlappyBird.scorePanel);
		FlappyBird.scorePanel.start(this, FlappyBird.gamePanel.createBI());
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