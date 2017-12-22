import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The score screen after a game is over
 *
 * @author Keisun, Yitian
 * @version 0.4
 * @since December 21, 2017
 */
public class ScorePanel extends JPanel {

	private BufferedImage image;

	private ScoreCard scoreCard;
	private JButton btnRestart, btnLeaderboard;

	private final String ACTION_PLAY = "Play";
	private final String ACTION_LEADER = "Leaderboard";
	
	private Font fontButton;

	public ScorePanel() {
		setPreferredSize(new Dimension(480, 800));
		setBackground(Color.blue);
		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		fontButton = FlappyBird.fontBase.deriveFont(14f);

		scoreCard = new ScoreCard();
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scoreCard, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, scoreCard, 0, SpringLayout.VERTICAL_CENTER, this);
		btnRestart = new JButton(ACTION_PLAY);
		btnRestart.setFont(fontButton);
		layout.putConstraint(SpringLayout.NORTH, btnRestart, 16, SpringLayout.SOUTH, scoreCard);
		layout.putConstraint(SpringLayout.WEST, btnRestart, 0, SpringLayout.WEST, scoreCard);
		layout.putConstraint(SpringLayout.EAST, btnRestart, -12, SpringLayout.HORIZONTAL_CENTER, this);
		btnLeaderboard = new JButton(ACTION_LEADER);
		btnLeaderboard.setFont(fontButton);
		layout.putConstraint(SpringLayout.NORTH, btnLeaderboard, 16, SpringLayout.SOUTH, scoreCard);
		layout.putConstraint(SpringLayout.EAST, btnLeaderboard, 0, SpringLayout.EAST, scoreCard);
		layout.putConstraint(SpringLayout.WEST, btnLeaderboard, 12, SpringLayout.HORIZONTAL_CENTER, this);

		// Create an ActionListener for the buttons
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
					case ACTION_PLAY:
						System.out.println("Play");
						FlappyBird.frame.changePanel(ScorePanel.this, FlappyBird.gamePanel);
						FlappyBird.gamePanel.start();
						break;
					case ACTION_LEADER:
						System.out.println("Leaderboard");
				}
			}
		};

		btnRestart.addActionListener(listener);
		btnLeaderboard.addActionListener(listener);

		add(scoreCard);
		add(btnRestart);
		add(btnLeaderboard);
	}

	public void start(GameControl game, BufferedImage image) {
		this.image = image;
		scoreCard.lbScore.setText(Integer.toString(game.score));
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}
}

/**
 * The card to show the player score and best score.
 *
 * @author Keisun, Yitian
 * @version 0.3
 * @since December 21, 2017
 */
class ScoreCard extends JPanel {
	JLabel lbScoreCaption, lbBestCaption, lbScore, lbBest;

	private Font fontCaption;
	private Font fontScore;

	public ScoreCard() {
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		setBackground(Color.CYAN);

		fontScore = FlappyBird.fontBase.deriveFont(32f);
		fontCaption = FlappyBird.fontBase.deriveFont(24f);

		lbScoreCaption = new JLabel("Score");
		lbScoreCaption.setFont(fontCaption);
		lbScoreCaption.setHorizontalTextPosition(SwingConstants.RIGHT);
		layout.putConstraint(SpringLayout.EAST, lbScoreCaption, -16, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, lbScoreCaption, 16, SpringLayout.NORTH, this);

		lbScore = new JLabel("0");
		lbScore.setFont(fontScore);
		lbScore.setHorizontalTextPosition(SwingConstants.RIGHT);
		layout.putConstraint(SpringLayout.EAST, lbScore, 0, SpringLayout.EAST, lbScoreCaption);
		layout.putConstraint(SpringLayout.NORTH, lbScore, 4, SpringLayout.SOUTH, lbScoreCaption);

		lbBestCaption = new JLabel("Best");
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
}