package com.example.mand4.fragmento;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by mand4 on 14/10/2017.
 */

public class RecycleAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<Imagem> list;
    public RecycleAdapter(Context context,List<Imagem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImagemHolder im = (ImagemHolder) holder;
        im.getNome().setText(list.get(position).getNome());
        im.getImg().setImageResource(list.get(position).getImagem());
    }

    public int getItemCount(){
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.inflar,parent,false);
        ImagemHolder holder = new ImagemHolder(v);
        return holder;
    }
}
