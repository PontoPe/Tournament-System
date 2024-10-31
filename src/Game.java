import java.util.Random;

public class Game {
    public Team play(Team team1, Team team2) {
        Random random = new Random();
        System.out.println("Game is being played between " + team1.getName() + " and " + team2.getName());

        team1.resetRoundsWonGame();
        team2.resetRoundsWonGame();

        while (team1.getRoundsWonGame() < 13 && team2.getRoundsWonGame() < 13) {
            playRound(team1, team2);
        }

        Team winner = team1.getRoundsWon() == 13 ? team1 : team2;
        System.out.println("Winner is " + winner.getName());

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

            if (killer == getRandomPlayer(team1)) {
                team1Kills++;
            } else {
                team2Kills++;
            }
        }

        if (team1Kills == 5) {
            team1.winRoundGame();
            team1.winRound();
            System.out.println(team1.getName() + " wins the round");
            team2.loseRound();
        } else {
            team2.winRoundGame();
            team2.winRound();
            System.out.println(team2.getName() + " wins the round");
            team1.loseRound();
        }
    }

    private Player getRandomPlayer(Team team) {
        Random random = new Random();
        return team.getPlayers().get(random.nextInt(team.getPlayers().size()));
    }
}