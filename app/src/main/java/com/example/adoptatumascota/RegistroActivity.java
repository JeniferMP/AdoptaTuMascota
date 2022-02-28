package com.example.adoptatumascota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;


public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txt_nombre, txt_apellidos, txt_direccion, txt_telefono, txt_correo, txt_contaseña, txt_confcontraseña;
    Spinner cbo_tipo_usuario;
    Button btn_atras, btn_registrar;
    CheckBox chk_terminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txt_nombre = findViewById(R.id.reg_lbl_nombre);
        txt_apellidos = findViewById(R.id.reg_lbl_apellido);
        txt_direccion = findViewById(R.id.reg_lbl_direccion);
        txt_telefono = findViewById(R.id.reg_lbl_telefono);
        txt_correo = findViewById(R.id.reg_lbl_correo);
        txt_contaseña = findViewById(R.id.reg_lbl_contraseña);
        txt_confcontraseña = findViewById(R.id.reg_lbl_confcontraseña);
        btn_atras = findViewById(R.id.reg_btn_atras);
        btn_registrar = findViewById(R.id.reg_btn_registro);
        cbo_tipo_usuario = findViewById(R.id.sp_cbo_tipo_usuario);
        chk_terminos = findViewById(R.id.reg_chk_terminos);
        btn_atras.setOnClickListener(this);
        btn_registrar.setOnClickListener(this);

        elegir_usuario();
    }

    private void elegir_usuario() {
        cbo_tipo_usuario.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,
                new String[]{"--Elegir usuario--"}));
        AsyncHttpClient shc_cbo_tipousuario = new AsyncHttpClient();
        String s_url = "http://proyect-mascota-upn.atwebpages.com/ws/tipo-usuario.php";
        shc_cbo_tipousuario.post(s_url, new BaseJsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response) {
                if (statusCode == 200) {
                    try {
                        Log.i("MAIN_APP", "exito");
                        JSONArray jsonArray = new JSONArray(rawJsonResponse);
                        String[] s_tipousuarios = new String[jsonArray.length() + 1];
                        s_tipousuarios[0] = "--Elegir usuario--";
                        for (int i = 1; i < jsonArray.length() + 1; i++) {
                            s_tipousuarios[i] = jsonArray.getJSONObject(i - 1).getString("tipo_usuario");
                        }
                        cbo_tipo_usuario.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,
                                s_tipousuarios));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, Object errorResponse) {
                Toast.makeText(RegistroActivity.this, "Error" + statusCode, Toast.LENGTH_SHORT).show();
                Log.i("MAIN_APP", "fallo_servidor" + statusCode);
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                //Log.i("MAIN_APP","fallo_applicacion");

                return null;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reg_btn_atras:
                regresar();

                break;
            case R.id.reg_btn_registro:
                registrar_user();
                break;
        }
    }

    private void registrar_user() {
        if (!validar_formulario())
            return;
        //precedemos registrar usuario


    }

    private boolean validar_formulario() {
        if (cbo_tipo_usuario.getSelectedItemPosition() == 0 || txt_nombre.getText().toString().isEmpty() ||
                txt_apellidos.getText().toString().isEmpty() || txt_direccion.getText().toString().isEmpty() ||
                txt_telefono.getText().toString().isEmpty() || txt_correo.getText().toString().isEmpty() || txt_contaseña.getText().toString().isEmpty()
                || txt_confcontraseña.getText().toString().isEmpty()) {
            Toast.makeText(RegistroActivity.this, "Debe completar todos los campos", Toast.LENGTH_LONG).show();
            return false;
        }
        if (txt_telefono.getText().toString().length() < 9) {
            Toast.makeText(RegistroActivity.this, "El teléfono requiere mínimo 9 caracteres", Toast.LENGTH_LONG).show();
        }
        if (txt_contaseña.getText().toString().length() < 8) {
            Toast.makeText(RegistroActivity.this, "La contraseña requiere mínimo 8 caracteres", Toast.LENGTH_LONG).show();
            return false;
        }

        if (txt_contaseña.getText().toString().equals(txt_confcontraseña.getText().toString())) {
            Toast.makeText(RegistroActivity.this, "La contraseña no coinciden", Toast.LENGTH_LONG).show();
            return false;
        }
        if (chk_terminos.isChecked()) {
            Toast.makeText(RegistroActivity.this, "Debe Aceptar Términos yCondiciones", Toast.LENGTH_LONG).show();
            return false;
        } else {
            Toast.makeText(RegistroActivity.this, "Se registró con éxito", Toast.LENGTH_LONG).show();
            Intent i_login = new Intent(this, LoginActivity.class);
            startActivity(i_login);

        }
      return true;
    }

    private void regresar () {
        Intent i_mostrar = new Intent(getApplicationContext(), LoginActivity.class);
        finish();
        startActivity(i_mostrar);
    }
}