package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.exception.AtorJaExistenteException;
import br.com.cwi.reset.projeto1.exception.AtorNaoExistenteException;
import br.com.cwi.reset.projeto1.repository.AtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtorService {


    @Autowired
    private AtorRepository repository;

    public Ator salvar(Ator ator) throws AtorJaExistenteException {
        Ator atorJaExistente = repository.findByNome(ator.getNome());

        if (atorJaExistente != null) {
            throw new AtorJaExistenteException("Ator com o nome " + ator.getNome() + " já existe");
        }
        repository.save(ator);
        return ator;
    }

    public List<Ator> listarTodos() {
        return repository.findAll();
    }

    public Ator buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }
    public Ator buscarPeloNumeroDeOscars(Integer numeroDeOscars) {
        return repository.findByAtornumeroOscars(numeroDeOscars);
    }

    public void deletar(String nomeAtor) throws AtorNaoExistenteException {
        Ator Ator = buscarPorNome(nomeAtor);
        if (Ator == null) {
            throw new AtorNaoExistenteException("Ator com o nome " + nomeAtor + " não existe");
        }
        repository.delete(Ator);
    }

    public Ator atualizar(Ator ator) throws AtorNaoExistenteException {
        Ator AtorJaCadastrado = buscarPorNome(ator.getNome());
        if (AtorJaCadastrado == null) {
            throw new AtorNaoExistenteException("Ator com o nome " + ator.getNome() + " não existe");
        }
        return repository.save(ator);
    }
}