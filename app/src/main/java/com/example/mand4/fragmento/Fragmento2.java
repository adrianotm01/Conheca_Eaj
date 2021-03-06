package com.example.mand4.fragmento;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.renderscript.Double2;
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
    private TextView latitude,longitude,nome;
    private AlertDialog.Builder alertDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment2, container, false);
        nome = (TextView) getActivity().findViewById(R.id.fragmentotexto3);
        longitude = (TextView) getActivity().findViewById(R.id.longitude);
        latitude = (TextView) getActivity().findViewById(R.id.latitude);
        localizacao = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        flag = (TextView) getActivity().findViewById(R.id.textView);
         alertDialog =  new AlertDialog.Builder(getActivity());
        alertDialog.setMessage("Para continuar, permita que o dispositivo ative a localização que usa o serviço de localização");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getActivity().finish();
            }
        });
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
                googleMap.addMarker(new MarkerOptions().title(nome.getText().toString()).position(new LatLng(Double.parseDouble(latitude.getText().toString()),
                        Double.parseDouble(longitude.getText().toString()))));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(latitude.getText().toString()),
                        Double.parseDouble(longitude.getText().toString())), 18));
            }
            else{
                alertDialog.create();
                alertDialog.show();
            }
            if ((ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            }
        }else{
            googleMap.addMarker(new MarkerOptions().position(new LatLng(-5.885650,-35.364258)).title("EAJ"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-5.885650,-35.364258),18));
        }

    }


}
