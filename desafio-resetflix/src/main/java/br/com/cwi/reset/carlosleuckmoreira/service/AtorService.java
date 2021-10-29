package br.com.cwi.reset.carlosleuckmoreira.service;

import br.com.cwi.reset.carlosleuckmoreira.model.StatusCarreira;
import br.com.cwi.reset.carlosleuckmoreira.exception.*;
import br.com.cwi.reset.carlosleuckmoreira.model.domain.PersonagemAtor;
import br.com.cwi.reset.carlosleuckmoreira.repository.AtorRepository;
import br.com.cwi.reset.carlosleuckmoreira.repository.PersonagemAtorRepository;
import br.com.cwi.reset.carlosleuckmoreira.response.AtorEmAtividade;
import br.com.cwi.reset.carlosleuckmoreira.request.AtorRequest;
import br.com.cwi.reset.carlosleuckmoreira.model.domain.Ator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtorService {
    @Autowired
    private AtorRepository atorRepository;
    @Autowired
    private PersonagemAtorRepository personagemAtorRepository;

    public void criarAtor(AtorRequest atorRequest) {

        try {

            validarSeNomeESobreNomeForamInformados(atorRequest);
            validarSeAnoDeInicioDeAtividadeMaiorQueDataNascimentoAtor(atorRequest);
            validarSeJaExisteAtorCadastradoComMesmoNome(atorRequest);

            Ator ator = new Ator(atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
            atorRepository.save(ator);

        } catch (NomeESobrenomeDevemSerInformadosException
                | AtorJaCadastradoException | AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoAtorException e) {
            e.printStackTrace();
        }
    }

    private void validarSeAnoDeInicioDeAtividadeMaiorQueDataNascimentoAtor(AtorRequest atorRequest) throws AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoAtorException {
        if (atorRequest.getAnoInicioAtividade() <= atorRequest.getDataNascimento().getYear()) {
            throw new AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoAtorException();
        }
    }

    private void validarSeNomeESobreNomeForamInformados(AtorRequest atorRequest) throws NomeESobrenomeDevemSerInformadosException {
        if (atorRequest.getNome().split(" ").length < 2) {
            throw new NomeESobrenomeDevemSerInformadosException("ator");
        }
    }

    private void validarSeJaExisteAtorCadastradoComMesmoNome(AtorRequest atorRequest) throws AtorJaCadastradoException {
        String nomeAtor;
        nomeAtor = atorRequest.getNome();

        for (int i = 0; i < atorRepository.findAll().size(); i++) {
            if (atorRepository.findAll().get(i).getNome().equals(nomeAtor)) {
                throw new AtorJaCadastradoException(nomeAtor);
            }
        }
    }


    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) throws NaoExisteAtorCadastradoException {
        List<AtorEmAtividade> listaFiltrada = listarAtoresEmAtividade().stream()
                .filter(ator -> ator.getNome().toUpperCase().contains(filtroNome.toUpperCase()))
                .collect(Collectors.toList());

        if (listaFiltrada.isEmpty()) {
            throw new NaoExisteAtorCadastradoException(
                    );
        }

        return listaFiltrada;
    }
    public List<AtorEmAtividade> listarAtoresEmAtividade() throws NaoExisteAtorCadastradoException {
        if (atorRepository.findAll().isEmpty()) {
            throw new NaoExisteAtorCadastradoException();
        }
        return atorRepository.findAll().stream()
                .filter(ator -> ator.getStatusCarreira().toString().equals("EM_ATIVIDADE"))
                .map(ator -> new AtorEmAtividade(ator)).collect(Collectors.toList());
    }

    public Ator consultarAtor(Integer id) {
        Ator atorFiltradoPeloId = atorRepository.findAtorById(id);

        try {
            if (atorFiltradoPeloId == null) {
                throw new NaoExisteAtorComOIdInformadoException(id);
            }
        } catch (NaoExisteAtorComOIdInformadoException e) {
            e.printStackTrace();
        }
        return atorFiltradoPeloId;
    }

    public List<Ator> consultarAtores() {
        List<Ator> listaComTodosAtoresConsultados = atorRepository.findAll();

        try {
            if (listaComTodosAtoresConsultados.isEmpty()) {
                throw new NaoExisteAtorCadastradoException();
            }
        } catch (NaoExisteAtorCadastradoException e) {
            e.printStackTrace();
        }
        return listaComTodosAtoresConsultados;
    }

    public void atualizarAtor(Integer id, AtorRequest atorRequest) {

        try {
            validarSeJaExisteAtorCadastradoComMesmoNome(atorRequest);
            Ator atorAtualizado = new Ator(atorRequest.getNome(), atorRequest.getDataNascimento(),
                    atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
            atorAtualizado.setId(consultarAtor(id).getId());
            atorRepository.save(atorAtualizado);

        } catch (AtorJaCadastradoException e) {
            e.printStackTrace();
        }
    }

    public void removerAtor(Integer id) {
        Ator atorQueDeveSerRemovido = consultarAtor(id);

        if (validarSeAtorEstaVinculadoMaisDeUmPersonagem(atorQueDeveSerRemovido)) {
            try {
                throw new AtorNaoPodeEstarVinculadoMaisDeUmPersonagemException();
            } catch (AtorNaoPodeEstarVinculadoMaisDeUmPersonagemException e) {
                e.printStackTrace();
            }
        }
        atorRepository.delete(atorQueDeveSerRemovido);
    }

    private boolean validarSeAtorEstaVinculadoMaisDeUmPersonagem(Ator ator) {
        for (PersonagemAtor p : personagemAtorRepository.findAll()) {
            if (p.getAtor().getNome().equalsIgnoreCase(ator.getNome())) {
                return true;
            }
        }
        return false;
    }

}
