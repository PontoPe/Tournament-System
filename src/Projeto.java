import java.util.ArrayList;

public class Projeto {
    private String nome;
    private String descricao;
    private String dataInicio;
    private String dataFim;
    private String status;
    private ArrayList<Tarefa> tarefas;
    private String responsavel;
    private String prioridade;
    private int progresso;
    private String tipo;

    public Projeto(String nome, String descricao, String prioridade, String responsavel) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = java.time.LocalDate.now().toString();
        this.status = "Em andamento";
        this.prioridade = prioridade;
        this.responsavel = responsavel;
        this.tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(Tarefa tarefa) { tarefas.add(tarefa); }
    public void removerTarefa(Tarefa tarefa) { tarefas.remove(tarefa); }
    public void atualizarStatus(String novoStatus) { this.status = novoStatus; }
    public void calcularProgresso() {
        int concluida = (int) tarefas.stream().filter(Tarefa::isConcluida).count();
        this.progresso = (int) ((concluida / (double) tarefas.size()) * 100);
    }
    public void definirDataFim(String dataFim) { this.dataFim = dataFim; }
    public void redefinirResponsavel(String responsavel) { this.responsavel = responsavel; }
    public void atualizarPrioridade(String prioridade) { this.prioridade = prioridade; }
    public String visualizarProjeto() { return "Projeto: " + nome + " - " + descricao; }
    public boolean verificarAtraso() {
        return java.time.LocalDate.now().isAfter(java.time.LocalDate.parse(dataFim));
    }
}