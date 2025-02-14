package com.example.biblioteis;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookViewHolder extends RecyclerView.ViewHolder {
    ImageView imgPortada;
    TextView tvTitulo, tvAutor;

    public BookViewHolder(View view) {
        super(view);
        this.imgPortada = view.findViewById(R.id.img_portada);
        this.tvTitulo = view.findViewById(R.id.tv_titulo);
        this.tvAutor = view.findViewById(R.id.tv_autor);
    }

    public ImageView getImgPortada() {
        return imgPortada;
    }

    public void setImgPortada(ImageView imgPortada) {
        this.imgPortada = imgPortada;
    }

    public TextView getTvTitulo() {
        return tvTitulo;
    }

    public void setTvTitulo(TextView tvTitulo) {
        this.tvTitulo = tvTitulo;
    }

    public TextView getTvAutor() {
        return tvAutor;
    }

    public void setTvAutor(TextView tvAutor) {
        this.tvAutor = tvAutor;
    }
}
