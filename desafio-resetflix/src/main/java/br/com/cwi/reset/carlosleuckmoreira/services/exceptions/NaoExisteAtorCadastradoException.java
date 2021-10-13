package br.com.cwi.reset.carlosleuckmoreira.services.exceptions;

public class NaoExisteAtorCadastradoException extends Exception {
    public NaoExisteAtorCadastradoException() {
        super("Nenhum ator cadastrado, favor cadastar atores.");
    }
}
