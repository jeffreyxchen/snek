import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game implements Runnable {
    public void run() {
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
