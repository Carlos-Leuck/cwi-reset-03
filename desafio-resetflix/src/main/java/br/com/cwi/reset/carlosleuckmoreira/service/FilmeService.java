package br.com.cwi.reset.carlosleuckmoreira.service;

import br.com.cwi.reset.carlosleuckmoreira.exception.*;
import br.com.cwi.reset.carlosleuckmoreira.model.Genero;
import br.com.cwi.reset.carlosleuckmoreira.request.FilmeRequest;
import br.com.cwi.reset.carlosleuckmoreira.FakeDatabase;
import br.com.cwi.reset.carlosleuckmoreira.model.Filme;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

public class FilmeService {

    private FakeDatabase fakeDatabase;
    @Autowired
    private EstudioService estudioService;
    @Autowired
    private DiretorService diretorService;
    @Autowired
    private AtorService atorService;

    public FilmeService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void cadastrarFilme(FilmeRequest filmeRequest) {


//

        //  1.Campos com * são obrigatórios: nome, anoLancamento, capaFilme,idDiretor,idEstudio, resumo, personagens: OK

        try {
            verificarCampoObrigatorio(filmeRequest);


            //  2.Caso não exista nenhum estúdio cadastrado para o id informado deve retornar erro
            estudioService.consultarEstudio(filmeRequest.getIdEstudio());

            //  3.Caso não exista nenhum diretor cadastrado para o id informado deve retornar erro:
            diretorService.consultarDiretor(filmeRequest.getIdDiretor());

            //  4.Caso não exista nenhum ator (entrar na lista de personagens e pegar o id)
            //  cadastrado para o id informado deve retornar erro
            for (int i = 0; i < fakeDatabase.recuperaAtores().size(); i++) {
                atorService.consultarAtor(filmeRequest.getPersonagens().get(i).getIdAtor());
            }

           /*   5.Não é permitido cadastrar filmes que apresentem duas vezes o mesmo gênero na lista informada
                na requisição (lista<PersonagemRequest>, caso encontrado gêneros duplicados deve retornar
                a mensagem de erro: "Não é permitido informar o mesmo gênero mais de uma vez para o mesmo filme."
            */


        } catch (CampoObrigatorioNaoInformadoException campoObrigatorioNaoInformadoException) {
            campoObrigatorioNaoInformadoException.printStackTrace();
        }



            /*  6.Não é permitido cadastrar o mesmo personagem com o mesmo ator mais de uma vez,
                caso seja informado o mesmo ator para o mesmo personagem deve retornar a mensagem de erro:
                 "Não é permitido informar o mesmo ator/personagem mais de uma vez para o mesmo filme."
            */


        Filme filme = new Filme(filmeRequest);
        fakeDatabase.persisteFilme(filme);

    }

    private void verificarCampoObrigatorio(FilmeRequest filmeRequest) throws CampoObrigatorioNaoInformadoException {
        if (filmeRequest.getNome() == null) {
            throw new CampoObrigatorioNaoInformadoException("nome");
        }
        if (filmeRequest.getAnoLancamento() == null) {
            throw new CampoObrigatorioNaoInformadoException("anoLancamento");
        }
        if (filmeRequest.getCapaFilme() == null) {
            throw new CampoObrigatorioNaoInformadoException("capaFilme");
        }
        if (filmeRequest.getIdDiretor() == null) {
            throw new CampoObrigatorioNaoInformadoException("idDiretor");
        }
        if (filmeRequest.getIdEstudio() == null) {
            throw new CampoObrigatorioNaoInformadoException("idEstudio");
        }
        if (filmeRequest.getResumo() == null) {
            throw new CampoObrigatorioNaoInformadoException("resumo");
        }
        if (filmeRequest.getPersonagens() == null) {
            throw new CampoObrigatorioNaoInformadoException("personagens");
        }
        if (filmeRequest.getGeneros() == null) {
            throw new CampoObrigatorioNaoInformadoException("generos");
        }
    }


}

