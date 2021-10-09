package br.com.cwi.reset.aula.dois.exercicios;

public class Diretor extends Pessoa {
    private Integer quantidadeFilmesDirigidos;

    public Diretor(String nome, Integer idade, Genero genero, Integer quantidadeFilmesDirigidos) {
        super(nome, idade, genero);
        this.quantidadeFilmesDirigidos = quantidadeFilmesDirigidos;
    }


}
