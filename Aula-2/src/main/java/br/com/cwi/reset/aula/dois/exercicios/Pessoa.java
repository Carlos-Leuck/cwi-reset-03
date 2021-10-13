package br.com.cwi.reset.aula.dois.exercicios;

import java.time.LocalDate;
import java.time.Period;

public abstract class Pessoa {
    private String nome;
    private Integer idade;
    private LocalDate dataNascimento;
    private Genero genero;

    public Pessoa(String nome, LocalDate dataNascimento, Genero genero) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
    }

    public void imprimirInformacoesPessoais() {
        System.out.println("Pessoa{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", genero=" + genero.getDescricao() +
                '}');
    }

    public String getNome() {
        return nome;
    }
// LocalDate.of(year, month, day)


    public int calcularIdade(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}
