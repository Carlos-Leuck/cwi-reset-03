package br.com.cwi.reset.carlosleuckmoreira.controller;

import br.com.cwi.reset.carlosleuckmoreira.request.FilmeRequest;
import br.com.cwi.reset.carlosleuckmoreira.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    //    CADASTRAR FILME
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarFilme(@RequestBody FilmeRequest filmeRequest) {
        this.filmeService.cadastrarFilme(filmeRequest);

    }


    //   CONSULTAR FILMES
/*
    @GetMapping
    public List<Filme> consultarFilmes(@RequestParam(value = "nome", required = false) String filtroNome) {
        return this.filmeService.listarFilmes(filtroNome);
    }
*/

}
