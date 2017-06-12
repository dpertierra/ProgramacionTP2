package com.example.a42025177.tp2nuevo2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Usuario on 1/6/2017.
 */

public class baseTP3SQLiteHelper extends SQLiteOpenHelper {

    public baseTP3SQLiteHelper(Context context, String Nombre, SQLiteDatabase.CursorFactory fabrica, int version)
    {
        super(context, Nombre, fabrica,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.d("SQLite","Declaro e inicializo la variable para crear la tabla personas");
        String sqlCrearTablaJugadores;
        sqlCrearTablaJugadores= "create table jugadores(Nombre text, Record integer)";
        Log.d("SQLite" , "Invoco creador de tabla");
        db.execSQL(sqlCrearTablaJugadores);
        db.execSQL("insert into jugadores values ('Admin', '10')");
        db.execSQL("insert into jugadores values ('Prueba', '100')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}