package com.example.adoptatumascota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adoptatumascota.SQLite.Sesion;
import com.example.adoptatumascota.clases.Hash;
import com.example.adoptatumascota.clases.Usuario;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txt_correo, txt_contrasenia;
    Button btn_ingresar;
    CheckBox chk_recordar;
    TextView lbl_registro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt_correo= findViewById(R.id.login_txt_usuario);
        txt_contrasenia= findViewById(R.id.login_txt_contrasenia);
        chk_recordar= findViewById(R.id.login_chk_recordar);
        btn_ingresar= findViewById(R.id.login_btn_ingresar);
        lbl_registro= findViewById(R.id.login_lbl_registrate);

        btn_ingresar.setOnClickListener(this);
        lbl_registro.setOnClickListener(this);
        validar_si_recordo_sesion();
    }

    private void validar_si_recordo_sesion() {
        Sesion sesion= new Sesion(getApplicationContext());
        if(sesion.recordo_sesion()){
            iniciar_sesion(sesion.extraer_usuario("correo"),
                    sesion.extraer_usuario("clave"),true);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn_ingresar:
                iniciar_sesion(txt_correo.getText().toString().trim(), txt_contrasenia.getText().toString().trim(),false);
                break;
            case R.id.login_lbl_registrate:
                registrar_usuario();
                break;
        }
    }


    private void iniciar_sesion(String correo, String contrasenia, boolean b_recordo) {

        Hash hash= new Hash();
        contrasenia= (b_recordo==true? contrasenia:hash.StringToHash(contrasenia, "SHA1"));

        AsyncHttpClient ahc_login = new AsyncHttpClient();
        String s_url= "http://adopta-tu-mascota.atwebpages.com/WS/login.php";
        RequestParams params= new RequestParams();
        params.add("correo", correo);
        params.add("clave", contrasenia);
        ahc_login.post(s_url, params, new BaseJsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response) {
                if(statusCode==200){
                    try {
                        JSONArray jsonArray= new JSONArray(rawJsonResponse);
                        if(jsonArray.length()>0){
                            int usuario_id= jsonArray.getJSONObject(0).getInt("Usuario_ID");
                            if (usuario_id==-1){
                                Toast.makeText(getApplicationContext(),"Credenciales Incorrectas",Toast.LENGTH_LONG).show();

                            }else{
                                //guardar los datos del json en la clase
                                Usuario usuario = new Usuario();
                                usuario.setUsuario_ID(usuario_id);
                                usuario.setRol_ID(jsonArray.getJSONObject(0).getInt("Rol_ID"));
                                usuario.setUsu_nombre(jsonArray.getJSONObject(0).getString("Usu_nombre"));
                                usuario.setUsu_apellidos(jsonArray.getJSONObject(0).getString("Usu_apellidos"));
                                usuario.setUsu_telefono(jsonArray.getJSONObject(0).getString("Usu_telefono"));
                                usuario.setUsu_direccion(jsonArray.getJSONObject(0).getString("Usu_direccion"));
                                usuario.setUsu_correo(jsonArray.getJSONObject(0).getString("Usu_correo"));
                                usuario.setUsu_contrasenia(jsonArray.getJSONObject(0).getString("Usu_contrasenia"));
                                if (chk_recordar.isChecked()){
                                    Sesion sesion= new Sesion(getApplicationContext());
                                    sesion.agregar_usuario(usuario.getUsuario_ID(),usuario.getUsu_correo(), usuario.getUsu_contrasenia() );

                                    Toast.makeText(getApplicationContext(),"Recordó sesión",Toast.LENGTH_SHORT).show();
                                }

                                Intent menu= new Intent(getApplicationContext(), MenuAdoptanteActivity.class);
                                menu.putExtra("usuario",usuario);//nombre del argumento, valor
                                startActivity(menu);
                                finish();
                            }
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, Object errorResponse) {

            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return null;
            }
        });


    }

    private void registrar_usuario() {
        Intent i_registro= new Intent(this, RegistroActivity.class);
        startActivity(i_registro);
    }


}