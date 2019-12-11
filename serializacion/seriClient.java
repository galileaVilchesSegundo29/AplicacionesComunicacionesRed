/*SOCKET CLIENTE.
SERIALIZACION - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
AGOSTO 2019.*/
import java.net.*;
import java.io.*;

public class seriClient{
    public static void main(String[] args) {
        String host =  "127.0.0.1";
        int pto = 9999;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try{
            Socket cl = new Socket(host, pto);
            System.out.println("Conexion establecida...");
            oos = new ObjectOutputStream(cl.getOutputStream());
            ois = new ObjectInputStream(cl.getInputStream());
            Usuario u = new Usuario("Pepito", "Perez", "Lopez", "12345", 20);
            System.out.println("\n\nEnviando Objeto...");
            oos.writeObject(u); oos.flush();
            System.out.println("\n\nEsperando respuesta...");
            Usuario u2 = (Usuario)ois.readObject();
            System.out.println("\n\nObjeto recibido. Datos: ");
            System.out.println("\n\nNombre: " + u2.getNombre());
            System.out.println("\n\nApellido Paterno: " + u2.getNombre());
            System.out.println("\n\nApellido Materno: " + u2.getNombre());
            System.out.println("\n\nPassword: " + u2.getNombre());
            System.out.println("\n\nEdad: " + u2.getNombre());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}