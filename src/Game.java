import java.util.Random;

public class Game {
    public Team play(Team team1, Team team2) {
        Random random = new Random();
        return random.nextBoolean() ? team1 : team2;
    }
}