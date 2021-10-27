package br.com.cwi.reset.carlosleuckmoreira.controller;

import br.com.cwi.reset.carlosleuckmoreira.model.domain.Filme;
import br.com.cwi.reset.carlosleuckmoreira.request.FilmeRequest;
import br.com.cwi.reset.carlosleuckmoreira.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarFilme(@RequestBody @Valid FilmeRequest filmeRequest){
        this.filmeService.criarFilme(filmeRequest);
    }

    @GetMapping
    public List<Filme> consultarFilmes(
            @RequestParam String nomeFilme,
            @RequestParam String nomeDiretor,
            @RequestParam String nomePersonagem,
            @RequestParam String nomeAtor){
        return filmeService.consultarFilmes(nomeFilme, nomeDiretor, nomePersonagem, nomeAtor);
    }

}
