import model.Game;
import ui.GUI;

import javax.swing.*;

/**
 * @author Markus
 */
public class Main {

	public static void main(String args[]) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		Game game = new Game(true, false, true);
		GUI gui = new GUI(game);
	}
}
