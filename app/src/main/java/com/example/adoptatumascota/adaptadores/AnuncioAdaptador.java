package com.example.adoptatumascota.adaptadores;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adoptatumascota.DetalleAnuncioFragment;
import com.example.adoptatumascota.R;
import com.example.adoptatumascota.clases.Anuncio;

import org.w3c.dom.Text;

import java.util.List;

public class AnuncioAdaptador extends RecyclerView.Adapter<AnuncioAdaptador.ViewHolder>  {

    private List<Anuncio> lista_anuncios;

    public AnuncioAdaptador(List<Anuncio>lista_anuncios){
        this.lista_anuncios = lista_anuncios;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anuncio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Anuncio anuncio= lista_anuncios.get(position);
        holder.lbl_anunciante.setText(anuncio.getAnunciante());
        holder.lbl_direccion.setText(anuncio.getDireccion());
        holder.lbl_descripcion.setText(anuncio.getDescripcion());
        holder.lbl_telefono.setText(anuncio.getTelefono());
        holder.lbl_fecha.setText(anuncio.getFecha());
        String imagen = anuncio.getImagen();
        byte[] image_byte = Base64.decode(imagen, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image_byte, 0, image_byte.length);
        holder.iv_foto_anuncio.setImageBitmap(bitmap);

        holder.btn_ver_mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return lista_anuncios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lbl_anunciante, lbl_direccion, lbl_descripcion, lbl_telefono, lbl_fecha;
        ImageView iv_foto_anuncio;
        Button btn_ver_mas;
        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
        super(itemView);

        lbl_anunciante= itemView.findViewById(R.id.item_lbl_anunciante);
        lbl_direccion= itemView.findViewById(R.id.item_lbl_direccion);
        lbl_descripcion= itemView.findViewById(R.id.item_lbl_descripcion);
        lbl_telefono= itemView.findViewById(R.id.item_lbl_telefono);
        lbl_fecha= itemView.findViewById(R.id.item_lbl_fecha);
        iv_foto_anuncio= itemView.findViewById(R.id.iv_item_anuncio);
        btn_ver_mas= itemView.findViewById(R.id.item_btn_ver_mas);
        }
    }

}
