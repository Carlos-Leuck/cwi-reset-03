package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class NomeESobrenomeDevemSerInformadosException extends Exception {
    public NomeESobrenomeDevemSerInformadosException(String s) {
        super("Deve ser informado no mínimo nome e sobrenome para o " + s);
    }
}
