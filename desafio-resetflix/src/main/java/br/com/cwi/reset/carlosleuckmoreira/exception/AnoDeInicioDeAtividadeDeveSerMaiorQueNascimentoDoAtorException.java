package br.com.cwi.reset.carlosleuckmoreira.exception;

public class AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoAtorException extends Exception {
    public AnoDeInicioDeAtividadeDeveSerMaiorQueNascimentoDoAtorException() {
        super("Ano de início de atividade inválido para o ator cadastrado.");
    }
}
