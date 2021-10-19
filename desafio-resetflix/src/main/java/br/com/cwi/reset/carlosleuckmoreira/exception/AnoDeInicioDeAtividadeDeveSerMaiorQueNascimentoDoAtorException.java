package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoAtorException extends Exception {
    public AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoAtorException() {
        super("Ano de início de atividade inválido para o ator cadastrado.");
    }
}
