import java.util.Random;

/**
 * Created by Moises on 29/11/2015.
 */
public class Coche extends Thread {

    int matricula;
    int contador = 5;  //esta variable es para probar que este coche aparque y salga 5 veces antes de terminar
    boolean aparcado = false;
    int plazaActual;
    Semaforo semaforo;


    //CONSTRUCTOR
    public Coche() {

        Random rnd = new Random();
        matricula = rnd.nextInt(8999)+1000; //Asignación aleatoria de numero de matricula
        semaforo = new Semaforo();
    }

    /**
     * asigna una plaza al coche cuando entra en el parking
     * @param plazaActual plaza que ocupa el coche
     */
    public void ocupar(int plazaActual){
        this.plazaActual = plazaActual;
        aparcado = true;
    }

    /**
     * define el coche como no aparcado cuando sale del parking
     * @return el numero de plaza que desocupara del parking
     */
    public int desocupar(){
        aparcado = false;
        return plazaActual;
    }

    /**
     * imprime por pantalla información del coche cuando entra en el parking
     */
    public void infoEntrada(){
        System.out.println("Coche("+matricula+") entra en la plaza "+plazaActual+"("+contador+")------->");
    }

    /**
     * imprime por pantalla información del coche cuando sale del parking
     */
    public void infoSalida(){
        System.out.println("<--------Coche("+matricula+") sale de la plaza "+plazaActual+"("+contador+")\n");
    }


    @Override
    public final void run() {

        while (contador > 0){
            try {
                ocupar(semaforo.entra(this));   //ENTRA DEL PARKING
                infoEntrada();

                semaforo.espera();              //ESPERA EN EL INTERIOR

                semaforo.salir(this);           //SALE DEL PARKING
                infoSalida();
                semaforo.espera();              //ESPERA FUERA ANTES DE VOLVER A ENTRAR
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            contador--;
        }

    }
}
