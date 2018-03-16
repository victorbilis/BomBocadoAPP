package com.example.i14584i.bombocado;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class getProdutos extends AsyncTask<String,String,String> {
    Activity context;
    ListView listView;
    String data = "";
    String res = "";
    public getProdutos(Activity context, ListView listView, String data)
    {
        this.context = context;
        this.listView = listView;
        this.data = data;
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
        List<Produto> produtos = new ArrayList<>();

        while(i< partes.length)
        {
            String partes_partes[] = partes[i].split(";");
            String cod = partes_partes[0];
            String nome_prod = partes_partes[1];
            String preco_prod = partes_partes[2];
            String img = partes_partes[3];

            Produto produto = new Produto();
            produto.setCod(cod);
            produto.setNome_prod(nome_prod);
            produto.setPreco_prod("Preço: R$"+preco_prod);
            produto.setImg(img);

            produtos.add(produto);


            i++;
        }
        listView.setAdapter(new ProdutosAdapter(context,produtos));
    }
}
