/*SOCKET SERVIDOR.
JAVA-C - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
OCTUBRE 2019.*/
import java.net.*;
import java.io.*; 
 
public class MulticastServer2 extends Thread{
    //dir clase D valida, grupo al que nos vamos a unir
    public static final String MCAST_ADDR = "230.0.0.1";
    public static final int MCAST_PORT = 9013;
    public static final int DGRAM_BUF_LEN = 512;

	public void run(){
    	String msg = "Hola"; // se cambiará para poner la ip de la maquina con lo siguiente
    	InetAddress group = null;
    	try{
		//msg=InetAddress.getLocalHost().getHostAddress();
    		group = InetAddress.getByName(MCAST_ADDR); //resolver dir multicast
    	}catch(UnknownHostException e){
    		e.printStackTrace();
    		System.exit(1);
    	}
/********************inicia loop***************************/
	for(;;){
    	try{
    		MulticastSocket socket = new MulticastSocket(MCAST_PORT);
    		socket.joinGroup(group); // se configura para escuchar el paquete
    		DatagramPacket packet = new DatagramPacket(msg.getBytes(),msg.length(),group,MCAST_PORT);
    		System.out.println("Enviando: " + msg+"  con un TTL= "+socket.getTimeToLive());
    		socket.send(packet);
    		socket.close();    		
    	}catch(IOException e){
    		e.printStackTrace();
    		System.exit(2);
    	}

	try{
		Thread.sleep(1000*5);
	}catch(InterruptedException ie){}
        }//for;;
/*****************termina Loop***************************/    	
	}//run
    	
    public static void main(String[] args) {
	try{
	    MulticastServer2 mc2 = new MulticastServer2();
	    mc2.start();
	}catch(Exception e){e.printStackTrace();}

    }//main
}//class
