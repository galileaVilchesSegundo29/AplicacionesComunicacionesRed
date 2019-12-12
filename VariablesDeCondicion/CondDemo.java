/*CONDDEMO.
VARIABLES DE CONDICION - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
OCTUBRE 2019.*/

import java.util.concurrent.locksCondition;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CondDemo{
    public static void main(String []args){
        Shared s = new Shared();
        new Producer(s).start();
        new Consumer(s).start();
    }
}

class Shared{
    private volatile char c;
    private volatile boolean available;
    private final Lock lock;
    private final Condition condition;

    Shared(){
        c = '\u0000';   //Valor nulo
        available = false;
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    Lock getLock(){
        return lock;
    }

    char getSharedChar(){
        lock.lock();

        try{
            while(!available){
                try{
                    condition.await();
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }
            }

            available = true;
            condition.signal();

        }finally{
            lock.unlock();
        }
        return c;
    }

    void setSharedChar(char c){
        lock.lock();

        try{
            while(available){
                try{
                    condition.await();
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }
            }

            this.c = c;
            available = true;

        }finally{
            lock.unlock();
        }
    }
}