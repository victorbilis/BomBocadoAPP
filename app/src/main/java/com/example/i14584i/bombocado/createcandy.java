package com.example.i14584i.bombocado;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class createcandy extends AppCompatActivity {

    Spinner tipo;
    Spinner massa;
    Spinner recheio;
    Spinner recheio2;
    Spinner cobertura;

    EditText comp;
    EditText qtd;

    ImageView aviso;

    Database db;

    public String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createcandy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nome = getIntent().getStringExtra("nome");

        db = new Database(this);


        comp = (EditText) findViewById(R.id.editText9);

        tipo = (Spinner) findViewById(R.id.spinner);
        massa = (Spinner) findViewById(R.id.spinner2);
        recheio = (Spinner) findViewById(R.id.spinner4);
        recheio2 = (Spinner) findViewById(R.id.spinner5);
        cobertura = (Spinner) findViewById(R.id.spinner6);

        aviso = (ImageView) findViewById(R.id.imageView2);

        aviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder detalhes = new AlertDialog.Builder(createcandy.this);
                detalhes.setTitle("Aviso");
                detalhes.setMessage("Use somente 2 coberturas, quando escolher bolo!");
                detalhes.setIcon(R.drawable.logo);
                detalhes.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                detalhes.show();
            }
        });

        String sql = "SELECT * FROM ingrediente WHERE tipo_ingr='tipo'";
        String sql2 = "SELECT * FROM ingrediente WHERE tipo_ingr='massa'";
        String sql3 = "SELECT * FROM ingrediente WHERE tipo_ingr='recheio'";
        String sql4 = "SELECT * FROM ingrediente WHERE tipo_ingr='recheio'";
        String sql5 = "SELECT * FROM ingrediente WHERE tipo_ingr='cobertura'";


        new getIngredientes(createcandy.this,tipo,sql).execute("");
        new getIngredientes(createcandy.this,massa,sql2).execute("");
        new getIngredientes(createcandy.this,recheio,sql3).execute("");
        new getIngredientes(createcandy.this,recheio2,sql4).execute("");
        new getIngredientes(createcandy.this,cobertura,sql5).execute("");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                dialog.setTitle("BomBocado - Salvar");;
                dialog.setMessage("\n");
                dialog.setIcon(R.drawable.logo);
                Context context = view.getContext();
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText titleBox = new EditText(context);

                titleBox.setHint("Nome do seu doce");

                layout.addView(titleBox);

                dialog.setPositiveButton("SALVAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String sql = "INSERT INTO minhas_criacoes VALUES(null,'"+nome+"'," +
                                "'"+titleBox.getText()+"'," +
                                "'"+tipo.getSelectedItem().toString()+"'," +
                                "'"+massa.getSelectedItem().toString()+"'," +
                                "'"+cobertura.getSelectedItem().toString()+"'," +
                                "'"+recheio.getSelectedItem().toString()+"'," +
                                "'"+recheio2.getSelectedItem().toString()+"'," +
                                "'"+comp.getText()+"')";
                        db.sql(sql);

                        AlertDialog.Builder detalhes = new AlertDialog.Builder(createcandy.this);
                        detalhes.setTitle("Aviso");
                        detalhes.setMessage("Salvo com sucesso!");
                        detalhes.setIcon(R.drawable.logo);
                        detalhes.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                createcandy.this.finish();
                            }
                        });
                        detalhes.show();

                    }
                });

                dialog.setNegativeButton("VOLTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                dialog.setView(layout);
                dialog.show();
            }
        });
    }


}
