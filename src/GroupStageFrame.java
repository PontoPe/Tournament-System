import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GroupStageFrame extends JFrame {
    private List<Group> groups;
    private JTextArea winnersTextArea;

    public GroupStageFrame(List<Group> groups) {
        this.groups = groups;
        setTitle("Group Stage");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for displaying groups and games
        JPanel groupsPanel = new JPanel();
        groupsPanel.setLayout(new BoxLayout(groupsPanel, BoxLayout.Y_AXIS));
        for (Group group : groups) {
            JLabel groupLabel = new JLabel(group.getName());
            groupsPanel.add(groupLabel);
            for (Team team : group.getTeams()) {
                JLabel teamLabel = new JLabel(team.getName());
                groupsPanel.add(teamLabel);
            }
            for (Game game : group.getGames()) {
                JButton gameButton = new JButton("Game: " + game.getTeam1().getName() + " vs " + game.getTeam2().getName());
                gameButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        displayPlayByPlay(game);
                    }
                });
                groupsPanel.add(gameButton);
            }
        }

        // Text area for displaying winners and play-by-play
        winnersTextArea = new JTextArea();
        winnersTextArea.setEditable(false);
        winnersTextArea.setBackground(new Color(45, 45, 45));
        winnersTextArea.setForeground(new Color(230, 230, 230));

        // Button to display winners
        JButton showWinnersButton = new JButton("Show Winners");
        showWinnersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayWinners();
            }
        });

        // Add components to frame
        add(new JScrollPane(groupsPanel), BorderLayout.WEST);
        add(showWinnersButton, BorderLayout.SOUTH);
        add(new JScrollPane(winnersTextArea), BorderLayout.CENTER);
    }

    private void displayWinners() {
        StringBuilder winnersText = new StringBuilder();
        for (Group group : groups) {
            winnersText.append(group.getName()).append(":\n");
            for (Team team : group.getTeams()) {
                team.resetPoints(); // Reset points before calculation
                team.resetGamesPlayed(); // Reset games played before calculation
            }
            for (Game game : group.getGames()) {
                Team winner = game.getWinner();
                if (winner != null) {
                    winner.addPoints(2); // Add 2 points for a win
                }
                game.getTeam1().addGamePlayed();
                game.getTeam2().addGamePlayed();
            }
            // Sort teams by points
            group.getTeams().sort((t1, t2) -> Integer.compare(t2.getPoints(), t1.getPoints()));
            for (Team team : group.getTeams()) {
                winnersText.append(team.getName())
                           .append(" - Points: ").append(team.getPoints())
                           .append(" - Games Played: ").append(team.getGamesPlayed())
                           .append("\n");
            }
            winnersText.append("\n");
        }
        winnersTextArea.setText(winnersText.toString());
    }

    private void displayPlayByPlay(Game game) {
        String playByPlayText = "Game between " + game.getTeam1().getName() + " and " + game.getTeam2().getName() + ":\n";
        playByPlayText += game.playRound() + "\n";
        winnersTextArea.setText(playByPlayText);
    }
}