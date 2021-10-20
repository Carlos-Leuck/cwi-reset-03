package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class DataCriacaoEstudioNaoPodeSerMaiorQueDataAtualException extends Exception {
    public DataCriacaoEstudioNaoPodeSerMaiorQueDataAtualException() {
        super("Não é possível cadastrar estúdios do futuro");
    }
}
