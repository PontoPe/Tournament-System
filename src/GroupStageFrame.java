import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;

public class GroupStageFrame extends JFrame {
    public GroupStageFrame(List<Group> groups) {
        setTitle("Group Stage");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(0, 1));

        // Set dark mode colors
        Color backgroundColor = new Color(45, 45, 45);
        Color foregroundColor = new Color(230, 230, 230);
        getContentPane().setBackground(backgroundColor);

        Random random = new Random();
        for (Group group : groups) {
            List<Team> teams = group.getTeams();
            if (teams.size() >= 2) {
                final Team team1 = teams.get(random.nextInt(teams.size()));
                Team team2 = null;
                do {
                    team2 = teams.get(random.nextInt(teams.size()));
                } while (team1 == team2);

                final Team finalTeam2 = team2; // Make team2 effectively final

                JPanel groupPanel = new JPanel();
                groupPanel.setLayout(new GridLayout(1, 3));
                TitledBorder border = new TitledBorder(group.getName());
                border.setTitleColor(foregroundColor);
                groupPanel.setBorder(border);
                groupPanel.setBackground(backgroundColor);
                groupPanel.setForeground(foregroundColor);

                JLabel team1Label = new JLabel(team1.getName(), SwingConstants.CENTER);
                team1Label.setForeground(foregroundColor);
                JLabel vsLabel = new JLabel("vs", SwingConstants.CENTER);
                vsLabel.setForeground(foregroundColor);
                JLabel team2Label = new JLabel(finalTeam2.getName(), SwingConstants.CENTER);
                team2Label.setForeground(foregroundColor);

                groupPanel.add(team1Label);
                groupPanel.add(vsLabel);
                groupPanel.add(team2Label);

                groupPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new GameFrame(team1, finalTeam2).setVisible(true);
                    }
                });

                add(groupPanel);
            }
        }
    }
}