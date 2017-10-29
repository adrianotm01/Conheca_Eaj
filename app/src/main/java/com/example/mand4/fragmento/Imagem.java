package com.example.mand4.fragmento;

import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by mand4 on 14/10/2017.
 */

public class Imagem {

    private String nome;
    private int imagem;
    private String telefone;
    private LatLng latitudeLong;
    public Imagem(String nome, int imagem, String telefone,LatLng latitudeLong) {
        this.nome = nome;
        this.imagem = imagem;
        this.telefone = telefone;
        this.latitudeLong = latitudeLong;
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

    public LatLng getLatitudeLong() {
        return latitudeLong;
    }

    public void setLatitudeLong(LatLng latitudeLong) {
        this.latitudeLong = latitudeLong;
    }
}
