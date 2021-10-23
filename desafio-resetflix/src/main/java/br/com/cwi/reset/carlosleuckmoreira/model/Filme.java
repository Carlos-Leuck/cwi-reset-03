package br.com.cwi.reset.carlosleuckmoreira.model;

import br.com.cwi.reset.carlosleuckmoreira.request.FilmeRequest;

import java.time.LocalDate;
import java.util.List;

public class Filme {
    private Integer id;
    private String nome;
    private LocalDate anoLancamento;
    private String capaFilme;
    private List<Genero> generos;
    private Diretor diretor;
    private List<PersonagemAtor> personagens;
    private String resumo;

    //  GERAR ID AUTOMATICAMENTE
    static int contador = 0;

    public Filme(Integer id, String nome, LocalDate anoLancamento, String capaFilme, List<Genero> generos,
                 Diretor diretor, List<PersonagemAtor> personagens, String resumo) {
        this.id = id;
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.capaFilme = capaFilme;
        this.generos = generos;
        this.diretor = diretor;
        this.personagens = personagens;
        this.resumo = resumo;
    }


    public Filme(FilmeRequest filmeRequest) {
        contador++;
        id = contador;
        this.nome = filmeRequest.getNome();
        this.anoLancamento = filmeRequest.getAnoLancamento();
        this.capaFilme = filmeRequest.getCapaFilme();
        this.resumo = filmeRequest.getResumo();
        this.generos = filmeRequest.getGeneros();

    }

    public Integer getId() {
        return id;
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

    public Diretor getDiretor() {
        return diretor;
    }

    public List<PersonagemAtor> getPersonagens() {
        return personagens;
    }

    public String getResumo() {
        return resumo;
    }
}
