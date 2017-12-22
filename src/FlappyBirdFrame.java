import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The frame of the game
 *
 * @author Keisun, Yitian
 * @since December 20, 2017
 */
public class FlappyBirdFrame extends JFrame {

	/**
	 * Construct a new frame for the game.
	 */
	public FlappyBirdFrame() {
		super("Flappy Bird");
		// Prevent player from changing the size of the window
		setResizable(false);
		// Set action when player tries to close the window
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO: Action on window closing
				String[] options = {"Yes", "No"};
				int prompt = JOptionPane.showOptionDialog(FlappyBirdFrame.this,
						"Do you really want to exit?", "Exit",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						null,
						options, options[1]);
				if (prompt == 0)
					System.exit(0);
			}
		});
		// Set icon of the window
		Toolkit tk = Toolkit.getDefaultToolkit();
		setIconImage(tk.getImage("res/icon.png"));
		Dimension screenSize = tk.getScreenSize();

		int w = FlappyBird.W;
		int h = FlappyBird.H;
		setBounds((screenSize.width - w) / 2, (screenSize.height - h) / 2, w, h);
	}

	/**
	 * Show a specific panel on the frame.
	 *
	 * @param panel the panel to show on the frame.
	 */
	public void showPanel(JPanel panel) {
		add(panel);
		pack();
	}

	/**
	 * Change from a panel to another panel.
	 *
	 * @param oldPanel the panel to close
	 * @param newPanel the panel to open
	 */
	public void changePanel(JPanel oldPanel, JPanel newPanel) {
		remove(oldPanel);
		add(newPanel);
		validate();
		repaint();
	}
}
