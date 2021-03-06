package br.com.cwi.reset.carlosleuckmoreira.repository;

import br.com.cwi.reset.carlosleuckmoreira.model.domain.Ator;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AtorRepository extends CrudRepository<Ator, Integer> {
    List<Ator> findAll();
    Ator findAtorById(Integer id);
    List<Ator> findByNomeContainingIgnoringCase(String filtroNome);

}
