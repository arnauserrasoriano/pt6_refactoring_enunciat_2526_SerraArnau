package org.entdes;

public class JocService {

    public int jugar(int codiJugadaJugador1, int codiJugadaJugador2) {
        Jugada jugadaJugador1 = Jugada.fromCode(codiJugadaJugador1);
        Jugada jugadaJugador2 = Jugada.fromCode(codiJugadaJugador2);

        if (jugadaJugador1 == null || jugadaJugador2 == null) {
            return ResultatJoc.JUGADA_INVALIDA.getCode();
        }

        return jugadaJugador1.jugarContra(jugadaJugador2).getCode();
    }

    public String getNomJugada(int codiJugada) {
        Jugada jugada = Jugada.fromCode(codiJugada);
        if (jugada == null) {
            return "?";
        }
        return jugada.getNomVisible();
    }

    public String getResultatText(int codiResultat) {
        ResultatJoc resultat = ResultatJoc.fromCode(codiResultat);
        if (resultat == null) {
            return ResultatJoc.JUGADA_INVALIDA.getText();
        }
        return resultat.getText();
    }

    private enum Jugada {
        PEDRA(1, "Pedra") {
            @Override
            protected ResultatJoc resoldreContra(Jugada rival) {
                return switch (rival) {
                    case PEDRA -> ResultatJoc.EMPAT;
                    case PAPER -> ResultatJoc.GUANYA_JUGADOR_2;
                    case TISORES -> ResultatJoc.GUANYA_JUGADOR_1;
                };
            }
        },
        PAPER(2, "Paper") {
            @Override
            protected ResultatJoc resoldreContra(Jugada rival) {
                return switch (rival) {
                    case PEDRA -> ResultatJoc.GUANYA_JUGADOR_1;
                    case PAPER -> ResultatJoc.EMPAT;
                    case TISORES -> ResultatJoc.GUANYA_JUGADOR_2;
                };
            }
        },
        TISORES(3, "Tisores") {
            @Override
            protected ResultatJoc resoldreContra(Jugada rival) {
                return switch (rival) {
                    case PEDRA -> ResultatJoc.GUANYA_JUGADOR_2;
                    case PAPER -> ResultatJoc.GUANYA_JUGADOR_1;
                    case TISORES -> ResultatJoc.EMPAT;
                };
            }
        };

        private final int code;
        private final String nomVisible;

        Jugada(int code, String nomVisible) {
            this.code = code;
            this.nomVisible = nomVisible;
        }

        public ResultatJoc jugarContra(Jugada rival) {
            return resoldreContra(rival);
        }

        public String getNomVisible() {
            return nomVisible;
        }

        protected abstract ResultatJoc resoldreContra(Jugada rival);

        public static Jugada fromCode(int code) {
            for (Jugada jugada : values()) {
                if (jugada.code == code) {
                    return jugada;
                }
            }
            return null;
        }
    }

    private enum ResultatJoc {
        JUGADA_INVALIDA(-1, "Jugada no vàlida"),
        EMPAT(0, "EMPAT!"),
        GUANYA_JUGADOR_1(1, "GUANYA JUGADOR 1!"),
        GUANYA_JUGADOR_2(2, "GUANYA JUGADOR 2!");

        private final int code;
        private final String text;

        ResultatJoc(int code, String text) {
            this.code = code;
            this.text = text;
        }

        public int getCode() {
            return code;
        }

        public String getText() {
            return text;
        }

        public static ResultatJoc fromCode(int code) {
            for (ResultatJoc resultat : values()) {
                if (resultat.code == code) {
                    return resultat;
                }
            }
            return null;
        }
    }
}
