package com.example.adoptatumascota.nav;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adoptatumascota.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgregarAnuncioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarAnuncioFragment extends Fragment implements View.OnClickListener{

    Spinner cbo_especie, cbo_distrito, cbo_provincia;
    EditText txt_nombre, txt_raza, txt_descripcion, txt_edad;
    Button btn_guardar;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AgregarAnuncioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgregarAnuncioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgregarAnuncioFragment newInstance(String param1, String param2) {
        AgregarAnuncioFragment fragment = new AgregarAnuncioFragment();
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
        View v_agr_anun= inflater.inflate(R.layout.fragment_agregar_anuncio, container, false);
        cbo_especie= v_agr_anun.findViewById(R.id.crear_anuncio_spinner_especie);
        cbo_distrito=v_agr_anun.findViewById(R.id.crear_anuncio_spinner_distrito);
        cbo_provincia=v_agr_anun.findViewById(R.id.crear_anuncio_spinner_provincia);
        txt_nombre=v_agr_anun.findViewById(R.id.crear_anuncio_txt_nombre);
        txt_raza=v_agr_anun.findViewById(R.id.crear_anuncio_txt_raza);
        txt_descripcion=v_agr_anun.findViewById(R.id.crear_anuncio_txt_descripcion);
        txt_edad=v_agr_anun.findViewById(R.id.crear_anuncio_txt_edad);
        btn_guardar=v_agr_anun.findViewById(R.id.crear_anuncio_btn_guardar);


        btn_guardar.setOnClickListener(this);

        cbo_especie.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Seleccione especie", "Perro", "Gato", "Caballo"}));

        cbo_provincia.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Seleccione provincia", "Cajamarca", "La Libertad", "Lima"}));

        cbo_distrito.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Seleccione distrito", "Florencia de Mora", "Trujillo", "El Porvenir"}));
        return v_agr_anun;
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
            Toast.makeText(getContext(), "Debe completar todos los campos", Toast.LENGTH_LONG).show();
        }
    }
}