package br.com.cwi.reset.carlosleuckmoreira.exception;

public class DataDeNascimentoInvalidaException extends Exception {
    public DataDeNascimentoInvalidaException() {
        super("Não é possível cadastrar atores não nascidos.");
    }
}
