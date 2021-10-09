package br.com.cwi.reset.aula.dois;

public class Aplicacao {
    public static void main(String[] args) {
        Diretor diretor1 = new Diretor("Espen Sandberg", 49, 10);
        Diretor diretor2 = new Diretor("Spilber", 55, 23);
        Filme filme1 = new Filme("Piratas do Caribe", "filme de ação muito top", 90, 2003, 4, diretor1);
        Filme filme2 = new Filme("Monstros S.A", "animação muito legal", 120, 2009, 5, diretor2);

        filme1.reproduzir();
        filme2.reproduzir();
    }

}
