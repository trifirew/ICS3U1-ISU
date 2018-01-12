import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * The game screen
 *
 * @author Keisun, Yitian
 * @since December 20, 2017
 */
public class GamePanel extends JPanel {

	// Offscreen buffer to avoid flickering
	private Image offScreenI;
	private Graphics offScreenG;

	// UI variables
	private Font fontScore = FlappyBird.fontBase.deriveFont(36f);
	private Font fontHint = FlappyBird.fontBase.deriveFont(18f);
	private JLabel scoreLabel;

	// Game variables
	/**
	 * The current score of the player
	 */
	private int score;
	/**
	 * The number of lives left
	 */
	private int life;
	/**
	 * Whether the game is started
	 */
	private boolean started;
	/**
	 * The Y-coordinate of the ground of the game
	 */
	private final int GROUND = 613;
	/**
	 * The gravity of the game, related to acceleration
	 */
	private final double GRAVITY = 0.32;

	// Bird
	/**
	 * The coordinates of the bird
	 */
	private int birdX, birdY;
	/**
	 * The dimension of the bird
	 */
	private int birdW = 40, birdH = 40;
	/**
	 * The speed of the bird
	 */
	private double birdSpeed;
	/**
	 * The speed of the bird when it jumps
	 */
	private double jumpSpeed = -8;

	// Tubes
	private int[] tubesX = new int[2];
	/**
	 * The Y-coordinate of the top of the gap of the tube
	 */
	private int[] tubesGapY = new int[2];
	/**
	 * The width of the tube
	 */
	private int tubeW = 88;
	/**
	 * The height of the gap of the tube
	 */
	private int[] tubesGapH = new int[2];
	/**
	 * The moving speed of the tube
	 */
	private int tubeSpeed = -3;
	/**
	 * Whether the bird already scored by passing the tube
	 */
	private boolean[] tubesScored = new boolean[2];
	/**
	 * Whether the bird already crashed on the tube
	 */
	private boolean[] tubesCrashed = new boolean[2];

	// Set the Timer for the motion of the tubes and the bird
	private Timer motionTimer = new Timer(10, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 2; i++) {
				// Move the tubes left
				tubesX[i] += tubeSpeed;
				// If the tube goes pass the left border of the screen, move it to the right border of the screen
				if (tubesX[i] + tubeW < 0) {
					tubesX[i] = FlappyBird.W;
					tubesGapH[i] = 200;
					tubesGapY[i] = GameUtil.rand(50, GROUND - tubesGapH[i] - 50);
					tubesScored[i] = false;
				}
				// Check if the bird crash on the tube
				if (collide(i)) {
					System.out.println("collide");
					// Reduce life by one after crash
					if (!tubesCrashed[i]) life--;
					System.out.println(life);
					if (life == 0) gameover();
					// If player still have life, allow them to break the Tube once
					tubesGapY[i] = -1;
					tubesGapH[i] = FlappyBird.H;
					tubesCrashed[i] = true;
				}
			}
			// Make the bird free fall
			birdY += birdSpeed;
			birdSpeed += GRAVITY;
			System.out.println(birdY);
			System.out.println(birdSpeed);
			// Check if the bird hit the ground
			if (birdY + birdH >= GROUND) gameover();
			// Check if the player get a score
			checkScore();

