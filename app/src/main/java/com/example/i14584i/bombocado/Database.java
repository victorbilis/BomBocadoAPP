package com.example.i14584i.bombocado;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by bruno on 06/07/17.
 */

public class Database {
    SQLiteDatabase db;
    public Database(Context context)
    {
        db = context.openOrCreateDatabase("banco_DB",context.MODE_PRIVATE,null);
        create_tables();
    }
    public void sql(String sql)
    {
        db.execSQL(sql);
    }
    public Cursor select(String sql)
    {
        return db.rawQuery(sql,null);
    }

    public void create_tables()
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS minhas_criacoes(" +
                "cod INTEGER not null," +
                "nome_user varchar(500) not null,"+
                "nome varchar(500) not null," +
                "tipo varchar(500) not null," +
                "massa varchar(500) not null," +
                "cobertura varchar(500) not null," +
                "recheio varchar(500),"+
                "recheio2 varchar(500),"+
                "complemento varchar(5000),"+
                "Primary key(cod));");


        db.execSQL("CREATE TABLE IF NOT EXISTS users(" +
                "cod INTEGER not null," +
                "login varchar(500) not null," +
                "senha varchar(500) not null," +
                "Primary key(cod));");

    }
}


