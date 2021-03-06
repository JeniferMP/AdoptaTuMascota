package com.example.adoptatumascota.nav;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.adoptatumascota.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnunciosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnunciosFragment extends Fragment implements View.OnClickListener{
    Spinner cbo_provincia, cbo_distrito;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AnunciosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnunciosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnunciosFragment newInstance(String param1, String param2) {
        AnunciosFragment fragment = new AnunciosFragment();
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
        View v_anun= inflater.inflate(R.layout.fragment_anuncios, container, false);
        cbo_distrito= v_anun.findViewById(R.id.anuncios_busq_cbo_distrito);
        cbo_provincia= v_anun.findViewById(R.id.anuncios_busq_cbo_provincia);


        cbo_provincia.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Seleccione provincia", "Cajamarca", "La Libertad", "Lima"}));

        cbo_distrito.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Seleccione distrito", "Florencia de Mora", "Trujillo", "El Porvenir"}));

        return v_anun;
    }

    @Override
    public void onClick(View view) {

    }
}