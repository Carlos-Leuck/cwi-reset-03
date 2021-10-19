package br.com.cwi.reset.carlosleuckmoreira.exception;

public class NaoExisteDiretorCadastradoException extends Exception {
    public NaoExisteDiretorCadastradoException() {
        super("Nenhum diretor cadastrado, favor cadastar diretores.");
    }
}
