package com.tec.ciudad;

import java.text.DecimalFormat;

/**
 * The type Dist p.
 */
public class DistP {
    /**
     * Degrees to radians double.
     *
     * @param degrees the degrees
     * @return the double
     */
    public double degreesToRadians(double degrees) {
        return (degrees * Math.PI / 180);
    }

    /**
     * Gets distance between points.
     *
     * @param ciudadorigen  the ciudadorigen
     * @param ciudaddestino the ciudaddestino
     * @return the distance between points
     */
    public double getDistanceBetweenPoints(Ciudad ciudadorigen, Ciudad ciudaddestino) {
        //Un formato para la distancia resultante
        DecimalFormat formato1 = new DecimalFormat("#.00");

        // El radio del planeta tierra en metros.
        int R = 6378137;
        //Se obtienen las latitud y longitud de ambas ciudades
        double lat1 = ciudadorigen.getLatitud();
        double lng1 = ciudadorigen.getLongitud();
        double lat2 = ciudaddestino.getLatitud();
        double lng2 = ciudaddestino.getLongitud();
        //Se obtiene la Medida en radianes de las latitudes y longitudes
        double dLat = degreesToRadians(lat2 - lat1);
        double dLong = degreesToRadians(lng2 - lng1);

        //Fórmula de Haversine
        double a = Math.sin(dLat / 2)
                *
                Math.sin(dLat / 2)
                +
                Math.cos(degreesToRadians(lat1))
                        *
                        Math.cos(degreesToRadians(lat1))
                        *
                        Math.sin(dLong / 2)
                        *
                        Math.sin(dLong / 2);


        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        //Se obtiene la distancia en kilómetros, por defecto tiene 15 decimales, pero se lo reducimos a 2 al darle formato
        //Creamos una lista de carácteres con el numero formateado para cambiarle la "," por ".", para de esta manera poder
        //convertirlo a un número double
        double distanciasinformato = R * c * 0.001;
        char[] distformtemp = formato1.format(distanciasinformato).toCharArray();
        StringBuilder distanciaconformato = new StringBuilder();
        //Se recorren los caracteres de la cadena de caracteres, si el carácter es una ',' se cambia por el punto, haya entrado
        //al condicional o no, se agrega el carácter a distanciaconformato
        for (char caracter : distformtemp) {
            if (caracter == ',') caracter = '.';
            distanciaconformato.append(caracter);
        }
        //Ya con los caracteres correctos se puede pasar a número double

        return Double.parseDouble(distanciaconformato.toString());
    }
}

