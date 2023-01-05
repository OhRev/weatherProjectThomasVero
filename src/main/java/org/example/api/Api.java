package org.example.api;

import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

public class Api {

    public float getTemperature(float lat, float lng)
            throws URISyntaxException, ExecutionException, InterruptedException {
        //Appel api

        URI line_api_url = new URI("https://api.openweathermap.org/data/2.5/weather?lat=" + lat +
                "&lon=" + lng + "&appid=8118ed6ee68db2debfaaa5a44c832918");

        // creation client (objet qui permet de se connecter à un url)
        HttpClient client = HttpClient.newBuilder().build();

        // creation requete http a partir de l'uri
        HttpRequest request = HttpRequest.newBuilder().uri(line_api_url).GET().build();

        // demande au client de faire la requete
        JSONObject result = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).thenApply(JSONObject::new).get();
        JSONObject main = result.getJSONObject("main");
        float temp = main.getFloat("temp");
        return (temp - 273.15f);
    }

}
