package org.example.model;


import org.example.dao.WorldcityDao;

public class Lambda {

    public Coordonnees getCoordonees(String ville) {
        WorldcityDao wCDao = new WorldcityDao();
        WorldCity wc = wCDao.getWorldCityByCity(ville);
        return new Coordonnees(wc.getLat(), wc.getLng());
    }

    public void printCoordonnees(String ville) {
        Coordonnees coordonnees = getCoordonees(ville);
        System.out.println("Les coordonnées de " + ville + " sont :\n" +
                "- latitude : " + coordonnees.getLat() + "\n" +
                "- longitude : " + coordonnees.getLng());
    }

    public void printTemperature(String ville) {
        Coordonnees coordonnees = getCoordonees(ville);

    }
}
