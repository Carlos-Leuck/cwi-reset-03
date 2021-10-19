package br.com.cwi.reset.carlosleuckmoreira;

import br.com.cwi.reset.carlosleuckmoreira.model.StatusCarreira;
import br.com.cwi.reset.carlosleuckmoreira.request.AtorRequest;
import br.com.cwi.reset.carlosleuckmoreira.request.DiretorRequest;
import br.com.cwi.reset.carlosleuckmoreira.model.Ator;
import br.com.cwi.reset.carlosleuckmoreira.service.AtorService;
import br.com.cwi.reset.carlosleuckmoreira.service.DiretorService;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Aplicacao {
    public static void main(String[] args) {
        FakeDatabase fakeDatabase = new FakeDatabase();

        AtorService atorService = new AtorService(fakeDatabase);
        DiretorService diretorService = new DiretorService(fakeDatabase);

        String nome = "Will Smith";
        LocalDate dataNascimento = LocalDate.of(1968, Month.SEPTEMBER, 25);
        StatusCarreira statusCarreira = StatusCarreira.EM_ATIVIDADE;
        Integer anoInicioAtividade = 1986;
        AtorRequest atorRequest = new AtorRequest(nome, dataNascimento, statusCarreira, anoInicioAtividade);

        String nome2 = "Brad Pitt";
        LocalDate dataNascimento2 = LocalDate.of(1967, Month.SEPTEMBER, 25);
        StatusCarreira statusCarreira2 = StatusCarreira.EM_ATIVIDADE;
        Integer anoInicioAtividade2 = 1987;
        AtorRequest atorRequest2 = new AtorRequest(nome2, dataNascimento2, statusCarreira2, anoInicioAtividade2);

        String nome3 = "Tom Cruise";
        LocalDate dataNascimento3 = LocalDate.of(1968, Month.SEPTEMBER, 25);
        StatusCarreira statusCarreira3 = StatusCarreira.APOSENTADO;
        Integer anoInicioAtividade3 = 1986;
        AtorRequest atorRequest3 = new AtorRequest(nome3, dataNascimento3, statusCarreira3, anoInicioAtividade3);

        atorService.criarAtor(atorRequest);
        atorService.criarAtor(atorRequest2);
        atorService.criarAtor(atorRequest3);

        String nome4 = "Steven Spilber";
        LocalDate dataNascimento4 = LocalDate.of(1968, Month.SEPTEMBER, 25);
        Integer anoInicioAtividade4 = 1986;

        String nome5 = "Tim Burton";
        LocalDate dataNascimento5 = LocalDate.of(1968, Month.SEPTEMBER, 25);
        Integer anoInicioAtividade5 = 1986;


        DiretorRequest diretorRequest = new DiretorRequest(nome4, dataNascimento4, anoInicioAtividade4);
        DiretorRequest diretorRequest2 = new DiretorRequest(nome5, dataNascimento5, anoInicioAtividade5);


        List<Ator> atores = fakeDatabase.recuperaAtores();
//        List<Diretor> diretores = fakeDatabase.recuperaDiretores();


//        System.out.println("Deve conter 1 ator, quantidade encontrada: " + atores.size());
//        System.out.println("Id:" + atores.get(0).getId());
//        System.out.println("Primeiro ator deve ser 'Will Smith', valor encontrado: " + atores.get(0).getNome());
//        System.out.println(atorService.consultarAtores().toString());


        // TESTE CONSULTAR ATOR POR ID: FUNCIONANDO, ARRUMAR MSG EXCEÇÃO
        //System.out.println("CONSULTAR ATORES POR ID");
//        System.out.println(atorService.consultarAtor(null));
//        System.out.println(atorService.consultarAtor(2));
//        System.out.println(atorService.consultarAtor(1));


        //TESTE CONSULTAR TODOS OS ATORES: OK.
//       System.out.println("CONSULTAR TODOS ATORES");
//       System.out.println(atorService.consultarAtores());

//        TESTE LISTAR ATORES EM ATIVIDADE
//        System.out.println(atorService.listarAtoresEmAtividade("W"));

//        TESTAR diretorService
//        diretorService.cadastrarDiretor(diretorRequest);
//        diretorService.cadastrarDiretor(diretorRequest2);

//        System.out.println(diretorService.listarDiretores(null));
//        System.out.println(diretorService.listarDiretores("Brad"));
    }
}
