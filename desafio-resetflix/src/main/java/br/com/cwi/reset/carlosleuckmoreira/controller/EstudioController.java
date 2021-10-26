package br.com.cwi.reset.carlosleuckmoreira.controller;

import br.com.cwi.reset.carlosleuckmoreira.model.domain.Estudio;
import br.com.cwi.reset.carlosleuckmoreira.request.EstudioRequest;
import br.com.cwi.reset.carlosleuckmoreira.service.EstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudios")
public class EstudioController {

    @Autowired
    private EstudioService estudioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarEstudio(@RequestBody EstudioRequest estudioRequest) {
        this.estudioService.cadastrarEstudio(estudioRequest);

    }

    @GetMapping
    public List<Estudio> consultarEstudios(@RequestParam(value = "nome", required = false) String filtroNome) {
        return this.estudioService.listarEstudios(filtroNome);
    }

    @GetMapping(value = "/{id}")
    public Estudio consultarEstudio(@PathVariable Integer id) {
        return this.estudioService.consultarEstudio(id);
    }
}
