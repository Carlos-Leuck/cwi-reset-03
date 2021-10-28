package br.com.cwi.reset.projeto1.controller;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.exception.AtorJaExistenteException;
import br.com.cwi.reset.projeto1.exception.AtorNaoExistenteException;
import br.com.cwi.reset.projeto1.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AtorController {
    @Autowired
    private AtorService atorService;

    @PostMapping
    public ResponseEntity<Ator> cadastrarAtor(@RequestBody Ator ator) {
        try {
            Ator atorSalvo = atorService.salvar(ator);
            return ResponseEntity.ok(atorSalvo);
        } catch (AtorJaExistenteException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<Ator> consultarTodos() {
        return atorService.listarTodos();
    }

    @GetMapping("/{nome}")
    public Ator buscarAtorPeloNome(@PathVariable String nome) {
        return atorService.buscarPorNome(nome);
    }


    @GetMapping("/{numeroOscars}")
    public Ator buscarAtorPeloNumeroDeOscars(@PathVariable Integer numeroOscars) {
        return atorService.buscarPeloNumeroDeOscars(numeroOscars);
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity deletarAtor(@PathVariable String nome) {
        try {
            atorService.deletar(nome);
            return ResponseEntity.ok().build();
        } catch (AtorNaoExistenteException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Ator> atualizarAtor(@RequestBody Ator ator) {
        try {
            return ResponseEntity.ok(atorService.atualizar(ator));
        } catch (AtorNaoExistenteException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
