import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioGerenciadorTarefas extends Usuario {
    private List<Tarefa> tarefas;
    private boolean logado;

    public UsuarioGerenciadorTarefas(String nome, String email, String username, String senha) {
        super(nome, email, username, senha);
        this.tarefas = new ArrayList<>();
        this.logado = false;
    }

    @Override
    public void login(String username, String senha) {
        if (getUsername().equals(username) && validarSenha(senha)) {
            this.logado = true;
            registrarLogin();
            System.out.println("Login realizado com sucesso!");
        } else {
            System.out.println("Usuário ou senha inválidos.");
        }
    }

    @Override
    public void logout() {
        if (this.logado) {
            this.logado = false;
            System.out.println("Logout realizado com sucesso.");
        } else {
            System.out.println("Usuário não está logado.");
        }
    }

    @Override
    public void exibirMenu() {
        if (!this.logado) {
            System.out.println("Você precisa estar logado para acessar o menu.");
            return;
        }

        System.out.println("\n=== Menu do Gerenciador de Tarefas ===");
        System.out.println("1. Adicionar nova tarefa");
        System.out.println("2. Listar todas as tarefas");
        System.out.println("3. Marcar tarefa como concluída");
        System.out.println("4. Remover tarefa");
        System.out.println("5. Visualizar perfil");
        System.out.println("6. Sair");
        System.out.println("Escolha uma opção:");
    }

    public void adicionarTarefa(String titulo, String descricao, String dataPrazo, String prioridade) {
        if (!this.logado) {
            System.out.println("Você precisa estar logado para adicionar tarefas.");
            return;
        }
        Tarefa novaTarefa = new Tarefa(titulo, descricao, dataPrazo, prioridade);
        tarefas.add(novaTarefa);
        System.out.println("Tarefa adicionada com sucesso!");
    }

    public void listarTarefas() {
        if (!this.logado) {
            System.out.println("Você precisa estar logado para listar tarefas.");
            return;
        }
        if (tarefas.isEmpty()) {
            System.out.println("Não há tarefas cadastradas.");
        } else {
            System.out.println("\n=== Lista de Tarefas ===");
            for (int i = 0; i < tarefas.size(); i++) {
                System.out.println((i + 1) + ". " + tarefas.get(i).visualizarTarefa());
            }
        }
    }

    public void marcarTarefaConcluida(int indice) {
        if (!this.logado) {
            System.out.println("Você precisa estar logado para marcar tarefas como concluídas.");
            return;
        }
        if (indice > 0 && indice <= tarefas.size()) {
            Tarefa tarefa = tarefas.get(indice - 1);
            tarefa.concluir();
            System.out.println("Tarefa marcada como concluída!");
        } else {
            System.out.println("Índice de tarefa inválido.");
        }
    }

    public void removerTarefa(int indice) {
        if (!this.logado) {
            System.out.println("Você precisa estar logado para remover tarefas.");
            return;
        }
        if (indice > 0 && indice <= tarefas.size()) {
            tarefas.remove(indice - 1);
            System.out.println("Tarefa removida com sucesso!");
        } else {
            System.out.println("Índice de tarefa inválido.");
        }
    }

    private boolean validarSenha(String senha) {
        // Implementação simplificada. Em um cenário real, use métodos seguros de comparação de senhas.
        return getSenha().equals(senha);
    }

    // Método auxiliar para obter a senha (assumindo que existe um getter na classe pai)
    private String getSenha() {
        // Implementação depende da estrutura da classe pai
        return "senha_hash"; // Placeholder
    }
}