package com.example.guilhermeeyng.arkanoid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;
import android.view.View;

public class Barra extends View {

    public static final int LARGURA = 200, ALTURA = 20;

    private PointF coordenadas;

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Barra(Context context, float xInicial, float yInicial) {
        super(context);
        paint.setColor(context.getResources().getColor(R.color.colorPrimaryDark));
        this.coordenadas = new PointF(xInicial, yInicial);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(coordenadas.x, coordenadas.y, coordenadas.x+LARGURA, coordenadas.y+ALTURA, paint);
    }
    public PointF getCoordenadas(){
        return coordenadas;
    }
}
