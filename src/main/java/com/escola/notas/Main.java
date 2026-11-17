package com.escola.notas;

/**
 * Classe principal do projeto controleDeNotas para demonstrar o funcionamento no console.
 */
public class Main {
    public static void main(String[] args) {
        // Cria os objetos do sistema
        ValidadorNotas validador = new ValidadorNotas();
        BoletimRepository repository = new BoletimRepository();
        BoletimService service = new BoletimService(validador, repository);

        System.out.println("=== CONTROLE DE NOTAS (controleDeNotas) ===");

        // Cadastro 1: Aluno aprovado
        Aluno a1 = service.registrarBoletim("PROFESSOR", "202601", "Ana Silva", 8.0, 7.0, 90);
        System.out.println(a1);

        // Cadastro 2: Aluno em recuperação
        Aluno a2 = service.registrarBoletim("PROFESSOR", "202602", "Carlos Souza", 5.0, 6.0, 80);
        System.out.println(a2);

        // Cadastro 3: Aluno reprovado por falta mesmo com média alta
        Aluno a3 = service.registrarBoletim("PROFESSOR", "202603", "Mariana Lima", 9.0, 9.5, 70);
        System.out.println(a3);
    }
}
