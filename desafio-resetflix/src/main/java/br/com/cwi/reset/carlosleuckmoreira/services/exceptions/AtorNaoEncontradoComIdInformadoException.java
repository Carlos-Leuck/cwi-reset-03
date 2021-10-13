package br.com.cwi.reset.carlosleuckmoreira.services.exceptions;

public class AtorNaoEncontradoComIdInformadoException extends Exception {
    public AtorNaoEncontradoComIdInformadoException() {
        super("Nenhum ator encontrado com o parâmetro id={campo}, favor verifique os parâmetros informados.");
    }
}
