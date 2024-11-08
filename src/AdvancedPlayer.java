import java.util.Random;

public class AdvancedPlayer extends Player {
    private int headshots;
    private Random random;

    public AdvancedPlayer(String name) {
        super(name);
        this.headshots = 0;
        this.random = new Random();
    }

    public int getHeadshots() {
        return headshots;
    }

    @Override
    public void addKill() {
        super.addKill();
        if (random.nextDouble() < 0.3) {
            this.headshots++;
            System.out.println(getName() + " made a headshot!");
        }
    }
}