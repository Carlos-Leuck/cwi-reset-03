package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class FilmeNaoEncontradoComOsFiltrosUsadosException extends Exception {
    public FilmeNaoEncontradoComOsFiltrosUsadosException(String message) {
        super(message);
    }
}
