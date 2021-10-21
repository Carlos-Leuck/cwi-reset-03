package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class NaoEhPermitidoCadastrarMesmoPersonagemComMesmoAtorMaisDeUmaVezException extends Exception {
    public NaoEhPermitidoCadastrarMesmoPersonagemComMesmoAtorMaisDeUmaVezException() {
        super("Não é permitido informar o mesmo ator/personagem mais de uma vez para o mesmo filme.");
    }
}
