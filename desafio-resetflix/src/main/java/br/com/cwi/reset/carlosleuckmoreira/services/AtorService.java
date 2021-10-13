package br.com.cwi.reset.carlosleuckmoreira.services;

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
    //TODO ID DEVE SER INCREMENTAL E ÚNICO: falta implementar e testar.

    public void criarAtor(AtorRequest atorRequest) {

        //TODO validação campo obrigatório: falta retornar o campo e testar.
        try {
            if (atorRequest.getNome() == null || atorRequest.getDataNascimento() == null
                    || atorRequest.getAnoInicioAtividade() == null || atorRequest.getStatusCarreira() == null) {
                throw new CampoObrigatorioNaoInformadoException();
            }

            //TODO validação nome e sobrenome: falta testar.
            if (!atorRequest.getNome().contains(" ")) {
                throw new NomeESobrenomeDevemSerInformadosException();
            }

            //TODO validação ano de início de atividade não pode ser anterior ao ano de nascimento do ator: falta testar
            LocalDate nascimento = atorRequest.getDataNascimento();
            Integer inicioAtividade = atorRequest.getAnoInicioAtividade();
//            LocalDate dataInteiroToLocalDate = LocalDate.parse(inicioAtividade.toString(), DateTimeFormatter.BASIC_ISO_DATE);

            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy");
            String dataInteiroToLocalDate = newFormat.format(inicioAtividade);


            int compararDatas = dataInteiroToLocalDate.compareTo(String.valueOf(nascimento));
            //Se der valor positivo então inicioAtividade > data de nascimento
            if (!(compararDatas >0)) {
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
            nomeAtor = atorRequest.getNome();
            for (int i = 0; i < fakeDatabase.recuperaAtores().size(); i++) {
                if (fakeDatabase.recuperaAtores().get(i).getNome().equals(nomeAtor)) {
                    throw new AtorJaCadastradoException();
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
        // TODO validação Caso não exista atores cadastrados deve retornar a mensagem: falta testar
        try {
            if (lista.isEmpty()) {
                throw new NaoExisteAtorCadastradoException();
            }


            for (int i = 0; i < lista.size(); i++) {
                if (!lista.get(i).getStatusCarreira().toString().equals(filtroNome)) {
                    lista.remove(i);

                }
            }

            //TODO  validação Caso não seja encontrado nenhum ator com o filtro deve retornar a mensagem: falta testar
            if (lista.isEmpty()) {
                throw new NaoExisteAtorComOFiltroInformadoException();
            }

        } catch (NaoExisteAtorCadastradoException | NaoExisteAtorComOFiltroInformadoException e) {
            e.printStackTrace();
        }

        return lista.stream().map(x -> new AtorEmAtividade(x)).collect(Collectors.toList());

    }

    public Ator consultarAtor(Integer id) {
        List<Ator> lista = new ArrayList();
        lista = fakeDatabase.recuperaAtores();

        //TODO filtro ID é obrigatório: falta testar
        try {
            if (id == null) {
                throw new CampoObrigatorioNaoInformadoException();
            }
            //TODO Caso não encontrado o Ator: falta testar

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

    public List consultarAtores() {
        //TODO validação Caso não exista atores cadastrados deve retornar a mensagem: falta testar
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
