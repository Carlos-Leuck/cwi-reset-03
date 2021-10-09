package br.com.cwi.reset.aula.dois.exercicios;

public class Filme {
    private String nome;
    private String descricao;
    private Integer duracaoEmMinutos;
    private Integer anoDeLancamento;
    private Double notaDeAvaliacao;
    private Diretor diretor;



    public void reproduzir() {
        System.out.println("Filme{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", duracaoEmMinutos=" + duracaoEmMinutos +
                ", diretor=" + diretor.getNome() +
                '}');
    }


}
