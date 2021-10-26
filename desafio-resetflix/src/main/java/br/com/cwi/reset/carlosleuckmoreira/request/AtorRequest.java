package br.com.cwi.reset.carlosleuckmoreira.request;

import br.com.cwi.reset.carlosleuckmoreira.model.StatusCarreira;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AtorRequest {
    @NotEmpty(message = "Campo obrigatório não informado. Favor informar o campo nome.")
    // validar tamanho para nome e sobrenome
    private String nome;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo dataNascimento.")
    private LocalDate dataNascimento;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo statusCarreira.")
    private StatusCarreira statusCarreira;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo anoInicioAtividade.")
    private Integer anoInicioAtividade;

    public AtorRequest(String nome, LocalDate dataNascimento, StatusCarreira statusCarreira, Integer anoInicioAtividade) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.statusCarreira = statusCarreira;
        this.anoInicioAtividade = anoInicioAtividade;
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
