package org.entdes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JocApp extends Application {

    private final JocService joc = new JocService();
    private int jugadaJ1 = -1;
    private int jugadaJ2 = -1;

    private Label lblJ1Status;
    private Label lblJ2Status;
    private Label lblResultat;
    private Label lblJ1Emoji;
    private Label lblJ2Emoji;

    @Override
    public void start(Stage stage) {
        // Titol
        Label titol = new Label("PEDRA, PAPER, TISORES");
        titol.setFont(Font.font("Arial", 28));
        titol.setStyle("-fx-font-weight: bold;");

        // Instruccions tecles
        Label instrJ1 = new Label("JUGADOR 1:  Q=Pedra  W=Paper  E=Tisores");
        instrJ1.setFont(Font.font("Arial", 14));
        instrJ1.setStyle("-fx-text-fill: #2196F3;");

        Label instrJ2 = new Label("JUGADOR 2:  I=Pedra  O=Paper  P=Tisores");
        instrJ2.setFont(Font.font("Arial", 14));
        instrJ2.setStyle("-fx-text-fill: #F44336;");

        // Zona jugador 1
        Label lblJ1Nom = new Label("JUGADOR 1");
        lblJ1Nom.setFont(Font.font("Arial", 18));
        lblJ1Nom.setStyle("-fx-font-weight: bold; -fx-text-fill: #2196F3;");

        lblJ1Emoji = new Label("❓");
        lblJ1Emoji.setFont(Font.font(64));

        lblJ1Status = new Label("Esperant...");
        lblJ1Status.setFont(Font.font("Arial", 14));

        VBox boxJ1 = new VBox(10, lblJ1Nom, lblJ1Emoji, lblJ1Status);
        boxJ1.setAlignment(Pos.CENTER);
        boxJ1.setPrefWidth(200);
        boxJ1.setStyle("-fx-border-color: #2196F3; -fx-border-width: 2; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 20;");

        // VS
        Label lblVS = new Label("VS");
        lblVS.setFont(Font.font("Arial", 36));
        lblVS.setStyle("-fx-font-weight: bold;");

        // Zona jugador 2
        Label lblJ2Nom = new Label("JUGADOR 2");
        lblJ2Nom.setFont(Font.font("Arial", 18));
        lblJ2Nom.setStyle("-fx-font-weight: bold; -fx-text-fill: #F44336;");

        lblJ2Emoji = new Label("❓");
        lblJ2Emoji.setFont(Font.font(64));

        lblJ2Status = new Label("Esperant...");
        lblJ2Status.setFont(Font.font("Arial", 14));

        VBox boxJ2 = new VBox(10, lblJ2Nom, lblJ2Emoji, lblJ2Status);
        boxJ2.setAlignment(Pos.CENTER);
        boxJ2.setPrefWidth(200);
        boxJ2.setStyle("-fx-border-color: #F44336; -fx-border-width: 2; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 20;");

        HBox jugadors = new HBox(30, boxJ1, lblVS, boxJ2);
        jugadors.setAlignment(Pos.CENTER);

        // Resultat
        lblResultat = new Label("Premeu les tecles per jugar!");
        lblResultat.setFont(Font.font("Arial", 22));
        lblResultat.setStyle("-fx-font-weight: bold;");

        // Layout principal
        VBox root = new VBox(15, titol, instrJ1, instrJ2, jugadors, lblResultat);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #FAFAFA;");

        Scene scene = new Scene(root, 620, 480);

        scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();

            // Jugador 1: Q=Pedra, W=Paper, E=Tisores
            if (jugadaJ1 == -1) {
                if (key == KeyCode.Q) { jugadaJ1 = 1; lblJ1Status.setText("✔ Llest!"); lblJ1Emoji.setText("🤜"); }
                else if (key == KeyCode.W) { jugadaJ1 = 2; lblJ1Status.setText("✔ Llest!"); lblJ1Emoji.setText("🤜"); }
                else if (key == KeyCode.E) { jugadaJ1 = 3; lblJ1Status.setText("✔ Llest!"); lblJ1Emoji.setText("🤜"); }
            }

            // Jugador 2: I=Pedra, O=Paper, P=Tisores
            if (jugadaJ2 == -1) {
                if (key == KeyCode.I) { jugadaJ2 = 1; lblJ2Status.setText("✔ Llest!"); lblJ2Emoji.setText("🤛"); }
                else if (key == KeyCode.O) { jugadaJ2 = 2; lblJ2Status.setText("✔ Llest!"); lblJ2Emoji.setText("🤛"); }
                else if (key == KeyCode.P) { jugadaJ2 = 3; lblJ2Status.setText("✔ Llest!"); lblJ2Emoji.setText("🤛"); }
            }

            // Quan tots dos han triat -> resolver
            if (jugadaJ1 != -1 && jugadaJ2 != -1) {
                resoldreTorn();
            }
        });

        stage.setTitle("Pedra, Paper, Tisores");
        stage.setScene(scene);
        stage.show();
        scene.getRoot().requestFocus();
    }

    private void resoldreTorn() {
        // Jugar (tota la logica al JocService)
        int resultat = joc.jugar(jugadaJ1, jugadaJ2);

        // Mostrar noms jugades des del servei
        lblJ1Status.setText(joc.getNomJugada(jugadaJ1));
        lblJ2Status.setText(joc.getNomJugada(jugadaJ2));
        lblJ1Emoji.setText(emojiJugada(jugadaJ1));
        lblJ2Emoji.setText(emojiJugada(jugadaJ2));

        // Mostrar resultat des del servei
        lblResultat.setText(joc.getResultatText(resultat));
        lblResultat.setStyle("-fx-font-weight: bold; -fx-text-fill: " + colorResultat(resultat) + ";");

        // Reset per nova ronda (amb delay visual)
        javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(1.5));
        pause.setOnFinished(ev -> {
            jugadaJ1 = -1;
            jugadaJ2 = -1;
            lblJ1Status.setText("Esperant...");
            lblJ2Status.setText("Esperant...");
            lblJ1Emoji.setText("❓");
            lblJ2Emoji.setText("❓");
        });
        pause.play();
    }

    // --- Helpers visuals ---

    private String emojiJugada(int jugada) {
        if (jugada == 1) return "🪨";
        if (jugada == 2) return "📄";
        if (jugada == 3) return "✂️";
        return "❓";
    }

    private String colorResultat(int resultat) {
        if (resultat == 0) return "#FF9800";
        if (resultat == 1) return "#2196F3";
        if (resultat == 2) return "#F44336";
        return "#000000";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
