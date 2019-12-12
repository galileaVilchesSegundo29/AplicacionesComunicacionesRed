/*SOCKET SERVIDOR.
TAREA 1.4 - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
SEPTIEMBRE 2019.*/
//package tarea1_4;

import java.net.*;
import java.io.*;

public class ServerT4 {
    public static void main(String[] args){
        try {
            int port = 1234, max=20000;
            DatagramSocket s = new DatagramSocket(port);
            System.out.println("\nConexion establecida desde puerto " + s.getLocalPort());
            System.out.println("\nEsperando cliente...\n");
            for(;;){
                DatagramPacket pRecieve = new DatagramPacket(new byte[max],max);    
                s.receive(pRecieve);
                String msj = new String(pRecieve.getData(),0,pRecieve.getLength());
                System.out.println("\nMensaje recibido desde el cliente: "+msj + "\t\t...Regresando mensaje al cliente...");
                //s.send(pRecieve);

            }
        } 
        catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
}