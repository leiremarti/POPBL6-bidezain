package com.example.aitor.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.util.ArrayList;

public class Common {

    MainActivity main;
    float lat,lng = 0;
    LocationListener locationListener;
    LocationManager lm;
    boolean changed = false;

    public Common(MainActivity main){
        this.main = main;
        locationListener = new MyLocationListener();
        lm = (LocationManager) main.getSystemService(Context.LOCATION_SERVICE);
        if(checkLocationPermission()){
            lm.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        }

    }

    public ArrayList<Incidencia> parseXML(String s){
        ArrayList<Incidencia> lista = new ArrayList<>();
        String aux = s.split("<raiz>")[1].split("</raiz>")[0];

        String[] vec = aux.split("<incidenciaGeolocalizada>");
        String[] temp = new String[vec.length];
        int cont = 0;
        for (int i = 1; i < vec.length; i++, cont++) {
            temp[cont] = vec[i].split("</incidenciaGeolocalizada>")[0];
        }
        for (int i = 0; i < temp.length - 1; i++, cont++) {
            String pob = "No definido";
            String tipo = temp[i].split("<tipo>")[1];
            tipo = tipo.split("</tipo>")[0];
            String lat = temp[i].split("<latitud>")[1];
            lat = lat.split("</latitud>")[0];
            String lng = temp[i].split("<longitud>")[1];
            lng = lng.split("</longitud>")[0];
            lista.add(new Incidencia(Float.parseFloat(lat), Float.parseFloat(lng), tipo));
        }

        return lista;
    }


    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(main,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(main,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(main)
                        .setTitle("Baimenak")
                        .setMessage("Emaidazu baimena please")
                        .setPositiveButton("Ondo da", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(main,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions(main,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    public float getColor(String s){
        float a;
        switch(s){
            case "Accidente": a = BitmapDescriptorFactory.HUE_AZURE; break;
            case "Seguridad vial": a = BitmapDescriptorFactory.HUE_MAGENTA; break;
            case "Obras": a = BitmapDescriptorFactory.HUE_GREEN; break;
            case "Pruebas deportivas": a = BitmapDescriptorFactory.HUE_ORANGE; break;
            case "Puertos de montaña": a = BitmapDescriptorFactory.HUE_VIOLET; break;
            case "Vialidad invernal tramos": a = BitmapDescriptorFactory.HUE_YELLOW; break;
            case "Otras incidencias": a = BitmapDescriptorFactory.HUE_ROSE; break;
            default: a = BitmapDescriptorFactory.HUE_MAGENTA; break;
        }
        return a;
    }

    public Location getLocation(){
        Location location = null;
        if(checkLocationPermission()) {
            location = new Location("Location");
            if (changed) {
                location.setLatitude(lat);
                location.setLongitude(lng);
            } else {
                if(lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)!=null){
                    location.setLatitude((float) lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude());
                    location.setLongitude((float) lm.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude());
                }
            }
        }
        return location;
    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {
            lat = (float) loc.getLatitude();
            lng = (float) loc.getLongitude();
            changed = true;
        }

        @Override
        public void onProviderDisabled(String provider) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    }
}
