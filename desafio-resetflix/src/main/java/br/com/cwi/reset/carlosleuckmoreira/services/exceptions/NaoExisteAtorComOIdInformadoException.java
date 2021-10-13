package br.com.cwi.reset.carlosleuckmoreira.services.exceptions;

public class NaoExisteAtorComOIdInformadoException extends Exception {
    public NaoExisteAtorComOIdInformadoException() {
        super("Nenhum ator encontrado com o parâmetro id={campo}, favor verifique os parâmetros informados.");
    }
}
