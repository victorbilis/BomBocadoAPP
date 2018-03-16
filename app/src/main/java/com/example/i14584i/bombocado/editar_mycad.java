package com.example.i14584i.bombocado;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class editar_mycad extends AppCompatActivity {

    public String nome;
    EditText login;
    EditText senha;
    Button btn;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_mycad);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nome = getIntent().getStringExtra("nome");
        login = (EditText) findViewById(R.id.editText6);
        senha = (EditText) findViewById(R.id.editText7);

        btn = (Button) findViewById(R.id.button3);
        btn2 = (Button) findViewById(R.id.button4);

        String sql = "SELECT * FROM user WHERE login='"+nome+"'";
        new getLogin3(sql,editar_mycad.this,login,senha,nome).execute("");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new send("UPDATE user SET login='"+login.getText()+"',senha='"+senha.getText()+"' WHERE login='"+nome+"';",editar_mycad.this,"editar").execute("");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
