package br.com.cwi.reset.aula.dois.exercicios;

public class Diretor {
    private String nome;
    private Integer idade;
    private Integer quantidadeFilmesDirigidos;

    public Diretor(String nome, Integer idade, Integer quantidadeFilmesDirigidos) {
        this.nome = nome;
        this.idade = idade;
        this.quantidadeFilmesDirigidos = quantidadeFilmesDirigidos;
    }


    public String getNome() {
        return nome;
    }

}
