/*SOCKET CLIENTE.
TAREA 2.1 - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
SEPTIEMBRE 2019.*/

import java.net.*;

public class ClientMulticast2{
    public static void main(String[] args) {
        InetAddress gpo = null;
        try{
            MulticastSocket cl = new MulticastSocket(9999);
            System.out.println("Cliente escuchando puerto: "+cl.getLocalPort());
            cl.setReuseAddress(true);                   //Para correr varios clientes y no haya problemas en conexiones.
            cl.setTimeToLive(1);
            try{
                gpo = InetAddress.getByName("228.1.1.1");   //Para ver si la dirección IP es válida.
            }catch(UnknownHostException u){
                System.err.println("Dirección no válida\n");
            }
            cl.joinGroup(gpo);
            System.out.println("Unido al grupo\n");
            String msjS = "Hola Server. Soy un 2";
            byte[] b = msjS.getBytes();
            for(;;){
                //Parte que envía mensaje
                DatagramPacket pS = new DatagramPacket(b, b.length,gpo,9876);
                cl.send(pS);
                System.out.println("\n\nEnviando mensaje: "+msjS+" con un TTL = "+cl.getTimeToLive());
                try{
                    Thread.sleep(3000);
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }

                //Parte que recive mensaje
                DatagramPacket p = new DatagramPacket(new byte[100], 100);
                cl.receive(p);
                String msj = new String(p.getData());
                System.out.println("\n\nDatagrama recibido... "+msj);
                System.out.println("Servidor descubrierto: "+p.getAddress()+" Puerto: "+p.getPort());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}