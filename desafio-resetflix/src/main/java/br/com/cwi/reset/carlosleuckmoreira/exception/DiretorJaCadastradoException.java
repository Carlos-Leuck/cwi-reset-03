package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class DiretorJaCadastradoException extends Exception {
    public DiretorJaCadastradoException(String nomeDiretor) {
        super("Já existe um diretor cadastrado para o nome: "+ nomeDiretor +".");
    }
}
