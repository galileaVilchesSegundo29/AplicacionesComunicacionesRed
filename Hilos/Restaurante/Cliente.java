/*SOCKET CLIENTE.
SEMAFOROS EN JAVA - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
OCTUBRE 2019.*/

import java.util.Random;

class Cliente extends Thread{
    private Restaurante r;
    private int idCliente;
    private static final Random aleatorio = new Random();

    public Cliente(Restaurante r, int idCliente){
        this.r = r;
        this.idCliente = idCliente;
    }

    public void run(){
        r.obtenerMesas(this.idCliente);

        try{
            //Come durante un tiempo entre 1 y 30 segundos.
            int tiempoComida = aleatorio.nextInt(30) + 1;
            System.out.println("Cliente #" + this.idCliente + " comera por " + tiempoComida + "segundos.");
            Thread.sleep(tiempoComida*1000);
            System.out.println("Cliente #" + this.idCliente + " ya comio");
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }finally{
            r.regresaMesa(this.idCliente);
        }
    }
}