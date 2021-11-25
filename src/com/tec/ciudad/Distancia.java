package com.tec.ciudad;

import maps.java.Route;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

/**
 * The type Distancia.
 */
public class Distancia {
    private double mejorruta;
    /**
     * Distancia int.
     *
     * @param origen  the origen
     * @param destino the destino
     * @return the int
     * @throws MalformedURLException        the malformed url exception
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public double Distancia(String origen, String destino) throws MalformedURLException, UnsupportedEncodingException {
        //Se crea un objeto Route
        Route ObjRout = new Route();
        //Se obtiene una matriz resultado del método getRoute al cual le pasamos la ciudad de origen, destino, y el medio
        //de transporte
        String[][] resultadoRuta = ObjRout.getRoute(origen, destino, null, Boolean.TRUE, Route.mode.driving, Route.avoids.nothing);
        System.out.println("Las posibles rutas son: " + resultadoRuta.length);
        //Try catch para evitar errores en caso de que no hayan elementos en el resultado de la consulta
        try{
            if (resultadoRuta != null && Integer.parseInt(resultadoRuta[0][1]) < mejorruta ){
                mejorruta = Double.parseDouble(resultadoRuta[0][1]);
            }
            else{
                mejorruta = 0.0;
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Hubo un error en la solicitud de la ruta");
        }
        //Se comparan todos las distancias de las rutas y se conserva la ruta menor
        for (int i = 0; i < resultadoRuta.length; i++) {
            System.out.println("Tramo " + i + ":");
            double distanciaruta = Double.parseDouble(resultadoRuta[i][1]);
            if (resultadoRuta[i][1] != null && distanciaruta < mejorruta ){
                mejorruta = distanciaruta;
            }
            for (int j = 0; j < resultadoRuta[0].length; j++) {
                System.out.print(resultadoRuta[i][j] + "\t");

            }
        }
        return  mejorruta;
    }
}
