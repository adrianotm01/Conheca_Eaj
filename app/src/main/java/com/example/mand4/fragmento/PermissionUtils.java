package com.example.mand4.fragmento;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mand4 on 19/10/2017.
 */

public class PermissionUtils {

    public static boolean validar(Activity atividade, int requestCode, String... permissions){
        List<String> permissoesSolicitar = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if ((ContextCompat.checkSelfPermission(atividade, permissions[i]) != PackageManager.PERMISSION_GRANTED)) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(atividade,permissions[i])){
                    Toast.makeText(atividade, "teste", Toast.LENGTH_SHORT).show();
                    permissoesSolicitar.add(permissions[i]);
                }
                else{
                    permissoesSolicitar.add(permissions[i]);
                }
            }
        }
        if (permissoesSolicitar.isEmpty())
            return true;

        String[] novasPermissoes = new String[permissoesSolicitar.size()];
        novasPermissoes = permissoesSolicitar.toArray(novasPermissoes);

        ActivityCompat.requestPermissions(atividade,novasPermissoes,1);
            return false;
    }

}
