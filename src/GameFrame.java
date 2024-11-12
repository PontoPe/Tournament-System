import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;

public class GameFrame extends JFrame {
    private JTextArea textArea;

    public GameFrame(Team team1, Team team2) {
        setTitle("Game: " + team1.getName() + " vs " + team2.getName());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set dark mode colors
        Color backgroundColor = new Color(45, 45, 45);
        Color foregroundColor = new Color(230, 230, 230);
        getContentPane().setBackground(backgroundColor);

        textArea = new JTextArea();
        textArea.setBackground(backgroundColor);
        textArea.setForeground(foregroundColor);
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        Game game = new Game(this);
        PrintStream printStream = new PrintStream(new TextAreaOutputStream(textArea));
        System.setOut(printStream);
        System.setErr(printStream);

        new Thread(() -> {
            game.play(team1, team2);
        }).start();
    }

    public void appendColoredText(String text, Color color, int fontSize) {
        SwingUtilities.invokeLater(() -> {
            Font originalFont = textArea.getFont();
            textArea.setFont(new Font(originalFont.getName(), originalFont.getStyle(), fontSize));
            textArea.setForeground(color);
            textArea.append(text + "\n");
            textArea.setFont(originalFont); // Reset to original font
            textArea.setForeground(new Color(230, 230, 230)); // Reset to default color
        });
    }
}