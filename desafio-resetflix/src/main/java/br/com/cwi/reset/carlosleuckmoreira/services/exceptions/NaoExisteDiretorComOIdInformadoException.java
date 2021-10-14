package br.com.cwi.reset.carlosleuckmoreira.services.exceptions;

public class NaoExisteDiretorComOIdInformadoException extends Exception {
    public NaoExisteDiretorComOIdInformadoException(Integer idRetorno) {
        super("Nenhum diretor encontrado com o parâmetro id="+idRetorno+", favor verifique os parâmetros informados.");
    }
}
