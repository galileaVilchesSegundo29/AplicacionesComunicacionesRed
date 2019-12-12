/*SOCKET SERVIDOR.
HILOS EN JAVA - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
OCTUBRE 2019.*/

import java.io.*;
import java.net.*;

public class SEcoHilo{
    public static void main(String []args){
        try{
            ServerSocket s = new ServerSocket(9000);
            System.out.println("Servidor listo, esperando cliente...");

            for(;;){
                Socket cl = s.accept();
                System.out.println("Cliente conectado...");

                Manejador m = new Manejador(cl);
                m.start();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

class Manejador extends Thread{
    Socket cl;

    public Manejador(Socket cl){
        this.cl = cl;
    }

    public void run(){
        try{
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(cl.getInputStream()));
            String linea = "";

            for(;;){
                linea = br.readLine();
                System.out.println("Recibiendo mensaje: "+linea);

                if(linea.indexOf("SALIR") >= 0){
                    System.out.println("Cliente se va...");
                    cl.close();     break;
                }

                pw.println(linea);  pw.flush();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}