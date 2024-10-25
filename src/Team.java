import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<Player> players;
    private Coach coach;
    private int roundDiff;

    public Team(String name, Player player1, Player player2, Player player3, Player player4, Player player5, Coach coach) {
        this.name = name;
        this.players.add(player1);
        this.players.add(player2);
        this.players.add(player3);
        this.players.add(player4);
        this.players.add(player5);
        this.coach = coach;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Coach getCoach() {
        return coach;
    }

    public String getName() {
        return name;
    }

    public int getRoundDiff() {
        return roundDiff;
    }

    public void setRoundDiff(int roundDiff) {
        this.roundDiff = roundDiff;
    }
}