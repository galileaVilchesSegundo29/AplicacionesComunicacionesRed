/*MUTEX.
MUTEX - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
OCTUBRE 2019.*/

import java.util.concurrent.locks.ReentrantLock;

public class Mutex implements Runnable
{
    int cont;
    ReentrantLock r1;
    public Mutex()
    {
        this.cont = 0;
        r1 = new ReentrantLock();
    }
    int getCont()
    {
        return this.cont;
    }
    
    public void run()
    {
        System.out.println("Comienza mutex");
        r1.lock();
        int tmp = cont;
        try
        {
            Thread.sleep(100);
        }catch(InterruptedException io){}
        try
        {
            tmp++;
            cont = tmp;
        }catch(Exception e)
        {
            e.printStackTrace();
        }finally
        {
            r1.unlock();
        }
    }
    
    public static void main(String[]args)
    {
        try
        {
            Mutex m = new Mutex();
            Thread t1 = new Thread(m);
            Thread t2 = new Thread(m);
            Thread t3 = new Thread(m);
            Thread t4 = new Thread(m);
            Thread t5 = new Thread(m);
            t1.start();t2.start();t3.start();t4.start();t5.start();
            t1.join();t2.join();t3.join();t4.join();t5.join();
            System.out.println("Cont: "+m.getCont());
        }catch(Exception e)
        {
            e.printStackTrace();
        }//try-catch
    }//main
}//class
