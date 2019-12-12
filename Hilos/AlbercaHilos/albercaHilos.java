/*ALBERCA HILOS
ALBERCA DE HILOS - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
OCTUBRE 2019.*/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executor;

public class albercaHilos{
    public static void main(String[] args){
        System.out.println("Comienza la ejecucion");
        ExecutorService ex = ExecutorService.newFixedThreadPool(10);
        TareaAlbercaHilos t;

        for(int i = 0; i < 200; i++){
            t = new TareaAlbercaHilos(" " + i);
            ex.execute(t);
        }
        ex.shutdown();
    }
}
