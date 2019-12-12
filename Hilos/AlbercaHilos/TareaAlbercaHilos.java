/*TAREA ALBERCA HILOS
ALBERCA DE HILOS - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
OCTUBRE 2019.*/

public class TareaAlbercaHilos implements Runnable{
    private int sleepTime;
    private String name;

    public TareaAlbercaHilos(String name){
        this.name = name;
        sleepTime = 1000;
    }

    public void run(){
        try{
            System.out.println("El hilo de la tarea " + this.name + " va a dormir durante " + sleepTime + " milisegundos.\n");
            Thread.sleep(sleepTime);
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
        System.out.println("Este hilo ya ha dormido bastante :(");
    }
}