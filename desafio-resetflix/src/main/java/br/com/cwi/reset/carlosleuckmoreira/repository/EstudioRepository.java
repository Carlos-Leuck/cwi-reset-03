package br.com.cwi.reset.carlosleuckmoreira.repository;

import br.com.cwi.reset.carlosleuckmoreira.model.domain.Estudio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstudioRepository extends CrudRepository<Estudio, Integer> {
    List<Estudio> findAll();
    List<Estudio> findEstudioById(Integer id);
}
