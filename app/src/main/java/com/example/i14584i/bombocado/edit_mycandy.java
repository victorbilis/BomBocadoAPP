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
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class edit_mycandy extends AppCompatActivity {

    public String item;

    Database db;

    Spinner tipo;
    Spinner massa;
    Spinner recheio;
    Spinner recheio2;
    Spinner cobertura;
    EditText complemento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mycandy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        item = getIntent().getStringExtra("item");

        tipo = (Spinner) findViewById(R.id.sp_tipo);
        massa = (Spinner) findViewById(R.id.sp_massa);
        recheio = (Spinner) findViewById(R.id.sp_recheio);
        recheio2 = (Spinner) findViewById(R.id.sp_recheio2);
        cobertura = (Spinner) findViewById(R.id.sp_cobertura);

        complemento = (EditText) findViewById(R.id.txt_comp);


        db = new Database(this);

        Cursor c = db.select("SELECT * FROM minhas_criacoes WHERE nome='"+item+"';");

        String txt_tipo = null;
        String txt_massa = null;
        String txt_recheio = null;
        String txt_recheio2 = null;
        String txt_cobertura = null;
        String var_comp = null;

        while(c.moveToNext())
        {
            txt_tipo = c.getString(c.getColumnIndex("tipo"));
            txt_massa = c.getString(c.getColumnIndex("massa"));
            txt_recheio = c.getString(c.getColumnIndex("recheio"));
            txt_recheio2 = c.getString(c.getColumnIndex("recheio2"));
            txt_cobertura = c.getString(c.getColumnIndex("cobertura"));

            var_comp = c.getString(c.getColumnIndex("complemento"));
        }

        String sql = "SELECT * FROM ingrediente WHERE tipo_ingr='tipo'";
        String sql2 = "SELECT * FROM ingrediente WHERE tipo_ingr='massa'";
        String sql3 = "SELECT * FROM ingrediente WHERE tipo_ingr='recheio'";
        String sql4 = "SELECT * FROM ingrediente WHERE tipo_ingr='recheio'";
        String sql5 = "SELECT * FROM ingrediente WHERE tipo_ingr='cobertura'";


        new getIngredientes2(edit_mycandy.this,tipo,sql,txt_tipo).execute("");
        new getIngredientes2(edit_mycandy.this,massa,sql2,txt_massa).execute("");
        new getIngredientes2(edit_mycandy.this,recheio,sql3,txt_recheio).execute("");
        new getIngredientes2(edit_mycandy.this,recheio2,sql4,txt_recheio2).execute("");
        new getIngredientes2(edit_mycandy.this,cobertura,sql5,txt_cobertura).execute("");

        complemento.setText(var_comp);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder detalhes = new AlertDialog.Builder(edit_mycandy.this);
                detalhes.setTitle("Editar");
                detalhes.setMessage("Deseja editar o doce '"+item+"'?");
                detalhes.setIcon(R.drawable.cupcake_loading_dois);
                detalhes.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String sql = "UPDATE minhas_criacoes SET tipo='"+tipo.getSelectedItem().toString()+"'," +
                                "massa = '"+massa.getSelectedItem().toString()+"',"+
                                "recheio = '"+recheio.getSelectedItem().toString()+"'," +
                                "recheio2 = '"+recheio2.getSelectedItem().toString()+"'," +
                                "cobertura = '"+cobertura.getSelectedItem().toString()+"'," +
                                "complemento = '"+complemento.getText()+"' " +
                                "WHERE nome='"+item+"';";
                        db.sql(sql);

                        Toast.makeText(edit_mycandy.this,"Doce editado com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(edit_mycandy.this, look_candy.class);
                        i.putExtra("item",item);
                        startActivity(i)    ;
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
