package br.com.cwi.reset.projeto1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AtorJaExistenteException extends Exception {

    public AtorJaExistenteException(String message) {
        super(message);
    }
}