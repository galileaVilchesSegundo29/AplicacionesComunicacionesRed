/*SOCKET SERVIDOR.
SERIALIZACION - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
AGOSTO 2019.*/
import java.net.*;
import java.io.*;

public class seriServer{
    public static void main(String[] args){
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try{
            ServerSocket s = new ServerSocket(9999);
            System.out.println("Servidor iniciado...");
            for(;;){
                Socket cl = s.accept();
                System.out.println("\n\nConexion establecida desde" + cl.getInetAddress()+ ":" + cl.getPort());

                oos = new ObjectOutputStream(cl.getOutputStream());
                ois = new ObjectInputStream(cl.getInputStream());
                Usuario u = (Usuario)ois.readObject();
                System.out.println("\n\nObjeto recibido. Datos: ");
                System.out.println("\n\nNombre: " + u.getNombre());
                System.out.println("\n\nApellido Paterno: " + u.getNombre());
                System.out.println("\n\nApellido Materno: " + u.getNombre());
                System.out.println("\n\nPassword: " + u.getNombre());
                System.out.println("\n\nEdad: " + u.getNombre());
                System.out.println("\n\nDevolviendo objeto...");
                oos.writeObject(u); oos.flush();
            }
        }catch(Exception e){    //Manejo de excepciones
            e.printStackTrace();
        }
    }
}