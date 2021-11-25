package com.tec.ciudad;

import maps.java.MapsJava;
import maps.java.Places;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Lugares ciudad.
 */
public class LugaresCiudad {
    /**
     * Pruebalugares list.
     *
     * @param _latitud  the latitud
     * @param _longitud the longitud
     * @return the list
     */
    public List<String> pruebalugares(double _latitud, double _longitud) {
        //Se instancian los valores de latitud y longitud
        double latitud = _latitud;
        double longitud = _longitud;
        //Se instancia una lista para los lugares
        List<String> listalugares = new ArrayList();
        //Se asigna el API_Key
        MapsJava.setKey("AIzaSyAb0Wf4iNY8hD8OsuUkIIJbjDpw7SbzRUI");
        //Se instancia un objeto places
        Places ObjPlace = new Places();
        //Se intenta obtener una lista de los lugares
        try {
            //Se solicita una matriz con los lugares a una distancia de 3km alrededor del punto de las coordenadas
            String[][] resultado = ObjPlace.getPlaces(latitud, longitud,
                    3000, "", "", Places.Rankby.prominence, null);
            //Se entra a un bucle for en el cual se recorre la matriz del resultado y se agrega el nombre a listalugares
            for (int i = 0; i < resultado.length; i++) {
                listalugares.add(i, resultado[i][0]);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listalugares;
    }

}


