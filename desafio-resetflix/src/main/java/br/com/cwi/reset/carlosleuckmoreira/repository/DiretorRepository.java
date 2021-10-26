package br.com.cwi.reset.carlosleuckmoreira.repository;

import br.com.cwi.reset.carlosleuckmoreira.model.domain.Diretor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiretorRepository extends CrudRepository<Diretor, Integer> {
    List<Diretor> findAll();
    List<Diretor> findDiretorById(Integer id);
}
