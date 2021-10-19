package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class NaoExisteDiretorComOIdInformadoException extends Exception {
    public NaoExisteDiretorComOIdInformadoException(Integer idRetorno) {
        super("Nenhum diretor encontrado com o parâmetro id="+idRetorno+", favor verifique os parâmetros informados.");
    }
}
