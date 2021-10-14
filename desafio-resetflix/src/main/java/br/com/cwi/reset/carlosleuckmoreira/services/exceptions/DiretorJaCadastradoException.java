package br.com.cwi.reset.carlosleuckmoreira.services.exceptions;

public class DiretorJaCadastradoException extends Exception {
    public DiretorJaCadastradoException(String nomeDiretor) {
        super("Já existe um ator cadastrado para o nome: "+ nomeDiretor +".");
    }
}
