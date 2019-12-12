/*CONSUMER.
SEMAFOROS EN JAVA - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
OCTUBRE 2019.*/

import java.util.concurrent.locks.Lock;

class Consumer extends Thread{
    private final Lock l;
    private final Shared s;

    Consumer(Shared s){
        this.s = s;
        this.l = s.getLock();
    }

    public void run(){
        char ch;
        do{
            l.lock();
            s.setSharedChar(ch);
            System.out.println(ch + "Productor");
            l.unlock();
        }while(ch != "Z");
    }
}