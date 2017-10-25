package com.example.mand4.fragmento;

import android.widget.TextView;

/**
 * Created by mand4 on 14/10/2017.
 */

public class Imagem {

    private String nome;
    private int imagem;
    private String telefone;
    public Imagem(String nome, int imagem,String telefone) {
        this.nome = nome;
        this.imagem = imagem;
        this.telefone = telefone;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
