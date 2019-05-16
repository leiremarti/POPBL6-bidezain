package com.example.aitor.myapplication;

public class Incidencia{
    float lat;
    float lng;
    String tipo;

    public Incidencia(float lat, float lng, String tipo) {
        this.lat = lat;
        this.lng = lng;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "incidencia{" +
                "lat=" + lat +
                ", lng=" + lng +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
