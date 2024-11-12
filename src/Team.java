import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<Player> players;
    private Coach coach;
    private int roundDiff;
    private int roundsWon;
    private int roundsLost;
    private int roundsWonGame;
    private ArrayList<Sponsor> sponsors;
    private Game currentGame;
    private int points;
    private int gamesPlayed;

    public Team(String name, Player player1, Player player2, Player player3, Player player4, Player player5, Coach coach) {
        this.name = name;
        this.players = new ArrayList<>();
        this.players.add(player1);
        this.players.add(player2);
        this.players.add(player3);
        this.players.add(player4);
        this.players.add(player5);
        this.coach = coach;
        this.roundDiff = 0;
        this.roundsWon = 0;
        this.sponsors = new ArrayList<>();
        this.points = 0;
        this.gamesPlayed = 0;
    }

    public void resetPoints() {
        this.points = 0;
    }

    public void resetGamesPlayed() {
        this.gamesPlayed = 0;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Coach getCoach() {
        return coach;
    }

    public void addGamePlayed() {
        this.gamesPlayed++;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public String getName() {
        return name;
    }

    public void setGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public Game getGame() {
        return currentGame;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getRoundDiff() {
        return roundsWon - roundsLost;
    }

    public void setRoundDiff(int roundDiff) {
        this.roundDiff = roundDiff;
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    public void winRound() {
        this.roundsWon++;
    }

    public int getRoundsLost() {
        return roundsLost;
    }

    public void loseRound() {
        this.roundsLost++;
    }

    public int getRoundsWonGame() {
        return roundsWonGame;
    }

    public void winRoundGame() {
        this.roundsWonGame++;
    }

    public void resetRoundsWonGame() {
        this.roundsWonGame = 0;
    }

    public ArrayList<Sponsor> getSponsors() {
        return sponsors;
    }

    public void addSponsor(Sponsor sponsor) {
        this.sponsors.add(sponsor);
    }

    @Override
    public String toString() {
        return name;
    }
}