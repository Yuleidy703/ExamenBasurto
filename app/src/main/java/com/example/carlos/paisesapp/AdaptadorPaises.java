package com.example.carlos.paisesapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.PlaceHolderView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdaptadorPaises extends PlaceHolderView.Adapter<AdaptadorPaises.ViewHolderPaises> implements View.OnClickListener {

    Context context;
    ArrayList<Pais> listaPaises;
    private View.OnClickListener listener;

    public AdaptadorPaises(ArrayList<Pais> listaPaises) {
        this.listaPaises = listaPaises;
    }

    @Override
    public ViewHolderPaises onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ly_item,null,false);
        view.setOnClickListener(this);
        return new ViewHolderPaises(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderPaises holder, int position) {
        holder.nombrePais.setText(listaPaises.get(position).getNombre());

        Glide.with(this.context)
                .load(listaPaises.get(position).getUrl())
                .into(holder.imagenPais);
    }

    public AdaptadorPaises(ArrayList<Pais> listaDatos, Context contexto){
        this.context = contexto;
        this.listaPaises = listaDatos;
    }

    @Override
    public int getItemCount() {
        return listaPaises.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    public class ViewHolderPaises extends RecyclerView.ViewHolder {
        TextView nombrePais;
        ImageView imagenPais;

        public ViewHolderPaises(final View itemView) {
            super(itemView);
            nombrePais=(TextView)itemView.findViewById(R.id.lblNombrePais);
            imagenPais=(ImageView)itemView.findViewById(R.id.imgBanderaPais);
        }
    }
}
