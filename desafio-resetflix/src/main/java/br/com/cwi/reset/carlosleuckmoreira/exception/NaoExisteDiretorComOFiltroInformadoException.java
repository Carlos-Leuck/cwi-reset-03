package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class NaoExisteDiretorComOFiltroInformadoException extends Exception {
    public NaoExisteDiretorComOFiltroInformadoException(String filtroNome) {
        super("Diretor não encontrado com o filtro "+filtroNome+", favor informar outro filtro.");
    }
}
