package com.example.adoptatumascota.SQLite;

import android.content.Context;
import android.database.Cursor;
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
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS USUARIO");
        sqLiteDatabase.execSQL(s_tabla_usuario);
    }

    public boolean agregar_usuario (int id_id, String s_correo, String s_contrasenia){
        boolean b_ret= false;
        SQLiteDatabase db= getWritableDatabase();

        if(db!= null){                                      //apóstrofes porque son cadenas
            db.execSQL("INSERT INTO USUARIO VALUES("+id_id+",'"+s_correo+"','"+s_contrasenia+"')");
            db.close();
            b_ret= true;
        }

        return b_ret;
    }

    public boolean recordo_sesion(){
        boolean b_ret= false;
        SQLiteDatabase db= getReadableDatabase();
        if (db!=null){
            Cursor cursor= db.rawQuery("SELECT * FROM USUARIO", null);
            if (cursor.moveToNext())
                b_ret= true;
        }
        return b_ret;
    }

    public String extraer_usuario (String s_campo){
        String s_data="";
        SQLiteDatabase db= getReadableDatabase();
        String s_consulta= String.format("SELECT %s FROM USUARIO", s_campo);

        if (db != null){
            Cursor cursor = db.rawQuery(s_consulta, null);
            if (cursor.moveToNext())
                s_data = cursor.getString(0);
        }
        return s_data;
    }

    public boolean eliminar_usuario(int i_id){
        boolean b_ret= false;

        SQLiteDatabase db= getWritableDatabase();
        if (db!=null){
            db.execSQL("DELETE FROM USUARIO WHERE ID="+i_id);
            db.close();
            b_ret= true;
        }
        return b_ret;
    }

    public boolean actualizar_dato_usuario(int i_id, String s_campo, String s_valor){
        boolean b_ret= false;
        SQLiteDatabase db= getWritableDatabase();

        if(db!=null){
            db.execSQL("UPDATE USUARIO SET "+s_campo+"=  '"+s_valor+"' WHERE ID="+i_id);
            db.close();
            b_ret=true;
        }
        return b_ret;
    }
}
