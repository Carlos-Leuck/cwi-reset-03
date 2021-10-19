package br.com.cwi.reset.carlosleuckmoreira.service;

import br.com.cwi.reset.carlosleuckmoreira.exception.*;
import br.com.cwi.reset.carlosleuckmoreira.request.DiretorRequest;
import br.com.cwi.reset.carlosleuckmoreira.FakeDatabase;
import br.com.cwi.reset.carlosleuckmoreira.model.Diretor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiretorService {
    private FakeDatabase fakeDatabase;

    public DiretorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void cadastrarDiretor(DiretorRequest diretorRequest) {


        try {
            if (diretorRequest.getNome() == null) {
                throw new CampoObrigatorioNaoInformadoException("nome");
            }
            if (diretorRequest.getDataNascimento() == null) {
                throw new CampoObrigatorioNaoInformadoException("data de nascimento");
            }
            if (diretorRequest.getAnoInicioAtividade() == null) {
                throw new CampoObrigatorioNaoInformadoException("ano de inicio da atividade");
            }

//          melhorar essa validação para não aceitar nome sendo dois espaços em branco
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

    public List listarDiretores(String filtroNome) {
        List<Diretor> lista = new ArrayList();
        List<Diretor> listaDeRetorno = new ArrayList();
        lista = fakeDatabase.recuperaDiretores();

        try {
            if (lista.isEmpty()) {
                throw new NaoExisteDiretorCadastradoException();
            }
//            Não selecionou nenhum filtro
            if (filtroNome == "" || filtroNome==null) {
                return lista;
            }
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getNome().contains(filtroNome)) {
                    listaDeRetorno.add(lista.get(i));

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

            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getId().equals(id)) {
                    return lista.get(i);

                }
            }
            throw new NaoExisteDiretorComOIdInformadoException(id);

        } catch (CampoObrigatorioNaoInformadoException | NaoExisteDiretorComOIdInformadoException e) {
            e.printStackTrace();
        }
        return null;
    }
}

