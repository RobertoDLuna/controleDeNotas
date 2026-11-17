package com.escola.notas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários da classe ValidadorNotas do projeto controleDeNotas.
 * Cobre valores-limite, particionamento de equivalência, teste parametrizado e segurança.
 */
public class ValidadorNotasTest {

    private ValidadorNotas validador;

    // Roda antes de cada teste para criar uma instância nova
    @BeforeEach
    void configurar() {
        validador = new ValidadorNotas();
    }

    // --- TESTE PARAMETRIZADO (Item 8 do documento) ---
    @ParameterizedTest
    @CsvSource({
            "-0.1, false", // Limite inferior inválido
            "0.0,  true",  // Limite inferior válido
            "5.0,  true",  // Valor válido intermediário
            "10.0, true",  // Limite superior válido

            "10.1, false"  // Limite superior inválido
    })
    void isNotaValida_deveRespeitarLimitesDeZeroADez(double nota, boolean esperado) {
        assertEquals(esperado, validador.isNotaValida(nota));
    }

    // --- TESTES DE VALORES-LIMITE COM assertTrue E assertFalse ---
    @Test
    void isNotaValida_nosLimitesExatosZeroEDez_deveRetornarTrue() {
        assertTrue(validador.isNotaValida(0.0));
        assertTrue(validador.isNotaValida(10.0));
    }

    @Test
    void isNotaValida_foraDosLimites_deveRetornarFalse() {
        assertFalse(validador.isNotaValida(-0.01));
        assertFalse(validador.isNotaValida(10.01));
    }

    // --- TESTES NEGATIVOS DE EXCEÇÃO (Item 6 do documento) ---
    @Test
    void validarNota_comNotaNegativa_deveLancarExcecao() {
        assertThrows(IllegalArgumentException.class, () -> {
            validador.validarNota(-1.0);
        });
    }

    @Test
    void validarNota_comNotaMaiorQueDez_deveLancarExcecao() {
        assertThrows(IllegalArgumentException.class, () -> {
            validador.validarNota(10.5);
        });
    }

    @Test
    void validarFrequencia_foraDeZeroACem_deveLancarExcecao() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> validador.validarFrequencia(-1)),
                () -> assertThrows(IllegalArgumentException.class, () -> validador.validarFrequencia(101))
        );
    }

    // --- TESTES DE SEGURANÇA (Item 12 do documento) ---
    @Test
    void validarPermissao_quandoUsuarioForAluno_deveBloquearAcesso() {
        assertThrows(SecurityException.class, () -> {
            validador.validarPermissaoProfessor("ALUNO");
        });
    }

    @Test
    void validarPermissao_quandoUsuarioForNulo_deveBloquearAcesso() {
        assertThrows(SecurityException.class, () -> {
            validador.validarPermissaoProfessor(null);
        });
    }

    @Test
    void validarPermissao_quandoUsuarioForProfessor_devePermitirAcesso() {
        assertDoesNotThrow(() -> {
            validador.validarPermissaoProfessor("PROFESSOR");
        });
    }
}