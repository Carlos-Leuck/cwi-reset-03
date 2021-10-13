package br.com.cwi.reset.carlosleuckmoreira.services.exceptions;

public class CampoObrigatorioNaoInformadoException extends Exception {
    public CampoObrigatorioNaoInformadoException() {
        super("Campo obrigatório não informado. Favor informar o campo {campo}.");
    }
}
