package com.example.mand4.fragmento;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by mand4 on 14/10/2017.
 */

public class Fragmento2 extends Fragment implements LocationListener, OnMapReadyCallback {

    MapView mMapView;
    private GoogleMap googleMap;
    private LocationManager localizacao;
    private Location locationTeste;
    private LatLng latitudeMinha;
    private TextView flag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment2, container, false);

        localizacao = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        flag = (TextView) getActivity().findViewById(R.id.textView);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        locationTeste = localizacao.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(locationTeste != null)
            Log.i("acuracia", locationTeste.toString());
        else
            localizacao.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
    }
        mMapView.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location != null){
            Log.i("Localizacao",location.getLongitude()+" e "+ location.getLatitude());
            locationTeste = location;
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


    @Override
    public void onMapReady(GoogleMap mMap) {
        googleMap = mMap;
        googleMap.setMyLocationEnabled(true);
        if(flag.getText().toString().equals("true")) {
            if (locationTeste != null) {
                latitudeMinha = new LatLng(locationTeste.getLatitude(), locationTeste.getLongitude());
                getRoute(latitudeMinha,new LatLng(-5.885650, -35.364258));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latitudeMinha, 18));
            }
            if ((ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {

                if (locationTeste != null)
                    googleMap.addPolyline(new PolylineOptions().geodesic(true).add(latitudeMinha, new LatLng(-5.885650, -35.364258),
                            new LatLng(latitudeMinha.latitude + 0.15, latitudeMinha.longitude)));
            }
        }else{
            googleMap.addMarker(new MarkerOptions().position(new LatLng(-5.885650,-35.364258)).title("EAJ"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-5.885650,-35.364258),18));
        }

    }

    public void getRoute(LatLng origem, LatLng destino){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url= "http://maps.googleapis.com/maps/api/directions/json?origin="
                + origem.latitude+","+origem.longitude+"&destination="
                + destino.latitude+","+destino.longitude+"&sensor=false";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
               Log.i("resposta",response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        queue.add(stringRequest);
    }
}
