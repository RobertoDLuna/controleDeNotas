package com.escola.notas;

/**
 * Classe de serviço com as regras de negócio do projeto controleDeNotas.
 * Faz a integração entre ValidadorNotas e BoletimRepository.
 */
public class BoletimService {

    // Componentes utilizados pelo serviço
    private ValidadorNotas validador;
    private BoletimRepository repository;

    // Construtor recebe os dois componentes
    public BoletimService(ValidadorNotas validador, BoletimRepository repository) {
        this.validador = validador;
        this.repository = repository;
    }

    /**
     * Calcula a média simples de duas notas.
     */
    public double calcularMedia(double nota1, double nota2) {
        return (nota1 + nota2) / 2.0;
    }

    /**
     * Regras R2 e R3: Define a situação do aluno pela média e frequência.
     * - Frequência menor que 75% -> REPROVADO_POR_FALTA
     * - Média maior ou igual a 7.0 -> APROVADO
     * - Média entre 4.0 e 6.9 -> RECUPERACAO
     * - Média menor que 4.0 -> REPROVADO
     */
    public String definirSituacao(double media, int frequencia) {
        if (frequencia < 75) {
            return "REPROVADO_POR_FALTA";
        }
        if (media >= 7.0) {
            return "APROVADO";
        } else if (media >= 4.0) {
            return "RECUPERACAO";
        } else {
            return "REPROVADO";
        }
    }

    /**
     * Valida os dados, calcula a média/situação e salva o boletim do aluno.
     */
    public Aluno registrarBoletim(String perfilUsuario, String matricula, String nome,
                                  double nota1, double nota2, int frequencia) {

        // 1. Valida segurança (Regra R5)
        validador.validarPermissaoProfessor(perfilUsuario);

        // 2. Valida campos obrigatórios
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("A matrícula do aluno é obrigatória.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do aluno é obrigatório.");
        }

        // 3. Impede matrícula duplicada no repositório (Regra R4)
        if (repository.buscarPorMatricula(matricula) != null) {
            throw new IllegalArgumentException("Já existe boletim para a matrícula: " + matricula);
        }

        // 4. Valida limites de notas e frequência (Regras R1 e R3)
        validador.validarNota(nota1);
        validador.validarNota(nota2);
        validador.validarFrequencia(frequencia);

        // 5. Calcula média e situação (Regras R2 e R3)
        double media = calcularMedia(nota1, nota2);
        String situacao = definirSituacao(media, frequencia);

        // 6. Cria o aluno, guarda no repositório e retorna
        Aluno novoAluno = new Aluno(matricula, nome, nota1, nota2, frequencia, media, situacao);
        repository.salvar(novoAluno);

        return novoAluno;
    }
}
