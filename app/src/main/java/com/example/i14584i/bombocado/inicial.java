package com.example.i14584i.bombocado;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class inicial extends AppCompatActivity {



    ListView lista1;
    ListView lista2;
        Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new Database(this);



        final TabHost host = (TabHost)findViewById(R.id.tabhost);

        host.setup();
        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Produtos");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Descontos");
        host.addTab(spec);


        host.setCurrentTab(0);

        lista1 = (ListView) findViewById(R.id.list1);
        lista2 = (ListView) findViewById(R.id.list2);

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                switch (host.getCurrentTab()) {
                    case 0:
                        String valor = "SELECT * FROM produtos;";
                        new getProd(inicial.this, lista1 , valor).execute("");
                        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                ArrayAdapter list = (ArrayAdapter<String>) lista1.getAdapter();
                                String data = "SELECT * FROM produtos WHERE nome_prod='" + list.getItem(i).toString() + "'";
                                new getPrecoProd(data, inicial.this).execute("");
                            }
                        });
                        break;

                    case 1:
                        String valor2 = "SELECT * FROM descontos;";
                        new getDesc(inicial.this, lista2 , valor2).execute("");
                        lista2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                ArrayAdapter list = (ArrayAdapter<String>) lista2.getAdapter();
                                String data = "SELECT * FROM descontos WHERE nome_desc='" + list.getItem(i).toString() + "'";
                                new getPrecoDesc(data, inicial.this).execute("");
                            }
                        });
                        break;

                    default:
                        break;
                }
            }
        });

        String valor = "SELECT * FROM produtos;";
        new getProd(inicial.this, lista1 , valor).execute("");

        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter list = (ArrayAdapter<String>) lista1.getAdapter();
                String data = "SELECT * FROM produtos WHERE nome_prod='" + list.getItem(i).toString() + "'";
                new getPrecoProd(data, inicial.this).execute("");
            }
        });



    }
    public void login(View view)
    {
        Cursor c = db.select("SELECT * FROM users;");
        if(c.getCount() > 0)
        {
            String user = null;
            while(c.moveToNext())
            {
                user = c.getString(c.getColumnIndex("login"));
            }
            Intent intent = new Intent(inicial.this,Main.class);
            intent.putExtra("nome",user);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(inicial.this,form_login.class);
            startActivity(intent);
        }


    }
    public void contato(View view)
    {
        Intent intent = new Intent(inicial.this,local.class);
        startActivity(intent);

    }
}
