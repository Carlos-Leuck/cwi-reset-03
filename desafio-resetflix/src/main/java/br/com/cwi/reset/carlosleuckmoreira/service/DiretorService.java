package br.com.cwi.reset.carlosleuckmoreira.service;

import br.com.cwi.reset.carlosleuckmoreira.exception.*;
import br.com.cwi.reset.carlosleuckmoreira.request.DiretorRequest;
import br.com.cwi.reset.carlosleuckmoreira.repository.FakeDatabase;
import br.com.cwi.reset.carlosleuckmoreira.model.Diretor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiretorService {

    @Autowired
    private FakeDatabase fakeDatabase;

    public void cadastrarDiretor(DiretorRequest diretorRequest) {


        try {
            validarCampoObrigatorio(diretorRequest);

            if (!diretorRequest.getNome().contains(" ")) {
                throw new NomeESobrenomeDevemSerInformadosException("Diretor");
            }

            LocalDate nascimento = diretorRequest.getDataNascimento();
            Integer inicioAtividade = diretorRequest.getAnoInicioAtividade();

            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy");
            String dataInteiroToLocalDate = newFormat.format(inicioAtividade);

            //Se der valor positivo então inicioAtividade > data de nascimento
            if (nascimento.compareTo(LocalDate.now()) > 0) {
                throw new DataDeNascimentoInvalidaException();
            }

            int compararDatas = dataInteiroToLocalDate.compareTo(String.valueOf(nascimento));
            if (compararDatas <= 0) {
                throw new AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoAtorException();
            }

            String nomeDiretor;
            nomeDiretor = diretorRequest.getNome();
            for (int i = 0; i < fakeDatabase.recuperaDiretores().size(); i++) {
                if (fakeDatabase.recuperaDiretores().get(i).getNome().equals(nomeDiretor)) {
                    throw new DiretorJaCadastradoException(nomeDiretor);
                }
            }

            Diretor diretor = new Diretor(diretorRequest);
            fakeDatabase.persisteDiretor(diretor);

        } catch (CampoObrigatorioNaoInformadoException | NomeESobrenomeDevemSerInformadosException |
                DataDeNascimentoInvalidaException | AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoAtorException
                | DiretorJaCadastradoException e) {
            e.printStackTrace();
        }


    }

    private void validarCampoObrigatorio(DiretorRequest diretorRequest) throws CampoObrigatorioNaoInformadoException {
        if (diretorRequest.getNome() == null) {
            throw new CampoObrigatorioNaoInformadoException("nome");
        }
        if (diretorRequest.getDataNascimento() == null) {
            throw new CampoObrigatorioNaoInformadoException("data de nascimento");
        }
        if (diretorRequest.getAnoInicioAtividade() == null) {
            throw new CampoObrigatorioNaoInformadoException("ano de inicio da atividade");
        }
    }

    public List<Diretor> listarDiretores(String filtroNome) {
        List<Diretor> lista = new ArrayList();
        List<Diretor> listaDeRetorno = new ArrayList();
        lista = fakeDatabase.recuperaDiretores();

        try {
            if (lista.isEmpty()) {
                throw new NaoExisteDiretorCadastradoException();
            }
//            Não selecionou nenhum filtro
            if (filtroNome == null) {
                return lista;
            }
            for (Diretor diretor : lista) {
                if (diretor.getNome().contains(filtroNome)) {
                    listaDeRetorno.add(diretor);

                }
            }
            if (listaDeRetorno.isEmpty())
                throw new NaoExisteDiretorComOFiltroInformadoException(filtroNome);

        } catch (NaoExisteDiretorCadastradoException | NaoExisteDiretorComOFiltroInformadoException e) {
            e.printStackTrace();
        }
        return listaDeRetorno;

    }


    public Diretor consultarDiretor(Integer id) {
        List<Diretor> lista = new ArrayList();
        lista = fakeDatabase.recuperaDiretores();

        try {
            if (id == null) {
                throw new CampoObrigatorioNaoInformadoException("ID");
            }

            for (Diretor diretor : lista) {
                if (diretor.getId().equals(id)) {
                    return diretor;

                }
            }
            throw new NaoExisteDiretorComOIdInformadoException(id);

        } catch (CampoObrigatorioNaoInformadoException | NaoExisteDiretorComOIdInformadoException e) {
            e.printStackTrace();
        }
        return null;
    }
}

