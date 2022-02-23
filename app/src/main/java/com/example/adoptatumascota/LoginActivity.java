package com.example.adoptatumascota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txt_usuario, txt_contrasenia;
    Button btn_ingresar;
    CheckBox chk_recordar;
    TextView lbl_registro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt_usuario= findViewById(R.id.login_txt_usuario);
        txt_contrasenia= findViewById(R.id.login_txt_contrasenia);
        chk_recordar= findViewById(R.id.login_chk_recordar);
        btn_ingresar= findViewById(R.id.login_btn_ingresar);
        lbl_registro= findViewById(R.id.login_lbl_registrate);

        btn_ingresar.setOnClickListener(this);
        lbl_registro.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn_ingresar:
                iniciar_sesion(txt_usuario.getText().toString().trim(), txt_contrasenia.getText().toString().trim());
                break;
            case R.id.login_lbl_registrate:
                registrar_usuario();
                break;
        }
    }


    private void iniciar_sesion(String usuario, String contrasenia) {
        if (chk_recordar.isChecked())
            Toast.makeText(this,"Recordó sesión",Toast.LENGTH_SHORT).show();
        if (usuario.equals("Adoptante")&& contrasenia.equals("12345678")){
            Intent i_principal= new Intent(this, MenuAdoptanteActivity.class);
            startActivity(i_principal);
            finish();
        }else {
            Toast.makeText(this,"Credenciales Incorrectas",Toast.LENGTH_LONG).show();
        }

    }

    private void registrar_usuario() {
        Intent i_registro= new Intent(this, RegistroActivity.class);
        startActivity(i_registro);
    }


}