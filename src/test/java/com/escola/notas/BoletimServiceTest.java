package com.escola.notas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários, de integração e de regressão da classe BoletimService (controleDeNotas).
 */
public class BoletimServiceTest {

    private ValidadorNotas validador;
    private BoletimRepository repository;
    private BoletimService service;

    @BeforeEach
    void configurar() {
        validador = new ValidadorNotas();
        repository = new BoletimRepository();
        service = new BoletimService(validador, repository);
    }

    // --- TESTES DE VALORES-LIMITE E REGRESSÃO ---

    // Protege contra regressão na regra de aprovação (media >= 7.0)
    @Test
    void definirSituacao_comMediaExataSeteEFrequenciaSetentaECinco_deveAprovar() {
        String situacao = service.definirSituacao(7.0, 75);
        assertEquals("APROVADO", situacao);
    }

    @Test
    void definirSituacao_comMediaSeisENove_deveColocarEmRecuperacao() {
        String situacao = service.definirSituacao(6.9, 80);
        assertEquals("RECUPERACAO", situacao);
    }

    @Test
    void definirSituacao_comMediaExataQuatro_deveColocarEmRecuperacao() {
        String situacao = service.definirSituacao(4.0, 80);
        assertEquals("RECUPERACAO", situacao);
    }

    @Test
    void definirSituacao_comMediaTresENove_deveReprovar() {
        String situacao = service.definirSituacao(3.9, 90);
        assertEquals("REPROVADO", situacao);
    }

    @Test
    void definirSituacao_comFrequenciaSetentaEQuatro_deveReprovarPorFalta() {
        // Valor-limite: 74% reprova mesmo com média 9.0
        String situacao = service.definirSituacao(9.0, 74);
        assertEquals("REPROVADO_POR_FALTA", situacao);
    }

    // --- TESTES DE INTEGRAÇÃO ENTRE COMPONENTES (Item 9 do documento) ---
    // Testa BoletimService + ValidadorNotas + BoletimRepository trabalhando juntos

    @Test
    void registrarBoletim_comDadosValidos_deveCalcularESalvarNoRepositorio() {
        Aluno alunoRetornado = service.registrarBoletim(
            "PROFESSOR", "202610", "João Pedro", 8.0, 6.0, 85
        );

        Aluno alunoNoBanco = repository.buscarPorMatricula("202610");

        assertAll(
            () -> assertNotNull(alunoNoBanco),
            () -> assertEquals("João Pedro", alunoNoBanco.getNome()),
            () -> assertEquals(7.0, alunoRetornado.getMedia()),
            () -> assertEquals("APROVADO", alunoRetornado.getSituacao()),
            () -> assertEquals(1, repository.contarTotal())
        );
    }

    @Test
    void registrarBoletim_comMatriculaDuplicada_deveRejeitarENaoSalvarDuplicado() {
        service.registrarBoletim("PROFESSOR", "202610", "João Pedro", 8.0, 8.0, 90);

        assertThrows(IllegalArgumentException.class, () -> {
            service.registrarBoletim("PROFESSOR", "202610", "Outro Aluno", 7.0, 7.0, 80);
        });

        assertEquals(1, repository.contarTotal());
    }
}