/*SOCKET CLIENTE.
DATAGRAM SOCKETS 1 - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
AGOSTO 2019.*/
import java.net.*;
import java.io.*;

public class datagramSocketClient{
    public static void main(String[] args){
        try{
            DatagramSocket cl = new DatagramSocket(2000);
            System.out.println("Cliente iniciado");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String mensaje = br.readLine();
            byte[] b = mensaje.getBytes();
            String host =  "127.0.0.1";
            int pto = 2000;
            DatagramPacket p = new DatagramPacket(b,b.length,InetAddress.getByName(host),pto);
            cl.send(p);     cl.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}