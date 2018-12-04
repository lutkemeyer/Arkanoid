package com.example.guilhermeeyng.arkanoid;

import android.graphics.PointF;
import android.os.Handler;
import android.util.Log;
import android.widget.FrameLayout;

public class ComportamentoBarra {

    private Barra barra;
    private int larguraTotal; // largura da tela
    private Handler redrawHandler = new Handler();

    public ComportamentoBarra(Barra barra, int larguraTotal) {
        this.barra = barra;
        this.larguraTotal = larguraTotal;
    }

    public void movimentar(float vel){
        PointF coordenadas = barra.getCoordenadas();

        if(xInicial() >= 0 && xFinal() <= larguraTotal){
            coordenadas.set(coordenadas.x + vel + ((vel>0)?3:-3), coordenadas.y);
        }
        if(xInicial() < 0){
            coordenadas.set(0,coordenadas.y);
        }else if(xFinal() > larguraTotal){
            coordenadas.set(larguraTotal - Barra.LARGURA, coordenadas.y);
        }
        redrawHandler.post(new Runnable() {public void run() { barra.invalidate();}});
    }

    public float xInicial(){
        return barra.getCoordenadas().x;
    }
    public float xFinal(){
        return xInicial()+Barra.LARGURA;
    }

    public PointF getCoordenadasBarra(){
        return barra.getCoordenadas();
    }
}
