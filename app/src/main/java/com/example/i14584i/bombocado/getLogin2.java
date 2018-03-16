package com.example.i14584i.bombocado;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Victor Gabriel on 01/08/2017.
 */

public class getLogin2 extends AsyncTask<String,String,String> {
    Activity ac;
    String data = "";
    String res = "";
    TextView senha;
    TextView login;
	String nome;
    ProgressDialog dialog;

    public getLogin2(String data, Activity ac, TextView senha, TextView login, String nome)
    {
        this.ac = ac;
		this.nome = nome;
        this.data = data;
        this.senha = senha;
        this.login = login;
        
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(ac);
        dialog.setTitle("Aviso");
        dialog.setIcon(R.drawable.cupcake_loading_dois);
        dialog.setMessage("Aguarde...");
        dialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            URL url = new URL("http://bombocado.esy.es/listLoginSenha.php");
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
                res+=line;
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
        super.onPostExecute(s);
        dialog.dismiss();
		String partes[] = s.split(";");
		login.setText(""+partes[1]);
		senha.setText(""+partes[0]);
        }
    }


