package com.example.i14584i.bombocado;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class reservardoce extends AppCompatActivity {

    public String nome;
    ListView lv;
    TextView preco_total2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservardoce);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nome = getIntent().getStringExtra("nome");
        preco_total2 = (TextView) findViewById(R.id.preco_total);
        lv = (ListView) findViewById(R.id.list);
        double preco = Double.parseDouble(preco_total2.getText().toString());
        new getProdutos2(this,lv,"SELECT * FROM produtos",preco_total2,preco,nome).execute("");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
    }

}
