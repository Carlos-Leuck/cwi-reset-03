package br.com.cwi.reset.carlosleuckmoreira.service;

import br.com.cwi.reset.carlosleuckmoreira.exception.*;
import br.com.cwi.reset.carlosleuckmoreira.model.domain.Diretor;
import br.com.cwi.reset.carlosleuckmoreira.repository.EstudioRepository;
import br.com.cwi.reset.carlosleuckmoreira.request.EstudioRequest;
import br.com.cwi.reset.carlosleuckmoreira.model.domain.Estudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
public class EstudioService {
    @Autowired
    private EstudioRepository estudioRepository;

    public void cadastrarEstudio(EstudioRequest estudioRequest) {

        try {
            validarEstudioJaCadastradoComNomeMesmoNome(estudioRequest);

            final Estudio estudio = new Estudio(estudioRequest.getNome(), estudioRequest.getDescricao(), estudioRequest.getDataCriacao(), estudioRequest.getStatusAtividade());
            estudioRepository.save(estudio);
        } catch (EstudioJaCadastradoException e) {
            e.printStackTrace();
        }
    }

    private void validarEstudioJaCadastradoComNomeMesmoNome(EstudioRequest estudioRequest) throws EstudioJaCadastradoException {
        String nomeEstudio;
        nomeEstudio = estudioRequest.getNome();
        for (int i = 0; i < estudioRepository.findAll().size(); i++) {
            if (estudioRepository.findAll().get(i).getNome().equals(nomeEstudio)) {
                throw new EstudioJaCadastradoException(nomeEstudio);
            }
        }
    }


    public List<Estudio> consultarEstudios(String filtroNome) {
        List<Estudio> estudiosCadastrados = estudioRepository.findAll();
        List<Estudio> retorno = new ArrayList();

        try {
            if (estudiosCadastrados.isEmpty()) {
                throw new NaoExisteEstudioCadastradoException();
            }

            if (filtroNome == null) {
                return estudiosCadastrados;
            }

            for (Estudio estudio : estudiosCadastrados) {
                if (estudio.getNome().contains(filtroNome)) {
                    retorno.add(estudio);
                }
            }

            if (retorno.isEmpty())
                throw new NaoExisteEstudioComOFiltroInformadoException(filtroNome);

        } catch (NaoExisteEstudioCadastradoException | NaoExisteEstudioComOFiltroInformadoException e) {
            e.printStackTrace();
        }
        return retorno;

    }


    public Estudio consultarEstudio(@NotNull(message = "Campo obrigatório não informado. Favor informar o campo id.") Integer id) {
        Estudio estudioFiltradoPeloId = estudioRepository.findEstudioById(id);

        try {
            if (estudioFiltradoPeloId == null) {
                throw new NaoExisteEstudioComOIdInformadoException(id);
            }
        } catch (NaoExisteEstudioComOIdInformadoException e) {
            e.printStackTrace();
        }
        return estudioFiltradoPeloId;
    }
}

