package com.example.i14584i.bombocado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class form_login extends AppCompatActivity {

    EditText login;
    EditText senha;
    Button btn;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        login = (EditText) findViewById(R.id.editText);
        senha = (EditText) findViewById(R.id.editText2);
        btn = (Button) findViewById(R.id.button);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sql = "SELECT * FROM user WHERE login='"+login.getText()+"' and senha='"+senha.getText()+"'";
                new getLogin(sql,form_login.this,senha,login, login.getText().toString()).execute("");
            }
        });

        btn2 = (Button) findViewById(R.id.button2);


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(form_login.this,cad.class);
                startActivity(i);
            }
        });

    }

}
