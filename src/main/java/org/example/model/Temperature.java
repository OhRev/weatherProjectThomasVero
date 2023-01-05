package org.example.model;

import java.util.Date;

public class Temperature {
    int id;
    Date timesstamp;
    String city;
    double temperature;

    public Temperature(int id, Date timesstamp, String city, double temperature) {
        this.id = id;
        this.timesstamp = timesstamp;
        this.city = city;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimesstamp() {
        return timesstamp;
    }

    public void setTimesstamp(Date timesstamp) {
        this.timesstamp = timesstamp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "id=" + id +
                ", timesstamp=" + timesstamp +
                ", city='" + city + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}
