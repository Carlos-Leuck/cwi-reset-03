package br.com.cwi.reset.carlosleuckmoreira.services.exceptions;

public class NaoExisteDiretorComOFiltroInformadoException extends Exception {
    public NaoExisteDiretorComOFiltroInformadoException() {
        super("Diretor não encontrado com o filtro {filtro}, favor informar outro filtro.");
    }
}
