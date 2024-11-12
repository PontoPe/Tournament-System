import java.util.Random;
import java.awt.Color;

public class Game {
    private Team winner;
    private GameFrame gameFrame;

    public Game(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public Team play(Team team1, Team team2) {
        Random random = new Random();
        gameFrame.appendColoredText("Game is being played between " + team1.getName() + " and " + team2.getName(), Color.WHITE, 16);

        team1.resetRoundsWonGame();
        team2.resetRoundsWonGame();

        while (team1.getRoundsWonGame() < 13 && team2.getRoundsWonGame() < 13) {
            playRound(team1, team2);
        }

        winner = team1.getRoundsWonGame() == 13 ? team1 : team2;
        gameFrame.appendColoredText("Winner is " + winner.getName(), Color.GREEN, 24);

        return winner;
    }

    private void playRound(Team team1, Team team2) {
        Random random = new Random();
        int team1Kills = 0;
        int team2Kills = 0;

        while (team1Kills < 5 && team2Kills < 5) {
            Player killer = random.nextBoolean() ? getRandomPlayer(team1) : getRandomPlayer(team2);
            Player victim = killer == getRandomPlayer(team1) ? getRandomPlayer(team2) : getRandomPlayer(team1);

            killer.addKill();
            victim.addDeath();

            gameFrame.appendColoredText(killer.getName() + " from " + killer.getTeam().getName() + " killed " + victim.getName(), Color.WHITE, 12);

            if (killer.getTeam() == team1) {
                team1Kills++;
            } else {
                team2Kills++;
            }
        }

        if (team1Kills == 5) {
            team1.winRoundGame();
            team1.winRound();
            gameFrame.appendColoredText(team1.getName() + " wins the round", Color.YELLOW, 18);
            team2.loseRound();
        } else {
            team2.winRoundGame();
            team2.winRound();
            gameFrame.appendColoredText(team2.getName() + " wins the round", Color.YELLOW, 18);
            team1.loseRound();
        }

        gameFrame.appendColoredText("Current score: " + team1.getName() + " " + team1.getRoundsWonGame() + " - " + team2.getRoundsWonGame() + " " + team2.getName(), Color.CYAN, 14);
    }

    private Player getRandomPlayer(Team team) {
        Random random = new Random();
        return team.getPlayers().get(random.nextInt(team.getPlayers().size()));
    }

    public Team getWinner() {
        return winner;
    }
}