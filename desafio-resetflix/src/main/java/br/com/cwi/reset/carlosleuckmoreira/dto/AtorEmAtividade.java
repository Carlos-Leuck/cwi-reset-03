package br.com.cwi.reset.carlosleuckmoreira.dto;

import br.com.cwi.reset.carlosleuckmoreira.entities.Ator;

import java.time.LocalDate;

public class AtorEmAtividade {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;

    public AtorEmAtividade(Integer id, String nome, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public AtorEmAtividade(Ator ator) {
        this.id = ator.getId();
        this.nome = ator.getNome();
        this.dataNascimento = ator.getDataNascimento();

    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
