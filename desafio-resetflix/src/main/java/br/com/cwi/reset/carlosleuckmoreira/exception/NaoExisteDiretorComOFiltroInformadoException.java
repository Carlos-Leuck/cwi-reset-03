package br.com.cwi.reset.carlosleuckmoreira.exception;

public class NaoExisteDiretorComOFiltroInformadoException extends Exception {
    public NaoExisteDiretorComOFiltroInformadoException(String filtroNome) {
        super("Diretor não encontrado com o filtro "+filtroNome+", favor informar outro filtro.");
    }
}
