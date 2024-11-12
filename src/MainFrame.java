import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame extends JFrame {
    private JPanel participantsPanel;
    private List<Group> groups;

    public MainFrame(List<Group> groups) {
        this.groups = groups;
        setTitle("Groups and Teams");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set dark mode colors
        Color backgroundColor = new Color(45, 45, 45);
        Color foregroundColor = new Color(230, 230, 230);
        getContentPane().setBackground(backgroundColor);

        JButton playButton = new JButton("Play Groups Stage");
        playButton.setBackground(backgroundColor);
        playButton.setForeground(foregroundColor);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GroupStageFrame(groups).setVisible(true);
            }
        });

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Groups");
        for (Group group : groups) {
            DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(group.getName());
            for (Team team : group.getTeams()) {
                DefaultMutableTreeNode teamNode = new DefaultMutableTreeNode(team);
                groupNode.add(teamNode);
            }
            root.add(groupNode);
        }

        JTree tree = new JTree(root);
        tree.setRootVisible(false);
        tree.setBackground(backgroundColor);
        tree.setForeground(foregroundColor);
        tree.setCellRenderer(new CustomTreeCellRenderer(backgroundColor, foregroundColor));
        tree.addTreeSelectionListener(e -> {
            TreePath path = e.getPath();
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
            if (selectedNode.getUserObject() instanceof Team) {
                Team selectedTeam = (Team) selectedNode.getUserObject();
                updateParticipantsPanel(selectedTeam);
            }
        });

        participantsPanel = new JPanel();
        participantsPanel.setLayout(new BoxLayout(participantsPanel, BoxLayout.Y_AXIS));
        participantsPanel.setBackground(backgroundColor);
        participantsPanel.setForeground(foregroundColor);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(tree), new JScrollPane(participantsPanel));
        splitPane.setDividerLocation(300);

        add(playButton, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
    }

    private void updateParticipantsPanel(Team team) {
        participantsPanel.removeAll();
        JLabel coachLabel = new JLabel("Coach: " + team.getCoach().getName());
        coachLabel.setForeground(new Color(230, 230, 230));
        participantsPanel.add(coachLabel);

        for (Player player : team.getPlayers()) {
            JLabel playerLabel = new JLabel("Player: " + player.getName());
            playerLabel.setForeground(new Color(230, 230, 230));
            participantsPanel.add(playerLabel);
        }

        participantsPanel.revalidate();
        participantsPanel.repaint();
    }

    private static class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
        private final Color backgroundColor;
        private final Color foregroundColor;

        public CustomTreeCellRenderer(Color backgroundColor, Color foregroundColor) {
            this.backgroundColor = backgroundColor;
            this.foregroundColor = foregroundColor;
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            Component c = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            c.setBackground(backgroundColor);
            c.setForeground(foregroundColor);
            if (c instanceof JComponent) {
                ((JComponent) c).setOpaque(true);
            }
            return c;
        }
    }
}