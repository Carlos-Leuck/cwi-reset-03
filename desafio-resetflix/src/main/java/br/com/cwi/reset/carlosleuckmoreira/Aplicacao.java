package br.com.cwi.reset.carlosleuckmoreira;

import br.com.cwi.reset.carlosleuckmoreira.dto.AtorRequest;
import br.com.cwi.reset.carlosleuckmoreira.entities.Ator;
import br.com.cwi.reset.carlosleuckmoreira.repositories.FakeDatabase;
import br.com.cwi.reset.carlosleuckmoreira.services.AtorService;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Aplicacao {
    public static void main(String[] args) {
        FakeDatabase fakeDatabase = new FakeDatabase();

        AtorService atorService = new AtorService(fakeDatabase);

        String nome = "Will Smith";
        LocalDate dataNascimento = LocalDate.of(1968, Month.SEPTEMBER, 25);
        StatusCarreira statusCarreira = StatusCarreira.EM_ATIVIDADE;
        Integer anoInicioAtividade = 1986;
        AtorRequest atorRequest = new AtorRequest(nome, dataNascimento, statusCarreira, anoInicioAtividade);

        String nome2 = "Brad Pit";
        LocalDate dataNascimento2 = LocalDate.of(1968, Month.SEPTEMBER, 25);
        StatusCarreira statusCarreira2 = StatusCarreira.EM_ATIVIDADE;
        Integer anoInicioAtividade2 = 1986;
        AtorRequest atorRequest2 = new AtorRequest(nome2, dataNascimento2, statusCarreira2, anoInicioAtividade2);

        atorService.criarAtor(atorRequest);
        atorService.criarAtor(atorRequest2);

//       atorService.consultarAtor(2);


        List<Ator> atores = fakeDatabase.recuperaAtores();


        System.out.println("Deve conter 1 ator, quantidade encontrada: " + atores.size());
        System.out.println("Id:" + atores.get(0).getId());
        System.out.println("Primeiro ator deve ser 'Will Smith', valor encontrado: " + atores.get(0).getNome());
    }
}
