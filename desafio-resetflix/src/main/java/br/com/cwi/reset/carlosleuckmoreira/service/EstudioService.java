package br.com.cwi.reset.carlosleuckmoreira.service;

import br.com.cwi.reset.carlosleuckmoreira.exception.*;
import br.com.cwi.reset.carlosleuckmoreira.request.EstudioRequest;
import br.com.cwi.reset.carlosleuckmoreira.repository.FakeDatabase;
import br.com.cwi.reset.carlosleuckmoreira.model.Estudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EstudioService {
    @Autowired
    private FakeDatabase fakeDatabase;

    public void cadastrarEstudio(EstudioRequest estudioRequest) {

        try {
            validarCampoObrigatorio(estudioRequest);


            if (estudioRequest.getDataCriacao().isAfter(LocalDate.now())) {
                throw new DataCriacaoEstudioNaoPodeSerMaiorQueDataAtualException();
            }

            validarEstudioJaCadastradComNomeMesmoNome(estudioRequest);

            Estudio estudio = new Estudio(estudioRequest);
            fakeDatabase.persisteEstudio(estudio);

        } catch (CampoObrigatorioNaoInformadoException |
                DataCriacaoEstudioNaoPodeSerMaiorQueDataAtualException |
                EstudioJaCadastradoException e) {
            e.printStackTrace();
        }
    }

    private void validarEstudioJaCadastradComNomeMesmoNome(EstudioRequest estudioRequest) throws EstudioJaCadastradoException {
        String nomeEstudio;
        nomeEstudio = estudioRequest.getNome();
        for (int i = 0; i < fakeDatabase.recuperaEstudios().size(); i++) {
            if (fakeDatabase.recuperaEstudios().get(i).getNome().equals(nomeEstudio)) {
                throw new EstudioJaCadastradoException(nomeEstudio);
            }
        }
    }

    private void validarCampoObrigatorio(EstudioRequest estudioRequest) throws CampoObrigatorioNaoInformadoException {
        if (estudioRequest.getNome() == null) {
            throw new CampoObrigatorioNaoInformadoException("nome");
        }
        if (estudioRequest.getDescricao() == null) {
            throw new CampoObrigatorioNaoInformadoException("descrição");
        }
        if (estudioRequest.getDataCriacao() == null) {
            throw new CampoObrigatorioNaoInformadoException("ano de inicio da atividade");
        }
        if (estudioRequest.getStatusAtividade() == null) {
            throw new CampoObrigatorioNaoInformadoException("status atividade");
        }
    }

    public List<Estudio> listarEstudios(String filtroNome) {
        List<Estudio> lista;
        List<Estudio> listaDeRetorno = new ArrayList();
        lista = fakeDatabase.recuperaEstudios();


        try {
            if (lista.isEmpty()) {
                throw new NaoExisteEstudioCadastradoException();
            }

            if (filtroNome == null) {
                return lista;
            }

            for (Estudio estudio : lista) {
                if (estudio.getNome().contains(filtroNome)) {
                    listaDeRetorno.add(estudio);
                }
            }

            if (listaDeRetorno.isEmpty())
                throw new NaoExisteEstudioComOFiltroInformadoException(filtroNome);

        } catch (NaoExisteEstudioCadastradoException | NaoExisteEstudioComOFiltroInformadoException e) {
            e.printStackTrace();
        }
        return listaDeRetorno;

    }


    public Estudio consultarEstudio(Integer id) {
        List<Estudio> lista = new ArrayList();
        lista = fakeDatabase.recuperaEstudios();

        try {
            if (id == null) {
                throw new CampoObrigatorioNaoInformadoException("ID");
            }

            for (Estudio estudio : lista) {
                if (estudio.getId().equals(id)) {
                    return estudio;
                }
            }
            throw new NaoExisteEstudioComOIdInformadoException(id);

        } catch (CampoObrigatorioNaoInformadoException | NaoExisteEstudioComOIdInformadoException e) {
            e.printStackTrace();
        }
        return null;
    }
}

