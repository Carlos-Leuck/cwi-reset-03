package br.com.cwi.reset.projeto1.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "diretor")
public class Diretor extends Pessoa {

    private Integer quantidadeFilmes;

    public Diretor(String nome, LocalDate nascimento, Integer quantidadeFilmes, Genero genero) {
        super(nome, nascimento, genero);
        this.quantidadeFilmes = quantidadeFilmes;
    }

    public Diretor() {
        super();
    }

    public Integer getQuantidadeFilmes() {
        return quantidadeFilmes;
    }
}