import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The main menu screen
 *
 * @author Keisun, Yitian
 * @version 0.1
 * @since December 20, 2017
 */
public class MainMenuPanel extends JPanel {

	private final String ACTION_PLAY = "Play";
	private final String ACTION_LEADER = "Leaderboard";

	private JButton btnPlay, btnLeaderboard;
	private JLabel lbTitle;

	public MainMenuPanel() {
		setPreferredSize(new Dimension(480, 800));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		lbTitle = new JLabel("Flappy Bird");
		lbTitle.setAlignmentX(CENTER_ALIGNMENT);
		Font fontTitle = new Font("Impact", Font.PLAIN, 48);
		lbTitle.setFont(fontTitle);

		btnPlay = new JButton(ACTION_PLAY);
		btnPlay.setAlignmentX(CENTER_ALIGNMENT);
		btnPlay.setPreferredSize(new Dimension(200, 50));
		btnPlay.setMaximumSize(new Dimension(200, 50));
		
		btnLeaderboard = new JButton(ACTION_LEADER);
		btnLeaderboard.setAlignmentX(CENTER_ALIGNMENT);
		btnLeaderboard.setPreferredSize(new Dimension(200, 50));
		btnLeaderboard.setMaximumSize(new Dimension(200, 50));

		// Create an ActionListener for the buttons
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
					case ACTION_PLAY:
						System.out.println("Play");
						FlappyBird.frame.changePanel(MainMenuPanel.this, FlappyBird.gamePanel);
						FlappyBird.gamePanel.start();
						break;
					case ACTION_LEADER:
						System.out.println("Leaderboard");
				}
			}
		};

		btnPlay.addActionListener(listener);
		btnLeaderboard.addActionListener(listener);

		add(Box.createRigidArea(new Dimension(0, 200)));
		add(lbTitle);
		add(Box.createVerticalGlue());
		add(btnPlay);
		add(btnLeaderboard);
		add(Box.createRigidArea(new Dimension(0, 200)));
	}
}