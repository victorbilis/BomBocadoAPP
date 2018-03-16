package com.example.i14584i.bombocado;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Victor Gabriel on 01/08/2017.
 */

public class getIngredientes2 extends AsyncTask<String,String,String> {
    Activity context;
    Spinner sp;
    String data = "";
    String res = "";
    String item = "";
    ProgressDialog dialog;
    public getIngredientes2(Activity context, Spinner sp, String data,String item)
    {
        this.item = item;
        this.context = context;
        this.sp = sp;
        this.data = data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setTitle("Aviso");
        dialog.setIcon(R.drawable.cupcake_loading_dois);
        dialog.setMessage("Aguarde...");
        dialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            URL url = new URL("http://bombocado.esy.es/listIngredientes.php");
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,partes);
        sp.setAdapter(adapter);
        sp.setSelection(adapter.getPosition(item));
        dialog.dismiss();
    }
}
