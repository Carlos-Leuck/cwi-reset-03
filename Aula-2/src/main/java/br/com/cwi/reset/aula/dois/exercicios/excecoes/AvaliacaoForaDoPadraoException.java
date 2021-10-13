package br.com.cwi.reset.aula.dois.exercicios.excecoes;

public class AvaliacaoForaDoPadraoException extends Exception {
    public AvaliacaoForaDoPadraoException() {
        super("A nota da avaliação deve ser um valor entre 1 e 5");
    }
}
