package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class NaoExisteEstudioComOFiltroInformadoException extends Exception {
    public NaoExisteEstudioComOFiltroInformadoException(String filtroNome) {
        super("Estúdio não encontrado com o filtro "+filtroNome+", favor informar outro filtro.");
    }
}
