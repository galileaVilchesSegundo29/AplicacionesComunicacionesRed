/*SOCKET CLIENTE.
TAREA 1.4 - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
SEPTIEMBRE 2019.*/
//package tarea1_4;

import java.net.*;
import java.io.*;

public class ClientT4 {
    public static void main(String[] args){
        try{
            int port = 1234, top = 20; 
            String host  = "127.0.0.1";
            InetAddress dir = InetAddress.getByName(host);
            DatagramSocket cl = new DatagramSocket();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\nCliente iniciado");

            System.out.println("\nIngresa el mensaje:\n");
            String message = br.readLine();
            byte[] b = message.getBytes();
            // DatagramPacket p = new DatagramPacket(b,b.length,dir,port);
            // cl.send(p);
            System.out.println("\n");

            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            int np = (int)b.length/top;
            String mensajeReconstruido = "";
            if(b.length%top>0)
                np++;
            for (int i = 0; i < np; i++) {                
                byte[] b2 = new byte[top];
                int n = bais.read(b2);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(b2);     oos.flush();
                DatagramPacket pSend = new DatagramPacket(b2,n,dir,port);
                cl.send(pSend); 
                DatagramPacket pRecieve = new DatagramPacket(new byte[top],top);    
                cl.receive(pRecieve);
                String msj = new String(pRecieve.getData(),0,pRecieve.getLength());
                mensajeReconstruido+=msj;
                oos.close();    baos.close();
            }
            System.out.println("Mensaje reconstruido: "+mensajeReconstruido);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}
