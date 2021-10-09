package br.com.cwi.reset.aula.dois;

import br.com.cwi.reset.aula.dois.exercicios.Ator;
import br.com.cwi.reset.aula.dois.exercicios.Diretor;
import br.com.cwi.reset.aula.dois.exercicios.Genero;

public class Aplicacao {
    public static void main(String[] args) {

        Ator ator = new Ator("Brad pit", 45, Genero.MASCULINO, 5);
        Diretor diretor1 = new Diretor("Greta Gerwig", 35, Genero.FEMININO, 10);

        ator.imprimirInformacoesPessoais();
        diretor1.imprimirInformacoesPessoais();
    }

}
