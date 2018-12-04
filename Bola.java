package com.example.guilhermeeyng.arkanoid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.View;

public class Bola extends View {

    private PointF coordenadas;
    private final int raio;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Bola(Context context, float xInicial, float yInicial, int raio) {
        super(context);
        paint.setColor(context.getResources().getColor(R.color.colorPrimary));
        coordenadas = new PointF(xInicial, yInicial);
        this.raio = raio;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(coordenadas.x, coordenadas.y, raio, paint);
    }

    public PointF getCoordenadas() {
        return coordenadas;
    }
}

