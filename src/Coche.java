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



    public Coche() {
        Random rnd = new Random();
        matricula = rnd.nextInt(8999)+1000;
        semaforo = new Semaforo();
    }

    public void ocupar(int plazaActual){
        this.plazaActual = plazaActual;
        aparcado = true;
    }

    public int desocupar(){
        aparcado = false;
        return plazaActual;
    }

    public String infoEntrada(){
        return "Coche("+matricula+") entra en la plaza "+plazaActual+"("+contador+")------->";
    }

    public String infoSalida(){
        return "<--------Coche("+matricula+") sale de la plaza "+plazaActual+"("+contador+")";
    }

    @Override
    public final void run() {

        while (contador > 0){
            try {
                System.out.println("primera espera");
                semaforo.espera();      //Tiempo aleatorio antes de entrar

                ocupar(semaforo.entra(this));
                infoEntrada();

                semaforo.espera();      //Tiempo aleatorio antes de salir

                semaforo.salir(this);
                infoSalida();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            contador--;
        }



    }

}
