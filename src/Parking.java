import java.util.Scanner;

/**
 * Created by Moises on 29/11/2015.
 */
public class Parking {

    static boolean[] plazas;
    static Coche[] coches;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.println("Cuantos plazas hay?");
        plazas = new boolean[scn.nextInt()];

        System.out.println("Cuantos coches entran?");
        coches = new Coche[scn.nextInt()];


        //CREAMOS LOS HILOS QUE SE SOLICITEN
        for (int i = 0; i < coches.length ; i++) {
            coches[i] = new Coche();
            coches[i].start();
        }

    }
}
