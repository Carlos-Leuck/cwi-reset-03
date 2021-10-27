package br.com.cwi.reset.carlosleuckmoreira.service;

import br.com.cwi.reset.carlosleuckmoreira.exception.*;
import br.com.cwi.reset.carlosleuckmoreira.model.Genero;
import br.com.cwi.reset.carlosleuckmoreira.model.domain.PersonagemAtor;
import br.com.cwi.reset.carlosleuckmoreira.repository.FilmeRepository;
import br.com.cwi.reset.carlosleuckmoreira.request.FilmeRequest;
import br.com.cwi.reset.carlosleuckmoreira.model.domain.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Objects.isNull;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;
    @Autowired
    private EstudioService estudioService;
    @Autowired
    private DiretorService diretorService;
    @Autowired
    private PersonagemAtorService personagemAtorService;

    public void criarFilme(FilmeRequest filmeRequest) {

        try {
            Filme filme = new Filme(filmeRequest.getNome(), filmeRequest.getAnoLancamento(), filmeRequest.getCapaFilme(),
                    filmeRequest.getGeneros(), estudioService.consultarEstudio(filmeRequest.getIdDiretor()),
                    diretorService.consultarDiretor(filmeRequest.getIdDiretor()),
                    personagemAtorService.cadastrarPersonagensFilme(filmeRequest.getPersonagens()), filmeRequest.getResumo());

            if (filme.getGeneros().isEmpty()) {
                throw new DeveSerInformadoPeloMenosUmGeneroParaCadastroDoFilmeException();
            }

            final Set<Genero> generoSet = new HashSet<>();

            for (Genero genero : filme.getGeneros()) {
                if (generoSet.contains(genero)) {
                    throw new NaoPodeInformarOMesmoGeneroMaisDeUmaVezParaMesmoFilmeException();
                } else {
                    generoSet.add(genero);
                }
            }
            filmeRepository.save(filme);
        } catch (Exception campoObrigatorioNaoInformadoException) {
            campoObrigatorioNaoInformadoException.printStackTrace();
        }
    }

    public List<Filme> consultarFilmes(
            String nomeFilme,
            String nomeDiretor,
            String nomePersonagem,
            String nomeAtor) {

        try {
            final List<Filme> filmesCadastrados = filmeRepository.findAll();

            if (filmesCadastrados.isEmpty()) {
                throw new ListaVaziaException(TipoDominioException.FILME.getSingular(), TipoDominioException.FILME.getPlural());
            }

            final List<Filme> filtrarNomePersonagem = filtrarNomePersonagem(filmesCadastrados, nomePersonagem);
            final List<Filme> filtrarNomeAtor = filtrarNomeAtor(filtrarNomePersonagem, nomeAtor);
            final List<Filme> filtrarNomeDiretor = filtrarNomeDiretor(filtrarNomeAtor, nomeDiretor);
            final List<Filme> filtroFinal = filtrarNomeFilme(filtrarNomeDiretor, nomeFilme);

            if (filtroFinal.isEmpty()) {
                throw new FilmeNaoEncontradoComOsFiltrosUsadosException(
                        String.format(
                                "Filme não encontrado com os filtros nomeFilme=%s, nomeDiretor=%s, nomePersonagem=%s, nomeAtor=%s, favor informar outros filtros.",
                                nomeFilme,
                                nomeDiretor,
                                nomePersonagem,
                                nomeAtor
                        )
                );
            }
            return filtroFinal;

        } catch (ListaVaziaException | FilmeNaoEncontradoComOsFiltrosUsadosException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private List<Filme> filtrarNomeFilme(final List<Filme> listaOriginal, final String nome) {
        if (isNull(nome)) {
            return listaOriginal;
        }

        final List<Filme> filmeFiltrado = new ArrayList<>();

        for (Filme filme : listaOriginal) {
            if (filme.getNome().toLowerCase(Locale.ROOT).equalsIgnoreCase(nome.toLowerCase(Locale.ROOT))) {
                filmeFiltrado.add(filme);
            }
        }

        return filmeFiltrado;
    }

    private List<Filme> filtrarNomeDiretor(final List<Filme> listaOriginal, final String nome) {
        if (isNull(nome)) {
            return listaOriginal;
        }

        final List<Filme> filmeFiltrado = new ArrayList<>();

        for (Filme filme : listaOriginal) {
            if (filme.getDiretor().getNome().toLowerCase(Locale.ROOT).equalsIgnoreCase(nome.toLowerCase(Locale.ROOT))) {
                filmeFiltrado.add(filme);
            }
        }

        return filmeFiltrado;
    }

    private List<Filme> filtrarNomeAtor(final List<Filme> listaOriginal, final String nome) {
        if (isNull(nome)) {
            return listaOriginal;
        }

        final List<Filme> filmeFiltrado = new ArrayList<>();

        for (Filme filme : listaOriginal) {
            for (PersonagemAtor personagens : filme.getPersonagens()) {
                if (personagens.getAtor().getNome().toLowerCase(Locale.ROOT).equalsIgnoreCase(nome.toLowerCase(Locale.ROOT))) {
                    filmeFiltrado.add(filme);
                    break;
                }
            }
        }

        return filmeFiltrado;
    }

    private List<Filme> filtrarNomePersonagem(final List<Filme> listaOriginal, final String nome) {
        if (isNull(nome)) {
            return listaOriginal;
        }

        final List<Filme> filmeFiltrado = new ArrayList<>();

        for (Filme filme : listaOriginal) {
            for (PersonagemAtor personagens : filme.getPersonagens()) {
                if (personagens.getNomePersonagem()
                        .toLowerCase(Locale.ROOT)
                        .equalsIgnoreCase(nome.toLowerCase(Locale.ROOT))
                ) {
                    filmeFiltrado.add(filme);
                    break;
                }
            }
        }
        return filmeFiltrado;
    }

    public void removerFilme(Integer id) {
    }
}

