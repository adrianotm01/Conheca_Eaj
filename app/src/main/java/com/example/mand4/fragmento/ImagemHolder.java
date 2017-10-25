package com.example.mand4.fragmento;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mand4 on 14/10/2017.
 */

public class ImagemHolder extends RecyclerView.ViewHolder {

    private TextView nome;
    private ImageView img;
    private TextView fone;
    public ImagemHolder(View view) {
        super(view);
        this.nome = view.findViewById(R.id.nome);
        this.img = view.findViewById(R.id.telefoneImagem);
        this.fone = view.findViewById(R.id.fone);
    }

    public TextView getNome() {
        return nome;
    }

    public void setNome(TextView nome) {
        this.nome = nome;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public TextView getFone() {
        return fone;
    }

    public void setFone(TextView fone) {
        this.fone = fone;
    }
}
