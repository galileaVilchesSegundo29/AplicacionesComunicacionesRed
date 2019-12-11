/*SOCKET CLIENTE.
MULTICAST - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
SEPTIEMBRE 2019.*/

import java.net.*;

public class cMulticastB{
    public static void main(String[] args) {
        InetAddress gpo = null;
        try{
            MulticastSocket cl = new MulticastSocket(9999);
            System.out.println("Cliente escuchando puerto: "+cl.getLocalPort());
            cl.setReuseAddress(true);                   //Para correr varios clientes y no haya problemas en conexiones.
            try{
                gpo = InetAddress.getByName("228.1.1.1");   //Para ver si la dirección IP es válida.
            }catch(UnknownHostException u){
                System.err.println("Dirección no válida\n");
            }
            cl.joinGroup(gpo);
            System.out.println("Unido al grupo\n");
            for(;;){
                DatagramPacket p = new DatagramPacket(new byte[10], 10);
                cl.receive(p);
                String msj = new String(p.getData());
                System.out.println("Datagrama recibido... "+msj);
                System.out.println("Servidor descubrierto: "+p.getAddress()+" Puerto: "+p.getPort());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}