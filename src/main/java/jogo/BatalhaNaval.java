package jogo;

import java.util.Scanner;

public class BatalhaNaval {
    private char[][] tabuleiro;
    private int tamanho;
    private int navios;
    private int jogadorAtual;

    public BatalhaNaval(int tamanho, int navios) {
        this.tamanho = tamanho;
        this.navios = navios;
        this.tabuleiro = new char[tamanho][tamanho];
        this.jogadorAtual = 1;
        inicializarTabuleiro();
        posicionarNavios();
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiro[i][j] = '~';
            }
        }
    }

    private void posicionarNavios() {
        int naviosColocadosJogador1 = 0;
        int naviosColocadosJogador2 = 0;
        Scanner scanner = new Scanner(System.in);
        while (naviosColocadosJogador1 < navios || naviosColocadosJogador2 < navios) {
            System.out.println("Jogador " + jogadorAtual + ", posicione o navio #" + (naviosColocadosJogador1 + naviosColocadosJogador2 + 1));
            System.out.print("Informe a linha: ");
            int linha = scanner.nextInt();
            System.out.print("Informe a coluna: ");
            int coluna = scanner.nextInt();
            if (linha < 0 || linha >= tamanho || coluna < 0 || coluna >= tamanho) {
                System.out.println("Posição inválida. Tente novamente.");
                continue;
            }
            if (tabuleiro[linha][coluna] == 'N') {
                System.out.println("Já existe um navio nessa posição. Tente novamente.");
                continue;
            }
            if (jogadorAtual == 1) {
                tabuleiro[linha][coluna] = 'N';
                naviosColocadosJogador1++;
                jogadorAtual = 2;
            } else {
                tabuleiro[linha][coluna] = 'M';
                naviosColocadosJogador2++;
                jogadorAtual = 1;
            }
        }
    }

    public void jogar(int jogador, int linha, int coluna) {
        if (linha < 0 || linha >= tamanho || coluna < 0 || coluna >= tamanho) {
            System.out.println("Posição inválida. Tente novamente.");
            return;
        }
        char simbolo;
        if (jogador == 1) {
            simbolo = 'M';
        } else {
            simbolo = 'N';
        }
        if (tabuleiro[linha][coluna] == simbolo) {
            System.out.println("Jogador " + jogador + " acertou um navio!");
            tabuleiro[linha][coluna] = 'X';
        } else if (tabuleiro[linha][coluna] == 'X') {
            System.out.println("Jogador " + jogador + " já acertou essa posição antes.");
        } else {
            System.out.println("Jogador " + jogador + " errou.");
            tabuleiro[linha][coluna] = '-';
        }
    }

    public void exibirTabuleiro() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean fimDeJogo() {
        int naviosRestantesJogador1 = 0;
        int naviosRestantesJogador2 = 0;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (tabuleiro[i][j] == 'N') {
                    naviosRestantesJogador1++;
                } else if (tabuleiro[i][j] == 'M') {
                    naviosRestantesJogador2++;
                }
            }
        }
        return naviosRestantesJogador1 == 0 || naviosRestantesJogador2 == 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o tamanho do tabuleiro: ");
        int tamanho = scanner.nextInt();
        System.out.print("Informe a quantidade de navios: ");
        int navios = scanner.nextInt();

        BatalhaNaval jogo = new BatalhaNaval(tamanho, navios);

        while (!jogo.fimDeJogo()) {
            System.out.println("Jogador " + jogo.jogadorAtual + ", é a sua vez.");
            System.out.print("Informe a linha para jogar: ");
            int linha = scanner.nextInt();
            System.out.print("Informe a coluna para jogar: ");
            int coluna = scanner.nextInt();
            jogo.jogar(jogo.jogadorAtual, linha, coluna);
            jogo.exibirTabuleiro();
        }

        System.out.println("Parabéns! Jogador " + jogo.jogadorAtual + " venceu o jogo.");
    }
}
