import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The frame of the game
 *
 * @author Keisun, Yitian
 * @since December 20, 2017
 * @version 0
 */
public class FlappyBirdFrame extends JFrame {
	
	private Toolkit tk = Toolkit.getDefaultToolkit();
	
	public FlappyBirdFrame() {
		super("Flappy Bird");
		// Set action when player tries to close the window
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// Set icon of the window
		setIconImage(tk.getImage("res/icon.png"));
		setSize(480, 800);
	}
}
