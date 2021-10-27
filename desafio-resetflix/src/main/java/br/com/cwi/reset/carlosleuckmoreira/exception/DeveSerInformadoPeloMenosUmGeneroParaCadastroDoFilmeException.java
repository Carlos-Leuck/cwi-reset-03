package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DeveSerInformadoPeloMenosUmGeneroParaCadastroDoFilmeException extends Exception {
    public DeveSerInformadoPeloMenosUmGeneroParaCadastroDoFilmeException() {
        super("Deve ser informado pelo menos um gênero para o cadastro do filme.");
    }
}
