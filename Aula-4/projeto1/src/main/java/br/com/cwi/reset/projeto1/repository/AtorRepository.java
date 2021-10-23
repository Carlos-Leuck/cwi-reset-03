package br.com.cwi.reset.projeto1.repository;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.domain.Filme;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface AtorRepository extends CrudRepository<Ator, Integer> {
    Ator findByNome(String nome);
    Ator findByAtornumeroOscars(Integer numeroOscars);
    List<Ator> findAll();
}
