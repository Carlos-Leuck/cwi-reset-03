package br.com.cwi.reset.carlosleuckmoreira.service;

import br.com.cwi.reset.carlosleuckmoreira.exception.*;
import br.com.cwi.reset.carlosleuckmoreira.repository.DiretorRepository;
import br.com.cwi.reset.carlosleuckmoreira.request.DiretorRequest;
import br.com.cwi.reset.carlosleuckmoreira.model.domain.Diretor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepository diretorRepository;

    public void cadastrarDiretor(DiretorRequest diretorRequest) {

        try {

            validarSeNomeESobreNomeForamInformados(diretorRequest);
            validarSeAnoDeInicioDeAtividadeMaiorQueDataNascimentoDiretor(diretorRequest);
            validarSeJaExisteDiretorCadastradoComMesmoNome(diretorRequest);

            final Diretor diretor = new Diretor(diretorRequest.getNome(), diretorRequest.getDataNascimento(),
                    diretorRequest.getAnoInicioAtividade());
            diretorRepository.save(diretor);

        } catch (NomeESobrenomeDevemSerInformadosException | DiretorJaCadastradoException |
                AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoDiretorException e) {
            e.printStackTrace();
        }
    }

    public List<Diretor> listarDiretores(String filtroNome) {
        List<Diretor> diretoresCadastrados = diretorRepository.findAll();
        List<Diretor> retorno = new ArrayList<>();

        try {
            if (diretoresCadastrados.isEmpty()) {
                throw new NaoExisteDiretorCadastradoException();
            }
            if (filtroNome == null) {
                return diretoresCadastrados;
            }

            for (Diretor diretor : diretoresCadastrados) {
                if (diretor.getNome().contains(filtroNome)) {
                    retorno.add(diretor);
                }
            }

            if (retorno.isEmpty())
                throw new NaoExisteDiretorComOFiltroInformadoException(filtroNome);
        } catch (NaoExisteDiretorCadastradoException | NaoExisteDiretorComOFiltroInformadoException e) {
            e.printStackTrace();
        }
        return retorno;
    }


    public Diretor consultarDiretor(@NotNull(message = "Campo obrigatório não informado. Favor informar o campo id.") Integer id) {

        Diretor diretorFiltradoPeloId = diretorRepository.findDiretorById(id);
        try {
            if (diretorFiltradoPeloId == null) {
                throw new NaoExisteDiretorComOIdInformadoException(id);
            }
        } catch (NaoExisteDiretorComOIdInformadoException e) {
            e.printStackTrace();
        }
        return diretorFiltradoPeloId;
    }

    private void validarSeAnoDeInicioDeAtividadeMaiorQueDataNascimentoDiretor(DiretorRequest diretorRequest) throws AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoDiretorException {
        if (diretorRequest.getAnoInicioAtividade() <= diretorRequest.getDataNascimento().getYear()) {
            throw new AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoDiretorException();
        }
    }

    private void validarSeNomeESobreNomeForamInformados(DiretorRequest diretorRequest) throws NomeESobrenomeDevemSerInformadosException {
        if (diretorRequest.getNome().split(" ").length < 2) {
            throw new NomeESobrenomeDevemSerInformadosException("diretor");
        }
    }

    private void validarSeJaExisteDiretorCadastradoComMesmoNome(DiretorRequest diretorRequest) throws DiretorJaCadastradoException {
        String nomeDiretor;
        nomeDiretor = diretorRequest.getNome();

        for (int i = 0; i < diretorRepository.findAll().size(); i++) {
            if (diretorRepository.findAll().get(i).getNome().equals(nomeDiretor)) {
                throw new DiretorJaCadastradoException(nomeDiretor);
            }
        }
    }
}

