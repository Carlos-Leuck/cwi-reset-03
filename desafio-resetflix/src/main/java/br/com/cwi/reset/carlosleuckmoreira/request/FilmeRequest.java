package br.com.cwi.reset.carlosleuckmoreira.request;

import br.com.cwi.reset.carlosleuckmoreira.model.Genero;

import java.time.LocalDate;
import java.util.List;

public class FilmeRequest {

    private String nome;
    private LocalDate anoLancamento;
    private String capaFilme;
    private List<Genero> generos;
    private Integer idDiretor;
    private Integer idEstudio;
    private String resumo;
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