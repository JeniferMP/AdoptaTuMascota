package com.example.adoptatumascota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adoptatumascota.clases.Hash;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.security.Permission;

import cz.msebera.android.httpclient.Header;


public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txt_nombre, txt_apellidos, txt_direccion, txt_telefono, txt_correo, txt_contasenia, txt_confcontrasenia;
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
        txt_contasenia = findViewById(R.id.reg_lbl_contrasenia);
        txt_confcontrasenia = findViewById(R.id.reg_lbl_confcontrasenia);
        btn_atras = findViewById(R.id.reg_btn_atras);
        btn_registrar = findViewById(R.id.reg_btn_registro);
        cbo_tipo_usuario = findViewById(R.id.sp_cbo_tipo_usuario);
        chk_terminos = findViewById(R.id.reg_chk_terminos);

        btn_atras.setOnClickListener(this);
        btn_registrar.setOnClickListener(this);


        //llenar tipos de usuario
        llenar_cbo("usuario","http://adopta-tu-mascota.atwebpages.com/WS/mostrar_controler.php", cbo_tipo_usuario, "1");
    }

    private void llenar_cbo( String nombre_cbo, String s_url, Spinner cbo, String tipo) {
        cbo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Seleccione "+ nombre_cbo}));

        AsyncHttpClient ahc_cbo= new AsyncHttpClient();
        RequestParams params= new RequestParams();
        params.add("tipo", tipo);
        ahc_cbo.post(s_url,params, new BaseJsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response) {
                if(statusCode==200){
                    try {
                        JSONArray jsonArray= new JSONArray(rawJsonResponse);
                        String[] s_arrayElement= new String[jsonArray.length()+1];
                        s_arrayElement[0]="--Seleccione"+ nombre_cbo+"--";

                        for (int i=1; i<jsonArray.length()+1; i++){
                            s_arrayElement[i]= jsonArray.getJSONObject(i-1).getString("Rol_nombre");
                        }

                        cbo.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                s_arrayElement));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, Object errorResponse) {
                Toast.makeText( getApplicationContext(), "Error"+statusCode, Toast.LENGTH_LONG).show();
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
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
        AsyncHttpClient ahc_usuario= new AsyncHttpClient();
        String s_url= "http://adopta-tu-mascota.atwebpages.com/WS/agregar_usuario.php";
        RequestParams params= new RequestParams();
        params.add("Rol_ID", String.valueOf(cbo_tipo_usuario.getSelectedItemPosition()));
        params.add("Usu_nombre", txt_nombre.getText().toString().trim());
        params.add("Usu_apellidos", txt_apellidos.getText().toString().trim());
        params.add("Usu_telefono", txt_telefono.getText().toString().trim());
        params.add("Usu_direccion", txt_direccion.getText().toString().trim());
        params.add("Usu_correo", txt_correo.getText().toString().trim());
        Hash hash= new Hash();
        String clave= hash.StringToHash (txt_contasenia.getText().toString().trim(), "SHA1");
        params.add("Usu_contrasenia",clave);

        ahc_usuario.post(s_url, params, new BaseJsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response) {
                if(statusCode==200){
                    int i_te_val = rawJsonResponse.length() == 0 ? 0 : Integer.parseInt(rawJsonResponse);

                    if(i_te_val==1){
                        Toast.makeText(getApplicationContext(), "Usuario Registrado con éxito", Toast.LENGTH_LONG).show();
                        Intent i_mostrar = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i_mostrar);
                        finish();

                    }else{
                        Toast.makeText(getApplicationContext(), "Error al Registrar", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, Object errorResponse) {
                Toast.makeText(getApplicationContext(), "Error: "+ statusCode, Toast.LENGTH_LONG).show();
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return null;
            }
        });
    }

    private boolean validar_formulario() {
        if (cbo_tipo_usuario.getSelectedItemPosition() == 0 || txt_nombre.getText().toString().isEmpty() ||
                txt_apellidos.getText().toString().isEmpty() || txt_direccion.getText().toString().isEmpty() ||
                txt_telefono.getText().toString().isEmpty() || txt_correo.getText().toString().isEmpty() || txt_contasenia.getText().toString().isEmpty()
                || txt_confcontrasenia.getText().toString().isEmpty()) {
            Toast.makeText(RegistroActivity.this, "Debe completar todos los campos", Toast.LENGTH_LONG).show();
            return false;
        }
        if (txt_telefono.getText().toString().length() < 9) {
            Toast.makeText(RegistroActivity.this, "El teléfono requiere mínimo 9 caracteres", Toast.LENGTH_LONG).show();
            return false;
        }
        if (txt_contasenia.getText().toString().length() < 8) {
            Toast.makeText(RegistroActivity.this, "La contraseña requiere mínimo 8 caracteres", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!(txt_contasenia.getText().toString().trim().equals(txt_confcontrasenia.getText().toString().trim()))) {
            Toast.makeText(RegistroActivity.this, "La contraseña no coinciden", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!(chk_terminos.isChecked())) {
            Toast.makeText(RegistroActivity.this, "Debe Aceptar Términos yCondiciones", Toast.LENGTH_LONG).show();
            return false;
        }
      return true;
    }

    private void regresar () {
        Intent i_mostrar = new Intent(getApplicationContext(), LoginActivity.class);
        finish();
        startActivity(i_mostrar);
    }
}