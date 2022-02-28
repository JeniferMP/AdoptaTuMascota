package com.example.adoptatumascota.nav;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adoptatumascota.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgregarAnuncioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarAnuncioFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner cbo_especie, cbo_distrito, cbo_provincia;
    EditText txt_nombre, txt_raza, txt_descripcion, txt_edad;
    Button btn_elegir, btn_agregar;
    ImageView jiv_foto_mascota;

    private static final int REQUEST_CODE_PERMISSION = 1;
    private static final int REQUEST_CODE_GALLERY = 2;

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
        btn_agregar=v_agr_anun.findViewById(R.id.crear_anuncio_btn_agregar);
        btn_elegir= v_agr_anun.findViewById(R.id.crear_anuncio_btn_elegir);
        jiv_foto_mascota= v_agr_anun.findViewById(R.id.crear_anuncio_iv_foto_mascota);


        btn_agregar.setOnClickListener(this);
        btn_elegir.setOnClickListener(this);
        cbo_provincia.setOnItemSelectedListener(this);

        //llenar especie
        llenar_cbo("Especie","http://adopta-tu-mascota.atwebpages.com/WS/mostrar_controler.php", cbo_especie, "4");

        //llenar Provincia
        llenar_cbo("Provincia","http://adopta-tu-mascota.atwebpages.com/WS/mostrar_controler.php",cbo_provincia, "3");

        return v_agr_anun;
    }
    private void llenar_cbo( String nombre_cbo, String s_url, Spinner cbo, String tipo) {
        cbo.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,
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
                        s_arrayElement[0]="--Seleccione--"+ nombre_cbo;

                        for (int i=1; i<jsonArray.length()+1; i++){
                            s_arrayElement[i]= jsonArray.getJSONObject(i-1).getString("Prov_nombre");
                        }

                        cbo.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                s_arrayElement));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, Object errorResponse) {
                Toast.makeText(getContext(), "Error"+statusCode, Toast.LENGTH_LONG).show();
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return null;
            }
        });
    }

    private void llenar_ditritos(int id_prov) {
        cbo_distrito.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Seleccione Distrito"}));
        AsyncHttpClient ahc_cbo= new AsyncHttpClient();
        String s_url= "http://adopta-tu-mascota.atwebpages.com/WS/mostrar_controler.php";
        RequestParams params= new RequestParams();
        params.add("tipo", "2");
        params.add("id_prov", String.valueOf(id_prov));

        ahc_cbo.post(s_url, params, new BaseJsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response) {
                if(statusCode==200){
                    try {
                        JSONArray jsonArray= new JSONArray(rawJsonResponse);
                        String[] s_arrayElement= new String[jsonArray.length()+1];
                        s_arrayElement[0]="--Seleccione Distrito--";

                        for (int i=1; i<jsonArray.length()+1; i++){
                            s_arrayElement[i]= jsonArray.getJSONObject(i-1).getString("Dist_nombre");
                        }

                        cbo_distrito.setAdapter(new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                s_arrayElement));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, Object errorResponse) {
                Toast.makeText(getContext(), "Error"+statusCode, Toast.LENGTH_LONG).show();
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return null;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.crear_anuncio_btn_agregar:
                guardar_anuncio();
                break;
            case R.id.crear_anuncio_btn_elegir:
                elegir_foto();
                break;
        }

    }

    private void elegir_foto(){

        requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_PERMISSION);
        Toast.makeText(getContext(), "imagen", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_PERMISSION){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent i_file_chooser = new Intent(Intent.ACTION_PICK);
                i_file_chooser.setType("image/*");
                startActivityForResult(i_file_chooser, REQUEST_CODE_GALLERY);
            }
            else
                Toast.makeText(getContext(), "No se puede acceder al almacenamiento externo", Toast.LENGTH_SHORT).show();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY){
            if(resultCode == Activity.RESULT_OK && data != null){
                Uri uri = data.getData();
                jiv_foto_mascota.setImageURI(uri);
            }
            else
                Toast.makeText(getContext(), "Debe elegir una imagen", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String image_view_to_base64(ImageView jiv_foto_anuncio) {
        Bitmap bitmap = ((BitmapDrawable)jiv_foto_anuncio.getDrawable()).getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        byte[] byteArray = stream.toByteArray();
        String imagen = Base64.encodeToString(byteArray, Base64.DEFAULT);

        return  imagen;
    }



    private void guardar_anuncio() {
        if(!validar_formulario())
            return;

        AsyncHttpClient ahc_anuncio= new AsyncHttpClient();
        String s_url= "http://adopta-tu-mascota.atwebpages.com/WS/agregar_anuncio.php";
        RequestParams params= new RequestParams();
        params.add("Esp_nombre", cbo_especie.getSelectedItem().toString().trim());
        params.add("Anun_nombre", txt_nombre.getText().toString().trim());
        params.add("Anun_raza", txt_nombre.getText().toString().trim());
        params.add("Anun_descripcion", txt_nombre.getText().toString().trim());
        params.add("Anun_edad", txt_nombre.getText().toString().trim());
        params.add("Dist_nombre", cbo_distrito.getSelectedItem().toString().trim());
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        params.add("Anun_fecha_public", date);
        params.add("Anun_imagen", image_view_to_base64(jiv_foto_mascota));

        ahc_anuncio.post(s_url, params, new BaseJsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Object response) {
                if(statusCode==200){
                   int i_te_val= rawJsonResponse.length()==0 ? 0 :Integer.parseInt(rawJsonResponse);

                   if(i_te_val==1){
                       Toast.makeText(getContext(), "Anuncio Registrado con éxito", Toast.LENGTH_LONG).show();

                       txt_nombre.setText("");
                       txt_raza.setText("");
                       txt_descripcion.setText("");
                       txt_edad.setText("");

                   }else{
                       Toast.makeText(getContext(), "Error al Registrar", Toast.LENGTH_LONG).show();
                   }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, Object errorResponse) {
                Toast.makeText(getContext(), "Error: "+ statusCode, Toast.LENGTH_LONG).show();
            }

            @Override
            protected Object parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return null;
            }
        });
    }

    private boolean validar_formulario(){
        if(cbo_especie.getSelectedItemPosition()==0 || txt_nombre.toString().isEmpty() || txt_raza.toString().isEmpty() ||
                txt_descripcion.toString().isEmpty() || txt_edad.toString().isEmpty() || cbo_distrito.getSelectedItemPosition()==0 || cbo_provincia.getSelectedItemPosition()==0){

            Toast.makeText(getContext(), "Debe completar todos los campos", Toast.LENGTH_LONG).show();
            return false;
        }
        if(cbo_especie.getSelectedItemPosition()==0){

            Toast.makeText(getContext(), "Falta seleccionar una especie", Toast.LENGTH_LONG).show();
            return false;
        }
        if( cbo_provincia.getSelectedItemPosition()==0){

            Toast.makeText(getContext(), "Falta seleccionar una provincia", Toast.LENGTH_LONG).show();
            return false;
        }
        if(cbo_distrito.getSelectedItemPosition()==0){

            Toast.makeText(getContext(), "Falta seleccionar distrito", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.crear_anuncio_spinner_provincia:
                llenar_ditritos(adapterView.getSelectedItemPosition());
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}