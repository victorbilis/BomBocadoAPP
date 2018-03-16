package com.example.i14584i.bombocado;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class local extends AppCompatActivity {

    ImageView imagem;
    ImageView tel;
    TextView num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);


        tel = (ImageView) findViewById(R.id.imageView9);
        imagem = (ImageView) findViewById(R.id.imageView10);
        num = (TextView) findViewById(R.id.textView13);


        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:"+num.getText().toString());
                Intent intent = new Intent(Intent.ACTION_DIAL,uri);

                startActivity(intent);
            }
        });

        imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new gps(local.this).execute("");
            }
        });

    }
}
