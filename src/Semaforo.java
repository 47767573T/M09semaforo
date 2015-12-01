import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by Moises on 30/11/2015.
 */
public class Semaforo {

    public synchronized void espera (){
        int tiempoMaximo = 10000;
        int tiempoMinimo = 1000;
        Random rnd = new Random();

        try {
            sleep(rnd.nextInt(tiempoMaximo)+tiempoMinimo);
        } catch (InterruptedException e) {
            System.out.println("ERROR EN LA ESPERA"+e);
        }
    }

    public synchronized int entra(Coche a) throws InterruptedException {
        System.out.println("esperando por una plaza "+a.matricula);
        while (numPlazasLibres() == 0){
            System.out.println("------>Coche("+a.matricula+") esperando a entrar<------");
            wait();
        }
        System.out.println("");
        return asignarPlaza();
    }

    public synchronized void salir (Coche a) throws InterruptedException {
        System.out.println("La plaza "+a.plazaActual+" se desocupará en breve");
        Parking.plazas[a.desocupar()] = false;

        notifyAll();
    }

    public static int numPlazasLibres(){
        int contador = 0;
        for (boolean plaza : Parking.plazas) {
            if (!plaza) contador++;
        }
        return contador;
    }

    public int asignarPlaza(){
        int contador = 0;
        for (boolean plaza : Parking.plazas) {
            if (plaza)contador++;
            else break;
        }
        Parking.plazas[contador] = true;
        return contador;
    }
}


