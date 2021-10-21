package br.com.cwi.reset.carlosleuckmoreira.service;

import br.com.cwi.reset.carlosleuckmoreira.model.StatusCarreira;
import br.com.cwi.reset.carlosleuckmoreira.exception.*;
import br.com.cwi.reset.carlosleuckmoreira.response.AtorEmAtividade;
import br.com.cwi.reset.carlosleuckmoreira.request.AtorRequest;
import br.com.cwi.reset.carlosleuckmoreira.FakeDatabase;
import br.com.cwi.reset.carlosleuckmoreira.model.Ator;

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

        try {

            verificarCampoObrigatorio(atorRequest);


            if (!atorRequest.getNome().contains(" ")) {
                throw new NomeESobrenomeDevemSerInformadosException("ator");
            }

            LocalDate nascimento = atorRequest.getDataNascimento();
            Integer inicioAtividade = atorRequest.getAnoInicioAtividade();

            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy");
            String dataInteiroToLocalDate = newFormat.format(inicioAtividade);

/*           Refatoração
             1.utilizar isBefore e isAfter para melhorar legibilidade ao trabalhar com datas
             2. extrair alguns métodos para melhorar legibilidade
             3. lembrar de testar Strings usando null e " "

 */

//            validação não é possível cadastrar atores não nascidos
            if (nascimento.compareTo(LocalDate.now()) > 0) {
                throw new DataDeNascimentoInvalidaException();
            }

            //validação ano de início de atividade não pode ser anterior ao ano de nascimento
            int compararDatas = dataInteiroToLocalDate.compareTo(String.valueOf(nascimento));
            if (compararDatas <= 0) {
                throw new AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoAtorException();
            }


            //validação já existe um ator cadastrado com esse nome
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

    private void verificarCampoObrigatorio(AtorRequest atorRequest) throws CampoObrigatorioNaoInformadoException {
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
    }

    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) {
        List<Ator> lista = new ArrayList();
        lista = fakeDatabase.recuperaAtores();
        List<Ator> listaDeRetorno = new ArrayList();


        try {
//            checar se lista está vazia
            if (lista.isEmpty()) {
                throw new NaoExisteAtorCadastradoException();
            }

//            Deixar apenas atores em atividade
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getStatusCarreira() == StatusCarreira.APOSENTADO) {
                    lista.remove(i);
                }
            }

//          Não selecionou nenhum filtro.
            if (filtroNome == null) {
                return lista.stream().map(AtorEmAtividade::new).collect(Collectors.toList());
            }


            for (Ator ator : lista) {
                if (ator.getNome().contains(filtroNome)) {
                    listaDeRetorno.add(ator);
                }
            }

            if (listaDeRetorno.isEmpty()) {
                throw new NaoExisteAtorComOFiltroInformadoException(filtroNome);
            }

        } catch (NaoExisteAtorCadastradoException | NaoExisteAtorComOFiltroInformadoException e) {
            e.printStackTrace();
        }
        return listaDeRetorno.stream().map(AtorEmAtividade::new).collect(Collectors.toList());

    }

    public Ator consultarAtor(Integer id) {
        List<Ator> lista = new ArrayList();
        lista = fakeDatabase.recuperaAtores();

        try {
            if (id == null) {
                throw new CampoObrigatorioNaoInformadoException("ID");
            }

            for (Ator ator : lista) {
                if (ator.getId().equals(id)) {
                    return ator;

                }
            }
            throw new NaoExisteAtorComOIdInformadoException(id);

        } catch (CampoObrigatorioNaoInformadoException | NaoExisteAtorComOIdInformadoException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Ator> consultarAtores() {
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
