package br.com.cwi.reset.carlosleuckmoreira.controller;

import br.com.cwi.reset.carlosleuckmoreira.FakeDatabase;
import br.com.cwi.reset.carlosleuckmoreira.model.Ator;
import br.com.cwi.reset.carlosleuckmoreira.request.AtorRequest;
import br.com.cwi.reset.carlosleuckmoreira.response.AtorEmAtividade;
import br.com.cwi.reset.carlosleuckmoreira.service.AtorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atores")
public class AtorController {

    private AtorService atorService;

    public AtorController() {
        this.atorService = new AtorService(FakeDatabase.getInstance());
    }

    //  CADASTRAR ATOR
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAtor(@RequestBody AtorRequest atorRequest) {
        this.atorService.criarAtor(atorRequest);
    }


    //      LISTAR ATORES EM ATIVIDADE
    @GetMapping(path = "/em_atividade")
    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) {
        return this.atorService.listarAtoresEmAtividade(filtroNome);
    }

    //  CONSULTAR ATOR
    @GetMapping(value = "/{id}")
    public Ator consultarAtor(@PathVariable Integer id) {
        return this.atorService.consultarAtor(id);
    }


    //  TODOS OS ATORES
    @GetMapping
    public List<Ator> consultarAtores() {
        return this.atorService.consultarAtores();
    }
}





