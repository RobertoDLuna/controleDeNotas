package com.escola.notas;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por guardar os boletins em memória usando ArrayList.
 * Simula um armazenamento simples sem precisar de banco de dados complexo.
 */
public class BoletimRepository {

    // Lista que armazena os boletins cadastrados
    private List<Aluno> listaAlunos;

    // Construtor inicia a lista vazia
    public BoletimRepository() {
        this.listaAlunos = new ArrayList<Aluno>();
    }

    /**
     * Adiciona um aluno na lista.
     */
    public void salvar(Aluno aluno) {
        listaAlunos.add(aluno);
    }

    /**
     * Procura um aluno pela matrícula usando um laço for simples.
     * Retorna o Aluno encontrado ou null caso não exista.
     */
    public Aluno buscarPorMatricula(String matricula) {
        for (int i = 0; i < listaAlunos.size(); i++) {
            Aluno alunoAtual = listaAlunos.get(i);
            if (alunoAtual.getMatricula().equalsIgnoreCase(matricula)) {
                return alunoAtual;
            }
        }
        return null;
    }

    /**
     * Retorna quantos boletins estão salvos no momento.
     */
    public int contarTotal() {
        return listaAlunos.size();
    }
}
