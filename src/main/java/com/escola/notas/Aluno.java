package com.escola.notas;

/**
 * Classe modelo que representa um aluno e o resultado do seu boletim no sistema controleDeNotas.
 */
public class Aluno {

    // Atributos privados do aluno
    private String matricula;
    private String nome;
    private double nota1;
    private double nota2;
    private int frequencia; // Percentual de 0 a 100
    private double media;
    private String situacao;

    // Construtor para preencher todos os dados do boletim do aluno
    public Aluno(String matricula, String nome, double nota1, double nota2, int frequencia, double media, String situacao) {
        this.matricula = matricula;
        this.nome = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.frequencia = frequencia;
        this.media = media;
        this.situacao = situacao;
    }

    // Métodos Getters para ler os dados do aluno

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public double getNota1() {
        return nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public double getMedia() {
        return media;
    }

    public String getSituacao() {
        return situacao;
    }

    // Formata os dados do aluno para exibição simples no console
    @Override
    public String toString() {
        return "Matrícula: " + matricula +
               " | Nome: " + nome +
               " | Média: " + media +
               " | Frequência: " + frequencia + "%" +
               " | Situação: " + situacao;
    }
}
