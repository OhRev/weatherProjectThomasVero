package org.example;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.util.concurrent.ExecutionException;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args)
            throws URISyntaxException, ExecutionException, InterruptedException {
        // Appel api

        URI line_api_url = new URI("https://api.openweathermap.org/data/2.5/weather?lat=35.6839" +
                "&lon=139.7744&appid=8118ed6ee68db2debfaaa5a44c832918");

        // creation client (objet qui permet de se connecter à un url)
        HttpClient client = HttpClient.newBuilder().build();

        // creation requete http a partir de l'uri
        HttpRequest request = HttpRequest.newBuilder().uri(line_api_url).GET().build();

        // demande au client de faire la requete
        JSONObject result = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).thenApply(JSONObject::new).get();
        JSONObject main = result.getJSONObject("main");
        float temp = main.getFloat("temp");


        System.out.println(main);
        System.out.println(temp);

    }
}