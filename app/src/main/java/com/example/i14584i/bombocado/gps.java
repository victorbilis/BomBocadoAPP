package com.example.i14584i.bombocado;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by i14584i on 08/08/2017.
 */

public class gps  extends AsyncTask<String,String,String> {
    Activity ac;
    Picasso picasso;
    public gps(Activity ac)
    {
        this.ac = ac;
        Picasso.Builder builder = new Picasso.Builder(ac);
        picasso = builder.build();
    }

    @Override
    protected String doInBackground(String... params) {

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String msg = "\n Endereço: Rua Caviúna Nº 227 Jd Alvorada 13460-000 Nov a Odessa \n";
        msg += "\n Telefone: (19) 3466-7250" +
                " \n";




        ImageView img = new ImageView(ac);
        picasso.load(R.drawable.local).resize(700,700).into(img);
        AlertDialog.Builder detalhes = new AlertDialog.Builder(ac);
        detalhes.setTitle("BomBocado");
        detalhes.setView(img);
        detalhes.setMessage(msg);
        detalhes.setIcon(R.drawable.cupcake_loading_dois);
        detalhes.setNegativeButton("VOLTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        detalhes.setPositiveButton("VER NO MAPS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/?q=-22.8047015,-47.3100324"));
                ac.startActivity(i);
                dialog.dismiss();
            }
        });
        detalhes.show();
    }
}
