package com.tec.ciudad;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Clase que crea objetos de tipo Ciudad
 */
public class Ciudad {
    private String nombre;
    private double latitud;
    private double longitud;
    private List<String> listalugares = new ArrayList();

    /**
     * Instantiates a new Ciudad.
     *
     * @param _nombre the nombre
     * @throws IOException          the io exception
     * @throws InterruptedException the interrupted exception
     */
    public Ciudad(String _nombre) throws IOException, InterruptedException {
        //Se recibe el nombre de la ciudad
        this.nombre = _nombre;
        //Se crean dos instancias, mapper para procesar el Json recibido de Geocoder
        ObjectMapper mapper = new ObjectMapper();
        Geocoder geocoder = new Geocoder();
        //Se realiza la solicitud y se obtienen las direcciones
        String response = geocoder.ObtenerDirecciones("Costa Rica, "+nombre);
        //Se mapea la respuesta para darle formato de json
        JsonNode responseJsonNode = mapper.readTree(response);
        LugaresCiudad pruebalug = new LugaresCiudad();
        JsonNode results = responseJsonNode.get("results");
        //Se navega en el Json para obtener las coordenadas de la ciudad
        try {
            JsonNode adressc = results.get(0);
            JsonNode geometry = adressc.get("geometry");
            JsonNode location = geometry.get("location");
            //Se le asigna a la ciudad los atributos de latitud y longitud
            this.latitud = Double.parseDouble(location.get("lat").asText());
            this.longitud = Double.parseDouble(location.get("lng").asText());
            //Con la latitud y longitud se optiene una lista con los lugares cercanos
            this.listalugares = pruebalug.pruebalugares(latitud, longitud);
        }

        catch(NullPointerException e){
            System.out.println("No hay objetos en la consulta");
        }

    }

    /**
     * Gets nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets nombre.
     *
     * @param nombre the nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets latitud.
     *
     * @return the latitud
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * Sets latitud.
     *
     * @param latitud the latitud
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    /**
     * Gets longitud.
     *
     * @return the longitud
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * Sets longitud.
     *
     * @param longitud the longitud
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * Gets listalugares.
     *
     * @return the listalugares
     */
    public List<String> getListalugares() {
        return listalugares;
    }

    /**
     * Sets listalugares.
     *
     * @param listalugares the listalugares
     */
    public void setListalugares(List<String> listalugares) {
        this.listalugares = listalugares;
    }
}
