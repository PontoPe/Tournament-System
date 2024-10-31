public class Categoria {
    private String nome;
    private String descricao;
    private String cor;
    private int prioridade;
    private String dataCriacao;
    private boolean ativa;
    private String criador;
    private String icone;
    private int quantidadeTarefas;
    private String tipo;

    public Categoria(String nome, String descricao, String cor, String criador) {
        this.nome = nome;
        this.descricao = descricao;
        this.cor = cor;
        this.criador = criador;
        this.dataCriacao = java.time.LocalDate.now().toString();
        this.ativa = true;
    }

    public void desativarCategoria() { this.ativa = false; }
    public void ativarCategoria() { this.ativa = true; }
    public void atualizarDescricao(String descricao) { this.descricao = descricao; }
    public void adicionarTarefa() { this.quantidadeTarefas++; }
    public void removerTarefa() { if (this.quantidadeTarefas > 0) this.quantidadeTarefas--; }
    public void atualizarPrioridade(int prioridade) { this.prioridade = prioridade; }
    public boolean verificarAtiva() { return ativa; }
    public String visualizarCategoria() { return "Categoria: " + nome + " - " + descricao; }
    public void mudarCor(String novaCor) { this.cor = novaCor; }
    public String getResumo() { return nome + " (" + quantidadeTarefas + " tarefas)"; }
}