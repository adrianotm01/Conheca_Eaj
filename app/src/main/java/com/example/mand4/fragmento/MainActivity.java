package com.example.mand4.fragmento;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.*;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.w3c.dom.Document;

public class MainActivity extends AppCompatActivity {
    private String[] perdoe = {
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET
    };
//    private GoogleApiClient mGoogleApiClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager pager = (ViewPager) findViewById(R.id.page);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        TabLayout tab = (TabLayout) findViewById(R.id.tab);
        tab.setupWithViewPager(pager);
        tab.getTabAt(0).setText("Fragmento 1");
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < grantResults.length; i++) {
            if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                Toast.makeText(this, "Poxa", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onStart() {
       PermissionUtils.validar(this,0,perdoe);
        super.onStart();

    }

    @Override
    protected void onStop() {

        super.onStop();
    }

}
