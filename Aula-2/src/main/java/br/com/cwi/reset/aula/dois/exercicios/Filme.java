package br.com.cwi.reset.aula.dois.exercicios;

public class Filme {
    private String nome;
    private String descricao;
    private Integer duracaoEmMinutos;
    private Integer anoDeLancamento;
    private Double notaDeAvaliacao;
    private Diretor diretor;

    public Filme(String nome, String descricao, Integer duracaoEmMinutos, Integer anoDeLancamento, Double notaDeAvaliacao, Diretor diretor) {
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


}
