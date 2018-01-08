import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The score screen after a game is over
 *
 * @author Keisun, Yitian
 * @since December 21, 2017
 */
public class ScorePanel extends JPanel {

	/**
	 * Screenshot of previous game
	 */
	private BufferedImage gameImage;

	private ScoreCard scoreCard;

	/**
	 * Construct the screen to display score.
	 */
	public ScorePanel() {
		setPreferredSize(new Dimension(FlappyBird.W, FlappyBird.H));
		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		Font fontButton = FlappyBird.fontBase.deriveFont(14f);

		scoreCard = new ScoreCard();
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scoreCard, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scoreCard, 0, SpringLayout.VERTICAL_CENTER, this);

		JButton btnRestart = new JButton("Play");
		btnRestart.setFont(fontButton);
		layout.putConstraint(SpringLayout.NORTH, btnRestart, 16, SpringLayout.SOUTH, scoreCard);
		layout.putConstraint(SpringLayout.WEST, btnRestart, 0, SpringLayout.WEST, scoreCard);
		layout.putConstraint(SpringLayout.EAST, btnRestart, -12, SpringLayout.HORIZONTAL_CENTER, this);

		JButton btnLeaderboard = new JButton("Leaderboard");
		btnLeaderboard.setFont(fontButton);
		layout.putConstraint(SpringLayout.NORTH, btnLeaderboard, 16, SpringLayout.SOUTH, scoreCard);
		layout.putConstraint(SpringLayout.EAST, btnLeaderboard, 0, SpringLayout.EAST, scoreCard);
		layout.putConstraint(SpringLayout.WEST, btnLeaderboard, 12, SpringLayout.HORIZONTAL_CENTER, this);

		// Create ActionListeners for the buttons
		btnRestart.addActionListener(e -> {
			System.out.println("Play");
			FlappyBird.frame.changePanel(ScorePanel.this, FlappyBird.gamePanel);
			FlappyBird.gamePanel.init();
		});
		btnLeaderboard.addActionListener(e -> {
			// TODO: Leaderboard screen
			System.out.println("Leaderboard");
		});

		// Add the components
		add(scoreCard);
		add(btnRestart);
		add(btnLeaderboard);
	}

	/**
	 * Action when the panel is shown on screen. Initialize the screen.
	 */
	public void onShow(int score, BufferedImage image) {
		this.gameImage = image;
		scoreCard.lbScore.setText(Integer.toString(score));
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Draw the previous game screen as background
		g.drawImage(gameImage, 0, 0, this);
	}
}

/**
 * The card to show the player score and best score.
 *
 * @author Keisun, Yitian
 * @since December 21, 2017
 */
class ScoreCard extends JPanel {

	JLabel lbScore, lbBest;

	/**
	 * Construct the card to display scores.
	 */
	ScoreCard() {
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		setBackground(Color.CYAN);

		Font fontScore = FlappyBird.fontBase.deriveFont(32f);
		Font fontCaption = FlappyBird.fontBase.deriveFont(20f);

		JLabel lbScoreCaption = new JLabel("Score");
		lbScoreCaption.setFont(fontCaption);
		lbScoreCaption.setHorizontalTextPosition(SwingConstants.RIGHT);
		layout.putConstraint(SpringLayout.EAST, lbScoreCaption, -16, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, lbScoreCaption, 16, SpringLayout.NORTH, this);

		lbScore = new JLabel("0");
		lbScore.setFont(fontScore);
		lbScore.setHorizontalTextPosition(SwingConstants.RIGHT);
		layout.putConstraint(SpringLayout.EAST, lbScore, 0, SpringLayout.EAST, lbScoreCaption);
		layout.putConstraint(SpringLayout.NORTH, lbScore, 4, SpringLayout.SOUTH, lbScoreCaption);

		JLabel lbBestCaption = new JLabel("Best");
		lbBestCaption.setFont(fontCaption);
		lbBestCaption.setHorizontalTextPosition(SwingConstants.RIGHT);
		layout.putConstraint(SpringLayout.EAST, lbBestCaption, -16, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, lbBestCaption, 16, SpringLayout.SOUTH, lbScore);

		lbBest = new JLabel("0");
		lbBest.setFont(fontScore);
		lbBest.setHorizontalTextPosition(SwingConstants.RIGHT);
		layout.putConstraint(SpringLayout.EAST, lbBest, 0, SpringLayout.EAST, lbBestCaption);
		layout.putConstraint(SpringLayout.NORTH, lbBest, 4, SpringLayout.SOUTH, lbBestCaption);

		// Make the size of the card fit the contents
		layout.putConstraint(SpringLayout.SOUTH, this, 16, SpringLayout.SOUTH, lbBest);

		add(lbScoreCaption);
		add(lbBestCaption);
		add(lbScore);
		add(lbBest);

		// Set the width of the card, the height is the preferred size
		setPreferredSize(new Dimension(360, getPreferredSize().height));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawHuaji(g, 32, 52);
	}

	/**
	 * Draw a Huaji face.
	 *
	 * @param g   the Graphics object to draw on
	 * @param x,y the coordinates of the top-left corner of the image
	 */
	public void drawHuaji(Graphics g, int x, int y) {
		g.setColor(Color.YELLOW);
		// Face
		g.fillOval(x, y, 100, 100);
		g.setColor(Color.DARK_GRAY);

		g.drawArc(x + 8, y + 4, 24, 20, 0, 180);
		g.drawArc(x + 68, y + 4, 24, 20, 0, 180);
		// Eyes
		g.drawArc(x + 4, y + 20, 36, 20, 0, 180);
		g.drawArc(x + 60, y + 20, 36, 20, 0, 180);
		g.drawArc(x + 4, y + 28, 36, 8, 0, 180);
		g.drawArc(x + 60, y + 28, 36, 8, 0, 180);
		g.fillOval(x + 8, y + 22, 8, 8);
		g.fillOval(x + 64, y + 22, 8, 8);
		g.setColor(Color.PINK);
		g.fillOval(x + 6, y + 36, 28, 12);
		g.fillOval(x + 66, y + 36, 28, 12);
		// Mouth
		g.setColor(Color.BLACK);
		g.drawArc(x + 10, y + 20, 80, 70, 0, -180);
	}

}