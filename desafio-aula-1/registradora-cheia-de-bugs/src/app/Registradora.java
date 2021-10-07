package app;

public class Registradora {

    public static void main(String[] args) {
//        primeiroBug();

//     segundoBug();

//        terceiroBug();
//        quartoBug();
//        quintoBug();
        sextoBug();
    }

    private static double registrarItem(String item, int quantidade) {
        double precoItem = RelacaoPesoPreco.retornaPrecoProduto(item, quantidade);

        if (QuantidadeMinimaItem.precisaReposicao(item)) {
            if ("pao".equals(item) || "sanduiche".equals(item) || "torta".equals(item)) {
                if (!DataProjeto.cozinhaEmFuncionamento()) {
                    System.out.println("Cozinha fechada!");
                } else {
                    ReposicaoCozinha.reporItem(item);

                }
            }

            if ("leite".equals(item) || "cafe".equals(item)) {
                ReposicaoFornecedor.reporItem(item);
            }
        }

        return precoItem;
    }

    private static boolean checarEstoque(String item, int quantidade) {
        if ("sanduiche".equals(item) && quantidade > ItensPorQuantidade.sanduiche) {
            System.out.println("Reposição indisponível de sanduíche");
            System.out.println(String.format("quantidade restante em estoque é de: %d", ItensPorQuantidade.sanduiche));
            return false;
        }
        return true;
    }

    private static void primeiroBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "sanduiche";
        int quantidade = 4;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void segundoBug() {
        DataProjeto.criarDataComCozinhaEncerradaMasComDiaUtil();
        String item = "torta";
        int quantidade = 10;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void terceiroBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "cafe";
        int quantidade = 40;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void quartoBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        // Cliente 1
        String item = "sanduiche";
        int quantidade = 20;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));

        // Cliente 2
        String item2 = "sanduiche";
        int quantidade2 = 5;

        double precoTotal2 = registrarItem(item2, quantidade2);

        System.out.println(String.format("Valor total: %.2f", precoTotal2));
    }

    private static void quintoBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "pao";
        int quantidade = 10;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void sextoBug() {
        DataProjeto.criarDataComCozinhaEncerradaSemDiaUtil();
        // Cliente 1
        String item = "sanduiche";
        int quantidade = 20;
        if (checarEstoque(item, quantidade)) {
            ItensPorQuantidade.removerSanduiche(quantidade);


            double precoTotal = registrarItem(item, quantidade);

            System.out.println(String.format("Valor total: %.2f", precoTotal));
        }
        // Cliente 2
        String item2 = "sanduiche";
        int quantidade2 = 5;
        if (checarEstoque(item2, quantidade2)) {
            ItensPorQuantidade.removerSanduiche(quantidade2);

            double precoTotal2 = registrarItem(item2, quantidade2);

            System.out.println(String.format("Valor total: %.2f", precoTotal2));
        }
    }

}
