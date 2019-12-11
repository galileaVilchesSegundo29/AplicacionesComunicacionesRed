/*SOCKET CLIENTE.
DATOS PRIMITIVOS CON DATAGRAMAS - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
AGOSTO 2019.*/
import java.net.*;
import java.io.*;

public class datosPrimitivosDClient{
    public static void main(String[] args){
        try{
            int pto = 2000;
            InetAddres dst= InetAddress.getByName("127.0.0.1");
            DatagramSocket cl = new DatagramSocket(2000);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeInt(4);    dos.flush();    
            dos.writeFloat(4.1f);    dos.flush();
            dos.writeLong(72);      dos.flush();
            byte[] b = baos.toByteArray();
            DatagramPacket p = new DatagramPacket(b, b.length,dst,pto);
            cl.send(p);     cl.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}