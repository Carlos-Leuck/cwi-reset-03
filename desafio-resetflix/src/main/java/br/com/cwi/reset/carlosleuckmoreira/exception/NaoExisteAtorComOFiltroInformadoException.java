package br.com.cwi.reset.carlosleuckmoreira.exception;

public class NaoExisteAtorComOFiltroInformadoException extends Exception {
    public NaoExisteAtorComOFiltroInformadoException(String filtroNome) {
        super("Ator não encontrado com o filtro "+filtroNome+", favor informar outro filtro.");
    }
}
