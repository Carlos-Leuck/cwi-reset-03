package br.com.cwi.reset.carlosleuckmoreira.model;

import br.com.cwi.reset.carlosleuckmoreira.request.EstudioRequest;

import java.time.LocalDate;

public class Estudio {
    private Integer id;
    private String nome;
    private String descricao;
    private LocalDate dataCriacao;
    private StatusAtividade statusAtividade;

    public Estudio(Integer id, String nome, String descricao, LocalDate dataCriacao, StatusAtividade statusAtividade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.statusAtividade = statusAtividade;
    }

    //  GERAR ID AUTOMATICAMENTE
    static int contador = 0;

    public Estudio(EstudioRequest estudioRequest) {
        contador++;
        id = contador;
        this.nome = estudioRequest.getNome();
        this.descricao = estudioRequest.getDescricao();
        this.dataCriacao = estudioRequest.getDataCriacao();
        this.statusAtividade = estudioRequest.getStatusAtividade();

    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public StatusAtividade getStatusAtividade() {
        return statusAtividade;
    }
}
