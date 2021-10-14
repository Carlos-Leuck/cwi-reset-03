package br.com.cwi.reset.carlosleuckmoreira.services.exceptions;

public class NaoExisteAtorComOIdInformadoException extends Exception {
    public NaoExisteAtorComOIdInformadoException(Integer idRetorno) {
        super("Nenhum ator encontrado com o parâmetro id= "+idRetorno+", favor verifique os parâmetros informados.");
    }
}
