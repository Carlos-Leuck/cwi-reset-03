package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class DataDeNascimentoInvalidaException extends Exception {
    public DataDeNascimentoInvalidaException() {
        super("Não é possível cadastrar atores não nascidos.");
    }
}
