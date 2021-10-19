package br.com.cwi.reset.carlosleuckmoreira.exception;

public class CampoObrigatorioNaoInformadoException extends Exception {
    public CampoObrigatorioNaoInformadoException(String s) {
        super("Campo obrigatório não informado. Favor informar o campo: "+ s);
    }
}
