import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Tarefa> tarefas = new ArrayList<>();

    public static void main(String[] args) {
        Usuario usuario = new UsuarioAdmin("João", "admin@exemplo.com"); // Mude para UsuarioComum para testar outro usuário
        int opcao;

        do {
            usuario.exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    if (usuario instanceof UsuarioAdmin) {
                        ((UsuarioAdmin) usuario).gerenciarUsuarios();
                    } else {
                        usuario.verPerfil();
                    }
                    break;
                case 2:
                    if (usuario instanceof UsuarioAdmin) {
                        ((UsuarioAdmin) usuario).verRelatorios();
                    } else {
                        System.out.print("Novo Nome: ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Novo Email: ");
                        String novoEmail = scanner.nextLine();
                        ((UsuarioComum) usuario).editarInformacoes(novoNome, novoEmail);
                    }
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 3);

        // Exemplo de criação e execução de tarefas
        Tarefa tarefaSimples = new TarefaSimples("Comprar leite");
        Tarefa tarefaRecorrente = new TarefaRecorrente("Fazer exercícios", LocalDate.now());

        tarefas.add(tarefaSimples);
        tarefas.add(tarefaRecorrente);

        System.out.println("\nExecutando tarefas:");
        for (Tarefa tarefa : tarefas) {
            tarefa.executar();
            System.out.println("Tarefa concluída: " + tarefa.getDescricao());
        }
    }
}
