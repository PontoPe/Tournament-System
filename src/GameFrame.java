import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private Game game;
    private JTextArea playRoundTextArea;

    public GameFrame(Team team1, Team team2) {
        this.game = new Game(team1, team2, this); // Pass 'this' to the Game constructor
        setTitle("Game: " + team1.getName() + " vs " + team2.getName());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        // Left panel for players and team stats
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(45, 45, 45));
        leftPanel.setForeground(new Color(230, 230, 230));

        JLabel team1Label = new JLabel(team1.getName());
        team1Label.setForeground(new Color(230, 230, 230));
        leftPanel.add(team1Label);

        for (Player player : team1.getPlayers()) {
            JLabel playerLabel = new JLabel("Player: " + player.getName() + " - Stats: " + player.getStats());
            playerLabel.setForeground(new Color(230, 230, 230));
            leftPanel.add(playerLabel);
        }

        JLabel team2Label = new JLabel(team2.getName());
        team2Label.setForeground(new Color(230, 230, 230));
        leftPanel.add(team2Label);

        for (Player player : team2.getPlayers()) {
            JLabel playerLabel = new JLabel("Player: " + player.getName() + " - Stats: " + player.getStats());
            playerLabel.setForeground(new Color(230, 230, 230));
            leftPanel.add(playerLabel);
        }

        // Right panel for playRound prints
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(new Color(45, 45, 45));
        rightPanel.setForeground(new Color(230, 230, 230));

        playRoundTextArea = new JTextArea();
        playRoundTextArea.setEditable(false);
        playRoundTextArea.setBackground(new Color(45, 45, 45));
        playRoundTextArea.setForeground(new Color(230, 230, 230));
        rightPanel.add(new JScrollPane(playRoundTextArea), BorderLayout.CENTER);

        add(leftPanel);
        add(rightPanel);

        // Simulate the game and print the results
        simulateGame();
    }

    private void simulateGame() {
        Team winner = game.play(game.getTeam1(), game.getTeam2());
        playRoundTextArea.append("Winner is " + winner.getName() + "\n");
    }

    public Game getGame() {
        return game;
    }

    public void appendColoredText(String s, Color color, int fontSize) {
        // Implementation for appending colored text
    }
}