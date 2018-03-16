package com.example.i14584i.bombocado;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Victor Gabriel on 02/08/2017.
 */

public class send extends AsyncTask<String,String,String> {
    String res = "";
    String data = "";
    String ac;
    Activity activity;
    public send(String data, Activity activity, String ac)
    {
        this.data = data;
        this.ac = ac;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(activity,"Enviando dados...",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            URL url = new URL("http://bombocado.esy.es/send.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type","application/x-www-form-urlencoded");

            DataOutputStream printer = new DataOutputStream(con.getOutputStream());
            printer.writeBytes("sql="+data);
            printer.flush();
            printer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
            String line = "";

            while((line=reader.readLine()) != null)
            {
                res=line;
            }
        }
        catch(Exception e)
        {
            res = ""+e;
        }
        return res;
    }

    @Override
    protected void onPostExecute(String s) {
        AlertDialog.Builder detalhes = new AlertDialog.Builder(activity);
        detalhes.setTitle("Aviso");
        detalhes.setMessage(s);
        detalhes.setIcon(R.drawable.logo);
        detalhes.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(ac.equals("form"))
                {
                    Intent i = new Intent(activity,form_login.class);
                    activity.startActivity(i);

                }
                else if(ac.equals("editar"))
                {
                    activity.finish();
                }
                else {
                    dialog.dismiss();
                }
                }
        });
        detalhes.show();
    }
}
