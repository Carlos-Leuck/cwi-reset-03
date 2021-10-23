package br.com.cwi.reset.projeto1.repository;

import br.com.cwi.reset.projeto1.domain.Filme;

import java.util.List;

public interface FilmeRepository {
    Filme findByNome(String nome);

    Filme save(Filme filme);

    void delete(Filme filme);

    Filme update(Filme filme);

    List<Filme> findAll();
}
