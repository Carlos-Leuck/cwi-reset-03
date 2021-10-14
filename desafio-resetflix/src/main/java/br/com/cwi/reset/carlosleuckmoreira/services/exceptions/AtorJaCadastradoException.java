package br.com.cwi.reset.carlosleuckmoreira.services.exceptions;

public class AtorJaCadastradoException extends Exception {
    public AtorJaCadastradoException(String nomeAtor) {
        super("Já existe um ator cadastrado para o nome: "+nomeAtor+".");
    }
}
