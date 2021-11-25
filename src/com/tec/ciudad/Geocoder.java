package com.tec.ciudad;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * The type Geocoder.
 */
public class Geocoder {
    //Elementos para la consulta
    private static final String GEOCODING_RESOURCE = "https://maps.googleapis.com/maps/api/geocode/json";// "https://geocode.search.hereapi.com/v1/geocode";
    private static final String API_KEY = "AIzaSyAb0Wf4iNY8hD8OsuUkIIJbjDpw7SbzRUI";

    /**
     * Geocode sync string.
     *
     * @param query the query
     * @return the string
     * @throws IOException          the io exception
     * @throws InterruptedException the interrupted exception
     */
    public String ObtenerDirecciones(String query) throws IOException, InterruptedException {
        //Se crea un cliente web para las solicitudes
        HttpClient httpClient = HttpClient.newHttpClient();
        String encodedQuery = URLEncoder.encode(query,"UTF-8");
        //Se crea una Uri para la solicitud
        String requestUri = GEOCODING_RESOURCE + "?key=" + API_KEY+ "&address=" + encodedQuery;
        //Se crea la solicitud con la Uri
        HttpRequest geocodingRequest = HttpRequest.newBuilder().GET().uri(URI.create(requestUri)).timeout(Duration.ofMillis(2000)).build();
        //Se realiza la solicitud
        HttpResponse geocodingResponse = httpClient.send(geocodingRequest, HttpResponse.BodyHandlers.ofString());
        //Se devuelve el json de la solicitud en formato de texto
        return (String) geocodingResponse.body();
    }

}


