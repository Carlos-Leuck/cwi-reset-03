package br.com.cwi.reset.aula.dois.exercicios;

public class Ator {
    private String nome;
    private Integer idade;
    private Integer numeroOscarsVencidos;
    private Genero genero;

    public Ator(String nome, Integer idade, Integer numeroOscarsVencidos, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.numeroOscarsVencidos = numeroOscarsVencidos;
        this.genero = genero;
    }


    @Override
    public String toString() {
        return "Ator{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", numeroOscarsVencidos=" + numeroOscarsVencidos +
                ", genero=" + genero +
                '}';
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
                ", genero=" + getGenero() +
                '}');
    }

}
