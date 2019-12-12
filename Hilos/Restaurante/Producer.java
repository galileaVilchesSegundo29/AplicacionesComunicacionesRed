/*PRODUCER.
SEMAFOROS EN JAVA - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
OCTUBRE 2019.*/

import java.util.concurrent.locks.Lock;

class Producer extends Thread{
    private final Lock l;
    private final Shared s;

    Producer(Shared s){
        this.s = s;
        this.l = s.getLock();
    }

    public void run(){
        for(char ch = 'A'; ch <= 'Z'; ch++){
            l.lock();
            s.setSharedChar(ch);
            System.out.println(ch + "Productor");
            l.unlock();
        }
    }
}