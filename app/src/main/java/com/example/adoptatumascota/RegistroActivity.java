package com.example.adoptatumascota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class RegistroActivity extends AppCompatActivity implements View.OnClickListener{
EditText txt_nombre, txt_apellidos, txt_direccion, txt_telefono, txt_correo, txt_contaseña, txt_confcontraseña;
Spinner cbo_tipo_usuario;
Button btn_atras, btn_registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txt_nombre=findViewById(R.id.reg_lbl_nombre);
        txt_apellidos =findViewById(R.id.reg_lbl_apellido);
        txt_direccion =findViewById(R.id.reg_lbl_direccion);
        txt_telefono =findViewById(R.id.reg_lbl_telefono);
        txt_correo =findViewById(R.id.reg_lbl_correo);
        txt_contaseña=findViewById(R.id.reg_lbl_contraseña);
        txt_confcontraseña =findViewById(R.id.reg_lbl_confcontraseña);
        btn_atras=findViewById(R.id.reg_btn_atras);
        btn_registrar=findViewById(R.id.reg_btn_registro);
        cbo_tipo_usuario=findViewById(R.id.sp_cbo_tipo_usuario);
        btn_atras.setOnClickListener(this);
        btn_registrar.setOnClickListener(this);

        elegir_usuario();
    }

    private void elegir_usuario() {

   cbo_tipo_usuario.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
           new String[]{"--Elegir usuario--", "Adoptante", "Protector"}));
   String s_url="https://adoptatumascota-upn.atwebpages.com/ws/tipousuario.php";

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.reg_btn_atras:
                regresar();

                break;
            case R.id.reg_btn_registro:
                registrar_user();
                break;
        }
    }
    private void registrar_user() {
        if (cbo_tipo_usuario.getSelectedItemPosition() == 0 || txt_nombre.getText().toString().isEmpty() ||
                txt_apellidos.getText().toString().isEmpty() || txt_direccion.getText().toString().isEmpty() ||
                txt_telefono.getText().toString().isEmpty() || txt_correo.getText().toString().isEmpty() || txt_contaseña.getText().toString().isEmpty()
                || txt_confcontraseña.getText().toString().isEmpty()) {
            Toast.makeText(RegistroActivity.this, "Debe completar todos los campos", Toast.LENGTH_LONG).show();
        } else {
            if (txt_telefono.getText().toString().length() < 9) {
                Toast.makeText(RegistroActivity.this, "El teléfono requiere mínimo 9 caracteres", Toast.LENGTH_LONG).show();
            } else {
                if (txt_contaseña.getText().toString().length() < 8) {
                    Toast.makeText(RegistroActivity.this, "La contraseña requiere mínimo 8 caracteres", Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(RegistroActivity.this, "Se registró con éxito", Toast.LENGTH_LONG).show();
                    Intent i_login = new Intent(this, LoginActivity.class);
                    startActivity(i_login);
                }
            }
        }
    }
    private void regresar() {
        Intent i_mostrar = new Intent(getApplicationContext(),LoginActivity.class);
        finish();
        startActivity(i_mostrar);
    }
}