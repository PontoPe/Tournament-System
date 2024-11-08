import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<String> names = generateNames();
        System.out.println("names.size()=" + names.size());
        List<Group> groups = createGroups(names);

        // Print groups, teams, and their members
        for (Group group : groups) {
            System.out.println("Group: " + group.getName());
            for (Team team : group.getTeams()) {
                System.out.println("  Team: " + team.getName());
                System.out.println("    Coach: " + team.getCoach().getName());
                System.out.println("-");
                for (Player player : team.getPlayers()) {
                    System.out.println("    Player: " + player.getName());
                }
                System.out.println("-----------------");
            }
            System.out.println("===================================");
        }
    }

    private static List<String> generateNames() {
        List<String> names = new ArrayList<>();
        Collections.addAll(names,
            "Ana", "Beatriz", "Camila", "Daniela", "Eduarda", "Fernanda", "Gabriela", "Helena", "Isabela", "Juliana",
            "Karina", "Larissa", "Mariana", "Natália", "Olivia", "Patrícia", "Quésia", "Rafaela", "Sabrina", "Tatiana",
            "Ursula", "Valentina", "Wendy", "Ximena", "Yasmin", "Zilda", "Adriana", "Bruna", "Carla", "Débora",
            "Eliana", "Fabiana", "Giovana", "Heloísa", "Ivana", "Jéssica", "Kátia", "Lúcia", "Mônica", "Nina",
            "Ofélia", "Paula", "Quitéria", "Renata", "Sandra", "Teresa", "Úrsula", "Vera", "Wanda", "Xuxa",
            "Yara", "Zuleica", "Alessandra", "Bianca", "Cecília", "Diana", "Elisa", "Flávia", "Glória", "Hilda",
            "Inês", "Joana", "Kelly", "Lara", "Márcia", "Nair", "Odete", "Priscila", "Quintina", "Regina",
            "Sônia", "Tânia", "Ulda", "Vânia", "Wilma", "Xênia", "Yolanda", "Zoraide", "Aline", "Bárbara",
            "Cláudia", "Denise", "Ester", "Fátima", "Graça", "Heloísa", "Iara", "Janaína", "Karla", "Lívia",
            "Marta", "Neide", "Odila", "Paloma", "Quitéria", "Rita", "Silvia", "Tereza", "Ursula", "Vânia",
            "Wanda", "Ximena", "Yara", "Zélia", "Amanda", "Brenda", "Cátia", "Dulce", "Evelyn", "Fabiana",
            "Gisele", "Helena", "Irene", "Júlia", "Kátia", "Lúcia", "Mara", "Nádia", "Olga", "Patrícia",
            "Quésia", "Rafaela", "Sabrina", "Tatiana", "Ursula", "Valéria", "Wanda", "Ximena", "Yasmin", "Zilda", "Ana", "Beatriz", "Camila", "Daniela", "Eduarda", "Fernanda", "Gabriela", "Helena", "Isabela", "Juliana", "Karina", "Larissa", "Mariana", "Natália", "Olivia", "Patrícia", "Marina de Lara", "Rafaela"
        );
        return names;
    }

    private static List<Group> createGroups(List<String> names) {
        List<Group> groups = new ArrayList<>();
        Random random = new Random();

        // Create 4 groups
        for (int i = 0; i < 4; i++) {
            Group group = new Group("Group " + (i + 1));
            for (int j = 0; j < 6; j++) {
                String teamName = "Team " + (i * 6 + j + 1);
                Coach coach = new Coach(names.remove(random.nextInt(names.size())));
                List<Player> players = new ArrayList<>();
                for (int k = 0; k < 5; k++) {
                    players.add(new Player(names.remove(random.nextInt(names.size()))));
                }
                Team team = new Team(teamName, players.get(0), players.get(1), players.get(2), players.get(3), players.get(4), coach);
                group.addTeam(team);
            }
            groups.add(group);
        }

        return groups;
    }
}