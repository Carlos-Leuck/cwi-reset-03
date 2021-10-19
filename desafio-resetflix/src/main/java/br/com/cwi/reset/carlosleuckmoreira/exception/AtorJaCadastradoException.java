package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class AtorJaCadastradoException extends Exception {
    public AtorJaCadastradoException(String nomeAtor) {
        super("Já existe um ator cadastrado para o nome: "+nomeAtor+".");
    }
}
