package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoDiretorException extends Exception {
    public AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoDiretorException() {
        super("Ano de início de atividade inválido para o diretor cadastrado.");
    }
}
