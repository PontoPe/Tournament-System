import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private List<Team> teams;

    public Group(String name) {
        this.name = name;
        this.teams = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        this.teams.add(team);
    }
}