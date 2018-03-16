package com.example.i14584i.bombocado;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

    TextView user = null;
    public String nome;
    ListView lv;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new Database(this);

        nome = getIntent().getStringExtra("nome");



        lv = (ListView) findViewById(R.id.listView);
        new getProdutos(this,lv,"SELECT * FROM produtos").execute("");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        user = (TextView) headerView.findViewById(R.id.txt_user);
        user.setAllCaps(true);
        user.setText(nome);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_doce) {
           Intent i = new Intent(Main.this,createcandy.class);
            i.putExtra("nome",nome);
            startActivity(i);
        } else if (id == R.id.nav_reservar) {
            Intent i = new Intent(Main.this,reservardoce.class);
            i.putExtra("nome",nome);
            startActivity(i);
        } else if (id == R.id.nav_mc) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
            Cursor c = db.select("SELECT * FROM minhas_criacoes WHERE nome_user='"+nome+"';");
            while(c.moveToNext())
            {
                String nome = c.getString(c.getColumnIndex("nome"));
                adapter.add(nome);
            }
            final ListView lv = new ListView(this);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ArrayAdapter<String> adapter = (ArrayAdapter<String>) lv.getAdapter();
                    String item = adapter.getItem(position);

                    Intent i = new Intent(Main.this,look_candy.class);
                    i.putExtra("item",item);
                    startActivity(i);

                }
            });

            AlertDialog.Builder detalhes = new AlertDialog.Builder(this);
            detalhes.setTitle("Minhas criações");
            detalhes.setView(lv);
            detalhes.setIcon(R.drawable.logo);
            detalhes.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            detalhes.show();
        } else if (id == R.id.nav_cad) {

           Intent i = new Intent(Main.this,mycad.class);
            i.putExtra("nome",nome);
            startActivity(i);
        } else if (id == R.id.nav_home) {
            finish();
        }
        else if (id == R.id.nav_sair) {
            db.sql("DELETE FROM users WHERE 1;");
            finish();
        } 

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

