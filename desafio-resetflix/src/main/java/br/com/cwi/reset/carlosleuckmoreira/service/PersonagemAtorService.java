package br.com.cwi.reset.carlosleuckmoreira.service;

import br.com.cwi.reset.carlosleuckmoreira.exception.NaoDeveSerInformadoOMesmoAtorOuPersonagemMaisDeUmaVezParaOMesmoFilmeException;
import br.com.cwi.reset.carlosleuckmoreira.model.domain.Ator;
import br.com.cwi.reset.carlosleuckmoreira.model.domain.PersonagemAtor;
import br.com.cwi.reset.carlosleuckmoreira.repository.PersonagemAtorRepository;
import br.com.cwi.reset.carlosleuckmoreira.request.PersonagemRequest;
import br.com.cwi.reset.carlosleuckmoreira.validator.PersonagemRequestCamposObrigatoriosValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonagemAtorService {

    @Autowired
    private PersonagemAtorRepository personagemAtorRepository;
    @Autowired
    private AtorService atorService;

    public PersonagemAtor cadastrarPersonagemAtor(PersonagemRequest personagemRequest) throws Exception {
        new PersonagemRequestCamposObrigatoriosValidator().accept(personagemRequest);

        final Ator ator = atorService.consultarAtor(personagemRequest.getIdAtor());

        final PersonagemAtor personagemAtor = new PersonagemAtor(ator, personagemRequest.getNomePersonagem(), personagemRequest.getDescricaoPersonagem(), personagemRequest.getTipoAtuacao());

        personagemAtorRepository.save(personagemAtor);

        return personagemAtor;
    }

    private void validarPersonagensAtoresFilme(final List<PersonagemRequest> personagens) throws Exception {

        final Set<PersonagemRequest> personagemRequestSet = new HashSet<>();

        for (PersonagemRequest personagemRequest : personagens) {
            new PersonagemRequestCamposObrigatoriosValidator().accept(personagemRequest);

            if (personagemRequestSet.contains(personagemRequest)) {
                throw new NaoDeveSerInformadoOMesmoAtorOuPersonagemMaisDeUmaVezParaOMesmoFilmeException();
            } else {
                personagemRequestSet.add(personagemRequest);
            }
        }
    }

    public List<PersonagemAtor> cadastrarPersonagensFilme(final List<PersonagemRequest> personagens) throws Exception {
        validarPersonagensAtoresFilme(personagens);

        final List<PersonagemAtor> personagensAtores = new ArrayList<>();

        for (PersonagemRequest request : personagens) {
            personagensAtores.add(cadastrarPersonagemAtor(request));
        }

        return personagensAtores;
    }
}
