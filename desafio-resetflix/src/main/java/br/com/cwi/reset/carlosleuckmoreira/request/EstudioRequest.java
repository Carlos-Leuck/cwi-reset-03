package br.com.cwi.reset.carlosleuckmoreira.request;

import br.com.cwi.reset.carlosleuckmoreira.model.StatusAtividade;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class EstudioRequest {
    @NotEmpty(message = "Campo obrigatório não informado. Favor informar o campo nome.")
    private String nome;
    @NotEmpty(message = "Campo obrigatório não informado. Favor informar o campo descricao.")
    private String descricao;
    private LocalDate dataCriacao;
    private StatusAtividade statusAtividade;

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
