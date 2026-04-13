package org.entdes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JocServiceTest {

    private JocService joc;

    @BeforeEach
    void setUp() {
        joc = new JocService();
    }

    // --- Tests d'empat ---

    @Test
    void testEmpatPedra() {
        assertEquals(0, joc.jugar(1, 1));
    }

    @Test
    void testEmpatPaper() {
        assertEquals(0, joc.jugar(2, 2));
    }

    @Test
    void testEmpatTisores() {
        assertEquals(0, joc.jugar(3, 3));
    }

    // --- Tests victoria jugador 1 ---

    @Test
    void testPedraGuanyaTisores() {
        assertEquals(1, joc.jugar(1, 3));
    }

    @Test
    void testPaperGuanyaPedra() {
        assertEquals(1, joc.jugar(2, 1));
    }

    @Test
    void testTisoresGuanyaPaper() {
        assertEquals(1, joc.jugar(3, 2));
    }

    // --- Tests victoria jugador 2 ---

    @Test
    void testPerdreContraPaper() {
        assertEquals(2, joc.jugar(1, 2));
    }

    @Test
    void testPerdreContraTisores() {
        assertEquals(2, joc.jugar(2, 3));
    }

    @Test
    void testPerdreContraPedra() {
        assertEquals(2, joc.jugar(3, 1));
    }

    // --- Tests jugades invalides ---

    @Test
    void testJugadaInvalidaJugador1() {
        assertEquals(-1, joc.jugar(0, 1));
        assertEquals(-1, joc.jugar(4, 2));
    }

    @Test
    void testJugadaInvalidaJugador2() {
        assertEquals(-1, joc.jugar(1, 0));
        assertEquals(-1, joc.jugar(2, 4));
    }

    // --- Tests getNomJugada ---

    @Test
    void testNomJugadaPedra() {
        assertEquals("Pedra", joc.getNomJugada(1));
    }

    @Test
    void testNomJugadaPaper() {
        assertEquals("Paper", joc.getNomJugada(2));
    }

    @Test
    void testNomJugadaTisores() {
        assertEquals("Tisores", joc.getNomJugada(3));
    }

    @Test
    void testNomJugadaInvalida() {
        assertEquals("?", joc.getNomJugada(0));
    }

    // --- Tests getResultatText ---

    @Test
    void testResultatTextEmpat() {
        assertTrue(joc.getResultatText(0).contains("EMPAT"));
    }

    @Test
    void testResultatTextJ1() {
        assertTrue(joc.getResultatText(1).contains("JUGADOR 1"));
    }

    @Test
    void testResultatTextJ2() {
        assertTrue(joc.getResultatText(2).contains("JUGADOR 2"));
    }

    @Test
    void testResultatTextInvalid() {
        assertTrue(joc.getResultatText(-1).toLowerCase().contains("no vàlida"));
    }
}
