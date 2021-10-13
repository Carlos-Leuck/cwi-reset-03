package br.com.cwi.reset.carlosleuckmoreira.services;

import br.com.cwi.reset.carlosleuckmoreira.dto.DiretorRequest;
import br.com.cwi.reset.carlosleuckmoreira.repositories.FakeDatabase;
import br.com.cwi.reset.carlosleuckmoreira.entities.Diretor;
import br.com.cwi.reset.carlosleuckmoreira.services.exceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DiretorService {
    private FakeDatabase fakeDatabase;

    public DiretorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void cadastrarDiretor(DiretorRequest diretorRequest) {
        Diretor diretor = new Diretor(diretorRequest);
        fakeDatabase.persisteDiretor(diretor);

        //TODO validação campo obrigatório: falta retornar o campo e testar.
        try {
            if (diretorRequest.getNome() == null || diretorRequest.getDataNascimento() == null
                    || diretorRequest.getAnoInicioAtividade() == null) {
                throw new CampoObrigatorioNaoInformadoException();
            }

            //TODO validação nome e sobrenome: falta testar.
            if (!diretorRequest.getNome().contains(" ")) {
                throw new NomeESobrenomeDevemSerInformadosException();
            }

            //TODO validação ano de início de atividade não pode ser anterior ao ano de nascimento do ator: falta testar
            LocalDate nascimento = diretorRequest.getDataNascimento();
            Integer inicioAtividade = diretorRequest.getAnoInicioAtividade();
            LocalDate date = LocalDate.parse(inicioAtividade.toString(), DateTimeFormatter.BASIC_ISO_DATE);
            int compararDatas = date.compareTo(nascimento);
            //Se der valor positivo então inicioAtividade > data de nascimento
            if (compararDatas > 0) {
                throw new AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoAtorException();
            }

            // TODO validação ator não nascido: falta testar
            //validação não é possível cadastrar atores não nascidos
            //se der positivo a data de nascimento > data atual
            //Se der 0 significa que data de nascimento == data atual
            if (nascimento.compareTo(LocalDate.now()) >= 0) {
                throw new DataDeNascimentoInvalidaException();
            }

            //TODO validação já existe um ator cadastrado com esse nome: falta testar
            String nomeAtor;
            nomeAtor = diretorRequest.getNome();
            for (int i = 0; i < fakeDatabase.recuperaAtores().size(); i++) {
                if (fakeDatabase.recuperaAtores().get(i).getNome().equals(nomeAtor)) {
                    throw new AtorJaCadastradoException();
                }
            }

            Diretor diretor1 = new Diretor(diretorRequest);
            fakeDatabase.persisteDiretor(diretor1);

        } catch (CampoObrigatorioNaoInformadoException | NomeESobrenomeDevemSerInformadosException
                | AtorJaCadastradoException | DataDeNascimentoInvalidaException
                | AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoAtorException e) {
            e.printStackTrace();
        }


    }

    public List listarDiretores(String filtroNome) {
        List<Diretor> lista = new ArrayList();
        lista = fakeDatabase.recuperaDiretores();
        // TODO validação Caso não exista diretores cadastrados deve retornar a mensagem: falta testar
        //TODO  validação Caso não seja encontrado nenhum ator com o filtro deve retornar a mensagem: falta testar
        try {
            if (lista.isEmpty()) {
                throw new NaoExisteDiretorCadastradoException();
            }

            for (int i = 0; i < lista.size(); i++) {
                if (!lista.get(i).getNome().equals(filtroNome)) {
                    lista.remove(i);
                }
            }
            if (lista.isEmpty()) {
                throw new NaoExisteDiretorComOFiltroInformadoException();
            }
        } catch (NaoExisteDiretorCadastradoException | NaoExisteDiretorComOFiltroInformadoException e) {
            e.printStackTrace();
        }
        return lista;
    }


    public Diretor consultarDiretor(Integer id) {
        List<Diretor> lista = new ArrayList();
        lista = fakeDatabase.recuperaDiretores();

        //TODO filtro ID é obrigatório: falta testar
        try {
            if (id == null) {
                throw new CampoObrigatorioNaoInformadoException();
            }
            //TODO Caso não encontrado o Diretor: falta testar

            for (int i = 0; i < lista.size(); i++) {
                if (!lista.get(i).getId().equals(id)) {
                    throw new NaoExisteAtorComOIdInformadoException();
                } else return lista.get(i);
            }
        } catch (CampoObrigatorioNaoInformadoException | NaoExisteAtorComOIdInformadoException e) {
            e.printStackTrace();
        }
        return lista.get(id);
    }
}

