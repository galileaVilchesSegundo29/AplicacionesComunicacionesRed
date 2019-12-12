/*RESTAURANTE.
SEMAFOROS EN JAVA - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
OCTUBRE 2019.*/

import java.util.concurrent.locks.Semaphore;

public class Restaurante{
    private Semaphore mesas;

    public Restaurante(int mesas){
        //Crea un restaurante con un numero de mesas
        this.mesas = new Semaphore(mesas);
    }

    public void obtenerMesas(int idCliente){
        try{
            System.out.println("Cliente #" + idCliente + " esta intentando obtener una mesa");
            //Obtiene una mesa
            mesas.acquire();
            System.out.println("Cliente #" + idCliente + " consiguio una mesa");

        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }

    public void regresaMesa(int idCliente){
        System.out.println("Cliente #" + idCliente + " devolvio una mesa");
        mesas.release();
    }

    public static void main(String[] args){
        Restaurante r = new Restaurante(2);

        for(int i = 1; i <= 5; i++){
            Cliente c  = new Cliente(r,i);
            c.start();
        }
    }
}