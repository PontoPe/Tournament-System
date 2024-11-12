import java.util.Random;
import java.awt.Color;

public class Game {
    private Team winner;
    private Team team1;
    private Team team2;
    private GameFrame gameFrame;

    public Game(Team team1, Team team2, GameFrame gameFrame) {
        this.team1 = team1;
        this.team2 = team2;
        this.gameFrame = gameFrame;
        this.winner = play(team1, team2);
    }

    public Team play(Team team1, Team team2) {
        Random random = new Random();
        gameFrame.appendColoredText("Game is being played between " + team1.getName() + " and " + team2.getName(), Color.WHITE, 16);

        team1.resetRoundsWonGame();
        team2.resetRoundsWonGame();
        team1.addGamePlayed();
        team2.addGamePlayed();

        while (team1.getRoundsWonGame() < 13 && team2.getRoundsWonGame() < 13) {
            playRound();
        }

        winner = team1.getRoundsWonGame() == 13 ? team1 : team2;
        gameFrame.appendColoredText("Winner is " + winner.getName(), Color.GREEN, 24);

        return winner;
    }

    public String playRound() {
        StringBuilder playByPlay = new StringBuilder();
        Random random = new Random();
        int team1Kills = 0;
        int team2Kills = 0;

        while (team1Kills < 5 && team2Kills < 5) {
            Player killer = random.nextBoolean() ? getRandomPlayer(team1) : getRandomPlayer(team2);
            Player victim = killer == getRandomPlayer(team1) ? getRandomPlayer(team2) : getRandomPlayer(team1);

            killer.addKill();
            victim.addDeath();

            playByPlay.append(killer.getName()).append(" from ").append(killer.getTeam().getName()).append(" killed ").append(victim.getName()).append("\n");

            if (killer.getTeam() == team1) {
                team1Kills++;
            } else {
                team2Kills++;
            }
        }

        if (team1Kills == 5) {
            team1.winRoundGame();
            team1.winRound();
            playByPlay.append(team1.getName()).append(" wins the round\n");
            team2.loseRound();
        } else {
            team2.winRoundGame();
            team2.winRound();
            playByPlay.append(team2.getName()).append(" wins the round\n");
            team1.loseRound();
        }

        playByPlay.append("Current score: ").append(team1.getName()).append(" ").append(team1.getRoundsWonGame()).append(" - ").append(team2.getRoundsWonGame()).append(" ").append(team2.getName()).append(("\n"));
        return playByPlay.toString();
    }

    private Player getRandomPlayer(Team team) {
        Random random = new Random();
        return team.getPlayers().get(random.nextInt(team.getPlayers().size()));
    }

    public Team getWinner() {
        return winner;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
}