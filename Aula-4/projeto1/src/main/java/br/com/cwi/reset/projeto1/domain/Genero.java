package br.com.cwi.reset.projeto1.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

public enum Genero {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    NAO_BINARIO("Não Binário");

    private String descricao;

    Genero(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Entity
    @Table(name = "pessoa")
    @Inheritance(strategy = InheritanceType.JOINED)
    public abstract static class Pessoa {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String nome;
        private LocalDate dataNascimento;
        private Genero genero;

        public Pessoa(String nome, LocalDate dataNascimento, Genero genero) {
            this.nome = nome;
            this.dataNascimento = dataNascimento;
            this.genero = genero;
        }

        public Pessoa() {

        }

        public void imprimirInformacoes() {
            System.out.println("Nome: " + nome);
            System.out.println("Idade: " + this.calcularIdade());
            System.out.println("Genero: " + genero.getDescricao());
        }

        public String getNome() {
            return nome;
        }

        public LocalDate getDataNascimento() {
            return dataNascimento;
        }

        public Genero getGenero() {
            return genero;
        }

        private Integer calcularIdade() {
            return Period.between(LocalDate.now(), dataNascimento).getYears();
        }
    }
}
