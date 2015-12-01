import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by Moises on 30/11/2015.
 */
public class Semaforo {


    public synchronized void semaforo (Coche a) {
    }

    public synchronized void espera (){
        int tiempoMaximo = 20000;
        int tiempoMinimo = 1000;
        Random rnd = new Random();

        try {
            sleep(rnd.nextInt(tiempoMaximo)+tiempoMinimo);
        } catch (InterruptedException e) {
            System.out.println("Error en la espera"+e);
        }
    }

    public synchronized int entra(Coche a) throws InterruptedException {
        System.out.println("esperando por una plaza "+a.matricula);
        while (numPlazasLibres() == 0) wait();
        System.out.println("hay plaza");
        return asignarPlaza();
    }

    public synchronized void salir (Coche a) throws InterruptedException {
        Parking.plazas[a.desocupar()] = false;
        notifyAll();
    }

    public static int numPlazasLibres(){
        int contador = 0;
        for (boolean plaza : Parking.plazas) {
            if (plaza == false) contador++;
        }
        return contador;
    }

    public int asignarPlaza(){
        int contador = 0;
        for (boolean plaza : Parking.plazas) {
            if (plaza == true)contador++;
            else break;
        }
        Parking.plazas[contador] = true;
        return contador;
    }
}


