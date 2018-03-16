package com.example.i14584i.bombocado;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Victor Gabriel on 14/08/2017.
 */

public class verificarCon extends AsyncTask<String,String,String> {

    Activity ac;

    public verificarCon(Activity loagin) {
        this.ac = loagin;
    }


    @Override
    protected String doInBackground(String... string) {
        String res = null;
        try
        {
            URL url = new URL("http://www.google.com.br");
            URLConnection con = url.openConnection();
            con.setDoOutput(false); //

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
            String line = null;
            while((line = reader.readLine()) != null)
            {
                res = "a";
            }
        }
        catch(Exception e)
        {
            res = "b";
            Log.i("Script","catch con="+e);
        }
        return res;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i("Script","resultasdo con="+s);
        if(s.contains("a"))
        {
            Intent i = new Intent(ac,inicial.class);
            ac.startActivity(i);
            ac.finish();
        }
        else
        {
            Intent i = new Intent(ac,wrong.class);
            ac.startActivity(i);
            ac.finish();
        }
    }
}
