package br.com.cwi.reset.carlosleuckmoreira.service;

import br.com.cwi.reset.carlosleuckmoreira.exception.*;
import br.com.cwi.reset.carlosleuckmoreira.model.domain.Filme;
import br.com.cwi.reset.carlosleuckmoreira.repository.DiretorRepository;
import br.com.cwi.reset.carlosleuckmoreira.repository.FilmeRepository;
import br.com.cwi.reset.carlosleuckmoreira.request.DiretorRequest;
import br.com.cwi.reset.carlosleuckmoreira.model.domain.Diretor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepository diretorRepository;
    @Autowired
    private FilmeRepository filmeRepository;


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


    public Diretor consultarDiretor(Integer id) {

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

    public void atualizarDiretor(Integer id, DiretorRequest diretorRequest) {
        try {
            validarSeJaExisteDiretorCadastradoComMesmoNome(diretorRequest);
            Diretor diretorAtualizado = new Diretor(diretorRequest.getNome(), diretorRequest.getDataNascimento(),
                    diretorRequest.getAnoInicioAtividade());
            diretorAtualizado.setId(consultarDiretor(id).getId());
            diretorRepository.save(diretorAtualizado);
        } catch (DiretorJaCadastradoException e) {
            e.printStackTrace();
        }
    }

    public void removerDiretores(Integer id) {
        Diretor diretorQueDeveSerRemovido = consultarDiretor(id);

        if (validarSeDiretorPossuiFilmes(diretorQueDeveSerRemovido)) {
            try {
                throw new DiretorNaoPodeEstarVinculadoMaisDeUmFilmeException();
            } catch (DiretorNaoPodeEstarVinculadoMaisDeUmFilmeException e) {
                e.printStackTrace();
            }
        }
        diretorRepository.delete(diretorQueDeveSerRemovido);
    }

    private boolean validarSeDiretorPossuiFilmes(Diretor diretor) {
        for (Filme d : filmeRepository.findAll()) {
            if (d.getDiretor().getNome().equalsIgnoreCase(diretor.getNome())) {
                return true;
            }
        }
        return false;
    }
}



