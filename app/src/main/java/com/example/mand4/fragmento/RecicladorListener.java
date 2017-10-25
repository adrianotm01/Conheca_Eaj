package com.example.mand4.fragmento;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by mand4 on 17/10/2017.
 */

public class RecicladorListener implements RecyclerView.OnItemTouchListener{
    private OnItemClickListener meuListener;
    private GestureDetector gestureDetector;

    public RecicladorListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {

        meuListener = listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View view = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(view != null && meuListener != null) {
                    meuListener.onItemClick(view, recyclerView.getChildAdapterPosition(view));
                }
                super.onSingleTapUp(e);
                Log.i("UMA TAPA","TESTE");
                return true;
            }

        });

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

}
