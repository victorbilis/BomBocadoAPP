package com.example.i14584i.bombocado;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class cad extends AppCompatActivity {

    EditText login;
    EditText senha;
    EditText senha2;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad);

        login = (EditText) findViewById(R.id.editText3);
        senha = (EditText) findViewById(R.id.editText4);
        senha2 = (EditText) findViewById(R.id.editText5);

        btn = (Button) findViewById(R.id.button3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(senha.getText().toString().equals(senha2.getText().toString()))
                {
                    new verificaLogin(cad.this,senha,login,senha2).execute("");
                }
                else
                {
                    AlertDialog.Builder detalhes = new AlertDialog.Builder(cad.this);
                    detalhes.setTitle("Aviso");
                    detalhes.setMessage("Senha diferentes!");
                    detalhes.setIcon(R.drawable.logo);
                    detalhes.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            senha.setText("");
                            senha2.setText("");
                        }
                    });
                    detalhes.show();
                }
            }
        });


    }
}
