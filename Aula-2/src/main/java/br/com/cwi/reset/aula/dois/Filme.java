package br.com.cwi.reset.aula.dois;

public class Filme {
    private String nome;
    private String descricao;
    private int duracaoEmMinutos;
    private int anoDeLancamento;
    private int notaDeAvaliacao;
    private Diretor diretor;

    public Filme(String nome, String descricao, int duracaoEmMinutos, int anoDeLancamento, int notaDeAvaliacao, Diretor diretor) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracaoEmMinutos = duracaoEmMinutos;
        this.anoDeLancamento = anoDeLancamento;
        this.notaDeAvaliacao = notaDeAvaliacao;
        this.diretor = diretor;
    }

    public void reproduzir() {
        System.out.println("Filme{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", duracaoEmMinutos=" + duracaoEmMinutos +
                ", diretor=" + diretor.getNome() +
                '}');
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public int getNotaDeAvaliacao() {
        return notaDeAvaliacao;
    }

    public void setNotaDeAvaliacao(int notaDeAvaliacao) {
        this.notaDeAvaliacao = notaDeAvaliacao;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }


}
