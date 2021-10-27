package br.com.cwi.reset.carlosleuckmoreira.request;

import br.com.cwi.reset.carlosleuckmoreira.model.Genero;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class FilmeRequest {

    @NotEmpty(message = "Campo obrigatório não informado. Favor informar o campo nome.")
    private String nome;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo anoLancamento.")
    private LocalDate anoLancamento;
    @NotEmpty(message = "Campo obrigatório não informado. Favor informar o campo capaFilme.")
    private String capaFilme;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo generos.")
    private List<Genero> generos;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo idDiretor.")
    private Integer idDiretor;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo idEstudio.")
    private Integer idEstudio;
    @NotEmpty(message = "Campo obrigatório não informado. Favor informar o campo resumo.")
    private String resumo;
    @Valid
    @NotEmpty(message = "Campo obrigatório não informado. Favor informar o campo personagens.")
    private List<PersonagemRequest> personagens;

    public FilmeRequest(String nome, LocalDate anoLancamento, String capaFilme, List<Genero> generos,
                        Integer idDiretor, Integer idEstudio, String resumo, List<PersonagemRequest> personagens) {
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.capaFilme = capaFilme;
        this.generos = generos;
        this.idDiretor = idDiretor;
        this.idEstudio = idEstudio;
        this.resumo = resumo;
        this.personagens = personagens;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getAnoLancamento() {
        return anoLancamento;
    }

    public String getCapaFilme() {
        return capaFilme;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public Integer getIdDiretor() {
        return idDiretor;
    }

    public Integer getIdEstudio() {
        return idEstudio;
    }

    public String getResumo() {
        return resumo;
    }

    public List<PersonagemRequest> getPersonagens() {
        return personagens;
    }
}
