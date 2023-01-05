package org.example.model;

public class Coordonnees {
    private float lat;
    private float lng;

    public Coordonnees(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }
}
