package br.com.cwi.reset.carlosleuckmoreira.exception;

public class AtorJaCadastradoException extends Exception {
    public AtorJaCadastradoException(String nomeAtor) {
        super("Já existe um ator cadastrado para o nome: "+nomeAtor+".");
    }
}
