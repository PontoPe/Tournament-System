public class Tarefa {
    private String titulo;
    private String descricao;
    private String dataCriacao;
    private String dataPrazo;
    private String prioridade;
    private boolean concluida;
    private String categoria;
    private String responsavel;
    private int progresso;
    private int tempoEstimado;

    public Tarefa(String titulo, String descricao, String dataPrazo, String prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = java.time.LocalDate.now().toString();
        this.dataPrazo = dataPrazo;
        this.prioridade = prioridade;
        this.concluida = false;
        this.progresso = 0;
    }

    public void concluir() { this.concluida = true; }
    public void atualizarProgresso(int progresso) { this.progresso = progresso; }
    public void atualizarPrioridade(String prioridade) { this.prioridade = prioridade; }
    public void extenderPrazo(String novaData) { this.dataPrazo = novaData; }
    public String visualizarTarefa() { return "Tarefa: " + titulo + ", Prazo: " + dataPrazo; }
    public void atribuirResponsavel(String responsavel) { this.responsavel = responsavel; }
    public boolean verificarAtraso() {
        return java.time.LocalDate.now().isAfter(java.time.LocalDate.parse(dataPrazo));
    }
    public void reabrirTarefa() { this.concluida = false; }
    public void redefinirTitulo(String novoTitulo) { this.titulo = novoTitulo; }
    public String getResumo() { return titulo + " - " + prioridade; }
}
