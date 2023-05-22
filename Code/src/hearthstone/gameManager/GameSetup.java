package hearthstone.gameManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class GameSetup {
    private JPanel Hearthstone;
    private JTextArea welcomeText;
    private JTextField player1Name;
    private JTextField player2Name;
    private JLabel player1Label;
    private JLabel player2Label;
    private JCheckBox player1Ready;
    private JCheckBox player2Ready;
    private JButton startButton;
    private boolean finished = false;

    /**
     * Create the GUI application at start of game to set up player names and whether they are ready
     */
    public GameSetup() {
        JFrame frame = new JFrame("Hearthstone");
        frame.setContentPane(this.Hearthstone);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        // on button click startGame()
        this.startButton.addActionListener(e -> {
            if (this.areBothPlayersReady()) {
                this.startGame();
            }
        });
    }

    /**
     * @return whether the game setup is finished
     */
    public boolean isFinished() {
        return this.finished;
    }

    /**
     * starts the game and closes the GUI frame
     */
    // on start button click end window and set finished to true and save names into
    public void startGame() {
        this.finished = true;
        // close window
        JFrame frame = (JFrame)SwingUtilities.getWindowAncestor(this.Hearthstone);
        frame.dispose();
    }

    /**
     * @return the player 1 name
     */
    public String getPlayer1Name() {
        return this.player1Name.getText();
    }

    /**
     * @return the player 2 name
     */

    public String getPlayer2Name() {
        return this.player2Name.getText();
    }

    /**
     * @return the player 1 ready checkbox
     */
    // return whether player 1 is ready
    public boolean isPlayer1Ready() {
        if (this.getPlayer1Name().trim().isEmpty()) {
            return false;
        }
        return this.player1Ready.isSelected();
    }

    /**
     * @return the player 2 ready checkbox
     */
    // return whether player 2 is ready
    public boolean isPlayer2Ready() {
        if (this.getPlayer2Name().trim().isEmpty()) {
            return false;
        }
        return this.player2Ready.isSelected();
    }

    /**
     * @return whether both players are ready
     */
    // return whether both players are ready
    public boolean areBothPlayersReady() {
        return this.isPlayer1Ready() && this.isPlayer2Ready();
    }

}