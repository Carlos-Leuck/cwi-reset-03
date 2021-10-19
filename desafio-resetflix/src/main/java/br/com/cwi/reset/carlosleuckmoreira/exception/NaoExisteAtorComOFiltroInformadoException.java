package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class NaoExisteAtorComOFiltroInformadoException extends Exception {
    public NaoExisteAtorComOFiltroInformadoException(String filtroNome) {
        super("Ator não encontrado com o filtro "+filtroNome+", favor informar outro filtro.");
    }
}
