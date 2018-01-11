import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		lbTitle.setForeground(Color.DARK_GRAY);

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
		btnPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Play");
				FlappyBird.frame.changePanel(MainMenuPanel.this, FlappyBird.gamePanel);
				FlappyBird.gamePanel.init();
			}
		});
		btnLeaderboard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: Leaderboard screen
				System.out.println("Leaderboard");
			}
		});

		add(Box.createRigidArea(new Dimension(0, 152)));
		add(lbTitle);
		add(Box.createVerticalGlue());
		add(btnPlay);
		add(btnLeaderboard);
		add(Box.createRigidArea(new Dimension(0, 324)));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw the background image
		g.drawImage(FlappyBird.bg, 0, 0, this);
	}
}