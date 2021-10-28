package br.com.cwi.reset.carlosleuckmoreira.controller;

import br.com.cwi.reset.carlosleuckmoreira.model.domain.Ator;
import br.com.cwi.reset.carlosleuckmoreira.request.AtorRequest;
import br.com.cwi.reset.carlosleuckmoreira.response.AtorEmAtividade;
import br.com.cwi.reset.carlosleuckmoreira.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/atores")
public class AtorController {

    @Autowired
    private AtorService atorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAtor(@RequestBody @Valid AtorRequest atorRequest) {
        this.atorService.criarAtor(atorRequest);
    }

    @GetMapping(path = "/em_atividade")
    public List<AtorEmAtividade> listarAtoresEmAtividade(@RequestParam(value = "nome", required = false) String filtroNome) {
        return this.atorService.listarAtoresEmAtividade(filtroNome);
    }

    @GetMapping(path = "/{id}")
    public Ator consultarAtor(@PathVariable Integer id) {
        return this.atorService.consultarAtor(id);
    }

    @GetMapping
    public List<Ator> consultarAtores() {
        return this.atorService.consultarAtores();
    }

    @PutMapping(path = "/{id}")
    public void atualizarAtor(@PathVariable Integer id, @Valid AtorRequest atorRequest) {
        this.atorService.atualizarAtor(id, atorRequest);
    }

    @DeleteMapping(path = "/{id}")
    public void removerAtor(@PathVariable Integer id) {
        this.atorService.removerAtor(id);
    }

}



