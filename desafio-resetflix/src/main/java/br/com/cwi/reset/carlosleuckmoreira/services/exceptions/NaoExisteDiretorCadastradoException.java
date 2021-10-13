package br.com.cwi.reset.carlosleuckmoreira.services.exceptions;

public class NaoExisteDiretorCadastradoException extends Exception {
    public NaoExisteDiretorCadastradoException() {
        super("Nenhum diretor cadastrado, favor cadastar diretores.");
    }
}
