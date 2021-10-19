package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class NaoExisteDiretorCadastradoException extends Exception {
    public NaoExisteDiretorCadastradoException() {
        super("Nenhum diretor cadastrado, favor cadastar diretores.");
    }
}
