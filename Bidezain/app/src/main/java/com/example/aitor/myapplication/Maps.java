package com.example.aitor.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Maps implements OnMapReadyCallback {
    MainActivity main;
    TTS tts;
    GoogleMap map;
    boolean lehenengoa = true;

    public Maps(MainActivity main){
        this.main=main;
        tts = new TTS(main.getApplicationContext());
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        ArrayList<Incidencia> lista = main.lista;
        googleMap.clear();

        if(lehenengoa){
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(43.0307426, -2.3116051), 8.75f));
            lehenengoa = false;
        }

        for(int i=0;i<lista.size();i++){
            MarkerOptions marker = new MarkerOptions();
            marker.position(new LatLng(lista.get(i).lat, lista.get(i).lng)).title(lista.get(i).tipo)
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(main.common.getColor(lista.get(i).tipo)));
            googleMap.addMarker(marker);
        }
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                tts.speak(marker.getTitle());
                return true;
            }
        });
    }
}
