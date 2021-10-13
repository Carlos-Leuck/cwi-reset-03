package br.com.cwi.reset.carlosleuckmoreira.entities;

import br.com.cwi.reset.carlosleuckmoreira.StatusCarreira;
import br.com.cwi.reset.carlosleuckmoreira.dto.AtorRequest;

import java.time.LocalDate;

public class Ator {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private StatusCarreira statusCarreira;
    private Integer anoInicioAtividade;

    public Ator(String nome, LocalDate dataNascimento, StatusCarreira statusCarreira, Integer anoInicioAtividade) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.statusCarreira = statusCarreira;
        this.anoInicioAtividade = anoInicioAtividade;
    }

//  GERAR ID AUTOMATICAMENTE
    static int contador=0;

    public Ator(AtorRequest atorRequest) {
        contador++;
        id=contador;
        this.nome = atorRequest.getNome();
        this.dataNascimento = atorRequest.getDataNascimento();
        this.statusCarreira = atorRequest.getStatusCarreira();
        this.anoInicioAtividade = atorRequest.getAnoInicioAtividade();

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

    public StatusCarreira getStatusCarreira() {
        return statusCarreira;
    }

    public Integer getAnoInicioAtividade() {
        return anoInicioAtividade;
    }


}
