package br.com.cwi.reset.projeto1.repository;

import br.com.cwi.reset.projeto1.domain.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FilmeRepositoryImpl implements FilmeRepository {

    @Autowired
    private List<Filme> filmes;

    public Filme findByNome(String nome) {
        for (Filme filme : filmes) {
            if (filme.getNome().equals(nome)) {
                return filme;
            }
        }
        return null;
    }

    public Filme save(Filme filme) {
        filmes.add(filme);
        return filme;
    }

    public void delete(Filme filme) {
        filmes.remove(filme);
    }

    public Filme update(Filme filme) {
        Filme filmeExistente = findByNome(filme.getNome());

        if (filmeExistente != null) {
            filmes.remove(filmeExistente);
            filmes.add(filme);
            return filme;
        }
        return null;
    }

    public List<Filme> findAll() {
        return filmes;
    }
}
