package com.example.adoptatumascota.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Sesion extends SQLiteOpenHelper {
    private static final String s_nombre_db= "vet.db";
    private static final int i_version_db= 1;
    private static final String s_tabla_usuario= "CREATE TABLE IF NOT EXISTS USUARIO(ID INTEGER, CORREO VARCHAR(60), CLAVE VARCHAR(20))";

    //CONSTRUCTOR DE LA BASE DE DATOS
    public Sesion(Context context) {
        super(context, s_nombre_db, null, i_version_db);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(s_tabla_usuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
