package app;

public class ItensPorQuantidade {

    static int pao = 3600;
    static int torta = 64;
    static int sanduiche = 20;
    static int leite = 20;
    static int cafe = 20;

    public static void removerCafe(int quantidade) {
        cafe -= quantidade;

    }

    public static void removerLeite(int quantidade) {
        leite -= quantidade;

    }

    public static void removerPao(int quantidade) {
        pao -= quantidade*60;

    }

    public static void removerSanduiche(int quantidade) {
        sanduiche -= quantidade;

    }

    public static void removerTorta(int quantidade) {
        torta -= quantidade;

    }



}
