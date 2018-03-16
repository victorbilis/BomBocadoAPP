package com.example.i14584i.bombocado;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class look_candy extends AppCompatActivity {

    public String item;

    TextView nome;

    TextView txt_tipo;
    TextView txt_massa;
    TextView txt_cobertura;
    TextView txt_recheio;
    TextView txt_recheio2;
    TextView txt_comp;

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_candy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new Database(this);

        item = getIntent().getStringExtra("item");

        nome = (TextView) findViewById(R.id.txt_nome);

        nome.setText(item);

        txt_tipo = (TextView) findViewById(R.id.tv_tipo);
        txt_massa = (TextView) findViewById(R.id.tv_massa);
        txt_cobertura = (TextView) findViewById(R.id.tv_cobertura);
        txt_recheio = (TextView) findViewById(R.id.tv_recheio);
        txt_recheio2 = (TextView) findViewById(R.id.tv_recheio2);
        txt_comp = (TextView) findViewById(R.id.tv_comp);

        String tipo = null;
        String massa = null;
        String cobertura = null;
        String recheio = null;
        String recheio2 = null;
        String comp = null;

        Cursor c = db.select("SELECT * FROM minhas_criacoes WHERE nome='"+item+"';");
        while(c.moveToNext())
        {

            tipo = c.getString(c.getColumnIndex("tipo"));
            massa = c.getString(c.getColumnIndex("massa"));
            cobertura = c.getString(c.getColumnIndex("cobertura"));
            recheio = c.getString(c.getColumnIndex("recheio"));
            recheio2 = c.getString(c.getColumnIndex("recheio2"));

            comp = c.getString(c.getColumnIndex("complemento"));


        }

        txt_tipo.setText("Tipo: " + tipo);
        txt_massa.setText("Massa: " +massa);
        txt_cobertura.setText("Cobertura: " +cobertura);
        txt_recheio.setText("Recheios: " +recheio);
        txt_recheio2.setText(recheio2);
        txt_comp.setText("Complemento: " +comp);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent i = new Intent(look_candy.this,edit_mycandy.class);
            i.putExtra("item",item);
                startActivity(i);
            finish();

            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder detalhes = new AlertDialog.Builder(look_candy.this);
                detalhes.setTitle("Excluir");
                detalhes.setMessage("Deseja excluir este doce?");
                detalhes.setIcon(R.drawable.cupcake_loading_dois);
                detalhes.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        db.sql("DELETE FROM minhas_criacoes WHERE nome='"+item+"'");
                        Toast.makeText(look_candy.this,"Doce excluído com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                detalhes.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                detalhes.show();

            }
        });
    }

}
