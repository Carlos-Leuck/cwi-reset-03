package br.com.cwi.reset.aula.dois.exercicios;

public class Pessoa {
    private String nome;
    private Integer idade;
    private Genero genero;

    public Pessoa(String nome, Integer idade, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
    }

    public void imprimirInformacoesPessoais() {
        System.out.println("Pessoa{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", genero=" + genero +
                '}');
    }

    public String getNome() {
        return nome;
    }
}
