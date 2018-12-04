package com.example.guilhermeeyng.arkanoid;

import android.graphics.PointF;

public class Util {

    public static boolean acertou(PointF cBola, PointF cBarra){ // cBola = coordenadaBola | cBarra = coordenadaBarra
        return cBola.x >= cBarra.x &&
                cBola.x <= cBarra.x + Barra.LARGURA &&
                cBola.y >= cBarra.y &&
                cBola.y <= cBarra.y + Barra.ALTURA;
    }
}
