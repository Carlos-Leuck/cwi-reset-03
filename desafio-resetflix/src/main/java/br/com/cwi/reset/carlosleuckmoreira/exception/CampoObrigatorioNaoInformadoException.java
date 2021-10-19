package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class CampoObrigatorioNaoInformadoException extends Exception {
    public CampoObrigatorioNaoInformadoException(String s) {
        super("Campo obrigatório não informado. Favor informar o campo: "+ s);
    }
}
