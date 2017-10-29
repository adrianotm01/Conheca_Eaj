package com.example.mand4.fragmento;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mand4 on 13/10/2017.
 */

public class Fragmento1 extends Fragment{
    private List<Imagem> list;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        listar();
        Log.i("tamanho",""+getFragmentManager().getFragments().size());
        View view = inflater.inflate(R.layout.sla,container,false);
        RecyclerView reciclador = view.findViewById(R.id.reciclador);
        RecycleAdapter adapter = new RecycleAdapter(getContext(),list);
        reciclador.setAdapter(adapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        reciclador.setLayoutManager(layout);
        RecicladorListener listener = new RecicladorListener(getContext(), reciclador, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int posicao) {
                TabLayout tab = (TabLayout)getActivity().findViewById(R.id.tab);
                ImageView view1 = (ImageView) getActivity().findViewById(R.id.imageView3) ;
                view1.setImageResource(list.get(posicao).getImagem());
                TextView imageView = (TextView) getActivity().findViewById(R.id.fragmentotexto3);
                imageView.setText(list.get(posicao).getNome());
                TextView flag = (TextView) getActivity().findViewById(R.id.textView);
                TextView longitude = (TextView) getActivity().findViewById(R.id.longitude);
                longitude.setText(""+list.get(posicao).getLatitudeLong().longitude);
                TextView latitude = (TextView) getActivity().findViewById(R.id.latitude);
                latitude.setText(""+list.get(posicao).getLatitudeLong().latitude);
                flag.setText("true");
                tab.getTabAt(1).select();

            }
        });

        reciclador.addOnItemTouchListener(listener);
        return view;
    }

    public void listar(){
        list = new ArrayList<>();
        list.add(new Imagem("Informática",R.drawable.teste,"(84) 3342-2216",new LatLng(-5.885786, -35.365748)));
        list.add(new Imagem("CVT",R.drawable.eaj,"(84) 3342-4815", new LatLng(-5.884567, -35.364924)));
        list.add(new Imagem("Aquicultura",R.drawable.aquicultura,"(84) 3213-5214", new LatLng(-5.887602, -35.361685)));
        list.add(new Imagem("Biblioteca",R.drawable.biblioteca,"(84) 3214-5123",new LatLng(-5.885911, -35.366131)));
        list.add(new Imagem("Ensino Médio",R.drawable.ensinomeido,"(84) 3246-5482",new LatLng(-5.885205, -35.364782)));

    }
}
