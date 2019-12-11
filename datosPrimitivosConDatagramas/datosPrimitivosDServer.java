/*SOCKET SERVIDOR.
DATOS PRIMITIVOS CON DATAGRAMAS - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
AGOSTO 2019.*/
import java.net.*;
import java.io.*;

public class datosPrimitivosDServer{
    public static void main(String[] args){
        try{
            DatagramSocket s = new DatagramSocket(2000);
            System.out.println("Servidor iniciado");
            for(;;){
                DatagramPacket p = new DatagramPacket(new byte[2000], 2000);
                s.receive(p);
                System.out.println("\n\nDatagrama recibido desde: "+p.getAddress()+": "+p.getPort());
                DataInputStream dis = new DataInputStream(new ByteArrayInputStream(p.getData()));
                int x = dis.readInt();
                float f = dis.readFloat();
                long z = dis.readLong();
                System.out.println("\n\nEntero: "+x+"\tFlotante: "+f+"\tEntero largo: "+z);
                dis.close();  s.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}