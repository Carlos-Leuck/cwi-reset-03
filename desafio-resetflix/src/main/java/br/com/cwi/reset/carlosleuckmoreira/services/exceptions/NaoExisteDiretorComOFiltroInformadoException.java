package br.com.cwi.reset.carlosleuckmoreira.services.exceptions;

public class NaoExisteDiretorComOFiltroInformadoException extends Exception {
    public NaoExisteDiretorComOFiltroInformadoException(String filtroNome) {
        super("Diretor não encontrado com o filtro "+filtroNome+", favor informar outro filtro.");
    }
}
