package br.com.cwi.reset.carlosleuckmoreira.services.exceptions;

public class NomeESobrenomeDevemSerInformadosException extends Exception {
    public NomeESobrenomeDevemSerInformadosException(String s) {
        super("Deve ser informado no mínimo nome e sobrenome para o " + s);
    }
}
