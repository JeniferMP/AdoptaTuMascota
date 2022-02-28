package com.example.adoptatumascota.nav;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.adoptatumascota.LoginActivity;
import com.example.adoptatumascota.R;
import com.example.adoptatumascota.SQLite.Sesion;
import com.example.adoptatumascota.clases.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CerrarSesionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CerrarSesionFragment extends Fragment implements View.OnClickListener{
    Usuario usuario;
    EditText txt_correo, txt_clave;
    Button btn_cerrarSesion;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CerrarSesionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CerrarSesionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CerrarSesionFragment newInstance(String param1, String param2) {
        CerrarSesionFragment fragment = new CerrarSesionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v_cerrar= inflater.inflate(R.layout.fragment_cerrar_sesion, container, false);
        txt_correo= v_cerrar.findViewById(R.id.cerrar_txt_usuario);
        txt_clave= v_cerrar.findViewById(R.id.cerrar_txt_contrasenia);
        btn_cerrarSesion= v_cerrar.findViewById(R.id.cerrar_btn_Salir);

        btn_cerrarSesion.setOnClickListener(this);

        /*Bundle datos= getArguments();
        usuario= (Usuario) datos.getSerializable("usuario");*/
        return v_cerrar;


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cerrar_btn_Salir:
                cerrar_sesion();
                break;
        }

    }

    private void cerrar_sesion() {
        /*Sesion sesion= new Sesion(getContext());
        sesion.eliminar_usuario(1);

        //destruir historial
        getActivity().finish();

        //cargar actividad Login
        Intent i_login= new Intent(getContext(), LoginActivity.class);
        startActivity(i_login);*/
    }
}