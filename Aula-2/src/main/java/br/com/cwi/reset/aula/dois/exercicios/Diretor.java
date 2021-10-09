package br.com.cwi.reset.aula.dois.exercicios;

public class Diretor {
    private String nome;
    private Integer idade;
    private Integer quantidadeFilmesDirigidos;
    private Genero genero;


    public Diretor(String nome, Integer idade, Integer quantidadeFilmesDirigidos) {
        this.nome = nome;
        this.idade = idade;
        this.quantidadeFilmesDirigidos = quantidadeFilmesDirigidos;
    }

    public Diretor(String nome, Integer idade, Integer quantidadeFilmesDirigidos, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.quantidadeFilmesDirigidos = quantidadeFilmesDirigidos;
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public Genero getGenero() {
        return genero;
    }

    public void imprimirInformacoesPessoais() {
        System.out.println("Ator{" +
                "nome='" + getNome() + '\'' +
                ", idade=" + getIdade() +
                ", genero=" + getGenero().getDescricao() +
                '}');
    }


}
