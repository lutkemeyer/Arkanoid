package com.example.guilhermeeyng.arkanoid;

import android.graphics.PointF;
import android.os.Handler;

public class ComportamentoBola {

    public enum Direcao{ CIMA_DIREITA, CIMA_ESQUERDA, BAIXO_DIREITA, BAIXO_ESQUERDA}

    public static final int VELOCIDADE = 10;

    private Bola bola;
    private int larguraTotal, alturaTotal;
    private Handler redrawHandler = new Handler();
    private Direcao direcao = Direcao.CIMA_DIREITA;

    private OnPontosChanged listener;

    public ComportamentoBola(Bola bola, int larguraTotal, int alturaTotal, OnPontosChanged listener) {
        this.bola = bola;
        this.larguraTotal = larguraTotal;
        this.alturaTotal = alturaTotal;
        this.listener = listener;
    }

    public void movimentar(PointF cBarra){
        PointF cBola = bola.getCoordenadas();
        switch (direcao){
            case CIMA_DIREITA: // se a direcao está para cima e para direita
                if(bola.getCoordenadas().x >= larguraTotal){ // se a bola bater na parede direita
                    direcao = Direcao.CIMA_ESQUERDA; // inverte a direção para a esquerda
                    cBola.x -= VELOCIDADE; // faz ir para esquerda
                    cBola.y -= VELOCIDADE; // faz subir
                    break;
                }
                if(bola.getCoordenadas().y <= 0){ // se a bola bater na parede esquerda
                    direcao = Direcao.BAIXO_DIREITA; // inverte a direção para a direita
                    cBola.x += VELOCIDADE; // faz ir para direita
                    cBola.y += VELOCIDADE; // faz descer
                    break;
                }
                cBola.x += VELOCIDADE; // faz ir para direita
                cBola.y -= VELOCIDADE; // faz ir para cima
                break;
            case CIMA_ESQUERDA: // se a direcao está para cima e para esquerda
                if(bola.getCoordenadas().x <= 0){ // se a bola bater na parede esquerda
                    direcao = Direcao.CIMA_DIREITA; // inverte a direção para a direita
                    cBola.x += VELOCIDADE; // faz ir para direita
                    cBola.y -= VELOCIDADE; // faz subir
                    break;
                }
                if(bola.getCoordenadas().y <= 0){// se a bola bater no topo
                    direcao = Direcao.BAIXO_ESQUERDA; // inverte a direção para a baixo e para esquerda
                    cBola.x -= VELOCIDADE; // faz ir para esquerda
                    cBola.y += VELOCIDADE; // faz descer
                    break;
                }
                cBola.x -= VELOCIDADE; // faz ir para esquerda
                cBola.y -= VELOCIDADE; // faz subir
                break;
            case BAIXO_DIREITA:  // se a direcao está para baixo e para direita
                if(bola.getCoordenadas().x >= larguraTotal){  // se a bola bater na parede direita
                    direcao = Direcao.BAIXO_ESQUERDA; // inverte a direção para a baixo e para esquerda
                    cBola.x -= VELOCIDADE; // faz ir para esquerda
                    cBola.y += VELOCIDADE; // faz descer
                    break;
                }
                if(Util.acertou(cBola, cBarra)){ // se a bola bater na barra
                    direcao = Direcao.CIMA_DIREITA; // inverte a direção para cima e para direita
                    cBola.x += VELOCIDADE; // faz ir para direita
                    cBola.y -= VELOCIDADE; // faz subir
                    listener.marcarPonto(); // informa o listener (tela principal) que marcou um ponto
                    break;
                }
                if(bola.getCoordenadas().y >= alturaTotal){ // se a bola bater na base da tela
                    listener.perder();  // informa o listener (tela principal) que perdeu
                    reiniciar(); // reinicia (posiciona a bola no centro)
                    break;
                }
                cBola.x += VELOCIDADE; // faz ir para direita
                cBola.y += VELOCIDADE; // faz descer
                break;
            case BAIXO_ESQUERDA: // se a direcao está para baixo e para esquerda
                if(bola.getCoordenadas().x < 0){// se a bola bater na parede esquerda
                    direcao = Direcao.BAIXO_DIREITA; // inverte a direção para baixo e para direita
                    cBola.x += VELOCIDADE; // faz ir para direita
                    cBola.y += VELOCIDADE; // faz descer
                    break;
                }
                if(bola.getCoordenadas().y >= alturaTotal){ // se a bola bater na base da tela
                    listener.perder(); // informa o listener (tela principal) que perdeu
                    reiniciar(); // reinicia (posiciona a bola no centro)
                    break;
                }
                if(Util.acertou(cBola, cBarra)){ // se a bola bater na barra
                    direcao = Direcao.CIMA_ESQUERDA; // inverte a direção para cima e para esquerda
                    cBola.x -= VELOCIDADE; // faz ir para esquerda
                    cBola.y -= VELOCIDADE; // faz subir
                    listener.marcarPonto(); // informa o listener (tela principal) que marcou um ponto
                    break;
                }
                cBola.x -= VELOCIDADE; // faz ir para esquerda
                cBola.y += VELOCIDADE; // faz descer
                break;
        }
        redrawHandler.post(new Runnable() {public void run() { bola.invalidate();}}); // desenha a bolinha na tela
    }

    public void reiniciar(){
        bola.getCoordenadas().x = larguraTotal/2; // coloca a bola no centro horizontal
        bola.getCoordenadas().y = alturaTotal/2; // coloca a bola no centro vertical
        direcao = Direcao.CIMA_DIREITA;// inverte a direção para cima e para direita
    }


}
