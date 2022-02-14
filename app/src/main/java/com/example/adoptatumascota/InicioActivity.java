package com.example.adoptatumascota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Thread t_inicio= new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    //Validar configuraciones
                    //validar si hay acceso a internet
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //cargar la siguiente actividad
                    Intent i_login = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i_login);
                    finish(); //elimina la actividad anterior (no se puede regresar a la interfaz anterior)
                }
            }
        };
        t_inicio.start();
    }
}