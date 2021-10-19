package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class AtorNaoEncontradoComIdInformadoException extends Exception {
    public AtorNaoEncontradoComIdInformadoException() {
        super("Nenhum ator encontrado com o parâmetro id={campo}, favor verifique os parâmetros informados.");
    }
}
