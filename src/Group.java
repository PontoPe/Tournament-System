import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private List<Team> teams;
    private List<Game> games;

    public Group(String name) {
        this.name = name;
        this.teams = new ArrayList<>();
        this.games = new ArrayList<>(); // Initialize the games list
    }

    public String getName() {
        return name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Game> getGames() {
        return games;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void addGame(Game game) {
        games.add(game);
    }
}