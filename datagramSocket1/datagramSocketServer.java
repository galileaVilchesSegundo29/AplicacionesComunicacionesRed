/*SOCKET SERVIDOR.
DATAGRAM SOCKETS 1 - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
AGOSTO 2019.*/
import java.net.*;
import java.io.*;

public class datagramSocketServer{
    public static void main(String[] args){
        try{
            DatagramSocket s = new DatagramSocket(2000);
            System.out.println("Servidor iniciado");
            for(;;){
                DatagramPacket p = new DatagramPacket(new byte[2000], 2000);
                s.receive(p);
                System.out.println("\n\nDatagrama recibido desde: "+p.getAddress()+": "+p.getPort());
                String msj = new String(p.getData(),0,p.getLength());
                System.out.println("Mensaje: " + msj);
            }
            s.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}