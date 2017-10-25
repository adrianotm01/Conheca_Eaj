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
                flag.setText("true");
                tab.getTabAt(1).select();

            }
        });

        reciclador.addOnItemTouchListener(listener);
        return view;
    }

    public void listar(){
        list = new ArrayList<>();
        list.add(new Imagem("INSTITUTO METROPOLE DIGITAL",R.drawable.teste,"(84) 3342-2216"));
        list.add(new Imagem("ESCOLA AGRICOLA JUNDIAI",R.drawable.eaj,"(84) 3342-4815"));

    }
}
