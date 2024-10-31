import java.util.ArrayList;
import java.util.List;

public class Group {
    private List<Team> teams;

    public Group() {
        teams = new ArrayList<>();
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public List<Team> getTeams() {
        return teams;
    }
}