package com.example.i14584i.bombocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Loagin extends AppCompatActivity {

    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loagin);
        pb = (ProgressBar) findViewById(R.id.progressBar);



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new verificarCon(Loagin.this).execute("");
            }
        }).start();
    }
}
