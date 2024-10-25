import java.util.Random;

public class Game {
    public Team play(Team team1, Team team2) {
        Random random = new Random();
        Random random2 = new Random();
        System.out.println("Game is being played between " + team1.getName() + " and " + team2.getName());

        Team winner = random.nextBoolean() ? team1 : team2;
        int roundDifference = random2.nextInt(10);
        winner.setRoundDiff(winner.getRoundDiff() + roundDifference);
        System.out.println("Winner is " + winner.getName() + " with round difference of " + roundDifference);

        return winner;
    }

}