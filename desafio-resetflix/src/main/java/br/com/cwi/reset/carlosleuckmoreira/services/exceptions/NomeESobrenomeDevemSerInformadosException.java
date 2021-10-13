package br.com.cwi.reset.carlosleuckmoreira.services.exceptions;

public class NomeESobrenomeDevemSerInformadosException extends Exception {
    public NomeESobrenomeDevemSerInformadosException() {
        super("Deve ser informado no mínimo nome e sobrenome para o ator.");
    }
}
