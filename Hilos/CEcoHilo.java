/*SOCKET CLIENTE.
HILOS EN JAVA - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
OCTUBRE 2019.*/

import java.io.*;
import java.net.*;

public class CEcoHilo{
    public static void main(String []args){
        try{
            InetAddress dir = InetAddress.getByName("127.0.0.1");
            Socket cl = new Socket(dir, 9000);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(cl.getInputStream()));
            BufferedReader br2 = new BufferedReader(new InputStreamReader(system.in));

            String linea = "";

            System.out.println("Escriba mensaje <Enter>" + "Para salir escriba <SALIR>");

            for(;;){
                linea = br2.readLine();
                pw.println(linea);  pw.flush();

                if(linea.indexOf("SALIR") >= 0){
                    System.out.println("Adiosito...");
                    cl.close();     System.exit(0);
                }

                String eco = br.readLine();
                System.out.println("Eco: "+eco);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}