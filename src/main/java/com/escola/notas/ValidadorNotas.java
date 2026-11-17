package com.escola.notas;

/**
 * Classe responsável por validar regras de limite e permissão de acesso no controleDeNotas.
 */
public class ValidadorNotas {

    /**
     * Regra R5 (Segurança): Apenas usuários com perfil "PROFESSOR" podem lançar notas.
     * Se for outro perfil (ex: "ALUNO") ou nulo, bloqueia com SecurityException.
     */
    public void validarPermissaoProfessor(String perfilUsuario) {
        if (perfilUsuario == null || !perfilUsuario.equalsIgnoreCase("PROFESSOR")) {
            throw new SecurityException("Acesso negado: apenas professores podem lançar notas.");
        }
    }

    /**
     * Verifica se a nota está dentro do limite permitido (de 0.0 até 10.0).
     * Retorna true se for válida e false se for inválida.
     */
    public boolean isNotaValida(double nota) {
        return nota >= 0.0 && nota <= 10.0;
    }

    /**
     * Regra R1: Valida a nota e lança erro caso esteja fora de 0.0 a 10.0.
     */
    public void validarNota(double nota) {
        if (!isNotaValida(nota)) {
            throw new IllegalArgumentException("Nota inválida: deve estar entre 0.0 e 10.0.");
        }
    }

    /**
     * Regra R3 (limite): Valida se o percentual de frequência está entre 0 e 100.
     */
    public void validarFrequencia(int frequencia) {
        if (frequencia < 0 || frequencia > 100) {
            throw new IllegalArgumentException("Frequência inválida: deve estar entre 0 e 100.");
        }
    }
}
