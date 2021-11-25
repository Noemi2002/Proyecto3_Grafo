package com.tec.ciudad;

import maps.java.MapsJava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObtenerCiudades {
    double [][] matriz;
    ArrayList<Ciudad> listadeciudades;
    public ObtenerCiudades() throws IOException, InterruptedException {
        this.listadeciudades = ConstruirCiudades();
        this.matriz = ConstruirMatriz(listadeciudades);

    }
    public ArrayList<Ciudad> ConstruirCiudades() throws IOException, InterruptedException {
        //Se define como región a Costa Rica
        MapsJava.setRegion("cr");
        //Se crea una lista con el nombre de las ciudades
        List<String> listanombreciudades = Arrays.asList("Liberia", "Nicoya", "Monteverde", "Upala", "La Fortuna", "Guápiles",
                "Siquirres", "Limón", "Turrialba", "Cartago", "San Isidro de El General", "Buenos Aires", "San Vito",
                "Golfito", "Punto Jimenez", "Uvita", "Quepos", "Jacó");
        //Se crea un array con el tamaño de acuerdo a la cantidad de nombres de ciudades
        ArrayList<Ciudad> listadeciudades = new ArrayList<>(listanombreciudades.size());
        //Por cada ciudad en la lista de nombres de ciudades, se crea un objeto ciudad y se agrega a la lista de ciudades
        for (String ciudad : listanombreciudades) {
            Ciudad nuevaciudad = new Ciudad(ciudad);
            listadeciudades.add(nuevaciudad);
        }
        return listadeciudades;
    }
    public double[][] ConstruirMatriz(ArrayList<Ciudad> listadeciudades) {
        DistP distP = new DistP();
        int numerociudades = listadeciudades.size();
        double [][] matriztemp = new double[numerociudades][numerociudades];
        for (Ciudad ciudad : listadeciudades) {
            System.out.println(ciudad.getNombre());
        }
        for (int f =0; f<numerociudades; f++){
            for (int c = 0; c<numerociudades; c++){
                double distan = distP.getDistanceBetweenPoints(listadeciudades.get(f), listadeciudades.get(c));
                matriztemp[f][c] = distan;
            }
        }
        System.out.println(matriztemp);
        return matriztemp;
    }
    //Metodo para imprimir los datos de las matrices en consola
    public void ImprimirMatriz(double[][] matriz){
        for (int f = 0; f< matriz.length; f++) {
            System.out.print("[");
            for (int c = 0; c < matriz.length; c++) {
                if (c == matriz.length - 1) {
                    System.out.print(matriz[f][c]);
                } else {
                    System.out.print(matriz[f][c] + ",");
                }
            }
            System.out.println("]");
        }
    }
    public double[][] getMatriz() {
        return matriz;
    }
    public ArrayList<Ciudad> getCiudades(){
        return listadeciudades;
    }
}
