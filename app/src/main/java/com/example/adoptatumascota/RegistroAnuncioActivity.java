package com.example.adoptatumascota;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroAnuncioActivity extends AppCompatActivity implements View.OnClickListener  {
    Spinner cbo_especie, cbo_distrito, cbo_provincia;
    EditText txt_nombre, txt_raza, txt_descripcion, txt_edad;
    Button btn_guardar, btn_atras;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_anuncio);

        cbo_especie=findViewById(R.id.crear_anuncio_spinner_especie);
        cbo_distrito=findViewById(R.id.crear_anuncio_spinner_distrito);
        cbo_provincia=findViewById(R.id.crear_anuncio_spinner_provincia);
        txt_nombre=findViewById(R.id.crear_anuncio_txt_nombre);
        txt_raza=findViewById(R.id.crear_anuncio_txt_raza);
        txt_descripcion=findViewById(R.id.crear_anuncio_txt_descripcion);
        txt_edad=findViewById(R.id.crear_anuncio_txt_edad);
        btn_guardar=findViewById(R.id.crear_anuncio_btn_guardar);
        btn_atras=findViewById(R.id.crear_anuncio_btn_atras);

        btn_guardar.setOnClickListener(this);

        cbo_especie.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Seleccione especie", "Perro", "Gato", "Caballo"}));

        cbo_provincia.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Seleccione provincia", "Cajamarca", "La Libertad", "Lima"}));

        cbo_distrito.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Seleccione distrito", "Florencia de Mora", "Trujillo", "El Porvenir"}));

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.crear_anuncio_btn_guardar:
                guardar_anuncio();
                break;
        }
    }

    private void guardar_anuncio() {
        if(cbo_especie.getSelectedItemPosition()==0 || txt_nombre.toString().isEmpty() || txt_raza.toString().isEmpty() ||
         txt_descripcion.toString().isEmpty() || txt_edad.toString().isEmpty() || cbo_distrito.getSelectedItemPosition()==0 || cbo_provincia.getSelectedItemPosition()==0){
            Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_LONG).show();
        }
    }
}
