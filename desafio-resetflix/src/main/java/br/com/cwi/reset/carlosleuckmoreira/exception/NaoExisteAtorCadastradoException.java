package br.com.cwi.reset.carlosleuckmoreira.exception;

public class NaoExisteAtorCadastradoException extends Exception {
    public NaoExisteAtorCadastradoException() {
        super("Nenhum ator cadastrado, favor cadastar atores.");
    }
}
