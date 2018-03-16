package com.example.i14584i.bombocado;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Victor Gabriel on 01/08/2017.
 */

public class getPrecoProd2 extends AsyncTask<String,String,String> {
    Context context;
    String data = "";
    String res = "";
    TextView preco_total;
    double preco;
    ProgressDialog dialog;

    public getPrecoProd2(String data, Context context,TextView preco_total,double preco)
    {
        this.context = context;
        this.data = data;
        this.preco_total = preco_total;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setTitle("Aviso");
        dialog.setIcon(R.drawable.cupcake_loading_dois);
        dialog.setMessage("Adicionando...");
        dialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            URL url = new URL("http://bombocado.esy.es/listPrecoProd.php");
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
        preco = Double.parseDouble(preco_total.getText().toString());
        double preco2 = Double.parseDouble(partes[0]);
        double total = preco2 + preco;
        preco_total.setText(""+total);
        dialog.dismiss();

    }
}
