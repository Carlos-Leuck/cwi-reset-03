package br.com.cwi.reset.carlosleuckmoreira.services.exceptions;

public class NaoExisteAtorComOFiltroInformadoException extends Exception {
    public NaoExisteAtorComOFiltroInformadoException() {
        super("Ator não encontrado com o filtro {filtro}, favor informar outro filtro.");
    }
}
