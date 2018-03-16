package com.example.i14584i.bombocado;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Gabriel on 01/08/2017.
 */

public class getProdutos3 extends AsyncTask<String,String,String> {
    Activity context;
    String nome = "";
    String res = "";
    String data = "";
    int qtd = 0;

    public getProdutos3(Activity context, String nome,String data, int qtd)
    {
        this.context = context;
        this.nome = nome;
        this.data = data;
        this.qtd = qtd;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            URL url = new URL("http://bombocado.esy.es/listProd.php");
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
        String partes[] = s.split("&");
        int i =0;

        String cod = null;
        String nome_prod = null;
        double preco_prod = 0;
        String img = null;
        String tipo = null;
        String massa = null;
        String cob = null;
        String recheio = null;
        String comp = null;

        while(i< partes.length)
        {
            String partes_partes[] = partes[i].split(";");
            cod = partes_partes[0];
            nome_prod = partes_partes[1];
            preco_prod = Double.parseDouble(partes_partes[2]);
            img = partes_partes[3];
            tipo = partes_partes[4];
            massa = partes_partes[5];
            cob = partes_partes[6];
            recheio = partes_partes[7];
            comp = partes_partes[8];

            i++;
        }

        double preco = preco_prod * qtd;

        new send("INSERT INTO pedidos VALUES(null,'"+nome+"','"+nome_prod+"',"+preco+",'"+tipo+"','"+massa+"','"+cob+"','"+recheio+"',"+qtd+",'"+comp+"');",context,null).execute("");
    }
}
