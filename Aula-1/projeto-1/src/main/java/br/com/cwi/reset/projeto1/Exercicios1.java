package br.com.cwi.reset.projeto1;

import java.util.List;

public class Exercicios1 {

    public Integer somarLista(List<Integer> numeros) {
        Integer soma = 0;
        for (Integer numero : numeros) {
            soma += numero;

        }
        return soma;
    }

    public Double calcularMedia(List<Integer> numeros) {
        double media;
        media = (double) somarLista(numeros) / numeros.size();
        return media;
    }

    public Integer obterMaiorNumero(List<Integer> numeros) {
        Integer maiorNumero = 0;
        for (Integer numero : numeros) {
            if (numero > maiorNumero) {
                maiorNumero = numero;
            }

        }
        return maiorNumero;
    }

    public String obterPalavraInvertida(String palavra) {
        StringBuilder palavraInvertida = new StringBuilder();
        int contador = palavra.length() - 1;
        for (int i = 0; i < palavra.length(); i++) {
            palavraInvertida.append(palavra.charAt(contador));
            contador--;

        }

        return palavraInvertida.toString();
    }

    public List<Integer> ordenarLista(List<Integer> numeros) {

        for (int i = 0; i < numeros.size() - 1; i++) {
            for (int j = 0; j < numeros.size() - 1 - i; j++) {
                if (numeros.get(j) > numeros.get(j + 1)) {
                    int aux = numeros.get(j);
                    numeros.set(j, numeros.get(j + 1));
                    numeros.set(j + 1, aux);
                }
            }
        }

        return numeros;
    }
}

