package br.com.cwi.reset.aula.dois;

import br.com.cwi.reset.aula.dois.exercicios.Ator;
import br.com.cwi.reset.aula.dois.exercicios.Diretor;
import br.com.cwi.reset.aula.dois.exercicios.Filme;
import br.com.cwi.reset.aula.dois.exercicios.Genero;

public class Aplicacao {
    public static void main(String[] args) {
        Diretor diretor1 = new Diretor("Espen Sandberg", 49, 10);
        Diretor diretor2 = new Diretor("Spilber", 55, 23);
        Filme filme1 = new Filme("Piratas do Caribe", "filme de ação muito top", 90, 2003, 4.0, diretor1);
        Filme filme2 = new Filme("Monstros S.A", "animação muito legal", 120, 2009, 5.0, diretor2);

        filme1.reproduzir();
        filme2.reproduzir();

        Ator ator = new Ator("Brad pit", 45, 5, Genero.MASCULINO);
        Diretor diretor3 = new Diretor("Greta Gerwig", 35, 5, Genero.FEMININO);

        ator.imprimirInformacoesPessoais();
        diretor3.imprimirInformacoesPessoais();
    }

}
