package br.com.cwi.reset.carlosleuckmoreira.entities;

import br.com.cwi.reset.carlosleuckmoreira.dto.DiretorRequest;

import java.time.LocalDate;

public class Diretor {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private Integer anoInicioAtividade;

    public Diretor(Integer id,String nome, LocalDate dataNascimento, Integer anoInicioAtividade) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.anoInicioAtividade = anoInicioAtividade;
    }

    //  GERAR ID AUTOMATICAMENTE
    static int contador = 0;

    public Diretor(DiretorRequest diretorRequest) {
        contador++;
        id = contador;
        this.nome = diretorRequest.getNome();
        this.dataNascimento = diretorRequest.getDataNascimento();
        this.anoInicioAtividade = diretorRequest.getAnoInicioAtividade();

    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Integer getAnoInicioAtividade() {
        return anoInicioAtividade;
    }

    @Override
    public String toString() {
        return "Diretor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", anoInicioAtividade=" + anoInicioAtividade +
                '}';
    }


}
