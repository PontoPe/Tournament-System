public class Player extends Participant{
    private int kills;
    private int deaths;

    public Player(String name) {
        super(name);
        this.kills = 0;
        this.deaths = 0;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void addKill() {
        this.kills++;
    }

    public void addDeath() {
        this.deaths++;
    }

    @Override
    public void performAction() {
        System.out.println("Player " + getName() + " is playing");
    }
}