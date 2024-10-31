public class Player {
    private String name;
    private int kills;
    private int deaths;

    public Player(String name) {
        this.name = name;
        this.kills = 0;
        this.deaths = 0;
    }

    public String getName() {
        return name;
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
}