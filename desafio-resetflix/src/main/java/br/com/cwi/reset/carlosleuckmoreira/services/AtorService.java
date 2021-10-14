package br.com.cwi.reset.carlosleuckmoreira.services;

import br.com.cwi.reset.carlosleuckmoreira.StatusCarreira;
import br.com.cwi.reset.carlosleuckmoreira.dto.AtorEmAtividade;
import br.com.cwi.reset.carlosleuckmoreira.dto.AtorRequest;
import br.com.cwi.reset.carlosleuckmoreira.repositories.FakeDatabase;
import br.com.cwi.reset.carlosleuckmoreira.entities.Ator;
import br.com.cwi.reset.carlosleuckmoreira.services.exceptions.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AtorService {
    private FakeDatabase fakeDatabase;

    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarAtor(AtorRequest atorRequest) {

        //validação campo obrigatório ator: OK
        try {

            if (atorRequest.getNome() == null) {
                throw new CampoObrigatorioNaoInformadoException("nome");
            }
            if (atorRequest.getDataNascimento() == null) {
                throw new CampoObrigatorioNaoInformadoException("data de nascimento");
            }
            if (atorRequest.getAnoInicioAtividade() == null) {
                throw new CampoObrigatorioNaoInformadoException("ano de inicio da atividade");
            }
            if (atorRequest.getStatusCarreira() == null) {
                throw new CampoObrigatorioNaoInformadoException("status da carreira");
            }

            //validação nome e sobrenome: OK
            if (!atorRequest.getNome().contains(" ")) {
                throw new NomeESobrenomeDevemSerInformadosException("Ator");
            }

            LocalDate nascimento = atorRequest.getDataNascimento();
            Integer inicioAtividade = atorRequest.getAnoInicioAtividade();

            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy");
            String dataInteiroToLocalDate = newFormat.format(inicioAtividade);


//            validação não é possível cadastrar atores não nascidos:OK
            if (nascimento.compareTo(LocalDate.now()) > 0) {
                throw new DataDeNascimentoInvalidaException();
            }

            //validação ano de início de atividade não pode ser anterior ao ano de nascimento do ator: OK
            int compararDatas = dataInteiroToLocalDate.compareTo(String.valueOf(nascimento));
            if (compararDatas <= 0) {
                throw new AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoAtorException();
            }


            //validação já existe um ator cadastrado com esse nome: OK
            String nomeAtor;
            nomeAtor = atorRequest.getNome();
            for (int i = 0; i < fakeDatabase.recuperaAtores().size(); i++) {
                if (fakeDatabase.recuperaAtores().get(i).getNome().equals(nomeAtor)) {
                    throw new AtorJaCadastradoException(nomeAtor);
                }
            }

            Ator ator = new Ator(atorRequest);
            fakeDatabase.persisteAtor(ator);

        } catch (CampoObrigatorioNaoInformadoException | NomeESobrenomeDevemSerInformadosException
                | AtorJaCadastradoException | DataDeNascimentoInvalidaException
                | AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoAtorException e) {
            e.printStackTrace();
        }


    }

    public List listarAtoresEmAtividade(String filtroNome) {
        List<Ator> lista = new ArrayList();
        lista = fakeDatabase.recuperaAtores();

        try {
            if (lista.isEmpty()) {
                throw new NaoExisteAtorCadastradoException();
            }

            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getStatusCarreira() == StatusCarreira.APOSENTADO) {
                    lista.remove(i);
                }
            }

            for (int i = 0; i < lista.size(); i++) {
                if (!lista.get(i).getNome().contains(filtroNome) && lista.isEmpty()) {
                    throw new NaoExisteAtorComOFiltroInformadoException(filtroNome);
                }
            }

        } catch (NaoExisteAtorCadastradoException | NaoExisteAtorComOFiltroInformadoException e) {
            e.printStackTrace();
        }
        return lista.stream().map(AtorEmAtividade::new).collect(Collectors.toList());

    }

    public Ator consultarAtor(Integer id) {
        List<Ator> lista = new ArrayList();
        lista = fakeDatabase.recuperaAtores();

        try {
            if (id == null) {
                throw new CampoObrigatorioNaoInformadoException("ID");
            }

            for (int i = 0; i < lista.size(); i++) {
                if ((lista.get(i).getId()) == (id)) {
                    return lista.get(i);

                }
            }
            throw new NaoExisteAtorComOIdInformadoException(id);

        } catch (CampoObrigatorioNaoInformadoException | NaoExisteAtorComOIdInformadoException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List consultarAtores() {
        try {
            if (fakeDatabase.recuperaAtores().isEmpty()) {
                throw new NaoExisteAtorCadastradoException();

            }
        } catch (NaoExisteAtorCadastradoException e) {
            e.printStackTrace();
        }

        return fakeDatabase.recuperaAtores();
    }
}
