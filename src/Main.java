import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class Main {
    private static final String FILE_NAME = "./src/player_names.txt";

    public static void main(String[] args) {
        List<String> names = loadNamesFromFile(FILE_NAME);
        printNames(names);
        promptForPlayerName(names, FILE_NAME);

        try {
            List<Group> groups = createGroups(names);

            // Create and display the GUI
            SwingUtilities.invokeLater(() -> {
                MainFrame mainFrame = new MainFrame(groups);
                mainFrame.setVisible(true);

                // Example of initializing gameFrame and calling appendColoredText
                GameFrame gameFrame = new GameFrame(groups.get(0).getTeams().get(0), groups.get(0).getTeams().get(1));
                gameFrame.appendColoredText("Game started!", Color.GREEN, 14);
            });
        } catch (NotEnoughNamesException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void printNames(List<String> names) {
        for (int i = 0; i < names.size(); i++) {
            System.out.println((i + 1) + ". " + names.get(i));
        }
    }

    private static void promptForPlayerName(List<String> names, String fileName) {
        String playerName = JOptionPane.showInputDialog(null, "Do you want to add a player's name? If yes, enter the name:");
        if (playerName != null && !playerName.trim().isEmpty()) {
            names.add(playerName.trim());
            saveNameToFile(playerName.trim(), fileName);
        }
    }

    private static void saveNameToFile(String name, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(name);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> loadNamesFromFile(String fileName) {
        List<String> names = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    names.add(line.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }

    private static List<Group> createGroups(List<String> names) throws NotEnoughNamesException{
        if (names.size() < 4 * 6 * 5 + 4 * 6) {
            throw new NotEnoughNamesException("Not enough names to create teams and coaches.");
        }

        List<Group> groups = new ArrayList<>();
        Random random = new Random();
        int teamCounter = 1;

        // Create 4 groups
        for (int i = 0; i < 4; i++) {
            Group group = new Group("Group " + (i + 1));
            List<Team> teams = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                String teamName = "Team " + teamCounter++;
                Coach coach = new Coach(names.remove(random.nextInt(names.size())));
                List<Player> players = new ArrayList<>();
                for (int k = 0; k < 5; k++) {
                    Player player = new Player(names.remove(random.nextInt(names.size())));
                    players.add(player);
                }
                Team team = new Team(teamName, players.get(0), players.get(1), players.get(2), players.get(3), players.get(4), coach);
                for (Player player : players) {
                    player.setTeam(team);
                }
                teams.add(team);
                group.addTeam(team);
            }

            // Create games for each pair of teams in the group
            for (int t1 = 0; t1 < teams.size(); t1++) {
                for (int t2 = t1 + 1; t2 < teams.size(); t2++) {
                    Team team1 = teams.get(t1);
                    Team team2 = teams.get(t2);
                    GameFrame gameFrame = new GameFrame(team1, team2);
                    Game game = new Game(team1, team2, gameFrame);
                    team1.setGame(game);
                    team2.setGame(game);
                    group.addGame(game); // Add game to the group's games list
                }
            }

            groups.add(group);
        }

        return groups;
    }
}