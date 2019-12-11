/*SOCKET SERVIDOR.
EJEMPLO ENVIO ARCHIVOS - APLICACIONES PARA COMUNICACIONES EN RED.
AUTOR: VILCHES SEGUNDO GALILEA YANELY
AGOSTO 2019.*/
import java.net.*;
import java.io.*;

public class SArchTCPB{
    public static void main(String[]args){
    try{
    //Creamos el socket
    ServerSocket s = new ServerSocket(7000);
    System.out.println("Esperando cliente...");
    //Iniciamos el cico infinito del servidor
    for(;;){
        //Esperamos una conexion
        Socket cl = s.accept();
        System.out.println("Conexion establecida:" + cl.getInetAddress() + "" + cl.getPort());
        DataInputStream dis = new DataInputStream(cl.getInputStream());
        byte[] b = new byte[1024];
        String nombre = dis.readUTF();//Leer nombre
        System.out.println("Recibimos el archivo " + nombre);
        long tam = dis.readLong();//Leer tamaño
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(nombre));//Flujo de salida
        long recibidos = 0;
        int n, porcentaje;
        while(recibidos<tam){
            n = dis.read(b);//Lee bytes
            dos.write(b,0,n);//Lee de 0 a n
            dos.flush();
            recibidos = recibidos + n;
            porcentaje = (int)(recibidos * 100/tam);
            System.out.println("Recibido: " + porcentaje + "%\r");
        }//while
        System.out.println("\n\nArchivo recibido\n");
        dos.close();
        dis.close();
        cl.close();
    }//for
}catch(Exception e){
        e.printStackTrace();
        }//catch
    }//main
}//class