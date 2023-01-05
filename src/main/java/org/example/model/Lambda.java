package org.example.model;


import org.example.api.Api;
import org.example.dao.TemperatureDao;
import org.example.dao.WorldcityDao;

import java.net.URISyntaxException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Lambda {

    public Coordonnees getCoordonees(String ville) {
        WorldcityDao wCDao = new WorldcityDao();
        WorldCity wc = wCDao.getWorldCityByCity(ville);
        return new Coordonnees(wc.getLat(), wc.getLng());
    }

    public float getTemperature(String ville) throws URISyntaxException, ExecutionException, InterruptedException {
        Coordonnees coordonnees = getCoordonees(ville);
        Api api = new Api();
        return api.getTemperature(coordonnees.getLat(), coordonnees.getLng());
    }

    public void printCoordonnees(String ville) {
        Coordonnees coordonnees = getCoordonees(ville);
        System.out.println("Les coordonnées de " + ville + " sont :\n" +
                "- latitude : " + coordonnees.getLat() + "\n" +
                "- longitude : " + coordonnees.getLng());
    }

    public float printTemperature(String ville) throws URISyntaxException, ExecutionException, InterruptedException {
        float temp = getTemperature(ville);
        System.out.println("La température actuelle de " + ville + " est de " + temp + "°C");
        return temp;
    }

    public void recordTemperature(String ville) throws URISyntaxException, ExecutionException, InterruptedException {
        float temp = printTemperature(ville);
        TemperatureDao tempDao = new TemperatureDao();
        Temperature temperature = new Temperature(Date.valueOf(LocalDate.now()), ville, temp);
        tempDao.create(temperature);
    }

    public void printTableTemperatures() {
        TemperatureDao tempDao = new TemperatureDao();
        List<Temperature> temps = tempDao.readAll();
        for (Temperature elt: temps) {
            System.out.println(elt);
        }
    }
}
