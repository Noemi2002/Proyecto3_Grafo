package com.tec.dijkstra;

/**
 * Cálculo de la distancia más corta
 */
public class Dijkstra
{
    public  double distance[] = new double[100];

    /**
     * Realiza el cálculo sobre la matriz para obtener la ruta más corta
     * @param nodos
     * @param inicio
     * @param end
     * @param matriz
     */
    public double calc(int nodos,int inicio, int end, double[][] matriz) {

        int flag[] = new int[nodos + 1];
        int i = 1;
        int minpos = 1;
        int k;
        int c;
        double minimum;

        //Se guarda una fila en distance
        while (i < nodos) {
            flag[i] = 0;
            this.distance[i] = matriz[inicio][i];
            i++;
        }
        c = 2;
        while (c < nodos) {
            minimum = 99;

            //Intercambia el valor mínimo
            for (k = 1; k < nodos; k++) {
                if (this.distance[k] < minimum && flag[k] != 1) {
                    minimum = this.distance[i];
                    minpos = k;
                }
            }
            //Se cambia un posició de flag a 1
            flag[minpos] = 1;
            c++;


            for (k = 1; k < nodos; k++) {
                if (this.distance[minpos] + matriz[minpos][k] < this.distance[k] && flag[k] != 1)
                    this.distance[k] = this.distance[minpos] + matriz[minpos][k];
            }
        }
        //Immprime el camino más corto desde el origen hasta lugar de llegada
        System.out.println("El camino más corto es: \n");
        int x = 0;
        while (x < end){
            x++;
        }
        System.out.println("Desde :" + inicio + "\t hasta :" + end + "\t el costo mínimo es: " + distance[end] + "\t");
        return distance[end];
    }

    /*public static void main(String args[])
    {
        Dijkstra d = new Dijkstra();
        int node = 4;
        int verticeInicial = 2;
        double[][] graph = {{0, 3, 999, 7},
                {3, 0, 4, 2},
                {999, 4, 0, 5},
                {7, 2, 5,0}};
        /*double[][] graph = {
                {0.0, 75.78, 78.31, 54.98, 89.26, 186.67, 219.54, 272.08, 208.98, 187.57, 237.44, 282.79, 337.36, 340.5, 330.99, 248.22, 193.65, 144.15},
                {75.78, 0.0, 67.87, 110.94, 96.47, 174.01, 202.91, 253.68, 183.73, 158.11, 193.49, 238.79, 290.58, 288.38, 275.4, 198.39, 143.37, 88.8},
                {78.38, 67.82, 0.0, 72.41, 29.36, 113.73, 145.47, 197.94, 131.99, 109.61, 159.82, 204.96, 259.78, 264.15, 255.99, 171.92, 118.71, 76.22},
                {54.95, 110.91, 72.39, 0.0, 62.88, 154.2, 187.15, 238.23, 183.0, 166.42, 223.35, 266.52, 321.59, 329.32, 323.34, 238.43, 188.0, 148.61},
                {89.3, 96.37, 29.35, 62.92, 0.0, 97.63, 130.68, 183.13, 122.5, 104.14, 160.56, 203.92, 259.04, 266.6, 260.88, 175.89, 126.76, 94.38},
                {186.91, 173.88, 113.75, 154.46, 97.7, 0.0, 33.2, 85.56, 36.2, 41.36, 94.91, 126.84, 179.01, 193.87, 195.03, 117.05, 96.14, 112.63},
                {219.89, 202.82, 145.55, 187.52, 130.81, 33.21, 0.0, 52.67, 28.33, 51.63, 84.02, 105.46, 153.83, 171.9, 176.28, 106.96, 103.01, 132.98},
                {272.6, 253.65, 198.12, 238.8, 183.38, 85.62, 52.69, 0.0, 70.64, 96.94, 99.98, 97.06, 130.31, 154.63, 165.55, 119.53, 137.72, 178.1},
                {209.39, 183.76, 132.13, 183.37, 122.66, 36.2, 28.34, 70.65, 0.0, 26.33, 60.21, 90.78, 143.66, 157.76, 158.93, 82.96, 74.74, 108.08},
                {187.93, 158.15, 109.73, 166.71, 104.26, 41.37, 51.65, 96.97, 26.34, 0.0, 60.44, 100.66, 155.63, 165.59, 163.11, 80.43, 55.12, 81.99},
                {238.04, 193.79, 160.08, 223.8, 160.79, 94.91, 84.03, 100.07, 60.21, 60.46, 0.0, 45.82, 100.17, 106.26, 102.7, 22.96, 51.68, 105.53},
                {283.63, 239.27, 205.4, 267.21, 204.32, 126.9, 105.47, 97.08, 90.82, 100.75, 45.84, 0.0, 55.24, 67.1, 71.3, 44.51, 95.96, 150.56},
                {338.54, 291.36, 260.46, 322.57, 259.68, 179.19, 153.92, 130.31, 143.79, 155.84, 100.27, 55.27, 0.0, 29.08, 49.63, 92.49, 147.7, 202.32},
                {341.65, 289.19, 264.79, 330.23, 267.18, 194.01, 171.95, 154.63, 157.86, 165.75, 106.34, 67.11, 29.09, 0.0, 22.93, 92.59, 147.69, 200.9},
                {332.02, 276.15, 256.54, 324.11, 261.35, 195.1, 176.3, 165.58, 158.97, 163.22, 102.75, 71.3, 49.66, 22.94, 0.0, 85.14, 137.86, 189.11},
                {248.85, 198.76, 172.19, 238.87, 176.11, 117.05, 106.97, 119.64, 82.96, 80.44, 22.96, 44.51, 92.42, 92.51, 85.09, 0.0, 55.54, 109.84},
                {194.03, 143.56, 118.82, 188.21, 126.83, 96.18, 103.11, 137.9, 74.79, 55.14, 51.67, 95.9, 147.5, 147.48, 137.7, 55.51, 0.0, 54.68},
                {144.32, 88.87, 76.24, 148.66, 94.38, 112.77, 133.14, 178.29, 108.16, 82.05, 105.46, 150.39, 201.95, 200.51, 188.78, 109.72, 54.65, 0.0}};
        */
        //d.calc(node,verticeInicial, 2, graph);
        /*int nodes,source,i,j;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the Number of Nodes \n");
        nodes = in.nextInt();
        //nodes = 4;
        Dijkstra d = new Dijkstra();
        System.out.println("Enter the Cost Matrix Weights: \n");
        for(i=1;i<=nodes;i++)
            for(j=1;j<=nodes;j++)
            {
                d.cost[i][j]=in.nextDouble();
                if(d.cost[i][j]==0)
                    d.cost[i][j]=999;
            }
        System.out.println("Enter the Source Vertex :\n");
        source=in.nextInt();*/

        //d.calc(nodes,source);
        //System.out.println("The Shortest Path from Source \t"+verticeInicial+"\t to all other vertices are : \n");
        //for(int i=1; i<=node; i++)
          //  if(i!=verticeInicial)
            //    System.out.println("source :"+verticeInicial+"\t destination :"+i+"\t MinCost is :"+d.distance[i]+"\t");


   // }*/
}