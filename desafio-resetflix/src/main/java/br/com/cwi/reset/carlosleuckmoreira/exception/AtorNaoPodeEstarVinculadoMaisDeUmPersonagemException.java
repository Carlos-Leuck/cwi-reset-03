package br.com.cwi.reset.carlosleuckmoreira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AtorNaoPodeEstarVinculadoMaisDeUmPersonagemException extends CampoNaoInformadoException {
    public AtorNaoPodeEstarVinculadoMaisDeUmPersonagemException() {
        super("Este ator está vinculado a um ou mais personagens, para remover o ator é necessário remover os seus personagens de atuação.");
    }
}
