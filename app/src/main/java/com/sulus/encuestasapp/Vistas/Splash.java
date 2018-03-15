package com.sulus.encuestasapp.Vistas;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Splash extends AppCompatActivity {
    private static final int MY_PERMISSION_REQUEST_FINE_LOCATION = 101;
    private static final int MY_PERMISSION_REQUEST_COARSE_LOCATION = 102;
    private boolean permissionIsGranted = false;


    public static final String TAG = Splash.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permissionsNeeded = new ArrayList<String>();

            final List<String> permissionsList = new ArrayList<String>();
            if (!addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
                permissionsNeeded.add("GPS");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (permissionsList.size() > 0) {
                    if (permissionsNeeded.size() > 0) {
                        // Need Rationale
                        String message = "Necesitas darle acceso al " + permissionsNeeded.get(0);
                        for (int j = 1; j < permissionsNeeded.size(); j++)
                            message = message + ", " + permissionsNeeded.get(j);
                        showMessageOKCancel(message,
                                new DialogInterface.OnClickListener() {
                                    @RequiresApi(api = Build.VERSION_CODES.M)
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                                                MY_PERMISSION_REQUEST_FINE_LOCATION);
                                    }
                                });
                        return;
                    }
                    requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                            MY_PERMISSION_REQUEST_FINE_LOCATION);

                             /**/
                    return;
                }
                int secondsDelayed = 1;
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        Intent i;
                        SharedPreferences pref = getSharedPreferences("Encuestador", Context.MODE_PRIVATE);
                        if (pref.contains("usuario") && pref.contains("pass")) {
                            i = new Intent(Splash.this,
                                    com.sulus.encuestasapp.Vistas.ActividadControl.class);
                        } else {
                            i = new Intent(Splash.this,
                                    com.sulus.encuestasapp.Vistas.MainActivity.class);
                        }
                        startActivity(i);
                        finish();
                /**/


                    }
                }, secondsDelayed * 1000);


            } else {
                permissionIsGranted = true;
            }


        } else {
            permissionIsGranted = true;
        }


    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean addPermission(List<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!shouldShowRequestPermissionRationale(permission)) return false;
        }
        return true;
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                    permissionIsGranted = true;

                    int secondsDelayed = 1;
                    new Handler().postDelayed(new Runnable() {
                        public void run() {

                            Intent i;
                            SharedPreferences pref = getSharedPreferences("Encuestador", Context.MODE_PRIVATE);
                            if (pref.contains("usuario") && pref.contains("pass")) {
                                i = new Intent(Splash.this,
                                        com.sulus.encuestasapp.Vistas.ActividadControl.class);
                            } else {
                                i = new Intent(Splash.this,
                                        com.sulus.encuestasapp.Vistas.MainActivity.class);
                            }
                            startActivity(i);
                            finish();
                /**/


                        }
                    }, secondsDelayed * 1000);


                } else {
                    //permission denied
                    permissionIsGranted = false;
                    Toast.makeText(getApplicationContext(), "Este telefono requiere activar el GPS para funcionar", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            case MY_PERMISSION_REQUEST_COARSE_LOCATION:
                // do something for coarse location
                break;
        }
    }
}
