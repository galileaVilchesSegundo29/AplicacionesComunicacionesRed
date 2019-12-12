/*SOCKET CLIENTE.
TAREA 1.3 - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
AGOSTO 2019.*/
package STCPObj;

import java.net.*;
import java.io.*;

public class sClientT3{
    public static void main(String[] args) {
        ObjectOutputStream oos = null;
        try{
            int port = 7000, tport = 9000;
            // String host  = "127.0.0.1";
            String host  = "8.40.1.122";
            InetAddress dir = InetAddress.getByName(host);
            Socket cl = new Socket(dir, tport);
            System.out.println("Conexion establecida...");

            long boleta = 2014101780;
            String name = "Galilea Vilches";
            oos = new ObjectOutputStream(cl.getOutputStream());
            Persona person = new Persona(0, boleta,name,port);
            System.out.println("\n\nEnviando Objeto...");
            oos.writeObject(person);    oos.flush();
            oos.close();    cl.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
