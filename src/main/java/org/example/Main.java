package org.example;

import java.net.*;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import org.example.model.Lambda;

public class Main {
    public static void main(String[] args)
            throws URISyntaxException, ExecutionException, InterruptedException {
         //Appel api

//        URI line_api_url = new URI("https://api.openweathermap.org/data/2.5/weather?lat=35.6839" +
//                "&lon=139.7744&appid=8118ed6ee68db2debfaaa5a44c832918");
//
//        // creation client (objet qui permet de se connecter à un url)
//        HttpClient client = HttpClient.newBuilder().build();
//
//        // creation requete http a partir de l'uri
//        HttpRequest request = HttpRequest.newBuilder().uri(line_api_url).GET().build();
//
//        // demande au client de faire la requete
//        JSONObject result = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(HttpResponse::body).thenApply(JSONObject::new).get();
//        JSONObject main = result.getJSONObject("main");
//        float temp = main.getFloat("temp");
//
//
//        System.out.println(main);
//        System.out.println(temp);

        /*float lat = 35.6839f;
        float lng = 139.7744f;
        Api api = new Api();
        System.out.println(api.getTemperature(lat, lng) + "°C");
        System.out.println("******");

        Date date = new Date(114,8,20);
        Temperature temperature = new Temperature(date, "Paris", 0.0f);
        TemperatureDao tempDao = new TemperatureDao();
        tempDao.create(temperature);
        System.out.println(tempDao.readById(4));*/
        System.out.println("Démarage du truc, entrez 0 pour arrêter");
        Lambda l = new Lambda();
        String entree = "";
        while(!entree.equals("0")) {
            System.out.println("""
                    Entrez :
                     - 0 pour arrêter - 1 pour afficher les coordonnées d'une ville
                     - 2 pour afficher la température actuelle d'une la ville
                     - 3 pour afficher et enregistrer la température d'une la ville
                     - 4 pour afficher les températures enregistrées""");
            Scanner sc = new Scanner(System.in);
            entree = sc.nextLine();
            switch (entree) {
                case "1" -> {
                    System.out.println("entrez un nom de ville");
                    l.printCoordonnees(sc.nextLine());
                }
                case "2" -> {
                    System.out.println("entrez un nom de ville");
                    l.printTemperature(sc.nextLine());
                }
                case "3" -> {
                    System.out.println("entrez un nom de ville");
                    l.recordTemperature(sc.nextLine());
                }
                case "4" -> {
                    System.out.println("Affichage table temperature");
                    l.printTableTemperatures();
                }
                case "0" -> System.out.println("Le service va se fermer");
                default -> System.out.println("L'entrée ne correspond pas au format demandé");
            }


            if(!entree.equals("0"))
                System.out.println("\n\nVous pouvez effectuer une autre demande.");
        }

    }

}