package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class NaoExisteAtorComOIdInformadoException extends Exception {
    public NaoExisteAtorComOIdInformadoException(Integer idRetorno) {
        super("Nenhum ator encontrado com o parâmetro id= "+idRetorno+", favor verifique os parâmetros informados.");
    }
}
