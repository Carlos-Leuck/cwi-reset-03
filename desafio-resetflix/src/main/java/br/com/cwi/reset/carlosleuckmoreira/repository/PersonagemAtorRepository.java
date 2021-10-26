package br.com.cwi.reset.carlosleuckmoreira.repository;

import br.com.cwi.reset.carlosleuckmoreira.model.domain.PersonagemAtor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonagemAtorRepository extends CrudRepository<PersonagemAtor, Integer> {
    List<PersonagemAtor> findAll();
    List<PersonagemAtor> findPersonagemAtorById(Integer id);
}
