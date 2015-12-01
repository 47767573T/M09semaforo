import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by Moises on 30/11/2015.
 */
public class Semaforo {

    /**
     * Funcion de espera que aplica un tiempo al thread simulando la espera de una coche antes de querer entrar o salir
     */
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

    /**
     * Funcion que simula la entrada del hilo
     * @param a hilo "coche" que entra al parquing
     * @return Integer de la plaza que se la ha asignado al entrar
     * @throws InterruptedException error de concurrencia
     */
    public synchronized int entra(Coche a) throws InterruptedException {
        System.out.println("esperando por una plaza "+a.matricula);
        while (numPlazasLibres() == 0){
            System.out.println("------>Coche("+a.matricula+") esperando a entrar<------");
            wait();               //ESPERA PARA ENTRAR SI ESTA OCUPADO EL PARKING, SINO ENTRA
        }
        System.out.println("");
        return asignarPlaza();
    }

    /**
     * Función que simula que el coche sale del parquing
     * @param a hilo "coche" que entra al parquing
     * @throws InterruptedException error de concurrencia
     */
    public synchronized void salir (Coche a) throws InterruptedException {
        System.out.println("La plaza "+a.plazaActual+" se desocupará en breve");
        Parking.plazas[a.desocupar()] = false;

        notifyAll();
    }

    /**
     * metodo que busca si hay plazas libres en el parking
     * @return devuelve el numero de plazas libres
     */
    public static int numPlazasLibres(){
        int contador = 0;
        for (boolean plaza : Parking.plazas) {
            if (!plaza) contador++;
        }
        return contador;
    }

    /**
     * metodo que busca las plazas libres
     * @return devuelve la plaza libre para ser asignada al coche que entra en el parquing
     */
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


