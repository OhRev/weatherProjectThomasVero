package org.example.model;

import java.sql.Date;

public class Temperature {
    int id;
    Date timesstamp;
    String city;
    float temperature;

    public Temperature(Date timesstamp, String city, float temperature) {
        this.timesstamp = timesstamp;
        this.city = city;
        this.temperature = temperature;
    }
    public Temperature(int id, Date timesstamp, String city, float temperature) {
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

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
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
