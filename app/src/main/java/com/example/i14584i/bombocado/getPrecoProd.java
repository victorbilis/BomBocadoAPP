package com.example.i14584i.bombocado;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Victor Gabriel on 01/08/2017.
 */

public class getPrecoProd extends AsyncTask<String,String,String> {
    Context context;
    String data = "";
    String res = "";
    public getPrecoProd(String data,Context context)
    {
        this.context = context;
        this.data = data;
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
        String text = "\n R$ " + partes[0] +"\n";

        ImageView img = new ImageView(context);
        new LoadImage(context,img).execute("http://bombocado.esy.es/imgs/"+partes[1]);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Preço");
        builder.setMessage(text);
        builder.setIcon(R.drawable.logo);
        builder.show();
    }
}
