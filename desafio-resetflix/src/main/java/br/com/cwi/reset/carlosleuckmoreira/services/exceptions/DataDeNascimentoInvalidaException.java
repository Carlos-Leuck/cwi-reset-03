package br.com.cwi.reset.carlosleuckmoreira.services.exceptions;

public class DataDeNascimentoInvalidaException extends Exception {
    public DataDeNascimentoInvalidaException() {
        super("Não é possível cadastrar atores não nascidos.");
    }
}
