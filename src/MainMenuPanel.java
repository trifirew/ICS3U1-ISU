import javax.swing.*;
import java.awt.*;

/**
 * The main menu screen
 *
 * @author Keisun, Yitian
 * @since December 20, 2017
 */
public class MainMenuPanel extends JPanel {

	/**
	 * Construct the main menu screen.
	 */
	public MainMenuPanel() {
		setPreferredSize(new Dimension(FlappyBird.W, FlappyBird.H));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel lbTitle = new JLabel("Flappy Bird");
		lbTitle.setAlignmentX(CENTER_ALIGNMENT);
		Font fontTitle = FlappyBird.fontBase.deriveFont(48f);
		lbTitle.setFont(fontTitle);

		Font fontButton = FlappyBird.fontBase.deriveFont(18f);

		JButton btnPlay = new JButton("Play");
		btnPlay.setAlignmentX(CENTER_ALIGNMENT);
		btnPlay.setPreferredSize(new Dimension(200, 50));
		btnPlay.setMaximumSize(new Dimension(200, 50));
		btnPlay.setFont(fontButton);

		JButton btnLeaderboard = new JButton("Leaderboard");
		btnLeaderboard.setAlignmentX(CENTER_ALIGNMENT);
		btnLeaderboard.setPreferredSize(new Dimension(200, 50));
		btnLeaderboard.setMaximumSize(new Dimension(200, 50));
		btnLeaderboard.setFont(fontButton);

		// Add ActionListeners for the buttons
		btnPlay.addActionListener(e -> {
			System.out.println("Play");
			FlappyBird.frame.changePanel(MainMenuPanel.this, FlappyBird.gamePanel);
			FlappyBird.gamePanel.onShow();
		});
		btnLeaderboard.addActionListener(e -> {
			// TODO: Leaderboard screen
			System.out.println("Leaderboard");
		});

		add(Box.createRigidArea(new Dimension(0, 200)));
		add(lbTitle);
		add(Box.createVerticalGlue());
		add(btnPlay);
		add(btnLeaderboard);
		add(Box.createRigidArea(new Dimension(0, 200)));
	}
}