package jogo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BatalhaNavalGUI extends Application {
    private static final int TAMANHO_TABULEIRO = 10;
    private static final int TAMANHO_CELULA = 40;
    private static final Color COR_AGUA = Color.LIGHTBLUE;
    private static final Color COR_NAVIO = Color.DARKGRAY;
    private static final Color COR_ACERTO = Color.RED;
    private static final Color COR_ERRO = Color.GRAY;

    private BatalhaNaval jogo;
    private Rectangle[][] celulas;

    public BatalhaNavalGUI() {
        jogo = new BatalhaNaval(TAMANHO_TABULEIRO, 5); // Número de navios: 5
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(2);
        gridPane.setVgap(2);

        celulas = new Rectangle[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                Rectangle celula = new Rectangle(TAMANHO_CELULA, TAMANHO_CELULA);
                celula.setFill(COR_AGUA);
                celula.setStroke(Color.BLACK);
                celulas[i][j] = celula;

                int linha = i; // Salva os valores para a lambda abaixo
                int coluna = j;
                celula.setOnMouseClicked(event -> {
                    jogar(linha, coluna);
                });

                gridPane.add(celula, j, i);
            }
        }

        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("Batalha Naval");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void jogar(int linha, int coluna) {
        jogo.jogar(1, linha, coluna); // Jogador 1 é fixo neste exemplo

        if (jogo.fimDeJogo()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Fim de Jogo");
            alert.setHeaderText(null);
            alert.setContentText("Parabéns! Você destruiu todos os navios.");
            alert.showAndWait();
        } else {
            atualizarTabuleiro();
        }
    }

    private void atualizarTabuleiro() {
        char[][] tabuleiro = jogo.getTabuleiro();

        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                char estado = tabuleiro[i][j];

                if (estado == 'N') {
                    celulas[i][j].setFill(COR_NAVIO);
                } else if (estado == 'X') {
                    celulas[i][j].setFill(COR_ACERTO);
                } else if (estado == '-') {
                    celulas[i][j].setFill(COR_ERRO);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

