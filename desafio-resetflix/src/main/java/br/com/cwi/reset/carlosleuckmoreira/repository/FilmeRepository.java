package br.com.cwi.reset.carlosleuckmoreira.repository;

import br.com.cwi.reset.carlosleuckmoreira.model.domain.Filme;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilmeRepository  extends CrudRepository<Filme, Integer> {
    List<Filme> findAll();
    Filme findFilmeById(Integer id);
}
