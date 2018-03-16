package com.example.i14584i.bombocado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class mycad extends AppCompatActivity {

	public String nome;
    TextView login;
    TextView senha;
    Button btn;
    Button btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycad);

		nome = getIntent().getStringExtra("nome");
        login = (TextView) findViewById(R.id.textView6);
        senha = (TextView) findViewById(R.id.textView9);
		String sql = "SELECT * FROM user WHERE login='"+nome+"'";
		new getLogin2(sql,mycad.this,login,senha,nome).execute("");
		



        btn = (Button) findViewById(R.id.button3);
        btn2 = (Button) findViewById(R.id.button4);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mycad.this,editar_mycad.class);
                i.putExtra("nome",nome);
                startActivity(i);
                finish();
            }
        });


    }
}
