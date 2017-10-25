package com.example.mand4.fragmento;

import android.widget.TextView;

/**
 * Created by mand4 on 14/10/2017.
 */

public class Imagem {

    private String nome;
    private int imagem;

    public Imagem(String nome, int imagem) {
        this.nome = nome;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
