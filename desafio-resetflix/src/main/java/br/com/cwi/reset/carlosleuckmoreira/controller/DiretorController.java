package br.com.cwi.reset.carlosleuckmoreira.controller;

import br.com.cwi.reset.carlosleuckmoreira.model.Diretor;
import br.com.cwi.reset.carlosleuckmoreira.request.DiretorRequest;
import br.com.cwi.reset.carlosleuckmoreira.service.DiretorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    @Autowired
    private DiretorService diretorService;

    //    CADASTRAR DIRETOR
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarDiretor(@RequestBody DiretorRequest diretorRequest) {
        this.diretorService.cadastrarDiretor(diretorRequest);

    }


    //   LISTAR DIRETORES

    @GetMapping
    public List<Diretor> listarDiretores(@RequestParam(value = "nome", required = false) String filtroNome) {
        return this.diretorService.listarDiretores(filtroNome);
    }


//    CONSULTAR DIRETOR

    @GetMapping(value = "/{id}")
    public Diretor consultarDiretor(@PathVariable Integer id) {
        return this.diretorService.consultarDiretor(id);
    }
}
