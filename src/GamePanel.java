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
	private Tube[] tubes = {new Tube(480 + 120), new Tube(480 + 240 + 44 + 120)};
	private Bird bird = new Bird();
	private int score;
	private int life;
	private boolean started;

	// Set the Timer for the motion of the Tubes and the Bird
	private Timer motionTimer = new Timer(10, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Tube tube : tubes) {
				tube.moveLeft();
				if (collide(bird, tube)) {
					System.out.println("collide");
					// Minus one life
					if (!tube.crashed) life--;
					System.out.println(life);
					if (life == 0) gameover();
					// If player still have life, allow them to break the Tube once
					tube.gapY = -1;
					tube.gapHeight = FlappyBird.H;
					tube.crashed = true;
				}
			}
			// Make the Bird free fall
			bird.fall();
			// Check if the Bird hit the ground
			if (bird.hitBorder()) gameover();
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
	 * Check if the Tube collides with the Bird.
	 *
	 * @param bird the Bird object to check
	 * @return true if collide occurs
	 */
	private boolean collide(Bird bird, Tube tube) {
		if (tube.x > bird.x + bird.width || tube.x + tube.width < bird.x) {
			return false;
		}
		return (bird.y <= tube.gapY || bird.y + bird.height >= tube.gapY + tube.gapHeight);
	}

	/**
	 * Check if the Bird pass a Tube.
	 */
	private void checkScore() {
		for (Tube tube : tubes) {
			if (!tube.scored && bird.x + bird.width >= tube.x + tube.width) {
				tube.scored = true;
				score++;
			}
		}
		scoreLabel.setText(Integer.toString(score));
	}

	/**
	 * When the Bird hits something, the game is over.
	 * Perform actions after the game is over.
	 */
	private void gameover() {
		motionTimer.stop();
		scoreLabel.setVisible(false);
		FlappyBird.frame.changePanel(FlappyBird.gamePanel, FlappyBird.scorePanel);
		FlappyBird.scorePanel.onShow(score, FlappyBird.gamePanel.createBI());
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
					bird.jump();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
	}

	/**
	 * Action when the panel is shown on screen. Initialize the screen.
	 */
	public void init() {
		// Display score when playing
		scoreLabel.setVisible(true);
		scoreLabel.setText("Press SPACE to start");
		scoreLabel.setFont(fontHint);
		requestFocusInWindow();
		tubes = new Tube[]{new Tube(480 + 120), new Tube(480 + 240 + 44 + 120)};
		bird = new Bird();
		score = 0;
		life = 1;
		started = false;
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (offScreenG == null) {
			offScreenI = createImage(FlappyBird.W, FlappyBird.H);
			offScreenG = offScreenI.getGraphics();
		}
		offScreenG.clearRect(0, 0, FlappyBird.W, FlappyBird.H);

		offScreenG.drawImage(FlappyBird.bg, 0, 0, this);

		for (Tube tube : tubes) {
			tube.draw(offScreenG);
		}
		bird.draw(offScreenG);
		g.drawImage(offScreenI, 0, 0, this);
	}

	/**
	 * Create a BufferedImage (screenshot) of current screen.
	 *
	 * @return a BufferedImage of current screen
	 */
	public BufferedImage createBI() {
		BufferedImage bi = new BufferedImage(FlappyBird.W, FlappyBird.H, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		paint(g);
		return bi;
	}
}