			FlappyBird.gamePanel.repaint();
		}
	});

	/**
	 * Start moving the bird and the tubes after player press SPACE for the first time.
	 */
	public void startMoving() {
		motionTimer.start();
		started = true;
	}

	/**
	 * Check if the tube collides with the bird.
	 *
	 * @param tubeIndex the index of the tube to check
	 * @return true if collide occurs
	 */
	private boolean collide(int tubeIndex) {
		if (tubesX[tubeIndex] > birdX + birdW || tubesX[tubeIndex] + tubeW < birdX) {
			return false;
		}
		return (birdY <= tubesGapY[tubeIndex] || birdY + birdH >= tubesGapY[tubeIndex] + tubesGapH[tubeIndex]);
	}

	/**
	 * Check if the bird pass a tube.
	 */
	private void checkScore() {
		for (int i = 0; i < 2; i++) {
			if (!tubesScored[i] && birdX + birdW >= tubesX[i] + tubeW) {
				tubesScored[i] = true;
				score++;
			}
		}
		scoreLabel.setText(Integer.toString(score));
	}

	/**
	 * Perform actions after the player loses the game.
	 */
	private void gameover() {
		motionTimer.stop();
		scoreLabel.setVisible(false);
		FlappyBird.changePanel(FlappyBird.gamePanel, FlappyBird.scorePanel);
		FlappyBird.scorePanel.init(score, FlappyBird.gamePanel.createBI());
	}

	/**
	 * Construct the panel for the actual game. Set up the tubes.
	 */
	public GamePanel() {
		setPreferredSize(new Dimension(FlappyBird.W, FlappyBird.H));
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
					if (!started) {
						startMoving();
						scoreLabel.setText("");
						scoreLabel.setFont(fontScore);
					}
					// Make the bird jump
					birdSpeed = jumpSpeed;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
	}

	/**
	 * Initialize the screen when it is shown.
	 */
	public void init() {
		// Display score when playing
		scoreLabel.setVisible(true);
		scoreLabel.setText("Press SPACE to start");
		scoreLabel.setFont(fontHint);
		requestFocusInWindow();
		score = 0;
		life = 1;
		started = false;
		// Set up the bird
		birdX = 120;
		birdY = (GROUND - birdH) / 2;
		birdSpeed = 0;
		// Set up the tubes
		for (int i = 0; i < 2; i++) {
			tubesX[i] = (FlappyBird.W + 120) + i * (FlappyBird.W + tubeW) / 2;
			tubesGapH[i] = 200;
			tubesGapY[i] = GameUtil.rand(50, GROUND - tubesGapH[i] - 50);
			tubesScored[i] = false;
			tubesCrashed[i] = false;
		}
	}

	/**
	 * Draw the bird on the screen.
	 *
	 * @param g the Graphics object to draw on
	 */
	private void drawBird(Graphics g) {
		// TODO: The image of the Bird
//		g.drawRect(birdX, birdY, birdW, birdH);
		GameUtil.drawHappyFace(g, birdX, birdY, birdW, birdH);
	}

	/**
	 * Draw the tube on the screen.
	 *
	 * @param g the Graphics object to draw on
	 * @param i the index of the tube to draw
	 */
	public void drawTube(Graphics g, int i) {
		// Draw the tubes
		g.setColor(Color.GREEN);
		// The upper tube
		g.fillRect(tubesX[i] + 10, 0, tubeW - 20, tubesGapY[i] - 40);
		// The nozzle of the upper tube
		g.fillRect(tubesX[i], tubesGapY[i] - 40, tubeW, 40);
		// The lower tube
		g.fillRect(tubesX[i] + 10, tubesGapY[i] + tubesGapH[i] + 40, tubeW - 20, GROUND - (tubesGapY[i] + tubesGapH[i] + 40));
		// The nozzle of the lower tube
		g.fillRect(tubesX[i], tubesGapY[i] + tubesGapH[i], tubeW, 40);
		g.setColor(Color.BLACK);
		// The upper tube
		g.drawRect(tubesX[i] + 10, 0, tubeW - 20, tubesGapY[i] - 40);
		// The nozzle of the upper tube
		g.drawRect(tubesX[i], tubesGapY[i] - 40, tubeW, 40);
		// The lower tube
		g.drawRect(tubesX[i] + 10, tubesGapY[i] + tubesGapH[i] + 40, tubeW - 20, GROUND - (tubesGapY[i] + tubesGapH[i] + 40));
		// The nozzle of the lower tube
		g.drawRect(tubesX[i], tubesGapY[i] + tubesGapH[i], tubeW, 40);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (offScreenG == null) {
			offScreenI = createImage(FlappyBird.W, FlappyBird.H);
			offScreenG = offScreenI.getGraphics();
		}
		offScreenG.clearRect(0, 0, FlappyBird.W, FlappyBird.H);
		offScreenG.drawImage(FlappyBird.bg, 0, 0, this);

		for (int i = 0; i < 2; i++) drawTube(offScreenG, i);
		drawBird(offScreenG);

		g.drawImage(offScreenI, 0, 0, this);
	}

	/**
	 * Create a BufferedImage (screenshot) of current screen.
	 *
	 * @return a BufferedImage of current screen
	 */
	private BufferedImage createBI() {
		BufferedImage bi = new BufferedImage(FlappyBird.W, FlappyBird.H, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		paint(g);
		return bi;
	}
}