package br.com.cwi.reset.carlosleuckmoreira.controller;

import br.com.cwi.reset.carlosleuckmoreira.model.domain.Diretor;
import br.com.cwi.reset.carlosleuckmoreira.request.DiretorRequest;
import br.com.cwi.reset.carlosleuckmoreira.service.DiretorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    @Autowired
    private DiretorService diretorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarDiretor(@RequestBody @Valid DiretorRequest diretorRequest) {
        this.diretorService.cadastrarDiretor(diretorRequest);
    }

    @GetMapping
    public List<Diretor> listarDiretores(@RequestParam(value = "nome", required = false) String filtroNome) {
        return this.diretorService.listarDiretores(filtroNome);
    }

    @GetMapping(value = "/{id}")
    public Diretor consultarDiretor(@PathVariable Integer id) {
        return this.diretorService.consultarDiretor(id);
    }

    @PutMapping(value = "/{id}")
    public void atualizarDiretor(@PathVariable Integer id, @Valid DiretorRequest diretorRequest) {
        this.diretorService.atualizarDiretor(id,diretorRequest);
    }

    @DeleteMapping(path = "/{id}")
    public void removerDiretores(@PathVariable Integer id) {
        this.diretorService.removerDiretores(id);
    }

}
