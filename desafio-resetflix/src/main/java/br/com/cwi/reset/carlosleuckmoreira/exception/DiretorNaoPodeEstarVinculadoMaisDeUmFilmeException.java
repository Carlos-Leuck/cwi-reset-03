package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DiretorNaoPodeEstarVinculadoMaisDeUmFilmeException extends CampoNaoInformadoException {
    public DiretorNaoPodeEstarVinculadoMaisDeUmFilmeException() {
        super("Este diretor está vinculado a um ou mais filmes, para remover o diretor é necessário remover os seus filmes de participação.");
    }
}
