package com.example.guilhermeeyng.arkanoid;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity  implements SensorEventListener, OnPontosChanged{

    private ComportamentoBarra comportamentoBarra;
    private ComportamentoBola comportamentoBola;
    private float velocidade;

    private Timer timer = null;
    private TimerTask tarefa = null;
    private Handler handlerAtualizaPonto = new Handler();

    private TextView lblPontos;
    private int pontos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblPontos = findViewById(R.id.lblPontos);

        Display tela = getWindowManager().getDefaultDisplay();
        int larguraTela = tela.getWidth();
        int alturaTela = tela.getWidth();

        Barra barra = new Barra(MainActivity.this, larguraTela/2 - Barra.LARGURA/2, (alturaTela-80)*0.8f);
        Bola bola = new Bola(MainActivity.this, larguraTela/2, alturaTela/2, 15);

        comportamentoBarra = new ComportamentoBarra(barra, larguraTela);
        comportamentoBola = new ComportamentoBola(bola, larguraTela, alturaTela-80, this);

        FrameLayout container = findViewById(R.id.container);
        container.addView(barra);
        container.addView(bola);
        barra.invalidate();

        SensorManager sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onResume() {
        timer = new Timer();
        tarefa = new TimerTask() {
            public void run() {
                comportamentoBarra.movimentar(velocidade);
                comportamentoBola.movimentar(comportamentoBarra.getCoordenadasBarra());
            }};
        timer.schedule(tarefa,10,10); //start timer
        super.onResume();
    }

    @Override
    protected void onPause() {
        timer.cancel();
        timer = null;
        tarefa = null;
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        velocidade = -event.values[0];
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }



    // passar se tiver tempo
    @Override
    public void marcarPonto() {
        handlerAtualizaPonto.post(new Runnable() {
            @Override
            public void run() {
                pontos++;
                lblPontos.setText(pontos+"");
            }
        });
    }
    // passar se tiver tempo
    @Override
    public void perder() {
        handlerAtualizaPonto.post(new Runnable() {
            @Override
            public void run() {
                pontos=0;
                lblPontos.setText(pontos+"");
            }
        });

    }
}
