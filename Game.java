/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for local variables.

        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("Snek");
        frame.setLocation(400, 400);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final GameCourt court = new GameCourt(status);
        frame.add(court, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        control_panel.add(reset);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static String introduction = 
    		"Hello snake friends! \n\n" +
    
    		"You are going to be playing against each other in Snek, a rendition of the classic game \n" +
    		"Snake. This is a two-player game, so if you're by yourself, find a friend. \n" +
    		"The classic rules of snake apply, you can't run into walls, yourself, or the \n" +
    		"other player, or else you die. \n\n" +
    		
    		"There is a new competitive element to this game. Each game lasts for at most 1 minute. \n" +
    		"If one snake dies, then the game ends and the other snake wins. Otherwise at the \n" +
    		"end of the game, whichever snake has the most points wins. \n" + 
    		"Points can be obtained by getting powerups.\n\n" +
    		
    		"Directions and powerups will be explained on the following screen.";
    public static String directions =
    		"Directions: \n" +
    		"Red Snake Movement: WASD Keys \n" +
    		"Blue Snake Movement: Arrow Keys \n\n" +
    		"Power Ups: \n" +
    		"Orange: Grow (+5 Points)\n" +
    		"Red: Grow Even More (+10 Points)\n" +
    		"Green: Speed Up And Elongate\n" +
    		"Cyan: Speed Up And Elongate More\n" +
    		"Yellow: (+25 Points) \n\n" +
    		
    		"Good luck! The game starts with both snakes moving when you press OK.";
    public static void main(String[] args) {
    		JOptionPane.showMessageDialog(null, introduction);
    		JOptionPane.showMessageDialog(null, directions);
        SwingUtilities.invokeLater(new Game());
    }
}