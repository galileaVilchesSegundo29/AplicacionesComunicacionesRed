/*SOCKET SERVIDOR.
TAREA 2.1 - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
SEPTIEMBRE 2019.*/

import java.net.*;

public class ServerMulticast2{
    public static void main(String[] args){
        InetAddress gpo;
        try{
            MulticastSocket s = new MulticastSocket(9876);
            s.setReuseAddress(true);
            s.setTimeToLive(1);
            String msj = "Hola Clientes. Soy Server";
            byte[] b = msj.getBytes();
            gpo = InetAddress.getByName("228.1.1.1");
            s.joinGroup(gpo);

            System.out.println("Unido al grupo\n");
            for(;;){
                //Parte que envía mensaje
                DatagramPacket p = new DatagramPacket(b, b.length,gpo,9999);
                s.send(p);
                System.out.println("\n\nEnviando mensaje: "+msj+" con un TTL = "+s.getTimeToLive());
                try{
                    Thread.sleep(3000);
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }

                //Parte que recive mensaje
                DatagramPacket pC = new DatagramPacket(new byte[100], 100,gpo,9999);
                s.receive(pC);
                String msjC = new String(pC.getData());
                System.out.println("\n\nDatagrama recibido... "+msjC);
                System.out.println("Cliente descubrierto: "+pC.getAddress()+" Puerto: "+pC.getPort());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}