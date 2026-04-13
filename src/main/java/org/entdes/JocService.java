package org.entdes;

public class JocService {

    public int jugar(int a, int b) {
        int r = -1;
        if (a >= 1 && a <= 3 && b >= 1 && b <= 3) {
            if (a == b) {
                r = 0;
            } else {
                if (a == 1 && b == 3) {
                    r = 1;
                } else if (a == 2 && b == 1) {
                    r = 1;
                } else if (a == 3 && b == 2) {
                    r = 1;
                } else {
                    r = 2;
                }
            }
        }
        return r;
    }

    public String getNomJugada(int j) {
        String n = "";
        if (j == 1) { n = "Pedra"; }
        else if (j == 2) { n = "Paper"; }
        else if (j == 3) { n = "Tisores"; }
        else { n = "?"; }
        return n;
    }

    public String getResultatText(int r) {
        String txt = "";
        if (r == 0) { txt = "EMPAT!"; }
        else if (r == 1) { txt = "GUANYA JUGADOR 1!"; }
        else if (r == 2) { txt = "GUANYA JUGADOR 2!"; }
        else { txt = "Jugada no vàlida"; }
        return txt;
    }

}
