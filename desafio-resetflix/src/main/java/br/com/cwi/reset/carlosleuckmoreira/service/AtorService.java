package br.com.cwi.reset.carlosleuckmoreira.service;

import br.com.cwi.reset.carlosleuckmoreira.model.StatusCarreira;
import br.com.cwi.reset.carlosleuckmoreira.exception.*;
import br.com.cwi.reset.carlosleuckmoreira.repository.AtorRepository;
import br.com.cwi.reset.carlosleuckmoreira.response.AtorEmAtividade;
import br.com.cwi.reset.carlosleuckmoreira.request.AtorRequest;
import br.com.cwi.reset.carlosleuckmoreira.model.domain.Ator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtorService {
    @Autowired
    private AtorRepository atorRepository;

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


    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) {
        List<Ator> atoresCadastrados = atorRepository.findAll();
        List<Ator> retorno = new ArrayList<>();

        try {
            if (atoresCadastrados.isEmpty()) {
                throw new NaoExisteAtorCadastradoException();
            }

            for (int i = 0; i < atoresCadastrados.size(); i++) {
                if (atoresCadastrados.get(i).getStatusCarreira() != StatusCarreira.EM_ATIVIDADE) {
                    atoresCadastrados.remove(i);
                }
            }

            if (filtroNome == null) {
                return atoresCadastrados.stream().map(AtorEmAtividade::new).collect(Collectors.toList());
            }

            for (Ator ator : atoresCadastrados) {
                if (ator.getNome().contains(filtroNome)) {
                    retorno.add(ator);
                }
            }
            if (retorno.isEmpty()) {
                throw new NaoExisteAtorComOFiltroInformadoException(filtroNome);
            }
        } catch (NaoExisteAtorCadastradoException | NaoExisteAtorComOFiltroInformadoException e) {
            e.printStackTrace();
        }
        return retorno.stream().map(AtorEmAtividade::new).collect(Collectors.toList());

    }


    public Ator consultarAtor(@NotNull(message = "Campo obrigatório não informado. Favor informar o campo id.") Integer id) {
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
    }

    public void removerAtor(Integer id) {
    }
}
