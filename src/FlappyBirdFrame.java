import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The frame of the game
 *
 * @author Keisun, Yitian
 * @version 0.1
 * @since December 20, 2017
 */
public class FlappyBirdFrame extends JFrame {

	private Toolkit tk = Toolkit.getDefaultToolkit();

	private final int WIDTH = FlappyBird.WIDTH;
	private final int HEIGHT = FlappyBird.HEIGHT;

	/**
	 * Construct a new frame for the game.
	 */
	public FlappyBirdFrame() {
		super("Flappy Bird");
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		// Set action when player tries to close the window
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
		setIconImage(tk.getImage("res/icon.png"));
		Dimension screenSize = tk.getScreenSize();
		setBounds((screenSize.width - WIDTH) / 2, (screenSize.height - HEIGHT) / 2, WIDTH, HEIGHT);
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
